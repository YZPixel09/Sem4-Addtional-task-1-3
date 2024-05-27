package test.java.se.kth.iv1350.pos.integration;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pos.integration.Item;
import se.kth.iv1350.pos.integration.ItemDTO;

public class ItemTest {

    @Test
    public void testIncreaseQuantity() {
        // Arrange
        ItemDTO itemDTO = new ItemDTO("Juice", 0.06, 20, "Apple Juice", "12345");
        Item item = new Item(itemDTO, 1);

        // Act
        item.increaseQuantity(2);

        // Assert
        assertEquals(3, item.getQuantity(), "Quantity should be increased to 3");
    }

    @Test
    public void testIncreaseQuantityWithNegativeValue() {
        // Arrange
        ItemDTO itemDTO = new ItemDTO("Paprika", 0.06, 10, "Fresh Paprika", "54321");
        Item item = new Item(itemDTO, 5);

        // Act
        int negativeValue = -3;
        int expectedQuantity = 2; // 5 (initial quantity) - 3 (negative summand)
        item.increaseQuantity(negativeValue);

        // Assert
        assertEquals(expectedQuantity, item.getQuantity(), "Quantity should decrease to 2 when adding a negative value");
    }

    @Test
    public void testIncreaseQuantityWithMaxValue() {
        // Arrange
        ItemDTO itemDTO = new ItemDTO("Paprika", 0.06, 10, "Fresh Paprika", "54321");
        Item item = new Item(itemDTO, 5);

        // Act
        int maxValue = Integer.MAX_VALUE - 5;
        item.increaseQuantity(maxValue);

        // Assert
        assertEquals(Integer.MAX_VALUE, item.getQuantity(), "Quantity should be Integer.MAX_VALUE when adding max value to initial quantity");
    }
}
