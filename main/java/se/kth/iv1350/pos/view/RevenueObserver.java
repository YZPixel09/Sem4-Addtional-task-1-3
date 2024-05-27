package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.model.SaleObserver;

/**
 * A template for observers that handle revenue updates.
 */
public abstract class RevenueObserver implements SaleObserver {
    private double totalRevenue;

    public RevenueObserver() {
        totalRevenue = 0;
    }

    @Override
    public void newSale(double priceOfPurchase) {
        totalRevenue += priceOfPurchase;
        showTotalRevenue();
    }

    private void showTotalRevenue() {
        try {
            doShowTotalRevenue(totalRevenue);
        } catch (Exception e) {
            handleErrors(e);
        }
    }

    protected abstract void doShowTotalRevenue(double totalRevenue) throws Exception;

    protected abstract void handleErrors(Exception e);
}
