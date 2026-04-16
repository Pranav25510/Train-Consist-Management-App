import java.util.*;
import java.util.stream.*;

// Base class
abstract class Bogie {
    String id;

    public Bogie(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

// Passenger Bogie
class PassengerBogie extends Bogie {
    String type; // Sleeper, AC Chair, First Class
    int seatCapacity;

    public PassengerBogie(String id, String type, int seatCapacity) {
        super(id);
        this.type = type;
        this.seatCapacity = seatCapacity;
    }

    public String getType() {
        return type;
    }

    public int getSeatCapacity() {
        return seatCapacity;
    }

    @Override
    public String toString() {
        return "PassengerBogie{id='" + id + "', type='" + type +
                "', seatCapacity=" + seatCapacity + "}";
    }
}

// Goods Bogie
class GoodsBogie extends Bogie {
    String cargoType;

    public GoodsBogie(String id, String cargoType) {
        super(id);
        this.cargoType = cargoType;
    }

    public String getCargoType() {
        return cargoType;
    }

    @Override
    public String toString() {
        return "GoodsBogie{id='" + id + "', cargoType='" + cargoType + "'}";
    }
}

// Service class
class TrainConsistService {

    private List<Bogie> bogies;

    public TrainConsistService(List<Bogie> bogies) {
        this.bogies = bogies;
    }

    // UC9: Group bogies by category (Passenger Type / Cargo Type)
    public Map<String, List<Bogie>> groupBogiesByType() {

        return bogies.stream()
                .collect(Collectors.groupingBy(b -> {

                    if (b instanceof PassengerBogie) {
                        return "PASSENGER - " + ((PassengerBogie) b).getType();
                    } else if (b instanceof GoodsBogie) {
                        return "GOODS - " + ((GoodsBogie) b).getCargoType();
                    } else {
                        return "UNKNOWN";
                    }
                }));
    }
}

// Main Test Class
public class UseCase8TrainConsistMgmtTest {

    public static void main(String[] args) {

        List<Bogie> bogies = new ArrayList<>();

        // Passenger bogies
        bogies.add(new PassengerBogie("P1", "Sleeper", 72));
        bogies.add(new PassengerBogie("P2", "AC Chair", 48));
        bogies.add(new PassengerBogie("P3", "First Class", 24));
        bogies.add(new PassengerBogie("P4", "Sleeper", 80));

        // Goods bogies
        bogies.add(new GoodsBogie("G1", "Coal"));
        bogies.add(new GoodsBogie("G2", "Oil"));
        bogies.add(new GoodsBogie("G3", "Grain"));

        TrainConsistService service = new TrainConsistService(bogies);

        // UC9: Grouping
        Map<String, List<Bogie>> grouped = service.groupBogiesByType();

        System.out.println("=== Grouped Bogies by Type ===");

        grouped.forEach((key, value) -> {
            System.out.println("\n" + key);
            value.forEach(System.out::println);
        });
    }
}