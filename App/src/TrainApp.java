import java.util.Stack;
import java.util.HashSet;
import java.util.Set;

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
    // UC5: Using Stack to enforce LIFO (Last-In, First-Out) operations
    private Stack<Bogy> consist = new Stack<>();
    private Set<String> registeredIDs = new HashSet<>();

    // Operation: Push (Coupling)
    public void coupleBogy(Bogy bogy) {
        if (registeredIDs.add(bogy.getBogyID())) {
            consist.push(bogy);
            System.out.println("Coupled: " + bogy.getBogyID());
        } else {
            System.out.println("Rejected: Duplicate ID " + bogy.getBogyID());
        }
    }

    // Operation: Pop (Decoupling the last attached unit)
    public void decoupleLastBogy() {
        if (!consist.isEmpty()) {
            Bogy removed = consist.pop();
            registeredIDs.remove(removed.getBogyID());
            System.out.println("Decoupled (LIFO): " + removed.getBogyID());
        } else {
            System.out.println("Error: No bogies left to decouple.");
        }
    }

    public void showConsist() {
        System.out.println("\n--- Current Shunting State (Stack) ---");
        if (consist.isEmpty()) {
            System.out.println("[Engine] < (Empty)");
        } else {
            System.out.print("[Engine]");
            // Elements are displayed in order of insertion
            for (Bogy b : consist) {
                System.out.print(" -> ");
                b.displayInfo();
            }
        }
        System.out.println("\n--------------------------------------\n");
    }

    public static void main(String[] args) {
        TrainApp yard = new TrainApp();

        // Coupling sequence
        yard.coupleBogy(new PassengerBogy("P1", "Sleeper"));
        yard.coupleBogy(new PassengerBogy("P2", "AC-Chair"));
        yard.coupleBogy(new PassengerBogy("P3", "First-Class"));

        yard.showConsist();

        // Operational Rollback: Decoupling the last one added (P3)
        System.out.println("Action: Emergency detachment of the last bogy...");
        yard.decoupleLastBogy();

        yard.showConsist();
    }
}