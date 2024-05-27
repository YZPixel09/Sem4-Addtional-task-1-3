package se.kth.iv1350.pos.controller;

import se.kth.iv1350.pos.integration.*;
import se.kth.iv1350.pos.model.*;
import se.kth.iv1350.pos.util.Amount;
import se.kth.iv1350.pos.util.FileLogHandler;
import se.kth.iv1350.pos.discount.DiscountHandler;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the application's only controller.
 * All calls passing to the model go through this class.
 */
public class Controller {
    private SystemCreator systemCreator;
    public Sale sale;
    private CashRegister cashRegister;
    private ReceiptPrinter printer;
    private FileLogHandler logger;
    private DiscountHandler discountHandler;
    private List<SaleObserver> saleObservers = new ArrayList<>();

    /**
     * Creates a new instance of the Controller class.
     * Initializes the necessary system components.
     *
     * @param systemCreator   The creator of external systems.
     * @param cashRegister    The cash register used for the sale.
     * @param printer         The printer used to print receipts.
     * @param logger          The logger for logging exceptions.
     * @param discountHandler The handler for applying discounts.
     */
    public Controller(SystemCreator systemCreator, CashRegister cashRegister, ReceiptPrinter printer, FileLogHandler logger, DiscountHandler discountHandler) {
        this.systemCreator = systemCreator;
        systemCreator.getAccountingSystem();
        systemCreator.getInventorySystem();
        this.cashRegister = cashRegister;
        this.printer = printer;
        this.logger = logger;
        this.discountHandler = discountHandler;
    }

    /**
     * Initiates a new sale.
     * Sets the SystemCreator for the sale and adds the sale observers.
     */
    public void makeNewSale() {
        this.sale = new Sale();
        sale.setSystemCreator(systemCreator); // Ensure the SystemCreator is set for the sale
        sale.addSaleObservers(saleObservers);  // Add observers to the sale
    }

    /**
     * Registers an item in the current sale.
     *
     * @param itemIdentifier The identifier of the item.
     * @param quantity       The quantity of the item.
     * @return The registered item.
     * @throws ItemNotFoundException     If the item is not found.
     * @throws OperationFailedException If an unexpected error occurs.
     */
    public ItemDTO enterItem(String itemIdentifier, Amount quantity) throws ItemNotFoundException, OperationFailedException {
        try {
            ItemDTO foundItem = systemCreator.getInventorySystem().findItem(itemIdentifier);
            if (foundItem != null) {
                sale.addItemToSale(foundItem, (int) quantity.getAmount());
                return foundItem;
            } else {
                throw new ItemNotFoundException("Item with identifier: " + itemIdentifier + " not found, please try again.");
            }
        } catch (DatabaseUnavailableException dataExc) {
            logger.logException(dataExc);  // Ensure it is logged once
            throw new OperationFailedException("Inventory system server is down.", dataExc);
        } catch (ItemNotFoundException itemNotFoundExc) {
            logger.logException(itemNotFoundExc);  // Ensure it is logged once
            throw itemNotFoundExc;
        } catch (Exception exp) {
            logger.logException(exp);  // Ensure it is logged once
            throw new OperationFailedException("An unexpected error occurred while processing the item.", exp);
        }
    }

    /**
     * Processes the payment for the sale.
     *
     * @param paidAmount The amount paid by the customer.
     * @param customerID The customer ID for applying discounts.
     * @return The change to be given back to the customer.
     */
    public Amount payment(Amount paidAmount, String customerID) {
        if (customerID == null || customerID.isEmpty()) {
            return sale.paymentWithoutDiscount(paidAmount, cashRegister, printer);
        } else {
            return sale.paymentWithDiscount(paidAmount, customerID, cashRegister, discountHandler, printer);
        }
    }

    public boolean isEligibleForDiscount(String customerID) {
        return discountHandler.isEligibleForDiscount(customerID);
    }
    

    /**
     * Adds a sale observer to be notified when the sale is completed.
     *
     * @param observer The observer to add.
     */
    public void addSaleObserver(SaleObserver observer) {
        saleObservers.add(observer);
    }
}
