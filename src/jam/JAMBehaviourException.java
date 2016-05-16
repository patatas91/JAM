package jam;

/**
 * Excepcion que indica un error en la ejecucion de un comportamiento.
 * 
 * Eccezione che indica un errore nell'esecuzione di un comportamento.
 * 
 * @author Fernando Aliaga Ramon y Cristian Simon Moreno
 * 
 */
@SuppressWarnings("serial")
public class JAMBehaviourException extends JAMException {

	public JAMBehaviourException() {
		super();
	}

	public JAMBehaviourException(String message) {
		super(message);
	}

	public JAMBehaviourException(String message, Throwable cause) {
		super(message, cause);
	}

	public JAMBehaviourException(Throwable cause) {
		super(cause);
	}
}
