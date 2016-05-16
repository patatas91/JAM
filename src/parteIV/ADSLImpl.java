package parteIV;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Representa la implementacion del objeto remoto que sirve para listar,
 * insertar y quitar RemoteMessageBox de los agentes presentes en el sistema.
 * 
 * Rappresenta l'attuazione dell'oggetto remoto usato per visualizzare, inserire
 * e rimuovere agenti RemoteMessageBox presenti nel sistema.
 * 
 * @author Fernando Aliaga Ramon y Cristian Simon Moreno
 * 
 */
@SuppressWarnings("serial")
public class ADSLImpl extends UnicastRemoteObject implements ADSL {

	List<RemoteMessageBox> messageBoxes;
	private int port;
	private String name;
	private String ip;
	// Sincronizacion
	private ReadWriteLock rwl = new ReentrantReadWriteLock();
	private Lock rl = rwl.readLock();
	private Lock wl = rwl.writeLock();

	/**
	 * Constructor del objeto ADSL con parametros por defecto.
	 * 
	 * ADSL oggetto costruttore con i parametri di default.
	 * 
	 * IP: 127.0.0.1 Name: ADSL Port: 2000
	 */
	public ADSLImpl() throws RemoteException {
		this("127.0.0.1", "ADSL", 2000);

	}

	/**
	 * Constructor del objeto ADSL.
	 * 
	 * ADSL oggetto costruttore.
	 * 
	 * @param ip
	 * @param name
	 * @param port
	 * @throws RemoteException
	 */
	public ADSLImpl(String ip, String name, int port) throws RemoteException {
		this.ip = ip;
		this.name = name;
		this.port = port;
		this.messageBoxes = new Vector<RemoteMessageBox>();
	}

	/**
	 * Devuelve una lista con los RemoteMessageBox de un agentID.
	 * 
	 * Restituisce una lista RemoteMessageBox un agentID.
	 * 
	 * @throws RemoteException
	 */
	public List<RemoteMessageBox> getRemoteMessageBox(AgentID agentID)
			throws RemoteException {
		System.out
				.println("-- inicio getremotebox, Voy a pillar los messagebox de "
						+ agentID);
		List<RemoteMessageBox> lista = new Vector<RemoteMessageBox>();
		rl.lock();
		try {
			Iterator<RemoteMessageBox> i = messageBoxes.iterator();
			RemoteMessageBox box;
			while (i.hasNext()) {
				box = i.next();
				try {
					if (agentID.equals(box.getOwner())) {
						lista.add(box);
					}
				} catch (RemoteException e) {
					// Objeto remoto NO activo
					i.remove();
				}
			}
		} finally {
			rl.unlock();
		}
		System.out.println("-- muestro lo que he encontrado");
		for (RemoteMessageBox b : lista) {
			try {
				System.out.println("\t" + b.getOwner());
			} catch (RemoteException ex) {
				ex.printStackTrace();
			}
		}
		System.out.println("-- acabo getRemoteMessageBox " + agentID);
		// Si se ejecuta el GUI
		try {
			ADSLMonitor.get(agentID);
		} catch (Exception e) {
		}
		return lista;
	}

	/**
	 * Inserta el RemoteMessageBox en ADSL, siempre que este objeto no este
	 * presente ya en el ADSL.
	 * 
	 * Inserire il RemoteMessageBox in ADSL, a condizione che l'oggetto non è
	 * presente già in ADSL.
	 * 
	 * @throws RemoteException
	 */
	public void insertRemoteMessageBox(RemoteMessageBox messageBox)
			throws RemoteException {

		// Objeto activo?
		AgentID agente;
		try {
			agente = messageBox.getOwner();
		} catch (RemoteException e) {
			throw new IllegalArgumentException("objeto remoto no activo", e);
		}

		System.out.println("-- inicio insertRemoteMessageBox " + agente);
		System.out
				.println("-- muestro los messagebox antes de insertar el nuevo");
		mostrar();

		wl.lock();
		try {
			Iterator<RemoteMessageBox> i = messageBoxes.iterator();
			RemoteMessageBox box;
			PersonalAgentID owner = (PersonalAgentID) messageBox.getOwner();
			while (i.hasNext()) {
				box = i.next();
				try {
					if (box.getOwner().equals(owner)) {
						throw new RemoteException("ya presente");
					}
				} catch (RemoteException e) {
					// Objeto no activo
					i.remove();
				}

			}
			messageBoxes.add(messageBox);
		} finally {
			wl.unlock();
		}
		System.out
				.println("-- muestro los messagebox despues de insertar el nuevo");
		mostrar();
		System.out.println("-- fin insertRemoteMessageBox " + agente);
		// Si se ejecuta el GUI
		try {
			ADSLMonitor.insert(messageBox.getOwner());
		} catch (Exception e) {
		}

	}

	/**
	 * Quita el RemoteMessageBox de agentID de ADSL, si no borra ninguno lanza
	 * excepcion.
	 * 
	 * Rimuove agentID RemoteMessageBox di ADSL, altrimenti eliminare qualsiasi
	 * tiri eccezione.
	 * 
	 * @throws RemoteException
	 */
	public void removeRemoteMessageBox(AgentID agentID) throws RemoteException {
		System.out.println("-- inicio removeRemoteMessageBox " + agentID);
		System.out.println("-- muestro los messagebox antes de borrar");
		mostrar();

		boolean borrado = false;
		wl.lock();
		try {
			Iterator<RemoteMessageBox> i = messageBoxes.iterator();
			RemoteMessageBox box;
			while (i.hasNext()) {
				box = i.next();
				try {
					if (agentID.equals(box.getOwner())) {
						i.remove();
						borrado = true;
					} else {
						box.getOwner();
					}
				} catch (RemoteException e) {
					// Objeto remoto no activo
					i.remove();
				}
			}
		} finally {
			wl.unlock();
		}
		if (!borrado) {
			System.out
					.println("No se ha borrado porque no se ha encontrado ningun mensaje que cumpla requisitos");
		}
		System.out
				.println("-- muestro los messagebox despues de borrar el seleccionado");
		mostrar();
		System.out.println("-- fin removeRemoteMessageBox " + agentID);
		// Si se ejecuta el GUI
		try {
			ADSLMonitor.remove(agentID);
		} catch (Exception e) {
		}
	}

	/**
	 * Muestra en pantalla un listado con los RemoteMessageBox presentes en
	 * ADSL.
	 * 
	 * Visualizza un elenco dei RemoteMessageBox presente in ADSL.
	 */
	private void mostrar() {
		rl.lock();
		try {
			Iterator<RemoteMessageBox> i = messageBoxes.iterator();
			RemoteMessageBox box;
			while (i.hasNext()) {
				try {
					box = i.next();
					System.out.println("- " + box.getOwner());
				} catch (RemoteException e) {
					System.out.println("- OBJETO NO ACTIVO");
				}
			}
		} finally {
			rl.unlock();
		}
	}

	/**
	 * Habilita el registro RMI.
	 * 
	 * Abilita il registro RMI.
	 * 
	 * @throws RemoteException
	 */
	public void startRMIRegistry() throws RemoteException {
		java.rmi.registry.LocateRegistry.createRegistry(getPort());
	}

	/**
	 * Realiza la inscripcion del objeto en el registro (rebind).
	 * 
	 * Effettuare la registrazione dell'oggetto nel Registro di sistema
	 * (Rebind).
	 * 
	 * Naming.rebind("rmi://127.0.0.1:2000/ADSL", adsl);
	 * 
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public void startADSL() throws RemoteException, MalformedURLException {
		Naming.rebind("rmi://" + getIP() + ":" + getPort() + "/" + getName(),
				this);
	}

	/**
	 * Quita el objeto del registro (unbind).
	 * 
	 * Rimuove il Registro di oggetto (unbind).
	 * 
	 * Naming.unbind("rmi://127.0.0.1:2000/ADSL", adsl);
	 * 
	 * @throws NotBoundException
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * 
	 */
	public void stopADSL() throws RemoteException, MalformedURLException,
			NotBoundException {
		Naming.unbind("rmi://" + getIP() + ":" + getPort() + "/" + getName());
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
	 * Devuelve el puerto.
	 * 
	 * Restituisce la porta.
	 * 
	 * @return port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * Devuelve la direccion IP.
	 * 
	 * Restituisce l'indirizzo IP.
	 * 
	 * @return IP
	 */
	public String getIP() {
		return ip;
	}

	public static void main(String[] args) {

	}

}
