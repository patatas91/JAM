package jam;

/**
 * Clase que define el comportamiento SIMPLE de un JAMAgent.
 * 
 * Classe che definisce il comportamento SIMPLE di un JAMAgent.
 * 
 * @author Fernando Aliaga Ramon y Cristian Simon Moreno
 * 
 */
public abstract class JAMSimpleBehaviour extends JAMBehaviour {

	/**
	 * Metodo constructor de la clase.
	 * 
	 * Metodo costruttore di classe.
	 * 
	 * @param agent
	 */
	public JAMSimpleBehaviour(JAMAgent agent) {
		super(agent);
	}

	/**
	 * Ejecucion del comportamiento:
	 * 
	 * Esecuzione di comportamento:
	 * 
	 * 1- Setup 2- Action 3- Dispose
	 */
	public void run() {
		try {
			setup();
		} catch (JAMBehaviourInterruptedException e) {
			System.err.println("Error setup");
			return;
		}
		try {
			// Comportamiento simple
			action();
		} catch (JAMBehaviourInterruptedException err) {
			if (isDone()) {
				return;
			}
			System.err.println("Interrumpido por usuario");
		} finally {
			try {
				dispose();
				done();
			} catch (JAMBehaviourInterruptedException err) {
				System.err.println("Error dispose");
			}
		}
	}
}
