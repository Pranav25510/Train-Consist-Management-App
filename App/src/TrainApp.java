import java.util.ArrayList;
import java.util.List;

// Base class for all units in the consist
abstract class Bogy {
    protected String id;
    protected String type;

    public Bogy(String id, String type) {
        this.id = id;
        this.type = type;
    }

    public abstract String getDetails();
}

// Example Passenger Bogie
class PassengerBogy extends Bogy {
    private int capacity;

    public PassengerBogy(String id, String subType, int capacity) {
        super(id, "Passenger (" + subType + ")");
        this.capacity = capacity;
    }

    @Override
    public String getDetails() {
        return String.format("[%s] Type: %s | Capacity: %d seats", id, type, capacity);
    }
}

public class TrainApp {
    private List<Bogy> consist = new ArrayList<>();

    public void addBogy(Bogy b) {
        consist.add(b);
    }

    public void displaySummary() {
        System.out.println("=== Train Consist Summary ===");
        System.out.println("Engine: Locomotive-X1");
        for (Bogy b : consist) {
            System.out.println("  |-- " + b.getDetails());
        }
        System.out.println("Total Units: " + (consist.size() + 1));
    }

    public static void main(String[] args) {
        TrainApp myTrain = new TrainApp();

        // Adding initial consist
        myTrain.addBogy(new PassengerBogy("P101", "Sleeper", 72));
        myTrain.addBogy(new PassengerBogy("P102", "AC Chair", 56));

        // Display initial state
        myTrain.displaySummary();
    }
}