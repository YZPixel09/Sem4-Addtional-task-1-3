package se.kth.iv1350.pos.integration;

/**
 * This class is responsible for creating and providing access to the external systems like
 * Accounting and Inventory systems.
 */
public class SystemCreator {
    private AccountingSystem accountingSystem;
    private InventorySystem inventorySystem;

    /**
     * Initializes a new instance of SystemCreator, creating new instances of accounting
     * and inventory systems.
     */
    public SystemCreator() {
        accountingSystem = new AccountingSystem();
        inventorySystem = InventorySystem.getInstance();
    }

    /**
     * Provides access to the accounting system.
     *
     * @return the accounting system instance
     */
    public AccountingSystem getAccountingSystem() {
        return this.accountingSystem;
    }

    /**
     * Provides access to the inventory system.
     *
     * @return the inventory system instance
     */
    public InventorySystem getInventorySystem() {
        return this.inventorySystem;
    }
}

