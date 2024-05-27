package se.kth.iv1350.pos.integration;

/**
 * This exception is thrown when an item is not found in the inventory.
 */
public class ItemNotFoundException extends Exception {
    /**
     * Creates a new instance representing the condition described in the specified message.
     *
     * @param msg The message describing the exception.
     */
    public ItemNotFoundException(String msg) {
        super(msg);
    }
}
