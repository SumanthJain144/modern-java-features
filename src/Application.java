//multisource JAVA 22
public class Application {
    public static void main(String[] args) {
        // Creating Logger instances with different log levels
        Logger infoLogger = new Logger(LogLevel.INFO.name());
        Logger errorLogger = new Logger(LogLevel.ERROR.name());
        
        // Using the loggers to log messages
        infoLogger.log("This is an info message.");
        errorLogger.log("This is an error message.");
    }
}
