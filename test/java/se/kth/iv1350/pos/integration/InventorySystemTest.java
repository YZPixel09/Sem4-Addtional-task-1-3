package test.java.se.kth.iv1350.pos.integration;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.pos.integration.InventorySystem;
import se.kth.iv1350.pos.integration.ItemDTO;
import se.kth.iv1350.pos.integration.ItemNotFoundException;
import se.kth.iv1350.pos.integration.DatabaseUnavailableException;

public class InventorySystemTest {

    private InventorySystem inventorySystem;

    @BeforeEach
    public void setUp() {
        inventorySystem = InventorySystem.getInstance();
    }

    @Test
    public void testFindExistingItem() throws ItemNotFoundException, DatabaseUnavailableException {
        String expectedItemId = "11127"; // An actual existing item ID selected from your inventory initialization code
        ItemDTO resultItem = inventorySystem.findItem(expectedItemId);
        assertNotNull(resultItem, "The item should be found in the inventory");
        assertEquals(expectedItemId, resultItem.getItemIdentifier(), "The item ID should match the expected ID");
    }

    @Test
    public void testFindNonExistingItem() {
        String nonExistingItemId = "99999"; // An assumed non-existing item ID
        assertThrows(ItemNotFoundException.class, () -> {
            inventorySystem.findItem(nonExistingItemId);
        }, "ItemNotFoundException should be thrown for a non-existing item ID");
    }

    @Test
    public void testDatabaseUnavailable() {
        inventorySystem.setDatabaseUnavailable(true); // Simulate database unavailable
        String itemId = "11127"; // Any item ID

        assertThrows(DatabaseUnavailableException.class, () -> {
            inventorySystem.findItem(itemId);
        }, "DatabaseUnavailableException should be thrown when database is unavailable");

        inventorySystem.setDatabaseUnavailable(false); // Reset the database status
    }
}
