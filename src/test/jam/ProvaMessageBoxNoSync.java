package test.jam;

import jam.*;

import java.rmi.RemoteException;

/**
 * Clase ProvaMessageBoxNoSync.
 * 
 * Classe ProvaMessageBoxNoSync.
 * 
 * @author Fernando Aliaga Ramon y Cristian Simon Moreno
 *
 */
public class ProvaMessageBoxNoSync {
	
	/** 
	 * Metodo Main de la clase ProvaMessageBoxNoSync.
	 * 
	 * Metodo Main della classe ProvaMessageBoxNoSync.
	 * 
	 * @param args
	 * @throws RemoteException
	 */
	public static void main(String[] args) throws RemoteException, JAMMessageBoxException, InterruptedException {
		MessageBoxNoSync box = new MessageBoxNoSync(new PersonalAgentID(
				"categoria", "nombre"));
		PersonalAgentID user1 = new PersonalAgentID("cat1", "nom1");
		PersonalAgentID user2 = new PersonalAgentID("cat2", "nom2");
		PersonalAgentID user3 = new PersonalAgentID("cat3", "nom3");
		PersonalAgentID user4 = new PersonalAgentID("cat3", "nom4");
		Performative p = Performative.valueOf("QUERY_IF");
		Performative p1 = Performative.valueOf("REFUSE");

		// Escribir los mensajes
		box.writeMessage(new Message(user1, new GenericAgentID(), Performative
				.valueOf("REQUEST"), "mensaje 1"));
		box.writeMessage(new Message(user2, new GenericAgentID(), Performative
				.valueOf("QUERY_IF"), "mensaje 2"));
		box.writeMessage(new Message(user3, new GenericAgentID(), Performative
				.valueOf("REQUEST"), "mensaje 3"));
		
		// Pruebas
		// Positivas
		System.out.println("Hay mensajes?");
		if (!box.isBoxEmpty()) {
			System.out.println("Si");
			System.out.println(box.readMessage());
		} else {
			System.out.println("No hay mensajes");
		}

		System.out.println("-----------------------------------");
		System.out.println("Mensaje enviado de " + user1 + "?");
		if (box.isThereMessage(user1)) {
			System.out.println("Si");
			System.out.println(box.readMessage(user1));
		} else {
			System.out.println("No hay mensajes que cumplan los requisitos");
		}

		System.out.println("-----------------------------------");
		System.out.println("Mensaje enviado con performative " + p + "?");
		if (box.isThereMessage(p)) {
			System.out.println("Si");
			System.out.println(box.readMessage(p));
		} else {
			System.out.println("No hay mensajes que cumplan los requisitos");
		}

		System.out.println("-----------------------------------");
		System.out.println("Mensaje enviado de " + user2 + " con performative "
				+ p + "?");
		if (box.isThereMessage(user2, p)) {
			System.out.println("Si");
			System.out.println(box.readMessage(user2, p));
		} else {
			System.out.println("No hay mensajes que cumplan los requisitos");
		}

		// Negativas
		System.out.println("-----------------------------------");
		System.out.println("Mensaje enviado de " + user4 + "?");
		if (box.isThereMessage(user4)) {
			System.out.println("Si");
			System.out.println(box.readMessage(user4));
		} else {
			System.out.println("No hay mensajes que cumplan los requisitos");
		}

		System.out.println("-----------------------------------");
		System.out.println("Mensaje enviado con performative " + p1 + "?");
		if (box.isThereMessage(p1)) {
			System.out.println("Si");
			System.out.println(box.readMessage(p1));
		} else {
			System.out.println("No hay mensajes que cumplan los requisitos");
		}

		System.out.println("-----------------------------------");
		System.out.println("Mensaje enviado de " + user4 + " con performative "
				+ p1 + "?");
		if (box.isThereMessage(user4, p1)) {
			System.out.println("Si");
			System.out.println(box.readMessage(user4, p1));
		} else {
			System.out.println("No hay mensajes que cumplan los requisitos");
		}

	}
}
