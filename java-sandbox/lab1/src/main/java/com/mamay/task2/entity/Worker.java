/*
 * Copyright (c) 2023
 */
package com.mamay.task2.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Worker extends BaseEmployee {

  private WorkerType type;

  public Worker(String name, Address address, int income, WorkerType type) {
    super(name, address);
    setType(type);
    setIncome(income);
  }

  @Override
  public void setIncome(int income) {
    if (income >= type.getMinIncome() && income <= type.getMaxIncome()) {
      super.setIncome(income);
    }
  }
}
