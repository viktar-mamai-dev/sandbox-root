/*
 * Copyright (c) 2023
 */
package com.mamay;

import com.mamay.task1.model.Aquarium;
import com.mamay.task1.model.Item;
import com.mamay.task1.util.MessageLogger;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Task1Runner {

  public static void main(String[] args) {
    testAquarium1();
  }

  public static void testAquarium1() {
    Aquarium aqua1 = new Aquarium(478, "Table Aquarium", Item.TURTLE, 1);
    Aquarium aqua2 = new Aquarium(578, "Rectangular Aquarium ", Item.FISH, 3);

    try {
      aqua1.addComponent(Item.SAND);
      aqua1.addComponent(Item.SHELL);
      aqua1.addComponent(Item.TURTLE);
      log.debug("printing Info about aqua1\n" + aqua1);

      aqua2.addComponent(Item.FISH);
      aqua2.addComponent(Item.CASTLE);
      aqua2.addComponent(Item.ALGA);
      aqua2.addComponent(Item.TOOLS);
      log.debug("printing Info about aqua2\n" + aqua2);

      aqua2.addComponent(Item.TOOLS);
      log.debug("Orders in aqua2: " + aqua2.getCountOfOrders());

      aqua2.addComponent(Item.WATER);

    } catch (Lab1Exception e) {
      log.error(e.getMessage());
    }

    MessageLogger info = new MessageLogger();
    info.printCheck(aqua1);
    info.printCheck(aqua2);
  }
}
