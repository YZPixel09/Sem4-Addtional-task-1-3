package test.java.se.kth.iv1350.pos.view;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pos.view.TotalRevenueView;

import static org.junit.jupiter.api.Assertions.*;

public class TotalRevenueViewTest {

    private TotalRevenueView totalRevenueView;
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        totalRevenueView = new TotalRevenueView();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void tearDown() {
        totalRevenueView = null;
        System.setOut(originalOut);
    }

    @Test
    public void testNewSale() {
        double saleAmount = 100.50;
        totalRevenueView.newSale(saleAmount);
        String output = outContent.toString().trim();
        String expectedRevenuePart = "is: " + saleAmount;
        assertTrue(output.contains(expectedRevenuePart), "The total revenue is not correctly printed to system.out");
    }

    @Test
    public void testMultipleSales() {
        double saleAmount1 = 100.50;
        double saleAmount2 = 200.75;
        double expectedTotal = saleAmount1 + saleAmount2;
        
        totalRevenueView.newSale(saleAmount1);
        totalRevenueView.newSale(saleAmount2);
        
        String output = outContent.toString().trim();
        String expectedRevenuePart = "is: " + expectedTotal;
        assertTrue(output.contains(expectedRevenuePart), "The total revenue after multiple sales is not correctly printed to system.out");
    }
}
