package test.jam.testFinaleII;

/**
 * Clase Subasta. Crea y gestiona una subasta.
 * 
 * Classe Asta. Creare e gestire una vendita all'asta.
 * 
 * @author Fernando Aliaga Ramon y Cristian Simon Moreno
 *
 */
public class Subasta {
	/**
	 * Precio inicial de la subasta.
	 * Prezzo dell'asta di partenza.
	 */
	private int precioInicial;
	/**
	 * Puja actual vencedora de la subasta.
	 * Vincitore offerta corrente dell'asta.
	 */
	private int pujaActual;
	/**
	 * Nombre del objeto a subastar.
	 * Nome oggetto all'asta.
	 */
	private String nombre;

	/**
	 * Metodo constructor de objetos subasta.
	 * Asta oggetti metodo di costruzione.
	 * @param nombre
	 * @param precioInicial
	 */
	public Subasta (String nombre, int precioInicial) {
		this.precioInicial=precioInicial;
		pujaActual=precioInicial;
		this.nombre=nombre;
	}

	/**
	 * Devuelve la puja actual.
	 * Ritorna l'offerta attuale.
	 * @return puja actual
	 */
	public int getPujaActual() {
		return pujaActual;
	}

	/**
	 * Devuelve el precio inicial de la subasta.
	 * Ritorna il prezzo di partenza dell'asta.
	 * @return precio inicial de la subasta
	 */
	public int getPrecioInicial() {
		return precioInicial;
	}

	/**
	 * Devuelve el nombre del objeto a subastar.
	 * Ritorna il nome dell'oggetto da mettere all'asta.
	 * @return nombre del objeto a subastar
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Modifica el nombre del objeto a subastar.
	 * Cambia il nome dell'oggetto da mettere all'asta.
	 * @param n
	 */
	public void setNombre(String n) {
		this.nombre=n;
	}

	/**
	 * Modifica la puja actual.
	 * Modifica l'offerta attuale.
	 * @param p
	 */
	public void setPujaActual(int p) {
		pujaActual=p;
	}

	/**
	 * Modifica el precio inicial.
	 * Modificare il prezzo iniziale.
	 * @param p
	 */
	public void setPrecioInicial(int p) {
		precioInicial=p;
	}

	/**
	 * Devuelve un string con la puja actual.
	 * Ritorna uno string con l'offerta attuale.
	 */
	public String toString() {
		return (""+pujaActual);
	}
}