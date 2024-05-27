package se.kth.iv1350.pos.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * TotalRevenueView writes to the console when notified of a sale completion.
 */
public class TotalRevenueView extends RevenueObserver {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.US);

    /**
     * Constructs a new instance of TotalRevenueView.
     * Initializes the total revenue to zero.
     */
    public TotalRevenueView() {
        super();
    }

    @Override
    protected void doShowTotalRevenue(double totalRevenue) {
        String currentTime = LocalDateTime.now().format(formatter);
        System.out.println("Total revenue since the program started at " + currentTime + " is: " + totalRevenue);
    }

    @Override
    protected void handleErrors(Exception e) {
        System.err.println("Could not display total revenue. Reason: " + e.getMessage());
    }
}
