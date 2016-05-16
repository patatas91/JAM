package parteII;

/**
 * Representa la categoria de un agente.
 * 
 * Rappresenta la categoria di un agente.
 * 
 * @author Fernando Aliaga Ramon y Cristian Simon Moreno
 * 
 */
@SuppressWarnings("serial")
public class CategoryAgentID extends GenericAgentID implements AgentID {

	/**
	 * Metodo constructor de la clase.
	 * 
	 * Metodo costruttore di classe.
	 * 
	 * @param category
	 */
	public CategoryAgentID(String category) {
		// GenericAgentID
		super();
		this.category = category;
	}

	/**
	 * Devuelve true si la categoria de agent es igual a la del agente.
	 * 
	 * Restituisce true se la categoria di agenti è pari all'agente.
	 * 
	 * @return boolean
	 */
	public boolean equals(Object agent) {
		return super.category.equals(((AgentID) agent).getCategory());
	}

}
