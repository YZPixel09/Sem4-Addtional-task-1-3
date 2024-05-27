package se.kth.iv1350.pos.discount;

import se.kth.iv1350.pos.integration.Item;
import se.kth.iv1350.pos.model.Sale;
import se.kth.iv1350.pos.util.Amount;

import java.util.List;

/**
 * Discount strategy based on purchased items.
 */
public class ItemBasedDiscount implements DiscountStrategy {
    private final int discountPerItem;

    public ItemBasedDiscount(int discountPerItem) {
        this.discountPerItem = discountPerItem;
    }

    @Override
    public Amount calculateDiscount(Sale sale, String customerID) {
        List<Item> items = sale.getItems();
        int discount = 0;
        for (Item item : items) {
            discount += discountPerItem * item.getQuantity();
        }
        return new Amount(discount);
    }
}
