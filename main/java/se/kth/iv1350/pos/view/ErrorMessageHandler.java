package se.kth.iv1350.pos.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * This class shows the error messages to the user.
 */
public class ErrorMessageHandler {
    private static final ErrorMessageHandler INSTANCE = new ErrorMessageHandler();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.US);

    /**
     * Private constructor to prevent external instantiation.
     */
    private ErrorMessageHandler() {}

    /**
     * Provides access to the singleton instance of ErrorMessageHandler.
     * 
     * @return The single instance of ErrorMessageHandler.
     */
    public static ErrorMessageHandler getInstance() {
        return INSTANCE;
    }

    /**
     * Formats the current date and time as a string.
     * 
     * @return Formatted current date and time.
     */
    private String getCurrentDateTime() {
        return LocalDateTime.now().format(formatter);
    }

    /**
     * Displays an error message with a timestamp and a predefined format.
     * 
     * @param message The error message to be displayed.
     */
    public void displayErrorMessage(String message) {
        System.out.println("====== ERROR ======");
        System.out.println(getCurrentDateTime());
        System.out.println("ERROR: " + message);
        System.out.println("===================");
    }
}
