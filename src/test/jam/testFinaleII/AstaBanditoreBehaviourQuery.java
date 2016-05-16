package test.jam.testFinaleII;

import jam.JAMADSLException;
import jam.JAMAgent;
import jam.JAMBehaviourInterruptedException;
import jam.JAMIOException;
import jam.JAMMessageBoxException;
import jam.JAMWhileBehaviour;
import jam.Message;
import jam.Performative;
import test.jam.testFinaleI.LogFile;

/**
 * Clase AstaBanditoreBehaviourQuery. Define el comportamiento de un agente que 
 * facilita la puja actual de una subasta.
 * 
 * Class AstaBanditoreBehaviourQuery. Definisce il comportamento di un agente 
 * che facilita l'offerta attuale di un'asta.
 * 
 * @author Fernando Aliaga Ramon y Cristian Simon Moreno
 *
 */
public class AstaBanditoreBehaviourQuery extends JAMWhileBehaviour {

	/**
	 * Subasta actual.
	 * Aste correnti.
	 */
	private Subasta subasta;
	/**
	 * Archivo Log donde se escribe las acciones del agente.
	 * Log file in cui si scrive azioni dell'agente.
	 */
	private LogFile file;

	/**
	 * Metodo constructor de objetos AstaBanditoreBehaviourQuery.
	 * AstaBanditoreBehaviourQuery oggetti metodo Builder.
	 * @param agent
	 * @param s
	 */
	public AstaBanditoreBehaviourQuery (JAMAgent agent, Subasta s) {
		super(agent);
		subasta=s;
		file=new LogFile();
	}

	/**
	 * Inicializa el comportamiento del agente.
	 * Inizializza comportamento dell'agente.
	 */
	public void setup() throws JAMBehaviourInterruptedException {
		try {
			file.startLog(myAgent.getMyID().toString(),
					"Log file de "+myAgent.getMyID());
			file.log(myAgent.getMyID()+": objeto a subastar: "+subasta.getNombre()+
					"\nPrecio inicial: "+subasta.getPrecioInicial());
		}
		catch(JAMIOException e) {
			System.out.println("Errore: "+e);
			done();
		}
	}

	/**
	 * Metodo que ejecuta las acciones definidas para el agente de tipo QUERY.
	 * Responde a los mensajes entrantes con la puja actual de la subasta.
	 * 
	 * Metodo che esegue le azioni definite per tipo di agente QUERY. 
	 * Risponde ai messaggi in arrivo con l'incremento dell'offerta attuale.
	 */
	public void action() throws JAMBehaviourInterruptedException {
		try {
			Message mensajeIn=myAgent.receive();
			file.log(myAgent.getMyID()+": recibido mensaje de "+mensajeIn.getSender());
			/*
			 * Error en lectura del mensaje de tipo QUERY
			 */
			if((mensajeIn.getPerformative()!=Performative.QUERY_IF) |
					(!mensajeIn.getContent().equals("Puja actual?"))) {
				Message request=new Message(myAgent.getMyID(), mensajeIn.getSender(),
						Performative.REFUSE, "Mensaje incorrecto.", mensajeIn);
				myAgent.send(request);
			}
			else {
				file.log(myAgent.getMyID()+": mensaje QUERY recibido");
				Message agree=new Message(myAgent.getMyID(), mensajeIn.getSender(),
						Performative.AGREE, subasta.toString(), mensajeIn);
				myAgent.send(agree);
			}
			file.log(myAgent.getMyID()+": enviada puja actual a "+mensajeIn.getSender());;
			sleep(100);
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
	 * Finaliza el comportamiento del agente.
	 * Finisce comportamento dell'agente.
	 */
	public void dispose() throws JAMBehaviourInterruptedException {
		file.endLog();
		System.exit(1);
	}
}

