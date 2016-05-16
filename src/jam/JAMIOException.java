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
public class JAMIOException extends JAMException {

	public JAMIOException() {
		super();
	}

	public JAMIOException(String message) {
		super(message);
	}

	public JAMIOException(String message, Throwable cause) {
		super(message, cause);
	}

	public JAMIOException(Throwable cause) {
		super(cause);
	}
}
