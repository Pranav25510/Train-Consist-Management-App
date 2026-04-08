import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

abstract class Bogy {
    protected String bogyID;
    protected String type;
    protected int capacity;

    public Bogy(String bogyID, String type, int capacity) {
        this.bogyID = bogyID;
        this.type = type;
        this.capacity = capacity;
    }

    public int getCapacity() { return capacity; }

    @Override
    public String toString() {
        return String.format("[%s] %-15s | Capacity: %d", bogyID, type, capacity);
    }
}

class PassengerBogy extends Bogy {
    public PassengerBogy(String id, String type, int cap) { super(id, type, cap); }
}

public class TrainApp {
    private List<Bogy> consist = new ArrayList<>();

    public void addBogy(Bogy b) { consist.add(b); }

    // UC7: Sorting logic using a Comparator
    public void sortBogiesByCapacity() {
        System.out.println("\n--- Sorting Bogies by Capacity (High to Low) ---");

        // Comparator: Compares two bogies and returns (b2 - b1) for descending order
        Comparator<Bogy> capacityComparator = new Comparator<Bogy>() {
            @Override
            public int compare(Bogy b1, Bogy b2) {
                return Integer.compare(b2.getCapacity(), b1.getCapacity());
            }
        };

        Collections.sort(consist, capacityComparator);
    }

    public void displayConsist() {
        for (Bogy b : consist) {
            System.out.println(b);
        }
    }

    public static void main(String[] args) {
        TrainApp myTrain = new TrainApp();

        // Adding bogies with different capacities
        myTrain.addBogy(new PassengerBogy("P101", "Sleeper", 72));
        myTrain.addBogy(new PassengerBogy("P102", "AC Chair", 56));
        myTrain.addBogy(new PassengerBogy("P103", "First Class", 24));
        myTrain.addBogy(new PassengerBogy("P104", "General", 90));

        System.out.println("Original Consist:");
        myTrain.displayConsist();

        // Perform sorting
        myTrain.sortBogiesByCapacity();
        myTrain.displayConsist();
    }
}