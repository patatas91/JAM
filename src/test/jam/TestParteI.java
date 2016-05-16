package test.jam;

import jam.*;

import java.rmi.RemoteException;
import java.util.*;

/**
 * Clase TestParteI.
 * 
 * Classe TestParteI.
 * 
 * @author Fernando Aliaga Ramon y Cristian Simon Moreno
 *
 */
public class TestParteI {
	
	/** 
	 * Metodo Main de la clase TestParteI
	 * 
	 * Metodo Main della classe TestParteI
	 * 
	 * @param args
	 * @throws RemoteException
	 */
	public static void main(String[] args) {
		AgentID agenteCercato;
		List<AgentID> listaTrovati;

		List<AgentID> lista = new LinkedList<AgentID>();
		lista.add(new PersonalAgentID("Matteo", "Baldoni"));
		lista.add(new PersonalAgentID("Mario", "Rossi"));
		lista.add(new PersonalAgentID("Andrea", "Rossi"));
		lista.add(new PersonalAgentID("Alfredo", "Baldoni"));
		lista.add(new PersonalAgentID("Alfredo", "Rossi"));
		lista.add(new PersonalAgentID("Mercoledi", "Adams"));

		agenteCercato = new PersonalAgentID("Mario", "Rossi");
		listaTrovati = new LinkedList<AgentID>();
		for (AgentID agent : lista)
			if (agenteCercato.equals(agent))
				listaTrovati.add(agent);
		for (AgentID agent : listaTrovati)
			System.out.println(agent);
		
		System.out.println("---");
		
		agenteCercato = new CategoryAgentID("Rossi");
		listaTrovati = new LinkedList<AgentID>();
		for (AgentID agent : lista)
			if (agenteCercato.equals(agent))
				listaTrovati.add(agent);
		for (AgentID agent : listaTrovati)
			System.out.println(agent);
		
		System.out.println("---");
		
		agenteCercato = new GenericAgentID();
		listaTrovati = new LinkedList<AgentID>();
		for (AgentID agent : lista)
			if (agenteCercato.equals(agent))
				listaTrovati.add(agent);
		for (AgentID agent : listaTrovati)
			System.out.println(agent);

	}
}
