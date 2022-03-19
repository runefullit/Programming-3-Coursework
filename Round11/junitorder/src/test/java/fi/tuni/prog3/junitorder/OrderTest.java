package fi.tuni.prog3.junitorder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class OrderTest {

    @Mock
    Item item;
    @Mock
    Item item2;
    @Mock
    Entry entry;

    Order order;

    @Test
    public void addingNegativeItemCountThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> order.addItem(item, -1));
    }

    @Test
    public void addingItemWithSameNameAndDifferentPriceThrowsIllegalStateException() {
        String name = "name";
        when(item.getName()).thenReturn(name);
        when(item2.getName()).thenReturn(name);
        when(item.getPrice()).thenReturn(1);
        when(item2.getPrice()).thenReturn(Math.sqrt(2));

        order.addItem(item, 1);

        assertThrows(IllegalStateException.class, () -> order.addItem(item2, 1));
    }

    @Test
    public void addingItemReturnsTrue() {
        assertTrue(order.addItem(item, 1));
    }

    @Test
    public void addingExistingItemIncrementsTheCounter() {
        String name = "name";
        when(item.getName()).thenReturn(name);
        when(item.equals()).thenReturn(true);

        // TODO: Finish this test.
        assertTrue(false);
    }

    @Test
    public void getEntriesReturnsEmptyListIfNoEntries() {
        assertEquals(new ArrayList<Order.Entry>(), order.getEntries());
    }

    @Test
    public void getEntrycountReturnsZeroForNoEntries() {
        assertEquals(0, order.getEntries());
    }

    @Test
    public void getEntryCountReturnsExpectedCount() {
        order.addItem(item, 1);
        order.addItem(item, 2);

        assertEquals(2, order.getEntryCount().size());
    }

    @Test
    public void getItemCountReturnZeroForNoEntries() {
        assertEquals(0, order.getItemCount());
    }

    @Test
    public void getItemCountReturnsExpectedCount() {
        order.addItem(item, 1);
        order.addItem(item, 2);

        assertEquals(3, order.getEntryCount().size());
    }

    @Test
    public void getTotalPriceReturnsZeroForNoEntries() {
        assertEquals(0, order.getTotalPrice());
    }

    @Test
    public void getTotalPriceReturnsExpectedValues() {
        when(entry.getUnitPrice())
    }

    @BeforeEach
    public void setup() {
        order = new Order();
    }
}
