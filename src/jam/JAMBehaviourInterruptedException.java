package jam;

/**
 * Excepcion que indica un error al mandar una interrupcion a un hilo que estaba
 * en espera.
 * 
 * Eccezione che indica un errore nell'invio di un interrupt di un thread che
 * era in attesa.
 * 
 * @author Fernando Aliaga Ramon y Cristian Simon Moreno
 * 
 */
@SuppressWarnings("serial")
public class JAMBehaviourInterruptedException extends JAMException {

	public JAMBehaviourInterruptedException() {
		super();
	}

	public JAMBehaviourInterruptedException(String message) {
		super(message);
	}

	public JAMBehaviourInterruptedException(String message, Throwable cause) {
		super(message, cause);
	}

	public JAMBehaviourInterruptedException(Throwable cause) {
		super(cause);
	}
}
