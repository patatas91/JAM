package test.jam.testFinaleI;

import jam.*;



public class AskTimeAgent extends JAMAgent {
    public AskTimeAgent(String category, String name) throws JAMADSLException, JAMMessageBoxException {
        super(new PersonalAgentID(category, name));
    }
    public static void main(String[] args) throws JAMADSLException, JAMMessageBoxException {
        AskTimeAgent asktimeagent = new AskTimeAgent("Matteo", "Baldoni");
        asktimeagent.addBehaviour(new AskTimeBehaviour(asktimeagent));
        AskTimeAgent asktimeagent1 = new AskTimeAgent("Mario", "Rossi");
        asktimeagent1.addBehaviour(new AskTimeBehaviourRefuse(asktimeagent1));
        AskTimeAgent asktimeagent2 = new AskTimeAgent("Mercoledi", "Adams");
        asktimeagent2.addBehaviour(new AskTimeBehaviourNotUnderstood(asktimeagent2));
        asktimeagent.init();
        asktimeagent.start();
        asktimeagent1.init();
        asktimeagent1.start();
        asktimeagent2.init();
        asktimeagent2.start();
    }
}
