package fi.tuni.prog3.junitorder;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EntryTest {

    @Mock
    Order.Item item;


    @Test
    public void entryItemMatchesGivenParameter() {
        Order.Entry entry = new Order.Entry(item, 1);
        assertEquals(item, entry.getItem());
    }

    @Test
    public void entryItemNameIsExpectedItemName() {
        String name = "name";
        when(item.getName()).thenReturn(name);

        Order.Entry entry = new Order.Entry(item, 1);

        assertEquals(name, entry.getItemName());
    }

    @Test
    public void entryItemPriceIsExpectedItemPrice() {
        double price = Math.sqrt(2);

        when(item.getPrice()).thenReturn(price);

        Order.Entry entry = new Order.Entry(item, 1);

        assertEquals(price, entry.getUnitPrice());
    }

    @ParameterizedTest
    @ValueSource(ints = {1,5,100,11231})
    public void countMatchesGivenLegalParameter(int count) {
        Order.Entry entry = new Order.Entry(item, count);
        assertEquals(count, entry.getCount());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    public void countThrowsIllegalArgumentExceptionWithNonPositiveCount(int count) {
        assertThrows(IllegalArgumentException.class, () -> new Order.Entry(item, count));
    }

    @Test
    public void toStringProducesExpectedOutput() {
        String name = "name";
        double price = Math.sqrt(2);
        int count = 17;

        when(item.toString()).thenReturn(String.format("Item(%s, %.2f)", name, price));

        Order.Entry entry = new Order.Entry(new Order.Item(name, price), count);

        assertEquals(String.format("%d units of %s", count, item.toString()), entry.toString());
    }

}
