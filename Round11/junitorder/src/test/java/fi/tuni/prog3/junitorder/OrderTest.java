package fi.tuni.prog3.junitorder;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


public class OrderTest {

    private Order order;
    private Order.Item item = new Order.Item("name", 1.0);

    @Test
    public void addingNonPositiveItemCountThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> order.addItems(item, -1));
    }

    @Test
    public void addingItemWithSameNameAndDifferentPriceThrowsIllegalStateException() {
        this.order.addItems(item, 1);
        Order.Item item2 = new Order.Item("name", 1.5);

        assertThrows(IllegalStateException.class, () -> order.addItems(item2, 1));
    }

    @Test
    public void addingItemReturnsTrue() {
        assertTrue(order.addItems(item, 1));
    }

    @Test
    public void addingExistingItemIncrementsTheCounter() {
        this.order.addItems(item, 2);
        this.order.addItems(item,1);
        assertEquals(3, order.getEntries().get(0).getCount());
    }

    @Test
    public void getEntriesReturnsEmptyListIfNoEntries() {
        assertEquals(new ArrayList<Order.Entry>(), order.getEntries());
    }

    @Test
    public void getEntryCountReturnsZeroForNoEntries() {
        assertEquals(0, order.getEntryCount());
    }

    @Test
    public void getEntryCountReturnsExpectedCount() {
        Order.Item item2 = new Order.Item("name2", 1.5);
        order.addItems(item, 1);
        order.addItems(item2, 2);

        assertEquals(2, order.getEntryCount());
    }

    @Test
    public void getItemCountReturnZeroForNoEntries() {
        assertEquals(0, order.getItemCount());
    }

    @Test
    public void getItemCountReturnsExpectedCount() {
        order.addItems(item, 1);
        order.addItems(item, 2);

        assertEquals(3, order.getItemCount());
    }

    @Test
    public void getTotalPriceReturnsZeroForNoEntries() {
        assertEquals(0, order.getTotalPrice());
    }

    @Test
    public void getTotalPriceReturnsExpectedValues() {
        order.addItems(item, 1);
        order.addItems(item, 2);
        assertEquals(3, order.getTotalPrice());
    }

    @BeforeEach
    public void setup() {
        order = new Order();
    }
}
