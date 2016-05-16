package test.jam.testFinaleI;

import jam.*;

public class TimeProviderAgent extends JAMAgent {
    private int orologio;
    public TimeProviderAgent(String s) throws JAMADSLException, JAMMessageBoxException {
        super(new PersonalAgentID(s, "Time Provider"));
        addBehaviour(new TimeProviderBehaviourCalcola(this));
        addBehaviour(new TimeProviderBehaviourServiOra(this));
    }
    public void resetOra() {
        orologio = 0;
    }
    public void incrementaOra() {
        orologio++;
    }
    public int getOra() {
        return orologio;
    }
    public static void main(String args[])  throws JAMADSLException, JAMMessageBoxException  {
        TimeProviderAgent timeprovideragent = new TimeProviderAgent("Berlino");
        TimeProviderAgent timeprovideragent1 = new TimeProviderAgent("Parigi");
        timeprovideragent.init();
        timeprovideragent.start();
        timeprovideragent1.init();
        timeprovideragent1.start();
    }
}
