/*
 * Copyright (c) 2023
 */
package com.mamay.task1.util;

import com.mamay.task1.model.Aquarium;
import com.mamay.task1.model.Component;
import com.mamay.task1.model.Item;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AquaContainer {
  private final Map<Item, Component> possibleComponents;

  public AquaContainer() {
    possibleComponents = new HashMap<Item, Component>();
    possibleComponents.put(Item.FISH, new Component(10, Item.FISH));
    possibleComponents.put(Item.TURTLE, new Component(30, Item.TURTLE));
    possibleComponents.put(Item.CASTLE, new Component(10, Item.CASTLE));
    possibleComponents.put(Item.ALGA, new Component(3, Item.ALGA));
    possibleComponents.put(Item.SHELL, new Component(2, Item.SHELL));
    possibleComponents.put(Item.SAND, new Component(3, Item.SAND));
    possibleComponents.put(Item.TOOLS, new Component(40, Item.TOOLS));
  }

  public Map<Item, Component> getPossibleComponents() {
    return possibleComponents;
  }

  public Component getPossibleComponent(Item name) {
    return possibleComponents.get(name);
  }

  public Set<Item> getComponentNames() {
    return this.possibleComponents.keySet();
  }

  public boolean isFullAquarium(Aquarium aqua) {
    Set<Item> names = this.getComponentNames();
    return aqua.getComponents().stream().map(Component::getName).anyMatch(names::contains);
  }
}
