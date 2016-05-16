package test.parteIV;

import java.rmi.RemoteException;

import parteIV.*;

/**
 * Clase ProvaADSL. Lanza un registro RMI.
 * 
 * Class ProvaADSL. Gettare un registro RMI.
 * 
 * @author Fernando Aliaga Ramon y Cristian Simon Moreno
 *
 */
public class ProvaADSL {

	/**
	 * Metodo principal de la clase ProvaADSL.
	 * 
	 * Metodo Main della classe ProvaADSL.
	 * 
	 * @param args
	 * @throws RemoteException
	 */
	public static void main(String[] args) throws RemoteException {
		ADSLImpl adsl = new ADSLImpl();
		try {            
			adsl.startRMIRegistry();
			System.out.println("> Habilitado el RMI Registry");
			adsl.startADSL();
			System.out.println("> Registrado el objeto ADSL en el registro");
			System.out.println("> Esperando al cliente...");            
			System.out.println();

		} catch (Exception e) {
			System.err.println("Failed to bind to RMI Registry");
			e.printStackTrace();
			System.exit(1);
		}
	}
}