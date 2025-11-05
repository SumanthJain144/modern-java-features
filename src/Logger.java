//multisource JAVA 22
public class Logger {
    private String logLevel;

    // Constructor to set the log level
    public Logger(String logLevel) {
        this.logLevel = logLevel;
    }

    // Log a message with the specified level
    public void log(String message) {
        System.out.println("[" + logLevel + "] " + message);
    }
}
