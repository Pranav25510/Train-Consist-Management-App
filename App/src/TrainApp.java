import java.util.LinkedList;
import java.util.HashSet;
import java.util.Set;

// Base Abstract Class
abstract class Bogy {
    protected String bogyID;

    public Bogy(String bogyID) {
        this.bogyID = bogyID;
    }

    public String getBogyID() {
        return bogyID;
    }

    public abstract void displayInfo();
}

// Passenger Bogy implementation
class PassengerBogy extends Bogy {
    private String category;

    public PassengerBogy(String bogyID, String category) {
        super(bogyID);
        this.category = category;
    }

    @Override
    public void displayInfo() {
        System.out.print("[" + bogyID + ":" + category + "]");
    }
}

public class TrainApp {
    // UC4: Using LinkedList to model the physical chain of the train
    private LinkedList<Bogy> consist = new LinkedList<>();
    // Keeping the Set for uniqueness validation
    private Set<String> registeredIDs = new HashSet<>();

    // UC4: Operation - Attach Bogy to the end of the train
    public void attachBogy(Bogy bogy) {
        if (registeredIDs.add(bogy.getBogyID())) {
            consist.addLast(bogy); // Physically adding it to the end of the chain
            System.out.println("Coupled " + bogy.getBogyID() + " at the rear.");
        } else {
            System.out.println("Error: Bogy " + bogy.getBogyID() + " is already in the consist.");
        }
    }

    // UC4: Operation - Attach Bogy right after the engine (at the front)
    public void attachPriorityBogy(Bogy bogy) {
        if (registeredIDs.add(bogy.getBogyID())) {
            consist.addFirst(bogy);
            System.out.println("Priority Coupling: " + bogy.getBogyID() + " added behind the engine.");
        }
    }

    public void showConsist() {
        System.out.println("\n--- Physical Train Formation ---");
        System.out.print("ENGINE <-> ");
        for (int i = 0; i < consist.size(); i++) {
            consist.get(i).displayInfo();
            if (i < consist.size() - 1) System.out.print(" <-> ");
        }
        System.out.println(" <-> GUARD_COACH");
        System.out.println("Total Bogies: " + consist.size() + "\n");
    }

    public static void main(String[] args) {
        TrainApp myTrain = new TrainApp();

        // Adding bogies in a specific sequence
        myTrain.attachBogy(new PassengerBogy("S1", "Sleeper"));
        myTrain.attachBogy(new PassengerBogy("S2", "Sleeper"));

        // A VIP AC coach arrives and needs to be near the engine
        myTrain.attachPriorityBogy(new PassengerBogy("A1", "AC-First"));

        // Displaying the formation
        myTrain.showConsist();

        // Attempting a duplicate (still blocked by our Set logic)
        myTrain.attachBogy(new PassengerBogy("S1", "Sleeper"));
    }
}