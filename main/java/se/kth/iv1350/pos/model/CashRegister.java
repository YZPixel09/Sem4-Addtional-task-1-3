package se.kth.iv1350.pos.model;
import se.kth.iv1350.pos.util.Amount;

/**
 * * A class that represents the cash register. 
 *  It can process payments made to the register, updating the total cash held, and returning the change due to the customer.
 */
public class CashRegister {
    private double totalCash;
    
    
   /**
     * Constructs a new cash register with an initial amount of cash set to zero.
     */
    public CashRegister() {
        this.totalCash = 0;
    }
     /**
     * Processes a payment received from a customer, adding the payment to the register's total cash.
     * @param payment The payment object containing the amount of money to be added to the register.
     * @return The amount of change to be given back to the customer.
     */
    public Amount addPayment(CashPayment payment) {
        double paidAmount = payment.getTotalCost().getAmount();
        this.totalCash += paidAmount;
        return payment.getChange();
    }
    
    /**
     * Returns the total amount of cash currently held in the cash register.
     * @return The total cash in the cash register.
     */
    public double getTotalCash() {
        return totalCash;
    }

}
