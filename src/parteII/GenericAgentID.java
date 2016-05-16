package parteII;

/**
 * Representa a un agente generico.
 * 
 * Rappresenta un agente generico.
 * 
 * @author Fernando Aliaga Ramon y Cristian Simon Moreno
 * 
 */
@SuppressWarnings("serial")
public class GenericAgentID implements AgentID {

	// Nombre del agente
	public String name;
	// Categoria del agente
	public String category;

	/**
	 * Constructor de la clase.
	 * 
	 * Costruttore della classe.
	 */
	public GenericAgentID() {
	}

	/**
	 * Devuelve el nombre.
	 * 
	 * Restituisce il nome.
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Devuelve la categoria.
	 * 
	 * Restituisce la categoria.
	 * 
	 * @return category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Devuelve true si agent es igual al agente.
	 * 
	 * Restituisce true se l'agente è uguale per l'agente.
	 * 
	 * @return boolean
	 */
	public boolean equals(Object agent) {
		return true;
	}

	/**
	 * Devuelve un String con (nombre, categoria).
	 * 
	 * Restituisce una stringa (nome, categoria).
	 * 
	 * @return String
	 */
	public String toString() {
		return "(" + name + ", " + category + ")";
	}

}
