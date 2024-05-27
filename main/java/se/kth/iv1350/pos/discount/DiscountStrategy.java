package se.kth.iv1350.pos.discount;

import se.kth.iv1350.pos.model.Sale;
import se.kth.iv1350.pos.util.Amount;

/**
 * Interface for discount strategies.
 */
public interface DiscountStrategy {
    Amount calculateDiscount(Sale sale, String customerID);
}
