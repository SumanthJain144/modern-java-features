package session1;

//Java 10
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class VarDemo {

    public static void main(String[] args) {
        System.out.println("Before -- ");
        beforeScenario();
        System.out.println("After -- ");
        afterScenario();
    }

    private static void beforeScenario() {
        Map<String, LocalDate> joiningDates = new HashMap<>();
        joiningDates.put("Alice", LocalDate.of(2020, 5, 10));
        joiningDates.put("Bob", LocalDate.of(2021, 3, 22));

        for (Map.Entry<String, LocalDate> entry : joiningDates.entrySet()) {
            String name = entry.getKey();
            LocalDate date = entry.getValue();
            System.out.println(name + " joined on " + date);
        }
    }

    private static Map<String, LocalDate> afterScenario() {
    	HashMap<String, LocalDate> joiningDates = new HashMap<String, LocalDate>();
        joiningDates.put("Alice", LocalDate.of(2020, 5, 10));
        joiningDates.put("Bob", LocalDate.of(2021, 3, 22));

        for (Map.Entry<String, LocalDate> entry : joiningDates.entrySet()) {
            String name = entry.getKey();
            LocalDate date = entry.getValue();
            System.out.println(name + " joined on " + date);
        }

        return joiningDates;
    }
}
