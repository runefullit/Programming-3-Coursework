package fi.tuni.prog3.junitorder;

import org.junit.jupiter.api.Test;
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
    Item item;


    @Test
    public void entryItemMatchesGivenParameter() {
        Entry entry = new Entry(item, 1);
        assertEquals(item, entry.getItem());
    }

    @Test
    public void entryItemNameIsExpectedItemName() {
        String name = "name";
        when(item.getName()).thenReturn(name);

        Entry entry = new Entry(item, 1);

        assertEquals(name, entry.getItemName());
    }

    @Test
    public void entryItemPriceIsExpectedItemPrice() {
        double price = Math.sqrt(2);

        when(item.getPrice()).thenReturn(price);

        Entry entry = new Entry(item, 1);

        assertEquals(price, entry.getUnitPrice());
    }

    @ParameterizedTest
    @ValueSource(ints = {0,1,5,100,11231})
    public void countMatchesGivenLegalParameter(int count) {
        Entry entry = new Entry(item, count);
        assertEquals(count, entry.getCount());
    }

    @Test
    public void countThrowsIllegalArgumentExceptionWithNegativeCount() {
        int count = -1;
        assertThrows(IllegalArgumentException.class, () -> new Entry(item, count))
    }

    @ParameterizedTest
    @ValueSource(ints = {0,1,5,100,11231})
    public void toStringProducesExpectedOutputs(int count) {
        String name = "name";
        double price = Math.sqrt(2);

        when(item.toString()).thenReturn(String.format("Item(%s, %.2f)", name, price));

        assertEquals(String.format("%d units of %s", count, item.toString()));
    }

}
