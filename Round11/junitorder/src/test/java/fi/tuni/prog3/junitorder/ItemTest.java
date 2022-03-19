package fi.tuni.prog3.junitorder;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ItemTest {

    @Test
    public void itemThrowsIllegalArgumentExceptionIfNameNull() {
        assertThrows(IllegalArgumentException.class, () -> new Item(null, 0));
    }

    @Test
    public void itemThrowsIllegalArgumentExceptionIfPriceNegative() {
        assertThrows(IllegalArgumentException.class, ()-> new Item("name", -1));
    }

    @Test
    public void itemNameMatchesGivenParameter() {
        String name = "name";
        Item item = new Item(name, 0);
        assertEquals(name, item.getName());
    }

    @Test
    public void itemPriceMatchesGivenParameter() {
        double price = 0;
        Item item = new Item("name", price);
        assertEquals(price, item.getPrice());
    }

    @Test
    public void itemEqualsOtherDifferentPrices() {
        String name = "name";
        Item other = new Item(name, 0);
        Item self = new Item(name, 1);
        assertTrue(self.equals(other));
    }

    @Test
    public void itemEqualsOtherSamePrices() {
        String name = "name";
        Item other = new Item(name, 0);
        Item self = new Item(name, 0);
        assertTrue(self.equals(other));
    }

    @Test
    public void itemInequalToOtherDifferentPrices() {
        String name = "name";
        Item other = new Item(name, 0);
        Item self = new Item(name, 1);
        assertFalse(self.equals(other));
    }

    @Test
    public void itemInequalToOtherSamePrices() {
        String name = "name";
        Item other = new Item(name, 0);
        Item self = new Item(name, 0);
        assertFalse(self.equals(other));
    }

    @Test
    public void toStringProducesExpectedOutputs() {
        String name = "name";
        double price = Math.sqrt(2);
        Item item = new Item(name, price);
        assertEquals(String.format("Item(%s, %.2f)",name, price), item.toString());
    }
}
