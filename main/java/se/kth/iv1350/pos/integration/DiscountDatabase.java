package se.kth.iv1350.pos.integration;

import se.kth.iv1350.pos.discount.DiscountStrategy;
import se.kth.iv1350.pos.discount.TotalCostBasedDiscount;
import se.kth.iv1350.pos.discount.CustomerBasedDiscount;

/**
 * Simulates a discount database.
 */
public class DiscountDatabase {
    public DiscountStrategy getDiscountStrategy(String customerID) {
        if ("9312209438".equals(customerID)) {
            return new CustomerBasedDiscount(0.4);
        } else if ("8304102472".equals(customerID)) {
            return new CustomerBasedDiscount(0.6);
        } else {
            return new TotalCostBasedDiscount(0.05); 
        }
    }
}
