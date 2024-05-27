package se.kth.iv1350.pos.discount;

import se.kth.iv1350.pos.model.Sale;
import se.kth.iv1350.pos.util.Amount;

/**
 * Discount strategy based on total cost.
 */
public class TotalCostBasedDiscount implements DiscountStrategy {
    private final double discountPercentage;

    public TotalCostBasedDiscount(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    @Override
    public Amount calculateDiscount(Sale sale, String customerID) {
        int totalCost = sale.getTotalPriceIncludingVAT().getAmount();
        int discount = (int) (totalCost * discountPercentage);
        return new Amount(discount);
    }
}

