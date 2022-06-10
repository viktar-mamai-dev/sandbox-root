package com.mamay;

public abstract class Customer {
    private ShoppingCart cart;
    private Order order;

    // TODO implement methods
    public ShoppingCart getShoppingCart() {
        return null;
    }

    public boolean addItemToCart(Item item) {
        return false;
    }

    public boolean removeItemFromCart(Item item) {
        return false;
    }
}
