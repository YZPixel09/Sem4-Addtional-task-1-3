package se.kth.iv1350.pos.startup;

import java.io.IOException;

import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.integration.*;
import se.kth.iv1350.pos.model.CashRegister;
import se.kth.iv1350.pos.view.View;
import se.kth.iv1350.pos.util.FileLogHandler;
import se.kth.iv1350.pos.discount.DiscountHandler;

public class Main {
    public static void main(String[] args) {
        SystemCreator systemCreator = new SystemCreator();
        ReceiptPrinter printer = new ReceiptPrinter();
        CashRegister cashRegister = new CashRegister();
        DiscountHandler discountHandler = new DiscountHandler();

        FileLogHandler logHandler = null;
        try {
            logHandler = FileLogHandler.getInstance();
        } catch (IOException e) {
            System.err.println("Failed to initialize log handler.");
            e.printStackTrace();
            System.exit(1);
        }

        try {
            Controller controller = new Controller(systemCreator, cashRegister, printer, logHandler, discountHandler);
            View view = new View(controller, logHandler);
            view.sampleExecution();
        } catch (IOException e) {
            System.err.println("Failed to initialize view.");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
