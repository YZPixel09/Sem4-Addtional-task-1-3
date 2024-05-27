package se.kth.iv1350.pos.discount;

import se.kth.iv1350.pos.model.Sale;
import se.kth.iv1350.pos.util.Amount;

/**
 * Default no discount strategy.
 */
public class NoDiscount implements DiscountStrategy {
    @Override
    public Amount calculateDiscount(Sale sale, String customerID) {
        return new Amount(0); // No discount applied
    }
}
