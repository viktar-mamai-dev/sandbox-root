package com.mamay;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ShoppingCart {
    private List<Item> items;

    public boolean addItem(Item item) {
        return this.items.add(item);
    }

    public boolean removeItem(Item item) {
        return this.items.remove(item);
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
