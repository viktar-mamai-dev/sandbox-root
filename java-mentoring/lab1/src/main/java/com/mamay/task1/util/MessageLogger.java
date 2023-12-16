/*
 * Copyright (c) 2023
 */
package com.mamay.task1.util;

import com.mamay.task1.model.Aquarium;
import com.mamay.task1.model.Component;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class MessageLogger {
  public void printCheck(Aquarium aqua) {
    StringBuilder builder = new StringBuilder();
    builder.append("***************\n");
    builder.append("Order ").append(aqua.getId()).append("\n");
    builder.append("Customer ").append(aqua.getCustomerId()).append("\n");
    builder.append("Name ").append(aqua.getName()).append("\n");
    builder.append("_______________\n");
    builder.append("Aquarium ").append(aqua.getType().getCost()).append(" $").append("\n");
    for (Component c : aqua.getComponents()) {
      builder
          .append(c.getName().getName())
          .append(" ")
          .append(c.getCost())
          .append(" $")
          .append("\n");
    }
    builder.append("_______________\n");
    builder.append("Total ").append(aqua.getTotalCost()).append(" $").append("\n");
    builder.append("Count ").append(aqua.getCountOfOrders()).append("\n");
    builder.append("_______________\n");
    builder
        .append("Total sum ")
        .append(aqua.getTotalCost() * aqua.getCountOfOrders())
        .append(" $")
        .append("\n");
    builder.append("***************\n");
    log.debug(builder.toString());
  }
}
