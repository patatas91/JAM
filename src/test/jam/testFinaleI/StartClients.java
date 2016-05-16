package test.jam.testFinaleI;

import jam.*;

public class StartClients { 
	
	@SuppressWarnings("unused")
	public static void main(String[] args) throws JAMADSLException, JAMMessageBoxException {
		
        AskTimeAgent asktimeagent = new AskTimeAgent("Matteo", "Baldoni");
        asktimeagent.addBehaviour(new AskTimeBehaviour(asktimeagent));
		JAMAgentMonitor cliente = new JAMAgentMonitor(asktimeagent);

        TimeProviderAgent berlino = new TimeProviderAgent("Berlino");
        JAMAgentMonitor server1 = new JAMAgentMonitor(berlino);

        TimeProviderAgent tokyo = new TimeProviderAgent("Tokyo");
        JAMAgentMonitor server2 = new JAMAgentMonitor(tokyo);
    }

}
