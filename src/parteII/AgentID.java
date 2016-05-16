package parteII;

import java.io.Serializable;

/**
 * Representa la interfaz de un agente, que contiene un nombre y una categoria.
 * 
 * Rappresenta l'interfaccia di un agente, che contiene un nome e una categoria.
 * 
 * @author Fernando Aliaga Ramon y Cristian Simon Moreno
 * 
 */
public interface AgentID extends Serializable {

	/**
	 * Devuelve true si el objeto agentID es igual al agente.
	 * 
	 * AgentID Restituisce vero se l'oggetto è uguale per l'agente.
	 * 
	 * @param agentID
	 * @return boolean
	 */
	public boolean equals(Object agentID);

	/**
	 * Devuelve el nombre.
	 * 
	 * Restituisce il nome.
	 * 
	 * @return nombre
	 */
	public String getName();

	/**
	 * Devuelve la categoria.
	 * 
	 * Restituisce la categoria.
	 * 
	 * @return categoria
	 */
	public String getCategory();

	/**
	 * Metodo toString de la clase.
	 * 
	 * Metodo ToString della classe.
	 * 
	 * @return string
	 */
	public String toString();

}
