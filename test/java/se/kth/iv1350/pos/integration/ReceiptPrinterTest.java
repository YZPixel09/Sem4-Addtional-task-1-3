package test.java.se.kth.iv1350.pos.integration;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import se.kth.iv1350.pos.model.CashPayment;
import se.kth.iv1350.pos.model.Receipt;
import se.kth.iv1350.pos.model.Sale;
import se.kth.iv1350.pos.util.Amount;
import se.kth.iv1350.pos.integration.ItemDTO;
import se.kth.iv1350.pos.integration.ReceiptPrinter;

public class ReceiptPrinterTest {
    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;
    private ReceiptPrinter receiptPrinter;
    private Sale sale;
    private Receipt receipt;

    @BeforeEach
    public void setUp() {
        outContent = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outContent)); // Redirect System.out to capture print statements

        receiptPrinter = new ReceiptPrinter();
        sale = new Sale();
        sale.addItemToSale(new ItemDTO("Milk", 0.06, 20, "Fresh Milk", "12345"), 1);
        sale.addItemToSale(new ItemDTO("Bread", 0.06, 25, "Wholegrain Bread", "54321"), 2);
        CashPayment payment = new CashPayment(new Amount(100), sale.getTotalPriceIncludingVAT());
        receipt = new Receipt(sale, payment);
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut); // Restore System.out
        outContent.reset();
    }

    @Test
    public void testPrintReceiptHeader() {
        receiptPrinter.printReceipt(receipt);
        String printedContent = outContent.toString();
        assertTrue(printedContent.startsWith("---------- Receipt ----------\n"), "Receipt should start with the correct header.");
    }

    @Test
    public void testPrintReceiptItems() {
        receiptPrinter.printReceipt(receipt);
        String printedContent = outContent.toString();
        assertTrue(printedContent.contains("Milk x1 - 20.00 sek each"));
        assertTrue(printedContent.contains("Bread x2 - 25.00 sek each"));
    }

    @Test
    public void testPrintReceiptTotalDetails() {
        receiptPrinter.printReceipt(receipt);
        String printedContent = outContent.toString();
        double totalPrice = sale.getTotalPriceIncludingVAT().getAmount();
        double totalVAT = sale.getTotalVAT();
        assertTrue(printedContent.contains(String.format("Total Price (incl. VAT): %.2f sek", totalPrice)));
        assertTrue(printedContent.contains(String.format("Total VAT: %.2f sek", totalVAT)));
    }

    @Test
    public void testPrintReceiptPaymentDetails() {
        receiptPrinter.printReceipt(receipt);
        String printedContent = outContent.toString();
        double change = new Amount(100).minus(sale.getTotalPriceIncludingVAT()).getAmount();
        assertTrue(printedContent.contains("Amount Paid: 100.00 sek"));
        assertTrue(printedContent.contains(String.format("Change: %.2f sek", change)));
    }

    @Test
    public void testPrintReceiptFooter() {
        receiptPrinter.printReceipt(receipt);
        String printedContent = outContent.toString();
        assertTrue(printedContent.trim().endsWith("------------ End ------------"), "Receipt should end with the correct footer.");
    }
}
