package test.jam.testFinaleI;

import jam.*;

public class TimeProviderBehaviourCalcola extends JAMWhileBehaviour {
    public TimeProviderBehaviourCalcola(JAMAgent myAgent) {
        super(myAgent);
    }
    public void setup() throws JAMBehaviourInterruptedException {
        ((TimeProviderAgent)myAgent).resetOra();
    }
    public void dispose() throws JAMBehaviourInterruptedException { }
    public void action() throws JAMBehaviourInterruptedException {
        ((TimeProviderAgent)myAgent).incrementaOra();
        try {
			sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
