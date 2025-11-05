package session2;





public class PatternMatchingPrimitive {

    // Before Java 24: Traditional instanceof checks with explicit casting
    public static void processTemperatureBefore(Object input) {
        System.out.println("Before: Processing input:");

        // Traditional instanceof checks with casting
        if (input instanceof Integer) {
            int temperature = (Integer) input;  // Cast to Integer
            System.out.println("Temperature (Integer): " + temperature + "°C");

            if (temperature < 0) {
                System.out.println("Warning: Freezing temperature!");
            } else if (temperature > 30) {
                System.out.println("Warning: High temperature!");
            } else {
                System.out.println("Temperature is within normal range.");
            }
        } else if (input instanceof Double) {
            double temperature = (Double) input;  // Cast to Double
            System.out.println("Temperature (Double): " + temperature + "°C");

            if (temperature < 0) {
                System.out.println("Warning: Freezing temperature!");
            } else if (temperature > 30) {
                System.out.println("Warning: High temperature!");
            } else {
                System.out.println("Temperature is within normal range.");
            }
        } else if (input instanceof String) {
            String message = (String) input;  // Cast to String
            System.out.println("System message: " + message);
            if (message.contains("alert")) {
                System.out.println("System Alert: Please check the temperature readings!");
            } else {
                System.out.println("Status: " + message);
            }
        } else {
            System.out.println("Unknown input type.");
        }

        System.out.println();
    }

    // After Java 24: Pattern matching for primitives (preview)
    @SuppressWarnings("preview")
    public static void processTemperatureAfter(Object input) {
        System.out.println("After: Processing input:");

        // Pattern matching for primitives
        if (input instanceof Integer temperature) {
            // Handle integer temperature (whole number)
            System.out.println("Temperature (Integer): " + temperature + "°C");
            if (temperature < 0) {
                System.out.println("Warning: Freezing temperature!");
            } else if (temperature > 30) {
                System.out.println("Warning: High temperature!");
            } else {
                System.out.println("Temperature is within normal range.");
            }
        } else if (input instanceof Double temperature) {
            // Handle double temperature (precise reading)
            System.out.println("Temperature (Double): " + temperature + "°C");
            if (temperature < 0) {
                System.out.println("Warning: Freezing temperature!");
            } else if (temperature > 30) {
                System.out.println("Warning: High temperature!");
            } else {
                System.out.println("Temperature is within normal range.");
            }
        } else if (input instanceof String message) {
            // Handle string message (status update or alert)
            System.out.println("System message: " + message);
            if (message.contains("alert")) {
                System.out.println("System Alert: Please check the temperature readings!");
            } else {
                System.out.println("Status: " + message);
            }
        } else {
            System.out.println("Unknown input type.");
        }

        System.out.println();
    }

    public static void main(String[] args) {
        // Example input
        Object temp1 = 25;           // Integer temperature
        Object temp2 = 4.5;          // Double temperature
        Object statusMessage = "System alert: High temperature detected"; // String message

        // Before Java 24
        processTemperatureBefore(temp1);   // Process Integer temperature
        processTemperatureBefore(temp2);   // Process Double temperature
        processTemperatureBefore(statusMessage);  // Process String message

        // After Java 24 (with Pattern Matching)
        processTemperatureAfter(temp1);    // Process Integer temperature
        processTemperatureAfter(temp2);    // Process Double temperature
        processTemperatureAfter(statusMessage);  // Process String message
    }
}


//Conciseness: It eliminates the need for manual casting after type checks, making code shorter and cleaner.
//
//Safety: It prevents ClassCastException by automatically binding variables to their correct types.
//
//Readability: The code is more readable, as type checking and variable binding happen in one step.
//
//Maintainability: It reduces boilerplate code, making future changes easier and less error-prone.
