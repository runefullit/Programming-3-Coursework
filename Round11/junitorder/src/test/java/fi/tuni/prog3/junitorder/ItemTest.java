package fi.tuni.prog3.junitorder;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ItemTest {

    @Test
    public void itemThrowsIllegalArgumentExceptionIfNameNull() {
        assertThrows(IllegalArgumentException.class, () -> new Order.Item(null, 1));
    }

    @Test
    public void itemThrowsIllegalArgumentExceptionIfPriceNegative() {
        assertThrows(IllegalArgumentException.class, ()-> new Order.Item("name", -1));
    }

    @Test
    public void itemNameMatchesGivenParameter() {
        String name = "name";
        Order.Item item = new Order.Item(name, 0);
        assertEquals(name, item.getName());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, 1.41, 17.99, 99.30})
    public void itemPriceMatchesGivenParameter(double price) {
        Order.Item item = new Order.Item("name", price);
        assertEquals(price, item.getPrice());
    }

    @Test
    public void itemEqualsOtherDifferentPrices() {
        String name = "name";
        Order.Item self = new Order.Item(name, 1);
        Order.Item other = new Order.Item(name, 0);
        assertTrue(self.equals(other));
    }

    @Test
    public void itemEqualsOtherSamePrices() {
        String name = "name";
        Order.Item self = new Order.Item(name, 0);
        Order.Item other = new Order.Item(name, 0);
        assertTrue(self.equals(other));
    }

    @Test
    public void itemUnequalToOtherDifferentPrices() {
        String selfName = "selfName";
        String otherName = "otherName";
        Order.Item self = new Order.Item(otherName, 1);
        Order.Item other = new Order.Item(selfName, 0);
        assertFalse(self.equals(other));
    }

    @Test
    public void itemUnequalToOtherSamePrices() {
        String selfName = "selfName";
        String otherName = "otherName";
        Order.Item self = new Order.Item(otherName, 0);
        Order.Item other = new Order.Item(selfName, 0);
        assertFalse(self.equals(other));
    }

    @Test
    public void toStringProducesExpectedOutputs() {
        String name = "name";
        double price = Math.sqrt(2);
        Order.Item item = new Order.Item(name, price);
        assertEquals(String.format("Item(%s, %.2f)",name, price), item.toString());
    }
}
