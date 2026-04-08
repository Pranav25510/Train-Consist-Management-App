import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class TrainApp {

    // Regex Patterns
    private static final String TRAIN_ID_REGEX = "^TRN-\\d{4}$";
    private static final String CARGO_CODE_REGEX = "^[A-Z]{3}\\d{2}$";

    /**
     * UC11: Validates the Train ID format
     * @param trainID The ID to check
     * @return true if matches TRN-XXXX
     */
    public boolean isValidTrainID(String trainID) {
        return Pattern.matches(TRAIN_ID_REGEX, trainID);
    }

    /**
     * UC11: Validates the Cargo Code format
     * @param cargoCode The code to check
     * @return true if matches AAANN
     */
    public boolean isValidCargoCode(String cargoCode) {
        Pattern pattern = Pattern.compile(CARGO_CODE_REGEX);
        Matcher matcher = pattern.matcher(cargoCode);
        return matcher.matches();
    }

    public void processNewTrain(String id) {
        if (isValidTrainID(id)) {
            System.out.println("SUCCESS: Train [" + id + "] registered in the system.");
        } else {
            System.err.println("VALIDATION ERROR: Invalid Train ID format [" + id + "]. Expected: TRN-1234");
        }
    }

    public static void main(String[] args) {
        TrainApp app = new TrainApp();

        // Testing Train IDs
        System.out.println("--- Validating Train IDs ---");
        app.processNewTrain("TRN-1234"); // Valid
        app.processNewTrain("TRN1234");  // Invalid (missing hyphen)
        app.processNewTrain("TRN-12A3"); // Invalid (contains letter)

        // Testing Cargo Codes
        System.out.println("\n--- Validating Cargo Codes ---");
        String code1 = "OIL01";
        String code2 = "oil01";

        System.out.println(code1 + " is valid? " + app.isValidCargoCode(code1)); // true
        System.out.println(code2 + " is valid? " + app.isValidCargoCode(code2)); // false (lowercase)
    }
}