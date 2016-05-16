package jam;

import java.rmi.RemoteException;

/**
 * Representa un box de correo sincronizado a la que un agente podra enviar
 * mensajes pero solo podra ser leida por su propietario.
 * 
 * Rappresenta una casella di posta elettronica sincronizzata con un agente può
 * inviare messaggi ma potrebbe essere letto solo dal suo proprietario.
 * 
 * @author Fernando Aliaga Ramon y Cristian Simon Moreno
 * 
 */
@SuppressWarnings("serial")
public class MessageBox extends MessageBoxNoSync implements RemoteMessageBox {

	/**
	 * Contructor de la clase.
	 * 
	 * Metodo costruttore di classe.
	 * 
	 * @param owner
	 * @throws RemoteException
	 */
	public MessageBox(PersonalAgentID owner) throws RemoteException {
		super(owner);
	}

	/**
	 * Contructor de la clase.
	 * 
	 * Metodo costruttore di classe.
	 * 
	 * @param owner
	 * @param maxMessage
	 * @throws RemoteException
	 */
	public MessageBox(PersonalAgentID owner, int maxMessage)
			throws RemoteException {
		super(owner, maxMessage);
	}

	/**
	 * Devuelve el Primer mensaje.
	 * 
	 * Restituisce il primo messaggio.
	 * 
	 * @return message
	 * @throws InterruptedException
	 */
	synchronized public Message readMessage() throws InterruptedException {
		return readMessage(new GenericAgentID());
	}

	/**
	 * Devuelve el Primer mensaje de agente.
	 * 
	 * Restituisce il primo broker messaggio.
	 * 
	 * @param agentID
	 * @return message
	 * @throws InterruptedException
	 */
	synchronized public Message readMessage(AgentID agentID)
			throws InterruptedException {
		Message m = null;
		boolean b = false;
		while (!b) {
			try {
				m = super.readMessage(agentID);
				b = true;
			} catch (JAMMessageBoxException e) {
				wait();
			}
		}
		notifyAll();
		return m;
	}

	/**
	 * Devuelve el Primer mensaje con Performative performative.
	 * 
	 * Restituisce il primo messaggio performativo Performative.
	 * 
	 * @param performative
	 * @return message
	 * @throws InterruptedException
	 */
	synchronized public Message readMessage(Performative performative)
			throws InterruptedException {
		Message m = null;
		boolean b = false;
		while (!b) {
			try {
				m = super.readMessage(performative);
				b = true;
			} catch (JAMMessageBoxException e) {
				wait();
			}
		}
		notifyAll();
		return m;
	}

	/**
	 * Devuelve el Primer mensaje de performative y agente.
	 * 
	 * Restituisce il primo performativo messaggio e agente.
	 * 
	 * @param agentID
	 * @param performative
	 * @return message
	 * @throws InterruptedException
	 */
	synchronized public Message readMessage(AgentID agentID,
			Performative performative) throws InterruptedException {
		Message m = null;
		boolean b = false;
		while (!b) {
			try {
				m = super.readMessage(agentID, performative);
				b = true;
			} catch (JAMMessageBoxException e) {
				wait();
			}
		}
		notifyAll();
		return m;
	}

	/**
	 * Devuelve si hay mensajes.
	 * 
	 * Restituisce per i messaggi.
	 * 
	 * @return boolean
	 */
	synchronized public boolean isThereMessage() {
		return super.isThereMessage();
	}

	/**
	 * Devuelve si hay mensaje de un agente.
	 * 
	 * Restituito se un messaggio di agente.
	 * 
	 * @param agentID
	 * @return boolean
	 */
	synchronized public boolean isThereMessage(AgentID agentID) {
		return super.isThereMessage(agentID);
	}

	/**
	 * Devuelve si hay mensaje con Performative performative.
	 * 
	 * Indica se il messaggio Performative performative.
	 * 
	 * @param performative
	 * @return boolean
	 */
	synchronized public boolean isThereMessage(Performative performative) {
		return super.isThereMessage(performative);
	}

	/**
	 * Devuelve si hay mensaje con performative y agente determinado.
	 * 
	 * Restituito se nessun messaggio con l'agente performativo e dato.
	 * 
	 * @param agentID
	 * @param performative
	 * @return boolean
	 */
	synchronized public boolean isThereMessage(AgentID agentID,
			Performative performative) {
		return super.isThereMessage(agentID, performative);
	}

	/**
	 * Escribe un mensaje en un box.
	 * 
	 * Scrivi un messaggio su una casella.
	 * 
	 * @param message
	 * @throws InterruptedException
	 */
	synchronized public void writeMessage(Message message)
			throws InterruptedException {
		boolean b = false;
		while (!b) {
			try {
				super.writeMessage(message);
				b = true;
			} catch (JAMMessageBoxException e) {
				wait();
			}
		}
		notifyAll();
	}

	/**
	 * Muestra por pantalla un mensaje completo.
	 * 
	 * Stampa un messaggio completo.
	 */
	synchronized public void print() {
		super.print();
	}

}
