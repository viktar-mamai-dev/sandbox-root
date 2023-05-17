package com.mamay;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Item {
  private String productID;
  private int quantity;
  private double price;

  public void updateQuantity(int quantity) {
    this.quantity = quantity;
  }
}
