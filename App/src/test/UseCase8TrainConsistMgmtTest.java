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

// Passenger Bogie types
class PassengerBogie extends Bogie {
    String type; // Sleeper, AC Chair, First Class
    int seatCapacity;

    public PassengerBogie(String id, String type, int seatCapacity) {
        super(id);
        this.type = type;
        this.seatCapacity = seatCapacity;
    }

    public int getSeatCapacity() {
        return seatCapacity;
    }

    @Override
    public String toString() {
        return "PassengerBogie{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", seatCapacity=" + seatCapacity +
                '}';
    }
}

// Goods Bogie (not used in filtering, but part of system)
class GoodsBogie extends Bogie {
    String cargoType;
    double weightCapacity;

    public GoodsBogie(String id, String cargoType, double weightCapacity) {
        super(id);
        this.cargoType = cargoType;
        this.weightCapacity = weightCapacity;
    }

    @Override
    public String toString() {
        return "GoodsBogie{" +
                "id='" + id + '\'' +
                ", cargoType='" + cargoType + '\'' +
                ", weightCapacity=" + weightCapacity +
                '}';
    }
}

// Service class for train consist operations
class TrainConsistService {

    private List<Bogie> bogies;

    public TrainConsistService(List<Bogie> bogies) {
        this.bogies = bogies;
    }

    // UC8: Stream filtering passenger bogies
    public List<PassengerBogie> filterHighCapacityPassengerBogies(int minSeats) {

        return bogies.stream()
                .filter(b -> b instanceof PassengerBogie)
                .map(b -> (PassengerBogie) b)
                .filter(p -> p.getSeatCapacity() >= minSeats)
                .collect(Collectors.toList());
    }
}

// Main Test Class
public class UseCase8TrainConsistMgmtTest {

    public static void main(String[] args) {

        // Create bogies
        List<Bogie> bogies = new ArrayList<>();

        bogies.add(new PassengerBogie("P1", "Sleeper", 72));
        bogies.add(new PassengerBogie("P2", "AC Chair", 48));
        bogies.add(new PassengerBogie("P3", "First Class", 24));
        bogies.add(new PassengerBogie("P4", "Sleeper", 80));

        bogies.add(new GoodsBogie("G1", "Coal", 5000));
        bogies.add(new GoodsBogie("G2", "Oil", 3000));

        // Service
        TrainConsistService service = new TrainConsistService(bogies);

        // UC8: Filter passenger bogies with capacity >= 50
        List<PassengerBogie> result = service.filterHighCapacityPassengerBogies(50);

        // Output
        System.out.println("=== High Capacity Passenger Bogies (>= 50 seats) ===");
        result.forEach(System.out::println);
    }
}