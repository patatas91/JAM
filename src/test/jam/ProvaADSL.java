package test.jam;

import jam.*;

import java.rmi.RemoteException;

/**
 * Clase ProvaADSL.
 * 
 * Classe ProvaADSL.
 * 
 * @author Fernando Aliaga Ramon y Cristian Simon Moreno
 *
 */
public class ProvaADSL {

	/** 
	 * Metodo Main de la clase ProvaADSL.
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