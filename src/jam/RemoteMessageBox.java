package jam;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaz que describe un RemoteMessageBox.
 * 
 * Interfaccia che descrive un RemoteMessageBox.
 * 
 * @author Fernando Aliaga Ramon y Cristian Simon Moreno
 * 
 */
public interface RemoteMessageBox extends Remote {

	/**
	 * Escribe un mensaje en un box.
	 * 
	 * Scrivi un messaggio su una casella.
	 * 
	 * @param message
	 * @throws InterruptedException
	 * @throws JAMMessageBoxException
	 */
	public void writeMessage(Message message) throws RemoteException,
			InterruptedException;

	/**
	 * Devuelve el propietario del box.
	 * 
	 * Restituisce casella del proprietario.
	 * 
	 * @return owner
	 * @throws RemoteException
	 */
	public AgentID getOwner() throws RemoteException;

}
