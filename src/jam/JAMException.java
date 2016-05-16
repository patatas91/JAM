package jam;

/**
 * Excepcion producida en el JAM.
 * 
 * Eccezione prodotto nel JAM.
 * 
 * @author Fernando Aliaga Ramon y Cristian Simon Moreno
 * 
 */
@SuppressWarnings("serial")
public class JAMException extends Exception {

	public JAMException() {
		super();
	}

	public JAMException(String message) {
		super(message);
	}

	public JAMException(String message, Throwable cause) {
		super(message, cause);
	}

	public JAMException(Throwable cause) {
		super(cause);
	}

}
