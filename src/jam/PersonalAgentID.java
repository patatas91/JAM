package jam;

/**
 * Representa un PersonalAgentID, que tiene nombre y categoria.
 * 
 * Rappresenta un PersonalAgentID, che ha un nome e la categoria.
 * 
 * @author Fernando Aliaga Ramon y Cristian Simon Moreno
 * 
 */
@SuppressWarnings("serial")
public class PersonalAgentID extends CategoryAgentID implements AgentID {

	/**
	 * Contructor de la clase.
	 * 
	 * Metodo costruttore di classe.
	 * 
	 * @param name
	 * @param category
	 */
	public PersonalAgentID(String name, String category) {
		// GenericAgentID
		super(category);
		super.name = name;
	}

	/**
	 * Devuelve true si el nombre de agent es igual al del agente.
	 * 
	 * Restituisce true se il nome di agenti è pari all'agente.
	 * 
	 * @return boolean
	 */
	public boolean equals(Object agent) {
		return super.equals(agent) && name.equals(((AgentID) agent).getName());
	}

}
