package parteII;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * Representa un box de correo NO sincronizado a la que un agente podra enviar
 * mensajes pero solo podra ser leida por su propietario.
 * 
 * Rappresenta una casella di posta elettronica NO sincronizzata con un agente
 * può inviare messaggi ma potrebbe essere letto solo dal suo proprietario.
 * 
 * @author Fernando Aliaga Ramon y Cristian Simon Moreno
 * 
 */
public class MessageBoxNoSync {
	private PersonalAgentID owner;
	@SuppressWarnings("unused")
	private int DEFAULT_MAX_MESSAGE;
	private List<Message> box;

	/**
	 * Contructor de la clase.
	 * 
	 * Metodo costruttore di classe.
	 * 
	 * @param owner
	 */
	public MessageBoxNoSync(PersonalAgentID owner) {
		this.owner = owner;
		this.box = new Vector<Message>(20);
		this.DEFAULT_MAX_MESSAGE = 20;
	}

	/**
	 * Contructor de la clase.
	 * 
	 * Metodo costruttore di classe.
	 * 
	 * @param owner
	 * @param maxMessage
	 */
	public MessageBoxNoSync(PersonalAgentID owner, int maxMessage) {
		this.owner = owner;
		this.box = new Vector<Message>(maxMessage);
		this.DEFAULT_MAX_MESSAGE = maxMessage;
	}

	/**
	 * Devuelve el propietario del box.
	 * 
	 * Restituisce casella del proprietario.
	 * 
	 * @return owner
	 */
	public PersonalAgentID getOwner() {
		return owner;
	}

	/**
	 * Devuelve true si el box esta lleno.
	 * 
	 * Restituisce true se la casella è piena.
	 * 
	 * @return boolean
	 */
	public boolean isBoxEmpty() {
		return box.isEmpty();
	}

	/**
	 * Devuelve el Primer mensaje.
	 * 
	 * Restituisce il primo messaggio.
	 * 
	 * @return message
	 */
	public Message readMessage() {
		return box.get(0);
	}

	/**
	 * Devuelve el Primer mensaje de agente.
	 * 
	 * Restituisce il primo broker messaggio.
	 * 
	 * @param agentID
	 * @return message
	 */
	public Message readMessage(AgentID agentID) {
		Iterator<Message> i = box.iterator();
		Message m;
		while (i.hasNext()) {
			m = i.next();
			if (m.getSender().equals(agentID)) {
				return m;
			}
		}
		return null;
	}

	/**
	 * Devuelve el Primer mensaje con Performative performative.
	 * 
	 * Restituisce il primo messaggio performativo Performative.
	 * 
	 * @param performative
	 * @return message
	 */
	public Message readMessage(Performative performative) {
		Iterator<Message> i = box.iterator();
		Message m;
		while (i.hasNext()) {
			m = i.next();
			if (m.getPerformative().equals(performative)) {
				return m;
			}
		}
		return null;
	}

	/**
	 * Devuelve el Primer mensaje de performative y agente.
	 * 
	 * Restituisce il primo performativo messaggio e agente.
	 * 
	 * @param agentID
	 * @param performative
	 * @return message
	 */
	public Message readMessage(AgentID agentID, Performative performative) {
		Iterator<Message> i = box.iterator();
		Message m;
		while (i.hasNext()) {
			m = i.next();
			if (m.getPerformative().equals(performative)
					&& m.getSender().equals(agentID)) {
				return m;
			}
		}
		return null;
	}

	/**
	 * Devuelve si hay mensajes.
	 * 
	 * Restituisce per i messaggi.
	 * 
	 * @return boolean
	 */
	public boolean isThereMessage() {
		return !box.isEmpty();
	}

	/**
	 * Devuelve si hay mensaje de un agente.
	 * 
	 * Restituito se un messaggio di agente.
	 * 
	 * @param agentID
	 * @return boolean
	 */
	public boolean isThereMessage(AgentID agentID) {
		Iterator<Message> i = box.iterator();
		Message m;
		while (i.hasNext()) {
			m = i.next();
			if (m.getSender().equals(agentID)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Devuelve si hay mensaje con Performative performative.
	 * 
	 * Indica se il messaggio Performative performative.
	 * 
	 * @param performative
	 * @return boolean
	 */
	public boolean isThereMessage(Performative performative) {
		Iterator<Message> i = box.iterator();
		Message m;
		while (i.hasNext()) {
			m = i.next();
			if (m.getPerformative().equals(performative)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Devuelve si hay mensaje con performative y agente determinado.
	 * 
	 * Restituito se nessun messaggio con l'agente performativo e dato.
	 * 
	 * @param agentID
	 * @param performative
	 * @return boolean
	 */
	public boolean isThereMessage(AgentID agentID, Performative performative) {
		Iterator<Message> i = box.iterator();
		Message m;
		while (i.hasNext()) {
			m = i.next();
			if (m.getPerformative().equals(performative)
					&& m.getSender().equals(agentID)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Escribe un mensaje en un box.
	 * 
	 * Scrivi un messaggio su una casella.
	 * 
	 * @param message
	 */
	public void writeMessage(Message message) {
		box.add(message);
	}

	/**
	 * Muestra por pantalla un mensaje completo.
	 * 
	 * Stampa un messaggio completo.
	 */
	public void print() {
		Message m;
		Iterator<Message> i = box.iterator();
		while (i.hasNext()) {
			m = i.next();
			System.out.println("REMITENTE -> " + m.getSender());
			System.out.println("DESTINATARIO -> " + m.getReceiver());
			System.out.println("PERFORMATIVE -> " + m.getPerformative());
			System.out.println("CONTENIDO -> " + m.getContent());
		}
	}

}
