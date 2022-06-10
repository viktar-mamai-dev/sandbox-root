package com.mamay;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Item> items;

    public boolean addItem(Item item) {
        return this.addItem(item);
    }

    public boolean removeItem(Item item) {
        return this.removeItem(item);
    }

    public boolean updateItemQuantity(Item item, int quantity) {
        // TODO
        return true;
    }

    public List<Item> getItems() {
        return new ArrayList<>(items);
    }

    public boolean checkout() {
        // TODO
        return true;
    }
}
