import java.util.*;
import java.util.stream.Collectors;

abstract class Bogy {
    protected String bogyID;
    protected String type; // e.g., Sleeper, AC Chair, Rectangular, Cylindrical

    public Bogy(String bogyID, String type) {
        this.bogyID = bogyID;
        this.type = type;
    }

    public String getType() { return type; }

    @Override
    public String toString() {
        return "ID: " + bogyID;
    }
}

class PassengerBogy extends Bogy {
    public PassengerBogy(String id, String type) { super(id, type); }
}

class GoodsBogy extends Bogy {
    public GoodsBogy(String id, String type) { super(id, type); }
}

public class TrainApp {
    private List<Bogy> consist = new ArrayList<>();

    public void addBogy(Bogy b) { consist.add(b); }

    // UC9: Grouping bogies into a Map by their type
    public void generateCategorizedReport() {
        System.out.println("\n--- Generating Categorized Consist Report ---");

        // The groupingBy collector organizes elements into a Map
        Map<String, List<Bogy>> groupedBogies = consist.stream()
                .collect(Collectors.groupingBy(Bogy::getType));

        // Iterating through the Map to display groups
        groupedBogies.forEach((type, list) -> {
            System.out.println("Category: [" + type.toUpperCase() + "]");
            list.forEach(bogy -> System.out.println("  |-- " + bogy));
            System.out.println("  Total Count: " + list.size());
            System.out.println();
        });
    }

    public static void main(String[] args) {
        TrainApp myTrain = new TrainApp();

        // Adding a mix of Passenger and Goods Bogies
        myTrain.addBogy(new PassengerBogy("P101", "Sleeper"));
        myTrain.addBogy(new PassengerBogy("P102", "Sleeper"));
        myTrain.addBogy(new PassengerBogy("P201", "AC Chair"));
        myTrain.addBogy(new GoodsBogy("G301", "Cylindrical"));
        myTrain.addBogy(new GoodsBogy("G302", "Cylindrical"));
        myTrain.addBogy(new GoodsBogy("G401", "Rectangular"));

        // Run Grouping Logic
        myTrain.generateCategorizedReport();
    }
}