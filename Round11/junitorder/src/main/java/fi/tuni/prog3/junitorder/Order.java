package fi.tuni.prog3.junitorder;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


public class Order {

    public static class Entry {

        private final Order.Item item;
        private int count;

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
         * A convenience function that returns the name of this entry's item.
         * @return the name of the item.
         */
        public String getItemName() {
            return this.item.getName();
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

        private void addCount(int count) {
            this.count += count;
        }

    }

    public static class Item {

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
            return String.format("Item(%s, %.2f)", this.name, this.price);
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
        if (count <= 0) {
            throw new IllegalArgumentException();
        }
        Optional<Entry> entry = getEntryByName(item.getName());
        if (entry.isPresent()) {
            if (entry.get().getUnitPrice() != item.getPrice()) {
                throw new IllegalStateException();
            }
            entry.get().addCount(count);
        } else {
            this.orderList.add(new Order.Entry(item, count));
        }
        return true;
    }

    /**
     * <p>Adds count units of an item to the order. </p>
     *
     * <p>Note: The order is expected to already contain an entry with the specified item name, and this
     * function will increment that entry's item count by count.</p>
     *
     * @param name the name of the added item.
     * @param count the item unit count to add.
     * @return true if the items were added without errors.
     * @throws IllegalArgumentException if the item unit count to add is not positive.
     * @throws IllegalStateException if the order does not contain an entry with the specified item name.
     */
    public boolean addItems(String name, int count) throws IllegalArgumentException, NoSuchElementException {
        if (count <= 0) {
            throw new IllegalArgumentException();
        }
        for (Order.Entry entry : orderList) {
            if (entry.getItem().getName().equals(name)) {
                entry.addCount(count);
                return true;
            }
        }
        throw new NoSuchElementException();
    }

    /**
     * <p>Returns the order entries in their original adding order. </p>
     *
     * <p>Note: the returned list is a copy, so modifying it will not affect the internal state of the order.</p>
     *
     * @return the current entries of the order.
     */
    public List<Entry> getEntries() {
        return new ArrayList<>(this.orderList);
    }

    /**
     * Returns the total number of item entries in this order.
     * @return the total number of item entries in this order.
     */
    public int getEntryCount() {
        return this.orderList.size();
    }

    /**
     * Returns the total number of entries in this order (= sum of all entries' counts).
     * @return the total number of entries in this order.
     */
    public int getItemCount() {
        int sum = 0;
        for (Order.Entry entry : orderList) {
            sum += entry.getCount();
        }
        return sum;
    }

    /**
     * Returns the total price of the order.
     * @return the total price of the order.
     */
    public double getTotalPrice() {
        double sum = 0;
        for (Order.Entry entry : orderList) {
            sum += entry.getUnitPrice() * entry.getCount();
        }
        return sum;
    }

    /**
     * Tells whether the order is empty.
     * @return true if the order is empty, otherwise false.
     */
    public boolean isEmpty() {
        return this.orderList.size() == 0;
    }

    /**
     * <p>Removes count units of an item from the order. </p>
     *
     * <p>Note: If the order already contains an entry for the specified item (based on having the same item name),
     * the existing entry's item count is decremented by count. If the item count becomes zero, the item entry is
     * removed from the order.</p>
     *
     * @param name the name of the removed item.
     * @param count the item unit count to remove.
     * @return true if the items were removed without errors.
     * @throws IllegalArgumentException if the item unit count to remove is not positive or is larger than the
     * corresponding existing entry's item unit
     * @throws NoSuchElementException if the order does not contain an entry with the specified item name.
     */
    public boolean removeItems(String name, int count) throws IllegalArgumentException, NoSuchElementException {
        Optional<Entry> entry = getEntryByName(name);
        if (entry.isEmpty()) {
            throw new NoSuchElementException();
        }
        if (entry.get().getCount() < count || count <= 0) {
            throw new IllegalArgumentException();
        }
        if (entry.get().getCount() == count) {
            orderList.remove(entry.get());
        } else {
            entry.get().count -= count;
        }
        return true;
    }

    /**
     * Helperfunction for entry management, gets a reference of entry with given name. (Empty if doesn't exist)
     * @param name The searched entry's name
     * @return entry with the given name, Empty if not found.
     */
    private Optional<Entry> getEntryByName(String name) {
        return this.orderList.stream()
                .filter(entry -> entry.getItemName().equals(name))
                .findFirst();
    }

}
