/*
 * Copyright (c) 2023
 */
package com.mamay.task2.entity;

import com.mamay.Lab1Exception;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class Actor extends BaseEmployee {

  private final int MINIMUM_INCOME = 3000;
  private final int MAXIMUM_INCOME = 25000;

  private String description;

  public Actor(String name, Address address, int income) {
    super(name, address);
    setIncome(income);
  }

  public Actor(String name, Address address, int income, String description) {
    super(name, address);
    setIncome(income);
    setDescription(description);
  }

  public void setIncome(int income) {
    if (income >= MINIMUM_INCOME && income <= MAXIMUM_INCOME) {
      super.setIncome(income);
    }
  }
}
