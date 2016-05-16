package parteIV;

import java.io.Serializable;

/**
 * Representa un mensaje, que contiene un remitente, destinatario, performative,
 * contenido y un extraArgument. Dicho mensaje podra ser intercambiado por los
 * Agentes.
 * 
 * Rappresenta un messaggio contenente un mittente, destinatario, performativo,
 * e il contenuto extraArgument. Questo messaggio può essere scambiato dagli
 * agenti.
 * 
 * @author Fernando Aliaga Ramon y Cristian Simon Moreno
 * 
 */
@SuppressWarnings("serial")
public class Message implements Serializable {

	AgentID sender;
	AgentID receiver;
	Performative performative;
	String content;
	Object extraArgument;

	/**
	 * Contructor de la clase.
	 * 
	 * Metodo costruttore di classe.
	 * 
	 * @param sender
	 * @param receiver
	 * @param performative
	 * @param content
	 * @param extraArgument
	 */
	public Message(AgentID sender, AgentID receiver, Performative performative,
			String content, Object extraArgument) {
		this.sender = sender;
		this.receiver = receiver;
		this.performative = performative;
		this.content = content;
		this.extraArgument = extraArgument;
	}

	/**
	 * Constructor de la clase.
	 * 
	 * Metodo costruttore di classe.
	 * 
	 * @param sender
	 * @param receiver
	 * @param performative
	 * @param content
	 */
	public Message(AgentID sender, AgentID receiver, Performative performative,
			String content) {
		this.sender = sender;
		this.receiver = receiver;
		this.performative = performative;
		this.content = content;
	}

	/**
	 * Devuelve el remitente.
	 * 
	 * Restituisce il mittente.
	 * 
	 * @return sender
	 */
	public AgentID getSender() {
		return sender;

	}

	/**
	 * Define el remitente.
	 * 
	 * Definire il mittente.
	 * 
	 * @param sender
	 */
	public void setSender(AgentID sender) {
		sender = this.sender;
	}

	/**
	 * Devuelve el destinatario.
	 * 
	 * Restituisce il destinatario.
	 * 
	 * @return receiver
	 */
	public AgentID getReceiver() {
		return receiver;

	}

	/**
	 * Define el destinatario.
	 * 
	 * Definire il destinatario.
	 * 
	 * @param receiver
	 */
	public void setReceiver(AgentID receiver) {
		receiver = this.receiver;
	}

	/**
	 * Devuelve el performative.
	 * 
	 * Retituisce il performative.
	 * 
	 * @return performative
	 */
	public Performative getPerformative() {
		return performative;

	}

	/**
	 * Define el performative.
	 * 
	 * Definire il performative.
	 * 
	 * @param performative
	 */
	public void setPerformative(Performative performative) {
		performative = this.performative;
	}

	/**
	 * Devuelve el contenido.
	 * 
	 * Retituice il contenuto.
	 * 
	 * @return content
	 */
	public String getContent() {
		return content;

	}

	/**
	 * Define el contenido.
	 * 
	 * Definire il contenuto.
	 * 
	 * @param content
	 */
	public void setContent(String content) {
		content = this.content;
	}

	/**
	 * Devuelve el extraArgument.
	 * 
	 * Retituisce il extraArgument.
	 * 
	 * @return extraArgument
	 */
	public Object getExtraArgument() {
		return extraArgument;

	}

	/**
	 * Define el extraArgument.
	 * 
	 * Definire il extraArgument.
	 * 
	 * @param extraArgument
	 */
	public void setExtraArgument(Object extraArgument) {
		extraArgument = this.extraArgument;
	}

	/**
	 * Devuelve un String con el contenido del mensaje.
	 * 
	 * Restituisce una stringa con il contenuto del messaggio.
	 * 
	 * @return content
	 */
	public String toString() {
		return content;
	}

}
