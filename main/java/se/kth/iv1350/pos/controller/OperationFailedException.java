package se.kth.iv1350.pos.controller;

/**
 * Thrown when an operation fails, with the reason unknown.
 */
public class OperationFailedException extends Exception {
    
    /**
    * Creates an instance of <code>CatalogException</code> with a given message.
    *
    * @param message Information about the reason why the exception is thrown.
    */
    public OperationFailedException(String message, Exception exception) {
        super(message, exception);
    }
}