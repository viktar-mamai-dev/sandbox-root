/*
 * Copyright (c) 2023
 */
package com.mamay.task1.model;

import com.mamay.Lab1Exception;
import com.mamay.task1.util.AquaContainer;
import com.mamay.task1.util.Generator;
import java.util.HashSet;
import java.util.Set;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Aquarium {

  private final int id;
  // constants
  private final int MIN_NAME_LENGTH = 4;
  private final int MAX_NAME_LENGTH = 20;
  private final int MIN_ORDERS = 1;
  private final int MAX_ORDERS = 3;
  private final int ID_DIGITS = 5;
  private final int DEFAULT_ORDERS = 1;
  private int customerId;
  private String name;
  private Item type;
  private Set<Component> components;
  private int countOfOrders;

  public Aquarium(int customerId, String name, Item type, int countOfOrders) {
    super();
    this.id = Generator.generateRandomNumber(ID_DIGITS);
    this.setCustomerId(customerId);
    this.setName(name);
    this.setType(type);
    this.setCountOfOrders(countOfOrders);
    this.components = new HashSet<Component>();
  }

  public int getId() {
    return id;
  }

  public Item getType() {
    return type;
  }

  public void setType(Item type) {
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    if (name.length() >= MIN_NAME_LENGTH && name.length() <= MAX_NAME_LENGTH) {
      this.name = name;
    } else {
      this.name = "Client#" + this.id;
    }
  }

  public int getCustomerId() {
    return customerId;
  }

  public void setCustomerId(int customerId) {
    this.customerId = customerId;
  }

  public int getCountOfOrders() {
    return countOfOrders;
  }

  public void setCountOfOrders(int countOfOrders) {
    if (countOfOrders >= MIN_ORDERS && countOfOrders <= MAX_ORDERS) {
      this.countOfOrders = countOfOrders;
    } else {
      this.countOfOrders = DEFAULT_ORDERS;
    }
  }

  public Set<Component> getComponents() {
    return components;
  }

  public void print() {
    String output =
        "[Order: "
            + this.id
            + " Client: "
            + this.customerId
            + " Name: "
            + name
            + " Items: "
            + components.size()
            + " ]";
    log.debug(output);
  }

  public void newComponent(Item item) throws Lab1Exception {
    AquaContainer helper = new AquaContainer();
    Set<Item> possibleItems = helper.getPossibleComponents().keySet();
    if (!possibleItems.contains(item)) {
      throw new Lab1Exception("Error! Such component does not exist!");
    }
    for (Component c : components) {
      if (c.getName().equals(item)) {
        log.error("Component " + item.getName() + " already exists!");
        return;
      }
    }
    Component c = helper.getPossibleComponent(item);
    if (c != null) {
      components.add(c);
    }
    if (helper.isFullAquarium(this)) {
      log.debug("Aquarium is full!");
    }
  }

  public int getTotalCost() {
    int cost = type.getCost();
    for (Component component : components) {
      cost += component.getCost();
    }
    return cost;
  }
}
