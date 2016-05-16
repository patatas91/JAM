package test.jam.testFinaleII;

import jam.ADSLMonitor;

/**
 * Clase ProvaADSL. Lanza un registro RMI.
 * 
 * Class ProvaADSL. Gettare un registro RMI.
 * 
 * @author Fernando Aliaga Ramon y Cristian Simon Moreno
 *
 */
public class StartADSL {

	/**
	 * Metodo principal de la clase StartADSL.
	 * 
	 * Metodo Main della classe StartADSL.
	 * 
	 * @param args
	 */
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		ADSLMonitor monitor = new ADSLMonitor();
		monitor.start();
	}
}
