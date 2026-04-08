import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public String getType() { return type; }

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

    // UC8: Stream API to filter high-capacity bogies
    public void displayHighCapacityBogies(int threshold) {
        System.out.println("\n--- Filtering Bogies: Capacity > " + threshold + " ---");

        consist.stream()                                      // 1. Source
                .filter(b -> b.getCapacity() > threshold)      // 2. Intermediate Operation (Filter)
                .forEach(System.out::println);                // 3. Terminal Operation
    }

    // UC8: Stream API to get a specific sub-list (e.g., only Sleepers)
    public List<Bogy> getBogiesByType(String bogyType) {
        return consist.stream()
                .filter(b -> b.getType().equalsIgnoreCase(bogyType))
                .collect(Collectors.toList());          // Terminal operation to store results
    }

    public static void main(String[] args) {
        TrainApp myTrain = new TrainApp();

        myTrain.addBogy(new PassengerBogy("P101", "Sleeper", 72));
        myTrain.addBogy(new PassengerBogy("P102", "AC Chair", 56));
        myTrain.addBogy(new PassengerBogy("P103", "First Class", 24));
        myTrain.addBogy(new PassengerBogy("P104", "General", 90));

        // Use Case: Find big bogies for a holiday rush
        myTrain.displayHighCapacityBogies(60);

        // Use Case: Find all Sleepers for a night journey report
        System.out.println("\nSleeper Bogie Report:");
        myTrain.getBogiesByType("Sleeper").forEach(System.out::println);
    }
}