package fi.tuni.prog3.junitorder;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Order {

    public class Entry {

        private final Order.Item item;
        private final int count;

        /**
         * Constructs an entry with the given item and item unit count.
         * @param item the item.
         * @param count the item count.
         * @throws IllegalArgumentException if the item unit count is not positive.
         */
        public Entry(Order.Item item, int count) throws IllegalArgumentException {
            if (count <= 0) {
                throw new IllegalArgumentException();
            }
            this.item = item;
            this.count = count;
        }

        /**
         * A convenience function that returns the unit price of this entry's item.
         * @return the unit price of the item.
         */
        public double getUnitPrice() {
            return this.item.getPrice();
        }

        /**
         * Returns the item of this entry.
         * @return the item.
         */
        public Order.Item getItem() {
            return this.item;
        }

        /**
         * Returns the item unit count of this entry.
         * @return the entry's item unit count.
         */
        public int getCount() {
            return this.count;
        }

        /**
         * Returns a string representation of this entry. The presentation format is "x units of item", where
         * x is the item unit count and item is the string representation of this entry's item.
         * @return a string representation of this entry.
         */
        @Override
        public String toString() {
            return String.format("%d units of %s", this.count, this.item.toString());
        }

    }

    public class Item {

        private final String name;
        private final double price;

        /**
         * Constructs an item with the given name and unit price.
         * @param name the name of the item.
         * @param price the unit price of the item.
         * @throws IllegalArgumentException if the name is null or if the price is negative.
         */
        Item(String name, double price) throws IllegalArgumentException {
            if (name == null || price < 0) {
                throw new IllegalArgumentException();
            }
            this.name = name;
            this.price = price;
        }

        /**
         * Returns the name of the item.
         * @return the name of the item.
         */
        public String getName() {
            return this.name;
        }

        /**
         * Returns the unit price of the item.
         * @return the unit price of the item.
         */
        public double getPrice() {
            return this.price;
        }

        /**
         * Returns a string representation of this item. The presentation format is "Item(name, price)",
         * where the price is expressed with two decimals of precision.
         * @return a string representation of this item.
         */
        @Override
        public String toString() {
            return String.format("Item(%s, %.2f");
        }

        /**
         * Compares this item with another item based on item name.
         * @param other the other item to which this item is compared.
         * @return true if the name of this item equals the name of other, otherwise false.
         */
        public boolean equals(Order.Item other) {
            return this.name.equals(other.getName());
        }

    }

    private final ArrayList<Entry> orderList;

    /**
     * Constructs an initially empty order.
     */
    public Order() {
        this.orderList = new ArrayList<Entry>();
    }

    /**
     * <p>Adds count units of an item to the order.</p>
     *
     * <p>Note: If the order already contains an entry for the specified item (based on having the same item name),
     * the existing entry's item count is incremented by count. Otherwise a new entry with the specified item and
     * item unit count is added to the order.</p>
     * @param item the added item.
     * @param count the item unit count to add.
     * @return true if the items were added without errors.
     * @throws IllegalArgumentException if the item unit count to add is not positive.
     * @throws IllegalStateException if an existing entry has the same item name but a different price than the added item.
     */
    public boolean addItems(Order.Item item, int count) throws IllegalArgumentException, IllegalStateException {
        // TODO: Add error cases.
        this.orderList.add(new Entry(item, count));
        return true;
    }

    public boolean AddItems(String name, int count) throws IllegalArgumentException, IllegalStateException {
        throw new NotImplementedException();
    }

    public List<Entry> getEntries() {
        return this.orderList;
    }

    public int getEntryCount() {
        return this.orderList.size();
    }

    public int getItemCount() {
        throw new NotImplementedException();
    }

    public double getTotalPrice() {
        throw new NotImplementedException();
    }

    public boolean isEmpty() {
        return this.orderList.size() == 0;
    }

    public boolean removeItems(String name, int count) throws IllegalArgumentException, NoSuchElementException {
        throw new NotImplementedException();
    }


}
