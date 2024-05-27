package test.java.se.kth.iv1350.pos.integration;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pos.integration.ItemDTO;

public class ItemDTOTest {

    @Test
    public void testToString() {
        // Arrange
        String itemName = "Tomato";
        double price = 10.0;
        double VATRate = 0.25;
        String description = "Fresh Tomato";
        String itemId = "14523";
        ItemDTO itemDTO = new ItemDTO(itemName, VATRate, price, description, itemId);
        String expectedString = "ItemDTO{" +
                                "name='" + itemName + '\'' +
                                ", VATRate=" + VATRate +
                                ", price=" + price +
                                ", description='" + description + '\'' +
                                ", itemId='" + itemId + '\'' +
                                '}';

        // Act
        String resultString = itemDTO.toString();

        // Assert
        assertEquals(expectedString, resultString, "ItemDTO toString() should return the expected string representation");
    }


    @Test
    public void testConstructorWithEmptyDescription() {
        // Arrange
        String name = "Banana";
        double VATRate = 0.06;
        double price = 10.0;
        String description = ""; // Empty description
        String itemId = "54321";

    // Act & Assert
    assertDoesNotThrow(() -> {
        new ItemDTO(name, VATRate, price, description, itemId);
    }, "Constructor should not throw exception for empty description");
}
}
