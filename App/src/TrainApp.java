import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

class GoodsBogy {
    private String bogyID;
    private String shape; // "Cylindrical" or "Rectangular"
    private String cargoType; // "Liquid", "Gas", "Solid"

    public GoodsBogy(String bogyID, String shape, String cargoType) {
        this.bogyID = bogyID;
        this.shape = shape;
        this.cargoType = cargoType;
    }

    public String getShape() { return shape; }
    public String getCargoType() { return cargoType; }
    public String getBogyID() { return bogyID; }

    @Override
    public String toString() {
        return String.format("[%s] Shape: %-12s | Cargo: %s", bogyID, shape, cargoType);
    }
}

public class TrainApp {
    private List<GoodsBogy> goodsConsist = new ArrayList<>();

    // UC12: Define Safety Rules using Predicates (Functional Interface)
    // Rule: Cylindrical bogies MUST carry Liquids or Gases only.
    private static final Predicate<GoodsBogy> isSafetyCompliant = bogy -> {
        if (bogy.getShape().equalsIgnoreCase("Cylindrical")) {
            return bogy.getCargoType().equalsIgnoreCase("Liquid") ||
                    bogy.getCargoType().equalsIgnoreCase("Gas");
        }
        // Rectangular bogies are for Solids
        return bogy.getCargoType().equalsIgnoreCase("Solid");
    };

    public void addGoodsBogy(GoodsBogy bogy) {
        // Enforce the safety rule using the Predicate
        if (isSafetyCompliant.test(bogy)) {
            goodsConsist.add(bogy);
            System.out.println("SAFETY CHECK PASSED: Bogy " + bogy.getBogyID() + " attached.");
        } else {
            System.err.println("SAFETY VIOLATION: Bogy " + bogy.getBogyID() +
                    " (" + bogy.getShape() + ") cannot carry " + bogy.getCargoType() + "!");
        }
    }

    public void checkEntireConsist() {
        System.out.println("\n--- Current Goods Consist Compliance ---");
        goodsConsist.forEach(System.out::println);
    }

    public static void main(String[] args) {
        TrainApp yard = new TrainApp();

        // Case 1: Valid Cylindrical Bogy (carrying Oil/Liquid)
        yard.addGoodsBogy(new GoodsBogy("GB-001", "Cylindrical", "Liquid"));

        // Case 2: Invalid Assignment (Cylindrical carrying Coal/Solid)
        yard.addGoodsBogy(new GoodsBogy("GB-002", "Cylindrical", "Solid"));

        // Case 3: Valid Rectangular Bogy (carrying Grain/Solid)
        yard.addGoodsBogy(new GoodsBogy("GB-003", "Rectangular", "Solid"));

        yard.checkEntireConsist();
    }
}