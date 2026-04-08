import java.util.HashMap;
import java.util.Map;

abstract class Bogy {
    protected String bogyID;
    protected int capacity;

    public Bogy(String bogyID, int capacity) {
        this.bogyID = bogyID;
        this.capacity = capacity;
    }

    public String getBogyID() { return bogyID; }
    public int getCapacity() { return capacity; }
}

class PassengerBogy extends Bogy {
    public PassengerBogy(String id, int cap) { super(id, cap); }
}

class GoodsBogy extends Bogy {
    public GoodsBogy(String id, int cap) { super(id, cap); }
}

public class TrainApp {
    // UC6: Key = Bogy ID, Value = Capacity
    private Map<String, Integer> capacityManifest = new HashMap<>();

    public void registerBogy(Bogy bogy) {
        // Associate the ID with its capacity in the Map
        capacityManifest.put(bogy.getBogyID(), bogy.getCapacity());
        System.out.println("Registered " + bogy.getBogyID() + " with capacity: " + bogy.getCapacity());
    }

    public void getBogyCapacity(String id) {
        // Efficient lookup using the Key
        if (capacityManifest.containsKey(id)) {
            System.out.println("Bogy " + id + " Capacity: " + capacityManifest.get(id));
        } else {
            System.out.println("Bogy " + id + " not found in manifest.");
        }
    }

    public void calculateTotalCapacity() {
        int total = 0;
        // Iterating through all values in the Map
        for (int cap : capacityManifest.values()) {
            total += cap;
        }
        System.out.println("\n--- Total Train Capacity: " + total + " units ---");
    }

    public static void main(String[] args) {
        TrainApp myTrain = new TrainApp();

        // Registering various types of bogies
        myTrain.registerBogy(new PassengerBogy("P-101", 72));
        myTrain.registerBogy(new PassengerBogy("P-102", 56));
        myTrain.registerBogy(new GoodsBogy("G-501", 1000)); // 1000kg load

        // Instant lookup
        myTrain.getBogyCapacity("P-101");

        // Analytics
        myTrain.calculateTotalCapacity();
    }
}