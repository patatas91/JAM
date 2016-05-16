package test.jam;

import jam.*;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Clase ProvaRemoteMessageBoxDue.
 * 
 * Classe ProvaRemoteMessageBoxDue.
 * 
 * @author Fernando Aliaga Ramon y Cristian Simon Moreno
 *
 */
public class ProvaRemoteMessageBoxDue {
	
	/** 
	 * Metodo Main de la clase ProvaRemoteMessageBoxDue.
	 * 
	 * Metodo Main della classe ProvaRemoteMessageBoxDue.
	 * 
	 * @param args
	 * @throws RemoteException
	 */
	public static void main(String[] args) {
		ADSL adsl;
		AgentID myID;
		MessageBox box1;
		try {
			myID = new PersonalAgentID("user1", "cat1");
			System.out.println("..Soy " + myID);
    		System.out.println("..Registro mi box");
    		adsl = (ADSL)Naming.lookup("rmi://127.0.0.1:2000/ADSL");
    		box1 = new MessageBox((PersonalAgentID) myID);
    		adsl.insertRemoteMessageBox(box1);     		
    		
    		//AgentID agentRemoteID = new PersonalAgentID("user1", "cat1");
    		AgentID agentRemoteID = new CategoryAgentID("cat3");
    		//AgentID agentRemoteID = new GenericAgentID();
    		System.out.println("..Voy a mandar un mensaje a " + agentRemoteID);
    		Message msg = new Message(myID, agentRemoteID, Performative.INFORM, "Hola");
    		
    		List<RemoteMessageBox> remoteBox2 = adsl.getRemoteMessageBox(agentRemoteID);    			
    		for(RemoteMessageBox rmb : remoteBox2) {
    			System.out.println("-> " + rmb.getOwner());
    			rmb.writeMessage(msg);
    		}
    		
    		System.out.println("..Leo mis mensajes");
    		Message myMsg = box1.readMessage();
    		System.out.println(myMsg);
    		
    		System.out.println("..Quito mi box");
    		adsl.removeRemoteMessageBox(myID);
    			
		}
		catch(Exception e) {
			System.out.println("Failed rmi" + e );
		}		
	}
}
