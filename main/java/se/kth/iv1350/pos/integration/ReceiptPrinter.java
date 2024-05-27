
package se.kth.iv1350.pos.integration;

import se.kth.iv1350.pos.model.Receipt;

/**
 * A class that is a placeholder for the Receipt printer. 
 */

 public class ReceiptPrinter {
    /**
     * Prints out the receipt of the current sale to the customer.
     * @param receiptString a formatted string containing the receipt information
     * @return a message indicating that the receipt has been printed
     */
    public void printReceipt(Receipt receipt){
        System.out.println(receipt.toString());
    }
}

