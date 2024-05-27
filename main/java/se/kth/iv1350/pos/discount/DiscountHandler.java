package se.kth.iv1350.pos.discount;

import se.kth.iv1350.pos.model.Sale;
import se.kth.iv1350.pos.util.Amount;
import java.util.HashMap;
import java.util.Map;

/**
 * This class handles discount calculations based on customer ID and sale details.
 */
public class DiscountHandler {
    private Map<String, DiscountStrategy> customerDiscounts;

    public DiscountHandler() {
        customerDiscounts = new HashMap<>();
        customerDiscounts.put("1234567890", new CustomerBasedDiscount(0.10)); // 10% discount for customer ID 1234567890
        customerDiscounts.put("9876543210", new CustomerBasedDiscount(0.15)); // 15% discount for customer ID 9876543210
    }

    public Amount getDiscountAmount(Sale sale, String customerID) {
        DiscountStrategy discountStrategy = customerDiscounts.getOrDefault(customerID, new NoDiscount());
        
        Amount totalDiscount = discountStrategy.calculateDiscount(sale, customerID);
        
        // Ensure discount does not exceed total price
        if (totalDiscount.getAmount() > sale.getTotalPriceIncludingVAT().getAmount()) {
            totalDiscount = sale.getTotalPriceIncludingVAT();
        }

        return totalDiscount;
    }

    public boolean isEligibleForDiscount(String customerID) {
        return customerDiscounts.containsKey(customerID);
    }
    
}
