import java.util.ArrayList;
import java.util.List;

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
    private String category; // Sleeper, AC Chair, First Class
    private int capacity;

    public PassengerBogy(String bogyID, String category, int capacity) {
        super(bogyID);
        this.category = category;
        this.capacity = capacity;
    }

    @Override
    public void displayInfo() {
        System.out.println("Bogy ID: " + bogyID + " | Category: " + category + " | Capacity: " + capacity);
    }
}

public class TrainApp {
    // The 'consist' is managed via an ArrayList for dynamic resizing
    private List<Bogy> consist = new ArrayList<>();

    // UC2: Operation - Add Bogy
    public void addBogy(Bogy bogy) {
        consist.add(bogy);
        System.out.println("Added Bogy: " + bogy.getBogyID());
    }

    // UC2: Operation - Remove Bogy by ID
    public void removeBogy(String id) {
        boolean removed = consist.removeIf(b -> b.getBogyID().equalsIgnoreCase(id));
        if (removed) {
            System.out.println("Bogy " + id + " has been detached from the consist.");
        } else {
            System.out.println("Error: Bogy " + id + " not found.");
        }
    }

    // UC2: Operation - Check if Bogy exists
    public void findBogy(String id) {
        boolean exists = false;
        for (Bogy b : consist) {
            if (b.getBogyID().equalsIgnoreCase(id)) {
                System.out.print("Bogy Found: ");
                b.displayInfo();
                exists = true;
                break;
            }
        }
        if (!exists) System.out.println("Bogy " + id + " is not part of this train.");
    }

    public void showConsist() {
        System.out.println("\n--- Current Train Consist ---");
        if (consist.isEmpty()) {
            System.out.println("The engine is running solo (Empty Consist).");
        } else {
            consist.forEach(Bogy::displayInfo);
        }
        System.out.println("-----------------------------\n");
    }

    public static void main(String[] args) {
        TrainApp myTrain = new TrainApp();

        // 1. Dynamically Adding Passenger Bogies
        myTrain.addBogy(new PassengerBogy("S1", "Sleeper", 72));
        myTrain.addBogy(new PassengerBogy("A1", "AC Chair", 56));
        myTrain.addBogy(new PassengerBogy("F1", "First Class", 24));

        myTrain.showConsist();

        // 2. Finding a specific Bogy
        myTrain.findBogy("A1");

        // 3. Removing a Bogy (e.g., detaching at a junction)
        myTrain.removeBogy("S1");

        // 4. Final Display
        myTrain.showConsist();
    }
}