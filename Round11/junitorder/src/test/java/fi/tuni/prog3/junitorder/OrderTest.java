package fi.tuni.prog3.junitorder;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


public class OrderTest {

    private Order order;
    private final Order.Item item = new Order.Item("name", 1.7);

    // Testing addItems(Item.Order, int)
    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    public void addingNonPositiveItemCountThrowsIllegalArgumentException(int count) {
        assertThrows(IllegalArgumentException.class, () -> this.order.addItems(this.item, count));
    }

    @Test
    public void addingItemWithSameNameAndDifferentPriceThrowsIllegalStateException() {
        this.order.addItems(this.item, 1);
        Order.Item item2 = new Order.Item(this.item.getName(), this.item.getPrice() + 0.2);

        assertThrows(IllegalStateException.class, () -> this.order.addItems(item2, 1));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    public void addingItemWithSameNameAndNonPositiveCountThrowsIllegalArgumentException(int count) {
        this.order.addItems(this.item, 2);

        assertThrows(IllegalArgumentException.class, () -> this.order.addItems(this.item, count));
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

    // Testing addItems(String, int)
    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    public void addingItemsWithStringAndNonPositiveCountThrowsIllegalArgumentException(int count) {
        order.addItems(item, 1);
        assertThrows(IllegalArgumentException.class, () -> this.order.addItems("name", count));
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

    // Testing getEntries()
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
    public void getEntriesIsNotSorted() {
        Order.Item item2 = new Order.Item("name2", 1.0);
        this.order.addItems(item2, 1);
        this.order.addItems(this.item, 1);

        assertEquals(item2, this.order.getEntries().get(0).getItem());
        assertEquals(this.item, this.order.getEntries().get(1).getItem());
    }

    @Test
    public void getEntriesReturnsCopy() {
        this.order.addItems(this.item, 1);
        List<Order.Entry> orderCopy = this.order.getEntries();
        Order.Item item2 = new Order.Item("name2", 2.3);
        orderCopy.add(new Order.Entry(item2, 1));
        assertEquals(2, orderCopy.size());
        assertEquals(1, this.order.getEntries().size());
    }

    // Testing getEntryCount()
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

    // Testing getItemCount()
    @Test
    public void getItemCountReturnZeroForNoEntries() {
        assertEquals(0, this.order.getItemCount());
    }

    @ParameterizedTest
    @ValueSource(ints = {1,99, 1231551})
    public void getItemCountReturnsExpectedCount(int count) {
        this.order.addItems(this.item, 1);
        this.order.addItems(this.item, count);

        assertEquals(count + 1, order.getItemCount());
    }

    // Testing getTotalPrice()
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
    public void getTotalPriceFreeUnitsDoNotIncreaseTotal() {
        this.order.addItems(this.item, 2);
        Order.Item item2 = new Order.Item("name2", 0);
        this.order.addItems(item2, 3);
        assertEquals(3.4, this.order.getTotalPrice());
    }

    // Testing isEmpty()
    @Test
    public void isEmptyReturnsTrueWithNoEntries() {
        assertTrue(this.order.isEmpty());
    }

    @Test
    public void isEmptyReturnsFalseWithEntries() {
        this.order.addItems(this.item, 1);
        assertFalse(this.order.isEmpty());
    }

    // Testing removeItems(String, int)
    @Test
    public void removeItemsThrowsNoSuchElementExceptionOnEmptyList() {
        assertThrows(NoSuchElementException.class, () -> this.order.removeItems("name", 1));
    }

    @Test
    public void removeItemsThrowsIllegalArgumentExceptionWhenCountExceedsItemCount() {
        this.order.addItems(this.item, 2);
        assertThrows(IllegalArgumentException.class, () -> this.order.removeItems("name", 5));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    public void removeItemsThrowsIllegalArgumentExceptionWhenCountNonPositive(int count) {
        this.order.addItems(this.item, 2);
        assertThrows(IllegalArgumentException.class, () -> this.order.removeItems("name", count));
    }

    @Test
    public void removeItemsRemovesGivenElement() {
        Order.Item item2 = new Order.Item("name2", 1.3);
        this.order.addItems(item2, 3);
        this.order.addItems(this.item, 2);
        this.order.removeItems("name", 2);
        assertEquals(3, this.order.getItemCount());
        assertEquals(1, this.order.getEntryCount());
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
