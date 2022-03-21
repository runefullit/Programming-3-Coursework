package fi.tuni.prog3.junitorder;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;


public class EntryTest {
    private Order.Item item;

    @Test
    public void entryMayBeInitializedWithNullItem() {
        Order.Entry entry = new Order.Entry(null, 2);
        assertNull(entry.getItem());
    }

    @Test
    public void entryItemMatchesGivenParameter() {
        String name = "name";
        double price = Math.sqrt(2);
        this.item = new Order.Item(name, price);

        Order.Entry entry = new Order.Entry(this.item, 1);

        assertEquals(this.item, entry.getItem());
    }

    @Test
    public void entryItemNameIsExpectedItemName() {
        String name = "name";
        double price = Math.sqrt(2);
        this.item = new Order.Item(name, price);

        Order.Entry entry = new Order.Entry(this.item, 1);

        assertEquals(name, entry.getItemName());
    }

    @ParameterizedTest
    @ValueSource(doubles = {1.231, 99123.12})
    public void entryItemPriceIsExpectedItemPrice(double price) {
        String name = "name";
        this.item = new Order.Item(name, price);

        Order.Entry entry = new Order.Entry(this.item, 2);

        assertEquals(price, entry.getUnitPrice());
    }

    @ParameterizedTest
    @ValueSource(ints = {1,5,100,11231})
    public void countMatchesGivenLegalParameter(int count) {
        String name = "name";
        double price = Math.sqrt(2);
        this.item = new Order.Item(name, price);

        Order.Entry entry = new Order.Entry(this.item, count);

        assertEquals(count, entry.getCount());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    public void constructorThrowsIllegalArgumentExceptionWithNonPositiveCount(int count) {
        String name = "name";
        double price = Math.sqrt(2);
        this.item = new Order.Item(name, price);

        assertThrows(IllegalArgumentException.class, () -> new Order.Entry(this.item, count));
    }

    @Test
    public void toStringProducesExpectedOutput() {
        String name = "name";
        double price = Math.sqrt(2);
        int count = 17;
        this.item = new Order.Item(name, price);

        Order.Entry entry = new Order.Entry(new Order.Item(name, price), count);

        assertEquals(String.format("%d units of %s", count, this.item.toString()), entry.toString());
    }

}
