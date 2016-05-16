package jam;

/**
 * Excepcion que indica un error en la comunicacion con el ADSL.
 * 
 * Eccezione che indica un errore di comunicazione con ADSL.
 * 
 * @author Fernando Aliaga Ramon y Cristian Simon Moreno
 * 
 */
@SuppressWarnings("serial")
public class JAMADSLException extends JAMException {

	public JAMADSLException() {
		super();
	}

	public JAMADSLException(String message) {
		super(message);
	}

	public JAMADSLException(String message, Throwable cause) {
		super(message, cause);
	}

	public JAMADSLException(Throwable cause) {
		super(cause);
	}
}
