package se.kth.iv1350.pos.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Singleton class responsible for logging exceptions to a file.
 */
public class FileLogHandler {
    private static final String LOG_FILE_NAME = "pos-application-log.txt";
    private static FileLogHandler instance;
    private PrintWriter logFile;

    /**
     * Private constructor to prevent external instantiation.
     * Opens or creates the log file for appending log messages.
     * @throws IOException If an I/O error occurs.
     */
    private FileLogHandler() throws IOException {
        logFile = new PrintWriter(new FileWriter(LOG_FILE_NAME, true));
        // Removed to avoid unnecessary user message
    }

    /**
     * Provides access to the singleton instance of FileLogHandler.
     * @return The single instance of FileLogHandler.
     * @throws IOException If an I/O error occurs.
     */
    public static FileLogHandler getInstance() throws IOException {
        if (instance == null) {
            instance = new FileLogHandler();
        }
        return instance;
    }

    /**
     * Logs the provided exception to the log file.
     * @param exception The exception to be logged.
     */
    public void logException(Exception exception) {
        logFile.println(createLogMessage(exception));
        logFile.flush();
        // Removed to avoid exposing log operations to the user
    }

    /**
     * Creates a formatted log message for the provided exception.
     * @param exception The exception to create a log message for.
     * @return A formatted log message string.
     */
    private String createLogMessage(Exception exception) {
        StringBuilder logMsgBuilder = new StringBuilder();
        logMsgBuilder.append("Exception occurred: ");
        logMsgBuilder.append(exception.getMessage());
        logMsgBuilder.append(System.lineSeparator());
        logMsgBuilder.append("Time: ");
        logMsgBuilder.append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        logMsgBuilder.append(System.lineSeparator());

        for (StackTraceElement stackTraceElement : exception.getStackTrace()) {
            logMsgBuilder.append(stackTraceElement.toString());
            logMsgBuilder.append(System.lineSeparator());
        }

        return logMsgBuilder.toString();
    }

    /**
     * Closes the log file if it is open.
     */
    public void closeLogFile() {
        if (logFile != null) {
            logFile.close();
        }
    }
}
