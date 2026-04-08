import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

abstract class Bogy {
    protected String bogyID;
    protected int capacity;

    public Bogy(String bogyID, int capacity) {
        this.bogyID = bogyID;
        this.capacity = capacity;
    }

    public int getCapacity() { return capacity; }
}

class PassengerBogy extends Bogy {
    public PassengerBogy(String id, int cap) { super(id, cap); }
}

class GoodsBogy extends Bogy {
    public GoodsBogy(String id, int cap) { super(id, cap); }
}

public class TrainApp {
    private List<Bogy> consist = new ArrayList<>();

    public void addBogy(Bogy b) { consist.add(b); }

    // UC10: Using Stream.reduce() to aggregate values
    public void calculateTotalTrainCapacity() {
        // .reduce(Identity, Accumulator)
        // 0 is the starting value (Identity)
        // (sum, bogy) -> sum + bogy.getCapacity() is the Accumulator
        int totalCapacity = consist.stream()
                .map(Bogy::getCapacity) // Transform Stream<Bogy> to Stream<Integer>
                .reduce(0, (sum, capacity) -> sum + capacity);

        System.out.println("=======================================");
        System.out.println("TOTAL OPERATIONAL CAPACITY: " + totalCapacity);
        System.out.println("=======================================");
    }

    public static void main(String[] args) {
        TrainApp myTrain = new TrainApp();

        // Adding diverse units
        myTrain.addBogy(new PassengerBogy("S1", 72));
        myTrain.addBogy(new PassengerBogy("A1", 56));
        myTrain.addBogy(new GoodsBogy("G1", 1000)); // 1000kg load
        myTrain.addBogy(new GoodsBogy("G2", 500));  // 500kg load

        // Perform Reduction
        myTrain.calculateTotalTrainCapacity();
    }
}