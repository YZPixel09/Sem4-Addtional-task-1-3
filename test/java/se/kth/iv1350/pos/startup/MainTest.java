package test.java.se.kth.iv1350.pos.startup;

import org.junit.Test;
import se.kth.iv1350.pos.startup.Main;
import static org.junit.Assert.assertTrue;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;

/**
 * Unit test for Main class
 */
public class MainTest {
    private ByteArrayOutputStream printoutBuffer;
    private PrintStream originalSysOut;

    @Before
    public void setUp() {
        printoutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printoutBuffer);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);
    }

    @After
    public void tearDown() {
        printoutBuffer = null;
        System.setOut(originalSysOut);
    }

    @Test
    public void testMain() {
        String[] args = null;
        Main.main(args);
        String printout = printoutBuffer.toString().trim();
        System.out.println("Actual output: " + printout);
        assertTrue("Payment was not processed correctly.", printout.contains("Payment made. Change returned:"));
    }
}
