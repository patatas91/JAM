package test.jam.testFinaleI;

import jam.ADSLMonitor;

public class StartADSL {

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		ADSLMonitor monitor = new ADSLMonitor();
		monitor.start();
	}
}
