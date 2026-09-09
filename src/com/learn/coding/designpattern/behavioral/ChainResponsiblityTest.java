package com.learn.coding.designpattern.behavioral;

// Handler
abstract class SupportHandler {
    protected SupportHandler next;
    public void setNext(SupportHandler next) { this.next = next; }
    public abstract void handleRequest(String level);
}

// Concrete Handlers
class FrontDesk extends SupportHandler {
    public void handleRequest(String level) {
        if (level.equals("basic")) {
            System.out.println("FrontDesk handled basic issue.");
        } else if (next != null) next.handleRequest(level);
    }
}

class Supervisor extends SupportHandler {
    public void handleRequest(String level) {
        if (level.equals("intermediate")) {
            System.out.println("Supervisor handled intermediate issue.");
        } else if (next != null) next.handleRequest(level);
    }
}

class Manager extends SupportHandler {
    public void handleRequest(String level) {
        if (level.equals("advanced")) {
            System.out.println("Manager handled advanced issue.");
        } else if (next != null) next.handleRequest(level);
    }
}

// Test
public class ChainResponsiblityTest {
    public static void main(String[] args) {
        SupportHandler frontDesk = new FrontDesk();
        SupportHandler supervisor = new Supervisor();
        SupportHandler manager = new Manager();

        frontDesk.setNext(supervisor);
        supervisor.setNext(manager);

        frontDesk.handleRequest("basic");
        frontDesk.handleRequest("intermediate");
        frontDesk.handleRequest("advanced");
    }
}
