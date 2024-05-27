package se.kth.iv1350.pos.discount;

import se.kth.iv1350.pos.model.Sale;
import se.kth.iv1350.pos.util.Amount;

/**
 * Discount strategy based on customer ID.
 */
public class CustomerBasedDiscount implements DiscountStrategy {
    private final double discountPercentage;

    public CustomerBasedDiscount(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    @Override
    public Amount calculateDiscount(Sale sale, String customerID) {
        double totalCost = sale.getTotalPriceIncludingVAT().getAmount();
        double discount = totalCost * discountPercentage;
        return new Amount((int) discount);
    }

    
}
