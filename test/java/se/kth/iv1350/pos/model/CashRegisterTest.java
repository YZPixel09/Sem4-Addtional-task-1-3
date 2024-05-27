package test.java.se.kth.iv1350.pos.model;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import se.kth.iv1350.pos.util.Amount;
import se.kth.iv1350.pos.integration.ItemDTO;
import se.kth.iv1350.pos.model.CashPayment;
import se.kth.iv1350.pos.model.CashRegister;
import se.kth.iv1350.pos.model.Sale;

public class CashRegisterTest {
    private CashRegister cashRegister;
    private Sale sale;

    @BeforeEach
    public void setUp() {
        cashRegister = new CashRegister();
        sale = new Sale();

        
        sale.addItemToSale(new ItemDTO("Bread", 0.06, 20, "Fresh bread", "1001"), 2); 
        sale.addItemToSale(new ItemDTO("Milk", 0.02, 15, "Fresh Milk", "1002"), 3); 
    }

    @Test
    public void testAddPaymentProcessing() {
     
        Amount expectedTotal = sale.getTotalPriceIncludingVAT();
        Amount paidAmount = new Amount(100); 

        
        CashPayment payment = new CashPayment(paidAmount, expectedTotal);
        Amount change = cashRegister.addPayment(payment);
        
     
        double cashTotal = cashRegister.getTotalCash(); 
        assertEquals(expectedTotal.getAmount(), cashTotal, "Total cash should reflect the total cost of the payment");
        assertEquals(paidAmount.getAmount() - expectedTotal.getAmount(), change.getAmount(), "Change should be calculated as amount paid minus total cost");
    }

    @Test
    public void testPaymentWithZeroAmount() {
        Amount zeroAmount = new Amount(0);
        Amount totalCost = new Amount(50);
        CashPayment payment = new CashPayment(zeroAmount, totalCost);

        Amount change = cashRegister.addPayment(payment);
        assertEquals(-50, change.getAmount(), "Change should be negative when paid amount is zero.");
}

}
