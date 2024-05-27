package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.util.Amount;

/**
 * Represents a payment of a specific sale.
 */
public class CashPayment {
    private Amount amountPaid;
    private Amount totalCost;

    /**
     * Creates a new instance, represented as a payment.
     *
     * @param amountPaid The money amount received from the customer.
     * @param totalCost The total that will be paid by the customer.
     */
    public CashPayment(Amount amountPaid, Amount totalCost){
        this.amountPaid = amountPaid;
        this.totalCost = totalCost;
    }

    /**
     * Get the amount paid by the customer.
     *
     * @return The amount paid.
     */
    public Amount getAmountPaid() {
        return amountPaid;
    }

    /**
     * Get the total cost of the payment.
     *
     * @return The total cost.
     */
    public Amount getTotalCost() {
        return totalCost;
    }

    /**
     * Computes the change due to the customer by subtracting the total cost from the paid amount.
     * 
     * @return The change due to the customer as an Amount.
     */
    public Amount getChange() {
        return amountPaid.minus(totalCost);
    }
}
