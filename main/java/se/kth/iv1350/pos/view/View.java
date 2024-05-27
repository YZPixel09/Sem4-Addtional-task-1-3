package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.integration.ItemDTO;
import se.kth.iv1350.pos.util.Amount;
import se.kth.iv1350.pos.util.FileLogHandler;
import se.kth.iv1350.pos.controller.OperationFailedException;
import java.io.IOException;

/**
 * This class represents the user interface for the retail store system.
 * It simulates user actions and error handling based on interaction with the Controller.
 */
public class View {
    private Controller controller;
    private ErrorMessageHandler errorMessageHandler;
    private FileLogHandler logger;

    /**
     * Creates a new instance, using the provided controller to manage operations.
     * 
     * @param contr The controller used for all system operations.
     * @param logger The logger for recording exceptions.
     * @throws IOException If an I/O error occurs.
     */
    public View(Controller contr, FileLogHandler logger) throws IOException {
        this.controller = contr;
        this.logger = logger;
        this.errorMessageHandler = ErrorMessageHandler.getInstance();
        
        // Initialize observers
        initializeObservers();
    }

    /**
     * Initializes and adds revenue and error observers to the controller.
     * 
     * @throws IOException If an I/O error occurs.
     */
    private void initializeObservers() throws IOException {
        TotalRevenueView revenueView = new TotalRevenueView();
        TotalRevenueFileOutput revenueFileOutput = new TotalRevenueFileOutput();
        controller.addSaleObserver(revenueView);
        controller.addSaleObserver(revenueFileOutput);
    }

    /**
     * Simulates various operations including handling exceptions during item registration
     * and payment process.
     */
    public void sampleExecution() {
        try {
            System.out.println("Cashier starts new sale.");
            controller.makeNewSale();
            System.out.println("Cashier enters items.");

            // Register items
            registerItem("11127", new Amount(5)); // Example: Apples
            registerItem("11123", new Amount(2)); // Example: Bread
            registerItem("11132", new Amount(1)); // Example: Chicken
            registerItem("11135", new Amount(3)); // Example: Tea
            registerItem("99999", new Amount(1)); // Invalid item: Should cause ItemNotFoundException

            // Process payment with customer ID for discount
            Amount amountPaid = new Amount(500);
            String customerID = "9782459000"; // Example customer ID with discount, invalid

            // Check if customer ID is eligible for discount
            if (!controller.isEligibleForDiscount(customerID)) {
                System.out.println("Customer ID is not eligible for discount.");
            }

            Amount change = controller.payment(amountPaid, customerID);
            System.out.println("Payment made. Change returned: " + change);
        } catch (Exception e) {
            errorMessageHandler.displayErrorMessage("An error occurred during the sale. Please check the logs for more details.");
            logger.logException(e);
        }
    }

    /**
     * Helper method to register an item and handle possible exceptions.
     * 
     * @param itemIdentifier The item's identifier.
     * @param quantity The quantity of the item.
     */
    private void registerItem(String itemIdentifier, Amount quantity) {
        try {
            ItemDTO item = controller.enterItem(itemIdentifier, quantity);
            System.out.println("Registered item: " + item + ".");
        } catch (OperationFailedException e) {
            errorMessageHandler.displayErrorMessage("Error processing item. Please check the logs for more details.");
            logger.logException(e);  
        } catch (Exception e) {
            errorMessageHandler.displayErrorMessage("An unexpected error occurred. Please check the logs for more details.");
            logger.logException(e);  
        }
    }
}
