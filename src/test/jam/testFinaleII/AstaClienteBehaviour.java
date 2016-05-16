package test.jam.testFinaleII;

import jam.*;
import test.jam.testFinaleI.LogFile;

/**
 * Clase AstaClienteBehaviour. Define el comportamiento de un agente que 
 * puja en una subasta.
 * 
 * Class AstaClienteBehaviour. Definisce il comportamento di un agente 
 * un'offerta all'asta.
 * 
 * @author Fernando Aliaga Ramon y Cristian Simon Moreno
 *
 */
public class AstaClienteBehaviour extends JAMWhileBehaviour {
	
	/**
	 * Saldo del cliente.
	 * Saldo clienti.
	 */
	private int saldo;
	/**
	 * Oferta realizada por el cliente.
	 * Offerta da parte del cliente.
	 */
	private int oferta;
	/**
	 * Puja actual de la subasta.
	 * Aste al corrente.
	 */
	private int precio;
	/**
	 * Archivo Log donde se escribe las acciones del agente.
	 * Log file in cui si scrive azioni dell'agente.
	 */
	private LogFile file;
	/**
	 * Dato de tipo 'boolean' que representa si el cliente ha
	 * ganado la subasta.
	 * Tipo di dati 'booleano' che rappresenta se il client ha 
	 * vinto l'asta.
	 */
	boolean exito;


	/**
	 * Metodo constructor de objetos AstaClienteBehaviour.
	 * AstaClienteBehaviour oggetti metodo Builder.
	 * @param agent
	 */
	public AstaClienteBehaviour (JAMAgent agent) {
		super(agent);
		file=new LogFile();
		exito=false;
	}

	/**
	 * Inicializa el comportamiento del agente. 
	 * Inizializza comportamento dell'agente.
	 */
	public void setup() throws JAMBehaviourInterruptedException {
		saldo=(int)(Math.random() * 30000);
		try {
			file.startLog(myAgent.getMyID().toString(),
					"Log file de "+myAgent.getMyID());
			file.log("Saldo inicial: "+saldo);
		}
		catch (JAMIOException e) {
			System.out.println("Errore: "+e);
			done();
		}
	}


	/**
	 * Metodo que ejecuta el comportamiento de un cliente en una subasta.
	 * El cliente intenta ser el ganador de la puja hasta que la gana o hasta
	 * que no pueda superar la oferta actual, en dicho caso se retirará.
	 * 
	 * Metodo che esegue il comportamento di un cliente in asta. Il client 
	 * cerca di essere il vincitore fino a vincere o fino a quando non si 
	 * può battere l'offerta attuale, in questo caso, essere ritirata.
	 */
	public void action() throws JAMBehaviourInterruptedException {
		CategoryAgentID query= new CategoryAgentID("Query");
		CategoryAgentID request= new CategoryAgentID("Request");
		try{
			/*
			 * Primero obtenemos el precio de la puja actual.
			 */
			Message queryIf= new Message(
					myAgent.getMyID(),
					query,
					Performative.QUERY_IF,
					"Puja actual?",
					null);
			myAgent.send(queryIf);
			file.log(myAgent.getMyID()+": enviada consulta QUERY\n");
			Message mensajeIn= myAgent.receive(query);
			if(mensajeIn.getPerformative()!=Performative.AGREE) {
				file.log(myAgent.getMyID()+": envio de QUERY erroneo");
				done();
			}
			file.log(myAgent.getMyID()+": recibida puja actual");
			precio=Integer.parseInt(mensajeIn.getContent());
			file.log(myAgent.getMyID()+": Puja actual = "+precio);
			/*
			 * Realizamos una oferta si tenemos dinero suficiente
			 */
			if (precio<=saldo) {
				oferta=(int)(Math.random()*(saldo-precio+1)+(precio+1));
				Message req= new Message(
						myAgent.getMyID(),
						request,
						Performative.REQUEST,
						String.valueOf(oferta),
						null);
				myAgent.send(req);
				file.log(myAgent.getMyID()+
						": Envio oferta por valor de "+oferta);
				mensajeIn= myAgent.receive(request);
				/*
				 * Recibo la respuesta de la casa de subastas
				 */
				if (mensajeIn.getPerformative()==Performative.INFORM) {
					file.log(myAgent.getMyID()+": soy el mejor postor con la oferta de "+oferta);
					sleep(1300);
					/*
					 * Si hay un mensaje de tipo INFORM la subasta a finalizado
					 * con mi puja. Resto el valor de dicha apuesta de mi saldo.
					 */
					if (myAgent.check(Performative.INFORM)) {
						saldo-=oferta;
						exito=true;
						done();
					}
					else file.log(myAgent.getMyID()+": oferta superada. ");
				}
				else file.log(myAgent.getMyID()+": oferta incorrecta.");
			}
			else { //Non ho abbastanza soldi
				file.log(myAgent.getMyID()+": no dispongo de saldo suficiente. Abandono la subasta.");
				done();
			}
		}
		catch(JAMADSLException err1) {
			System.out.println("Errore: "+err1);
			done();
		}
		catch(JAMIOException err2) {
			System.out.println("Errore: "+err2);
			done();
		} catch (JAMMessageBoxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * Finaliza el comportamiento del agente. Informa del resultado de la subasta.
	 * Finisce comportamento dell'agente. Riporta l'esito dell'asta.
	 */
	public void dispose() throws JAMBehaviourInterruptedException {
		try {
			if(exito){
				file.log(myAgent.getMyID()+
						": he ganado la subasta con una puja de"+
						oferta);
			}	
			else{
				file.log(myAgent.getMyID()+
						": la subasta ha finalizado con la puja ganadora de otro comprador por " +
						precio);
			}
			file.endLog();
		}
		catch (JAMIOException e) {
			System.out.println("Errore: "+e);
			done();
		}
	}
}