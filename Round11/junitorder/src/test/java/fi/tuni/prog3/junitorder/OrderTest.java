package fi.tuni.prog3.junitorder;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.NoSuchElementException;


public class OrderTest {

    private Order order;
    private Order.Item item = new Order.Item("name", 1.7);

    @Test
    public void addingNonPositiveItemCountThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> this.order.addItems(this.item, -1));
    }

    @Test
    public void addingItemWithSameNameAndDifferentPriceThrowsIllegalStateException() {
        this.order.addItems(this.item, 1);
        Order.Item item2 = new Order.Item("name", 1.5);

        assertThrows(IllegalStateException.class, () -> this.order.addItems(item2, 1));
    }

    @Test
    public void addingItemReturnsTrue() {
        assertTrue(this.order.addItems(this.item, 1));
    }

    @Test
    public void addingExistingItemIncrementsCount() {
        this.order.addItems(this.item, 2);
        this.order.addItems(this.item,1);
        assertEquals(3, this.order.getEntries().get(0).getCount());
    }

    @Test
    public void addingItemsWithStringAndNegativeCountThrowsIllegalArgumentException() {
        order.addItems(item, 1);
        assertThrows(IllegalArgumentException.class, () -> this.order.addItems("name", -1));
    }

    @Test
    public void addingNewItemsWithStringThrowsNoSuchElementException() {
        assertThrows(NoSuchElementException.class, () -> this.order.addItems("name", 1));
    }

    @Test
    public void addingItemsWithStringReturnsTrue() {
        this.order.addItems(this.item, 1);

        assertTrue(this.order.addItems("name", 1));
    }

    @Test
    public void addingItemsWithStringIncrementsCount() {
        this.order.addItems(this.item, 2);
        assertEquals(2, this.order.getItemCount());
        this.order.addItems("name", 1);
        assertEquals(3, this.order.getItemCount());
    }

    @Test
    public void getEntriesReturnsEmptyListIfNoEntries() {
        assertEquals(new ArrayList<Order.Entry>(), this.order.getEntries());
    }

    @Test
    public void getEntriesReturnsExpectedResult() {
        this.order.addItems(this.item, 1);
        Order.Item item2 = new Order.Item("name2", 1.0);
        this.order.addItems(item2, 1);

        assertEquals(this.item, this.order.getEntries().get(0).getItem());
        assertEquals(item2, this.order.getEntries().get(1).getItem());
    }

    @Test
    public void getEntryCountReturnsZeroForNoEntries() {
        assertEquals(0, this.order.getEntryCount());
    }

    @Test
    public void getEntryCountReturnsExpectedCount() {
        Order.Item item2 = new Order.Item("name2", 1.5);
        this.order.addItems(item, 1);
        this.order.addItems(item2, 2);

        assertEquals(2, this.order.getEntryCount());
    }

    @Test
    public void getItemCountReturnZeroForNoEntries() {
        assertEquals(0, this.order.getItemCount());
    }

    @Test
    public void getItemCountReturnsExpectedCount() {
        this.order.addItems(this.item, 1);
        this.order.addItems(this.item, 2);

        assertEquals(3, order.getItemCount());
    }

    @Test
    public void getTotalPriceReturnsZeroForNoEntries() {
        assertEquals(0, order.getTotalPrice());
    }

    @Test
    public void getTotalPriceReturnsExpectedValues() {
        this.order.addItems(this.item, 1);
        this.order.addItems(this.item, 2);
        assertEquals(5.1, this.order.getTotalPrice());
    }

    @Test
    public void isEmptyReturnsTrueWithNoEntries() {
        assertTrue(this.order.isEmpty());
    }

    @Test
    public void isEmptyReturnsFalseWithEntries() {
        this.order.addItems(this.item, 1);
        assertFalse(this.order.isEmpty());
    }

    @Test
    public void removeItemsThrowsNoSuchElementExceptionOnEmptyList() {
        assertThrows(NoSuchElementException.class, () -> this.order.removeItems("name", 1));
    }

    @Test
    public void removeItemsThrowsIllegalArgumentExceptionWhenCountExceedsItemCount() {
        this.order.addItems(this.item, 2);
        assertThrows(IllegalArgumentException.class, () -> this.order.removeItems("name", 3));
    }

    @Test
    public void removeItemsThrowsIllegalArgumentExceptionWhenCountNegative() {
        this.order.addItems(this.item, 2);
        assertThrows(IllegalArgumentException.class, () -> this.order.removeItems("name", -1));
    }

    @Test
    public void removeItemsRemovesGivenElement() {
        this.order.addItems(this.item, 2);
        this.order.removeItems("name", 2);
        assertEquals(0, this.order.getItemCount());
    }

    @Test
    public void removeItemsDecreasesItemCount() {
        this.order.addItems(this.item, 2);
        this.order.removeItems("name", 1);
        assertEquals(1, this.order.getItemCount());
    }

    @BeforeEach
    public void setup() {
        this.order = new Order();
    }
}
