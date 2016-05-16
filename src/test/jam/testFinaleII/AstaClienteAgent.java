package test.jam.testFinaleII;

import jam.*;

/**
 * Clase AstaClienteAgent. Lanza tres clientes que pujarán por
 * un objeto gestionado por una casa de subastas.
 * 
 * Class AstaClienteAgent. Lanza tre clienti che farà offerte 
 * per gestita da un oggetto casa d'aste.
 * 
 * @author Fernando Aliaga Ramon y Cristian Simon Moreno
 *
 */
public class AstaClienteAgent extends JAMAgent {
	
	/**
	 * Metodo constructor de objetos AstaClienteAgent.
	 * AstaClienteAgent oggetti metodo Builder.
	 * @param name
	 * @param category
	 * @throws JAMADSLException
	 * @throws JAMMessageBoxException
	 */
	public AstaClienteAgent(String name, String category) throws JAMADSLException, JAMMessageBoxException {
		super(new PersonalAgentID(name, category));
	}

	/**
	 * Metodo principal de la clase. Lanza tres clientes que pujarán por
	 * un objeto gestionado por una casa de subastas.
	 * 
	 * Metodo Main della classe. Lanza tre clienti che farà offerte per 
	 * gestita da un oggetto casa d'aste.
	 * @param args
	 * @throws JAMADSLException
	 * @throws JAMMessageBoxException
	 */
	public static void main(String[] args) throws JAMADSLException, JAMMessageBoxException {
		AstaClienteAgent cliente1= new AstaClienteAgent("Cristian Simon", "Cliente");
		AstaClienteAgent cliente2= new AstaClienteAgent("Fernando Aliaga", "Cliente");
		AstaClienteAgent cliente3= new AstaClienteAgent("Matteo Baldoni", "Cliente");
		cliente1.addBehaviour(new AstaClienteBehaviour(cliente1));
		cliente2.addBehaviour(new AstaClienteBehaviour(cliente2));
		cliente3.addBehaviour(new AstaClienteBehaviour(cliente3));
		cliente1.init();
		cliente1.start();
		cliente2.init();
		cliente2.start();
		cliente3.init();
		cliente3.start();
	}
}
