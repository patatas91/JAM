package jam;

/**
 * Excepcion producida la consulta de un objeto MessageBox.
 * 
 * Eccezione prodotto consultando un oggetto MessageBox.
 * 
 * @author Fernando Aliaga Ramon y Cristian Simon Moreno
 * 
 */
@SuppressWarnings("serial")
public class JAMMessageBoxException extends JAMException {

	public JAMMessageBoxException() {
		super();
	}

	public JAMMessageBoxException(String message) {
		super(message);
	}

	public JAMMessageBoxException(String message, Throwable cause) {
		super(message, cause);
	}

	public JAMMessageBoxException(Throwable cause) {
		super(cause);
	}
}
