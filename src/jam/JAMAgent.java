package jam;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Observable;
import java.util.Vector;

/**
 * Clase que define un JAMAgent, un objeto inteligente dotado de comportamiento
 * (Behaviour).
 * 
 * Classe che definisce un JAMAgent, un comportamento intelligente dotata
 * oggetti (Behaviour).
 * 
 * @author Fernando Aliaga Ramon y Cristian Simon Moreno
 * 
 */
public abstract class JAMAgent extends Observable {

	// MessageBox del agente
	private MessageBox myMessageBox;
	private PersonalAgentID myID;
	// Conexion
	private ADSL adsl;
	private String name;
	private String ip;
	private int port;
	// Comportamiento
	private List<JAMBehaviour> myBehaviours;

	/**
	 * Constructor del objeto JAMAgent con parametros por defecto.
	 * 
	 * JAMAgent oggetto costruttore con i parametri di default.
	 * 
	 * IP: 127.0.0.1 Name: ADSL Port: 2000
	 */
	public JAMAgent(PersonalAgentID agentID) throws JAMMessageBoxException {
		this(agentID, "127.0.0.1", "ADSL", 2000);
	}

	/**
	 * Constructor del objeto JAMAgent.
	 * 
	 * JAMAgent oggetto costruttore.
	 * 
	 * @param agentID
	 * @param ip
	 * @param name
	 * @param port
	 * @throws JAMMessageBoxException
	 */
	public JAMAgent(PersonalAgentID agentID, String ip, String name, int port)
			throws JAMMessageBoxException {
		this.myID = agentID;
		this.ip = ip;
		this.name = name;
		this.port = port;
		try {
			myMessageBox = new MessageBox(this.myID);
		} catch (RemoteException e) {
			throw new JAMMessageBoxException(e);
		}
		myBehaviours = new Vector<JAMBehaviour>();
	}

	/**
	 * Devuelve el ID del JAMAgent.
	 * 
	 * Restituisce l'ID di JAMAgent.
	 * 
	 * @return myID
	 */
	public PersonalAgentID getMyID() {
		return myID;
	}

	/**
	 * Efectua el lookup del registro RMI e inscribe su RemoteMessageBox.
	 * 
	 * Effettuato il Registro di sistema di ricerca RMI e inscrivere il suo
	 * RemoteMessageBox.
	 * 
	 * @throws JAMADSLException
	 */
	public void init() throws JAMADSLException {
		try {
			adsl = (ADSL) Naming
					.lookup("rmi://" + ip + ":" + port + "/" + name);
			adsl.insertRemoteMessageBox(myMessageBox);
		} catch (MalformedURLException e) {
			throw new JAMADSLException(e);
		} catch (RemoteException e) {
			throw new JAMADSLException(e);
		} catch (NotBoundException e) {
			throw new JAMADSLException(e);
		}
	}

	/**
	 * Elimina del ADSL el RemoteMessageBox.
	 * 
	 * Elimina la RemoteMessageBox dall ADSL.
	 * 
	 * @throws JAMADSLException
	 */
	public void destroy() throws JAMADSLException {
		try {
			adsl.removeRemoteMessageBox(myID);
			for (JAMBehaviour lista : myBehaviours) {
				lista.done();
			}
		} catch (RemoteException e) {
			throw new JAMADSLException(e);
		}
	}

	/**
	 * Lee de su box el mensaje correspondiente a agentID y con performative
	 * performative.
	 * 
	 * Leggi il tuo casella corrispondente agentID e performativo messaggio
	 * performativo.
	 * 
	 * @param agentID
	 * @param performative
	 * @return Message
	 * @throws InterruptedException
	 * @throws JAMMessageBoxException
	 */
	public Message receive(AgentID agentID, Performative performative)
			throws InterruptedException, JAMMessageBoxException {
		Message recibido = myMessageBox.readMessage(agentID, performative);
		try {
			setChanged();
			notifyObservers("RECEIVE message " + recibido.performative + " to "
					+ recibido.getReceiver());
		} catch (Exception e) {
		}
		return recibido;
	}

	/**
	 * Lee de su box el mensaje correspondiente a agentID.
	 * 
	 * Leggi il tuo casella corrispondente agentID.
	 * 
	 * @param agentID
	 * @return Message
	 * @throws InterruptedException
	 * @throws JAMMessageBoxException
	 */
	public Message receive(AgentID agentID) throws InterruptedException,
			JAMMessageBoxException {
		if (agentID == null) {
			throw new IllegalArgumentException(
					"Todos los parametros son obligatorios");
		}
		Message recibido = myMessageBox.readMessage(agentID);
		try {
			setChanged();
			notifyObservers("RECEIVE message " + recibido.performative + " to "
					+ recibido.getReceiver());
		} catch (Exception e) {
		}
		return recibido;
	}

	/**
	 * Lee de su box el mensaje con performative performative.
	 * 
	 * Leggi il tuo casella performativo messaggio performativo.
	 * 
	 * @param performative
	 * @return Message
	 * @throws InterruptedException
	 * @throws JAMMessageBoxException
	 */
	public Message receive(Performative performative)
			throws InterruptedException, JAMMessageBoxException {
		Message recibido = myMessageBox.readMessage(performative);
		try {
			setChanged();
			notifyObservers("RECEIVE message " + recibido.performative + " to "
					+ recibido.getReceiver());
		} catch (Exception e) {
		}
		return recibido;
	}

	/**
	 * Lee de su box todos los mensajes.
	 * 
	 * Leggi la tua casella di tutti i messaggi.
	 * 
	 * @return Message
	 * @throws InterruptedException
	 * @throws JAMMessageBoxException
	 */
	public Message receive() throws InterruptedException,
			JAMMessageBoxException {
		return receive(new GenericAgentID());
	}

	/**
	 * Comprueba si hay mensajes que con agentID agent y Performative
	 * performative.
	 * 
	 * Controlla i messaggi con l'agente agentID e performativo Performative.
	 * 
	 * @param agent
	 * @param performative
	 * @return boolean
	 */
	public boolean check(AgentID agent, Performative performative) {
		return myMessageBox.isThereMessage(agent, performative);
	}

	/**
	 * Comprueba si hay mensajes que con agentID agent.
	 * 
	 * Controlla i messaggi con l'agente agentID.
	 * 
	 * @param agent
	 * @return boolean
	 */
	public boolean check(AgentID agent) {
		return myMessageBox.isThereMessage(agent);
	}

	/**
	 * Comprueba si hay mensajes con Performative performative.
	 * 
	 * Controlla i messaggi con performativo Performative.
	 * 
	 * @param performative
	 * @return boolean
	 */
	public boolean check(Performative performative) {
		return myMessageBox.isThereMessage(performative);
	}

	/**
	 * Comprueba si hay mensajes.
	 * 
	 * Controlla i messaggi.
	 * 
	 * @return boolean
	 */
	public boolean check() {
		return myMessageBox.isThereMessage();
	}

	/**
	 * Manda un mensage al destinatario contenido en el mensaje y que se
	 * encuentran en el ADSL.
	 * 
	 * Invia un messaggio al destinatario contenuta nel messaggio e sono in
	 * ADSL.
	 * 
	 * @param message
	 * @throws JAMADSLException
	 * @throws JAMMessageBoxException
	 * @throws InterruptedException
	 */
	public void send(Message message) throws JAMADSLException,
			JAMMessageBoxException, InterruptedException {
		List<RemoteMessageBox> destinatarios = null;
		try {
			destinatarios = adsl.getRemoteMessageBox(message.getReceiver());
		} catch (RemoteException e) {
			throw new JAMADSLException(e);
		}
		if (destinatarios.isEmpty()) {
			throw new JAMMessageBoxException("Vacia");
		}
		for (RemoteMessageBox rmb : destinatarios) {
			try {
				rmb.writeMessage(message);
			} catch (RemoteException e) {
				throw new JAMMessageBoxException(e);
			}
		}
		setChanged();
		notifyObservers("SEND message " + message.performative + " to "
				+ message.getReceiver());
	}

	/**
	 * Añade un comportamiento al JAMAgent.
	 * 
	 * Aggiungere un comportamento JAMAgent.
	 * 
	 * @param behaviour
	 */
	public void addBehaviour(JAMBehaviour behaviour) {
		myBehaviours.add(behaviour);
	}

	/**
	 * Habilita un nuevo Thread por cada comportamiento contenido en el agente.
	 * 
	 * Consente un nuovo thread per ogni comportamento contenute nel agente.
	 */
	public void start() {
		for (JAMBehaviour lista : myBehaviours) {
			Thread hilo = new Thread(lista);
			lista.setMyThread(hilo);
			hilo.start();
		}

	}

}
