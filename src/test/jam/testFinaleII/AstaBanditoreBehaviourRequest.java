package test.jam.testFinaleII;

import test.jam.testFinaleI.LogFile;
import jam.*;

/**
 * Clase AstaBanditoreBehaviourRequest. Define el comportamiento de un agente que 
 * gestiona una subasta. El agente recibe pujas, registra la mejor oferta e informa
 * cuando la subasta haya finalizado.
 * 
 * Class AstaBanditoreBehaviourRequest. Definisce il comportamento di un agente che 
 * gestisce una vendita all'asta. L'agente riceve le offerte, i verbali e le 
 * relazioni al meglio quando l'asta si è conclusa.
 * 
 * @author Fernando Aliaga Ramon y Cristian Simon Moreno
 *
 */
public class AstaBanditoreBehaviourRequest extends JAMWhileBehaviour {
	/**
	 * Subasta actual.
	 * Aste correnti.
	 */
	private Subasta subasta;
	/**
	 * Mensaje entrante.
	 * Messaggio in arrivo.
	 */
	private Message mensajeIn;
	/**
	 * Mejor puja actual.
	 * Migliore offerta corrente.
	 */
	private Message pujaActual;
	/**
	 * Archivo Log donde se escribe las acciones del agente.
	 * Log file in cui si scrive azioni dell'agente.
	 */
	private LogFile file;

	/**
	 * Metodo constructor de objetos AstaBanditoreBehaviourRequest.
	 * AstaBanditoreBehaviourRequest oggetti metodo Builder.
	 * @param agent
	 * @param subasta
	 */
	public AstaBanditoreBehaviourRequest (JAMAgent agent, Subasta subasta) {
		super(agent);
		this.subasta=subasta;
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
	 * Metodo que ejecuta las acciones definidas para el agente de tipo REQUEST.
	 * Recibe ofertas de los diferentes clientes. Gestiona la subasta actual e informa
	 * del ganador cuando la subasta haya terminado.
	 * 
	 * Metodo che esegue le azioni definite per il tipo di richiesta dell'agente. 
	 * Ricevi le offerte di diversi clienti. Gestisce e riporta il vincitore dell'asta 
	 * corrente quando l'asta si è conclusa.
	 */
	public void action() throws JAMBehaviourInterruptedException {
		try {
			mensajeIn=myAgent.receive();
			file.log(myAgent.getMyID()+": recibida oferta de "+mensajeIn.getSender());
			/*
			 * Error de lectura del mensaje de tipo REQUEST (puja)
			 */
			if(mensajeIn.getPerformative()!=Performative.REQUEST) {
				Message request=new Message(
						myAgent.getMyID(),
						mensajeIn.getSender(),
						Performative.REFUSE,
						"Mensaje de puja incorrecto",
						mensajeIn);
				myAgent.send(request);
			}
			else {
				int oferta;
				try {
					oferta=Integer.parseInt(mensajeIn.getContent());
				}
				catch (Exception e) {
					file.log(myAgent.getMyID()+": error lectura en el precio de la puja");
					Message non_capito=new Message(
							myAgent.getMyID(),
							mensajeIn.getSender(),
							Performative.NOT_UNDERSTOOD,
							"Oferta incorrecta",
							mensajeIn);
					myAgent.send(non_capito);
					oferta=-1;
				}
				if (oferta!=-1) {
					file.log(myAgent.getMyID()+": recibida puja de "+mensajeIn.getSender() + 
							" por valor de " + oferta);
					/*
					 * La oferta es mayor que la puja actual
					 */
					if (oferta>subasta.getPujaActual()) {
						subasta.setPujaActual(oferta); 
						pujaActual=mensajeIn;
						file.log(myAgent.getMyID()+": nueva mejor puja: "+oferta+" - "+
								pujaActual.getSender());
						Message inform=new Message(
								myAgent.getMyID(),
								mensajeIn.getSender(),
								Performative.INFORM,
								subasta.toString(),
								mensajeIn);
						myAgent.send(inform);
					}
					/*
					 * La oferta es menor que la puja actual
					 */
					else {
						Message refuse=new Message(
								myAgent.getMyID(),
								mensajeIn.getSender(),
								Performative.REFUSE,
								subasta.toString(),
								mensajeIn);
						myAgent.send(refuse);
						file.log(myAgent.getMyID()+": la oferta de "+mensajeIn.getSender()+
								" por valor de "+ oferta + " no supera la puja actual");
					}
					sleep(1000);
					/*
					 * Comprobamos si hay alguna nueva oferta. Si no hay ofertas en el perido de 1000 ms
					 * damos por finalizada la puja.
					 */
					if (!myAgent.check(Performative.REQUEST)) {
						file.log(myAgent.getMyID()+": no hay nuevas ofertas. Puja actual de "+
								pujaActual.getSender() + " por valor de " + oferta);
						Message finSubasta= new Message(
								myAgent.getMyID(),
								pujaActual.getSender(),
								Performative.INFORM,
								subasta.toString(),
								mensajeIn);
						file.log(myAgent.getMyID()+": FIN DE LA SUBASTA. Informo a "+mensajeIn.getSender()+
								" de su compra");
						myAgent.send(finSubasta);
						done();
						/*
						 * Fin de la subasta.
						 */
					}
				}
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
		try{
			file.log(myAgent.getMyID()+": la subasta por "+subasta.getNombre()+
					" ha sido ganada por "+ pujaActual.getSender()+
					" al precio de "+subasta.getPujaActual());
			file.endLog();
			done();
		}
		catch(JAMIOException e) {
			System.out.println("Errore: "+e);
			done();
		}
		System.exit(1);
	}
}