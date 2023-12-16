package com.mamay;

public abstract class Customer {
  private ShoppingCart cart;
  private Order order;

  public ShoppingCart getShoppingCart() {
    return cart;
  }

  public boolean addItemToCart(Item item) {
    return cart.addItem(item);
  }

  public boolean removeItemFromCart(Item item) {
    return cart.removeItem(item);
  }
}
