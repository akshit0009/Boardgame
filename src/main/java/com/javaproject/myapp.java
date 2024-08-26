import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
public class MyApp {
 
    // Create a logger instance
    private static final Logger logger = LoggerFactory.getLogger(MyApp.class);
 
    public static void main(String[] args) {
        // Example of logging at different levels
        logger.info("This is an info message");
        logger.debug("This is a debug message");
        logger.warn("This is a warning message");
        logger.error("This is an error message");
 
        // Your application logic
        System.out.println("Hello, World!");
    }
}