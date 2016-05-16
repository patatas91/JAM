package jam;

/**
 * Clase que define el comportamiento de un JAMAgent.
 * 
 * Classe che definisce il comportamento di un JAMAgent.
 * 
 * @author Fernando Aliaga Ramon y Cristian Simon Moreno
 * 
 */
public abstract class JAMBehaviour implements Runnable {

	// indica si el comportamiento se ha seguido completamente
	private boolean done;
	// Thread que sigue el comportamiento
	private Thread myThread;
	protected JAMAgent myAgent;

	/**
	 * Constructor de la clase.
	 * 
	 * Costruttore della classe.
	 * 
	 * @param agent
	 */
	public JAMBehaviour(JAMAgent agent) {
		this.done = false;
		this.myThread = null;
		this.myAgent = agent;
	}

	/**
	 * Pone done a true e interrumpe el thread.
	 * 
	 * Mette fatto un vero e interrompe il filo.
	 */
	public void done() {
		done = true;
		myThread.interrupt();
	}

	/**
	 * Devuelve el valor de done.
	 * 
	 * Restituisce il valore di done.
	 * 
	 * @return booleano
	 */
	public boolean isDone() {
		return done;
	}

	/**
	 * Define el Thread.
	 * 
	 * Imposta il Thread.
	 * 
	 * @param myThread
	 */
	public void setMyThread(Thread myThread) {
		this.myThread = myThread;
	}

	/**
	 * Pausa el Thread durante ms.
	 * 
	 * Pausa il thread per ms.
	 * 
	 * @param ms
	 */
	public void sleep(long ms) throws InterruptedException {
		Thread.sleep(ms);
	}

	/**
	 * Comportamiento a seguir una(simpleBehaviuor) o mas veces(whileBehaviour).
	 * 
	 * Comportamento a seguire un (simpleBehaviuor) o più volte
	 * (whileBehaviour).
	 * 
	 * @throws JAMBehaviourInterruptedException
	 * @throws JAMMessageBoxException
	 */
	public abstract void action() throws JAMBehaviourInterruptedException;

	/**
	 * Codigo a seguir antes de ejecutar el comportamiento.
	 * 
	 * Codice da seguire prima di eseguire il comportamento.
	 * 
	 * @throws JAMBehaviourInterruptedException
	 */
	public abstract void setup() throws JAMBehaviourInterruptedException;

	/**
	 * Codigo a seguir antes de finalizar el comportamiento.
	 * 
	 * Codice di seguire prima della fine della performance.
	 * 
	 * @throws JAMBehaviourInterruptedException
	 */
	public abstract void dispose() throws JAMBehaviourInterruptedException;

}
