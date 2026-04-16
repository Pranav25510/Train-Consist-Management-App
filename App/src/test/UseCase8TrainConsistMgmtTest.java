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

    public int getSeatCapacity() {
        return seatCapacity;
    }

    @Override
    public String toString() {
        return "PassengerBogie{id='" + id + "', type='" + type +
                "', seatCapacity=" + seatCapacity + "}";
    }
}

// Goods Bogie (not used in seat calculation but part of system)
class GoodsBogie extends Bogie {
    String cargoType;

    public GoodsBogie(String id, String cargoType) {
        super(id);
        this.cargoType = cargoType;
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

    // UC10: Total seat calculation using reduce()
    public int getTotalSeatCapacity() {

        return bogies.stream()
                .filter(b -> b instanceof PassengerBogie)
                .map(b -> (PassengerBogie) b)
                .map(PassengerBogie::getSeatCapacity)
                .reduce(0, (a, b) -> a + b);
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

        TrainConsistService service = new TrainConsistService(bogies);

        System.out.println("=== Train Consist Summary ===");

        int totalSeats = service.getTotalSeatCapacity();

        System.out.println("Total Passenger Seat Capacity: " + totalSeats);
    }
}