package test.java.se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.integration.*;
import se.kth.iv1350.pos.controller.*;
import se.kth.iv1350.pos.util.FileLogHandler;
import se.kth.iv1350.pos.discount.DiscountHandler;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.pos.model.CashRegister;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;

import java.io.PrintStream;

import se.kth.iv1350.pos.view.View;
import se.kth.iv1350.pos.view.TotalRevenueFileOutput;

public class ViewTest {
    private View view;
    private ByteArrayOutputStream printoutBuffer;
    private PrintStream originalSysOut;
    private FileLogHandler logHandler;
    private TotalRevenueFileOutput revenueFileOutput;
    private DiscountHandler discountHandler;

    @BeforeEach
    public void setUp() throws Exception {
        SystemCreator systemCreator = new SystemCreator();
        ReceiptPrinter printer = new ReceiptPrinter();
        logHandler = FileLogHandler.getInstance();
        CashRegister cashRegister = new CashRegister();
        discountHandler = new DiscountHandler();
        Controller contr = new Controller(systemCreator, cashRegister, printer, logHandler, discountHandler);

        view = new View(contr, logHandler);

        printoutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printoutBuffer);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);
    }

    @AfterEach
    public void tearDown() {
        if (revenueFileOutput != null) {
            revenueFileOutput.closeLogFile();
        }
        System.setOut(originalSysOut);
    }
    
    @Test
    public void testSampleExecution() {
        System.out.println("sampleExecution");
        view.sampleExecution();
        String printout = printoutBuffer.toString().trim();
    
        System.out.println("Actual output:\n" + printout);
    
        assertTrue(printout.contains("Cashier starts new sale."), "UI did not start correctly.");
        assertTrue(printout.contains("Cashier enters items."), "Items were not entered correctly.");
    }
}
