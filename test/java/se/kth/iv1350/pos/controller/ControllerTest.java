package test.java.se.kth.iv1350.pos.controller;

import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.controller.OperationFailedException;
import se.kth.iv1350.pos.integration.*;
import se.kth.iv1350.pos.model.*;
import se.kth.iv1350.pos.util.*;
import se.kth.iv1350.pos.discount.DiscountHandler;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {
    private SystemCreator systemCreator;
    private CashRegister cashRegister;
    private ReceiptPrinter printer;
    private FileLogHandler logger;
    private DiscountHandler discountHandler;
    private Controller controller;

    @BeforeEach
    public void setUp() throws Exception {
        systemCreator = new SystemCreator();
        printer = new ReceiptPrinter();
        logger = FileLogHandler.getInstance();
        cashRegister = new CashRegister();
        discountHandler = new DiscountHandler();
        controller = new Controller(systemCreator, cashRegister, printer, logger, discountHandler);
    }

    @Test
    public void testInitiateSale() {
        try {
            controller.enterItem("1", new Amount(1));
            fail("Should have thrown an exception");
        } catch (Exception e) {
            assertTrue(true);
        }

        controller.makeNewSale();

        try {
            controller.enterItem("1", new Amount(1));
            assertTrue(true);
        } catch (Exception e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testEnterItem() {
        controller.makeNewSale();
        try {
            controller.enterItem("1", new Amount(1));
            assertTrue(true);
        } catch (Exception e) {
            fail("Exception should not have been thrown");
        }

        try {
            controller.enterItem("11135", new Amount(5)); // Assuming "11135" is a valid item ID
            assertTrue(true);
        } catch (Exception e) {
            fail("Exception should not have been thrown");
        }
    }


    @Test
    public void testExceptionHandling() {
        controller.makeNewSale();
        try {
            controller.enterItem("invalidID", new Amount(1));
            fail("Should have thrown an exception");
        } catch (ItemNotFoundException e) {
            assertTrue(true);
        } catch (Exception e) {
            fail("Unexpected exception type");
        }

        try {
            systemCreator.getInventorySystem().setDatabaseUnavailable(true);
            controller.enterItem("simulateDBFail", new Amount(1));
            fail("Should have thrown an exception");
        } catch (OperationFailedException e) {
            assertTrue(true);
        } catch (Exception e) {
            fail("Unexpected exception type");
        } finally {
            systemCreator.getInventorySystem().setDatabaseUnavailable(false);
        }
    }
}
