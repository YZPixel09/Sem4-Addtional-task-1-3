package se.kth.iv1350.pos.integration;

import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.pos.model.Sale;

/**
 * Represents an external inventory system that holds items available for sale.
 * Implemented as a singleton to ensure a single shared instance across the application.
 */
public class InventorySystem {
    private static InventorySystem instance;
    private List<ItemDTO> inventory = new ArrayList<>();
    private boolean databaseIsDown = false;  

/**
 * Private constructor to prevent external instantiation of the InventorySystem class.
 * Initializes the system by adding default items.
 */
private InventorySystem() {
    addItems();
}


    /**
     * Provides access to the singleton instance of the InventorySystem.
     * @return the singleton instance of InventorySystem
     */
    public static InventorySystem getInstance() {
        if (instance == null) {
            synchronized (InventorySystem.class) {
                if (instance == null) {
                    instance = new InventorySystem();
                }
            }
        }
        return instance;
    }

    /**
     * Sets the database unavailable status for testing purposes.
     * @param unavailable true to simulate database unavailable, false otherwise.
     */
    public void setDatabaseUnavailable(boolean unavailable) {
        this.databaseIsDown = unavailable;
    }

    /**
     * Matches an ItemDTO identifier with all its available data.
     * @param itemId a proto-item, all values null except for identifier and quantity.
     * @return a completed ItemDTO matched to the given identifier.
     * @throws DatabaseUnavailableException if a connection to the inventory database cannot be established.
     * @throws ItemNotFoundException if the identifier does not match any item in the inventory.
     */
    public ItemDTO findItem(String itemId) throws ItemNotFoundException, DatabaseUnavailableException {
        verifyDatabaseConnection();

        for (ItemDTO item : inventory) {
            if (item.getItemIdentifier().equals(itemId)) {
                return item;
            }
        }

        throw new ItemNotFoundException("Item with ID: " + itemId + " not found in inventory.");
    }

    /**
     * Simulates a database connection to demonstrate handling connection issues.
     * @throws DatabaseUnavailableException if the database cannot be reached.
     */
    private void verifyDatabaseConnection() throws DatabaseUnavailableException {
        if (databaseIsDown) {
            throw new DatabaseUnavailableException("Failed to connect to the database.");
        }
    }

    /**
     * The function is a placeholder for when the system updates the inventory in the external system.
     * @param sale an object holding the information about the current sale.
     */
    public void updateInventory(Sale sale){
        System.out.println("Items logged, Inventory system updated");
    }

    /**
     * Adds sample items to the inventory. This simulates loading items from an external source.
     */
    private void addItems() {
        inventory.add(new ItemDTO("Apples", 0.06, 23, "Fresh apples", "11127"));
        inventory.add(new ItemDTO("Bread", 0.06, 28, "Whole wheat bread", "11123"));
        inventory.add(new ItemDTO("Chicken", 0.06, 150, "Free range chicken", "11132"));
        inventory.add(new ItemDTO("Tea", 0.06, 30, "Green tea", "11135"));
        // Add test item with ID "1"
        inventory.add(new ItemDTO("Test Item", 0.06, 10, "Item for testing", "1"));
    }
}
