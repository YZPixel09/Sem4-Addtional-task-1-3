package se.kth.iv1350.pos.model;

/**
 * A listener interface for receiving notifications about sales.
 */
public interface SaleObserver {
    /**
     * Invoked when a new sale is completed.
     * 
     * @param priceOfPurchase The total price of the completed sale.
     */
    void newSale(double priceOfPurchase);
}
