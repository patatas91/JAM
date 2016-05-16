package test.jam.testFinaleII;

import jam.*;

/**
 * Clase AstaBanditoreAgent. Lanza una casa de subastas compuesta por un
 * agente que recibe pujas y otro que informa de la mejor oferta actual.
 * 
 * Class AstaBanditoreAgent. Genera una casa d'aste è costituito da un 
 * agente che riceve le offerte e un altro che riporta la miglior offerta 
 * attuale.
 * 
 * @author Fernando Aliaga Ramon y Cristian Simon Moreno
 *
 */
public class AstaBanditoreAgent extends JAMAgent {

	/**
	 * Metodo constructor de objetos AstaBanditoreAgent.
	 * AstaBanditoreAgent oggetti metodo Builder.
	 * @param name
	 * @param category
	 * @throws JAMADSLException
	 * @throws JAMMessageBoxException
	 */
	public AstaBanditoreAgent(String name, String category) throws JAMADSLException, JAMMessageBoxException {
		super(new PersonalAgentID(name, category));
	}

	/**
	 * Metodo principal de la clase. Crea una nueva subasta. Lanza dos agentes, 
	 * uno de tipo 'query' y otro de tipo 'request'. 
	 * 
	 * Metodo Main della classe. Creare una nuova asta. Stendere due agenti, 
	 * uno di tipo 'query' e un altro 'richiesta' tipo.
	 * @param args
	 * @throws JAMADSLException
	 * @throws JAMMessageBoxException
	 */
	public static void main(String[] args) throws JAMADSLException, JAMMessageBoxException {
		Subasta subasta=new Subasta("Ferrari F40", 12000);
		AstaBanditoreAgent banditoreQuery= new AstaBanditoreAgent("Casa de subastas", "Query");
		AstaBanditoreAgent banditoreRequest= new AstaBanditoreAgent("Casa de subastas", "Request");
		banditoreQuery.addBehaviour(new AstaBanditoreBehaviourQuery(banditoreQuery, subasta));
		banditoreRequest.addBehaviour(new AstaBanditoreBehaviourRequest(banditoreRequest, subasta));
		banditoreQuery.init();
		banditoreQuery.start();
		banditoreRequest.init();
		banditoreRequest.start();
	}
}