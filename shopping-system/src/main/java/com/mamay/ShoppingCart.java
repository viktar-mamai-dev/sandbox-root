package com.mamay;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
