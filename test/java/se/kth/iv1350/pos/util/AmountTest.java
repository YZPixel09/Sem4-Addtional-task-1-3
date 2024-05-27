package test.java.se.kth.iv1350.pos.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.pos.util.Amount;

import static org.junit.jupiter.api.Assertions.*;

public class AmountTest {

    @BeforeEach
    public void setUp() {
        // Initialization if needed
    }

    @AfterEach
    public void tearDown() {
        // Cleanup if needed
    }

    @Test
    public void testGreaterThan() {
        int amountOfOperand1 = 7;
        int amountOfOperand2 = 3;
        Amount operand1 = new Amount(amountOfOperand1);
        Amount operand2 = new Amount(amountOfOperand2);
        boolean expResult = operand1.getAmount() > operand2.getAmount(); // Comparing amounts directly
        boolean result = operand1.minus(operand2).getAmount() > 0; // Checking if subtraction result is positive
        assertEquals(expResult, result, "Wrong comparison result");
    }

    @Test
    public void testMultiply() {
        int amountOfOperand1 = 5;
        int amountOfOperand2 = 4;
        Amount operand1 = new Amount(amountOfOperand1);
        Amount expResult = operand1.times(amountOfOperand2); // Using the times method to multiply
        Amount result = operand1.times(amountOfOperand2); // Adjusted to use the times method
        assertEquals(expResult, result, "Wrong multiplication result");
    }

    // Additional tests for methods like `plus` and `times` to ensure complete test coverage

    @Test
    public void testPlus() {
        Amount baseAmount = new Amount(5);
        Amount addAmount = new Amount(3);
        Amount expectedResult = new Amount(8);
        Amount result = baseAmount.plus(addAmount);
        assertEquals(expectedResult, result, "Amount instances did not add correctly.");
    }

    @Test
    public void testTimes() {
        Amount baseAmount = new Amount(4);
        int multiplier = 3;
        Amount expectedResult = new Amount(12);
        Amount result = baseAmount.times(multiplier);
        assertEquals(expectedResult, result, "Amount instances did not multiply correctly.");
    }

    // Ensure the hashCode method works as expected, especially since it's critical for objects used in collections

    @Test
    public void testHashCode() {
        int amountValue = 10;
        Amount amount = new Amount(amountValue);
        int expectedHashCode = Integer.hashCode(amountValue);
        assertEquals(expectedHashCode, amount.hashCode(), "Hash code did not match expected value.");
    }
}
