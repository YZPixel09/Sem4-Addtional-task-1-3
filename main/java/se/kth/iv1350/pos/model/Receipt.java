package se.kth.iv1350.pos.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import se.kth.iv1350.pos.integration.Item;
import se.kth.iv1350.pos.util.Amount;

/**
 * Represents a receipt for a sale transaction.
 */
public class Receipt {
    private final List<Item> listOfItems;
    private final Amount totalPrice;
    private final double totalVAT;
    private final Amount amountPaid;
    private final Amount change;
    private final Amount discountAmount;
    private String time;

    /**
     * Constructs a receipt with details about a sale and payment.
     * @param sale holding information about a Sale.
     * @param payment an object holding information about the payment of the sale.
     */
    public Receipt(Sale sale, CashPayment payment) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.time = LocalDateTime.now().format(formatter);
        this.listOfItems = sale.getItems();
        this.totalPrice = sale.getTotalPriceIncludingVAT();
        this.amountPaid = payment.getAmountPaid();
        this.totalVAT = sale.getTotalVAT();
        this.change = payment.getChange();
        this.discountAmount = sale.getTotalPriceIncludingVAT();
    }

    /**
     * Creates a formatted string of the receipt. This method uses helper functions
     * to improve readability and maintainability.
     *
     * @return formatted receipt string
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        appendLine(builder, "---------- Receipt ----------");
        appendLine(builder, "Time: " + time);
        for (Item item : listOfItems) {
            appendLine(builder, item.getName() + " x" + item.getQuantity() + " - " + String.format("%.2f", (double) item.getPrice()) + " sek each");
        }
        appendLine(builder, "Total Price (incl. VAT): " + String.format("%.2f", (double) totalPrice.getAmount()) + " sek");
        appendLine(builder, "Total VAT: " + String.format("%.2f", totalVAT) + " sek");
        if (discountAmount.getAmount() > 0) {
            appendLine(builder, "Discount applied: "  + String.format("%.2f", (double) (totalPrice.getAmount() - discountAmount.getAmount())) + " sek");
            appendLine(builder, "Total Price after discount (incl. VAT): " + String.format("%.2f", (double) discountAmount.getAmount()) + " sek");
        }
        appendLine(builder, "Amount Paid: " + String.format("%.2f", (double) amountPaid.getAmount()) + " sek");
        appendLine(builder, "Change: " + String.format("%.2f", (double) change.getAmount()) + " sek");
        appendLine(builder, "------------ End ------------");
        return builder.toString();
    }

    /**
     * Appends a line to the StringBuilder and adds a newline character.
     * This helps to keep the string building logic consistent and easy to modify.
     *
     * @param builder the StringBuilder to append to
     * @param line the line to append
     */
    private void appendLine(StringBuilder builder, String line) {
        builder.append(line);
        builder.append("\n");
    }
}
