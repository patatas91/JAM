package test.jam;

import jam.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;


/**
 * Clase ProvaRemoteMessageBoxUno.
 * 
 * Classe ProvaRemoteMessageBoxUno.
 * 
 * @author Fernando Aliaga Ramon y Cristian Simon Moreno
 *
 */
public class ProvaRemoteMessageBoxUno {

	/** 
	 * Metodo Main de la clase ProvaRemoteMessageBoxUno.
	 * 
	 * Metodo Main della classe ProvaRemoteMessageBoxUno.
	 * 
	 * @param args
	 * @throws RemoteException
	 */
	public static void main(String[] args) throws MalformedURLException,
			RemoteException, NotBoundException, JAMADSLException {

		AgentID myID = new PersonalAgentID("user3", "cat3");
		System.out.println("..Soy " + myID);
		System.out.println("..Registro mi box");
		ADSL adsl = (ADSL) Naming.lookup("rmi://127.0.0.1:2000/ADSL");
		MessageBox box1 = new MessageBox((PersonalAgentID) myID);
		adsl.insertRemoteMessageBox(box1);
		try {

			List<RemoteMessageBox> remoteBox2 = adsl
					.getRemoteMessageBox(new GenericAgentID());
			for (RemoteMessageBox rmb : remoteBox2) {
				System.out.println("-> " + rmb.getOwner());
			}

			System.out.println("Espero mensaje");
			Message myMsg = box1.readMessage();
			System.out.println(myMsg);
		} catch (Exception e) {
			System.err.println("Failed rmi" + e);
		} finally {
			System.out.println("Quito mi box");
			adsl.removeRemoteMessageBox(myID);
		}
	}
}
