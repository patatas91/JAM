package parteIV;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Representa la interfaz del objeto remoto que sirve para listar, insertar y
 * quitar RemoteMessageBox de los agentes presentes en el sistema.
 * 
 * Rappresenta l'interfaccia dell'oggetto remoto usato per visualizzare,
 * inserire e rimuovere agenti RemoteMessageBox presenti nel sistema.
 * 
 * @author Fernando Aliaga Ramon y Cristian Simon Moreno
 * 
 */
public interface ADSL extends Remote {

	/**
	 * Devuelve un listado con los RemoteMessageBox que pertenecen a agentID.
	 * 
	 * Restituisce un elenco di RemoteMessageBox appartenenti a agentID.
	 * 
	 * @param agentID
	 * @return List con los RemoteMessageBox de los usuarios presentes
	 * @throws RemoteException
	 */
	public List<RemoteMessageBox> getRemoteMessageBox(AgentID agentID)
			throws RemoteException;

	/**
	 * Inserta un nuevo objeto RemoteMessageBox en el ADSL.
	 * 
	 * Inserisce un nuovo oggetto nella RemoteMessageBox ADSL.
	 * 
	 * @param messageBox
	 * @throws RemoteException
	 */
	public void insertRemoteMessageBox(RemoteMessageBox messageBox)
			throws RemoteException;

	/**
	 * Quita el objeto RemoteMessageBox que pertenece a agentID del ADSL.
	 * 
	 * Rimuovere l'oggetto appartenente a RemoteMessageBox agentID ADSL.
	 * 
	 * @param agentID
	 * @throws RemoteException
	 */
	public void removeRemoteMessageBox(AgentID agentID) throws RemoteException;

}
