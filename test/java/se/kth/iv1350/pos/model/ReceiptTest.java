package test.java.se.kth.iv1350.pos.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pos.integration.*;
import se.kth.iv1350.pos.model.*;
import se.kth.iv1350.pos.util.Amount;

public class ReceiptTest {

    private Receipt receipt;
    private Sale sale;
    private CashPayment payment;

    @BeforeEach
    public void setUp() {
        sale = new Sale();
        InventorySystem inventorySystem = InventorySystem.getInstance();
        
        try {
            ItemDTO item = inventorySystem.findItem("11127"); 
            sale.addItemToSale(item, 2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    
        Amount paidAmount = new Amount(100);
        payment = new CashPayment(paidAmount, sale.getTotalPriceIncludingVAT());

        receipt = new Receipt(sale, payment);
    }

    @Test
    public void testReceiptIncludesBananaDetails() {
        String receiptString = receipt.toString();
        assertTrue(receiptString.contains("Apples x2"), "Receipt should include banana details.");
        assertTrue(receiptString.contains("Total Price (incl. VAT):"), "Receipt should include total price.");
        assertTrue(receiptString.contains("Amount Paid:"), "Receipt should include amount paid.");
        assertTrue(receiptString.contains("Change:"), "Receipt should include change.");
    }
}
