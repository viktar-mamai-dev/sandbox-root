/*
 * Copyright (c) 2023
 */
package com.mamay.task1.util;

import com.mamay.task1.model.Aquarium;
import com.mamay.task1.model.Component;
import com.mamay.task1.model.Item;

import java.util.*;
import java.util.stream.Collectors;

public class AquaContainer {
  private final List<Component> componentList = List.of(
          new Component(10, Item.FISH),
          new Component(30, Item.TURTLE),
          new Component(10, Item.CASTLE),
          new Component(3, Item.ALGA),
          new Component(2, Item.SHELL),
          new Component(3, Item.SAND),
          new Component(40, Item.TOOLS)
  );

  public Optional<Component> getPossibleComponent(Item name) {
    return componentList.stream()
            .filter(component -> component.getName().equals(name))
            .findAny();
  }

  public Set<Item> getComponentNames() {
    return this.componentList.stream()
            .map(Component::getName).collect(Collectors.toSet());
  }

  public boolean isFullAquarium(Aquarium aqua) {
    Set<Item> names = this.getComponentNames();
    return aqua.getComponents().stream()
            .map(Component::getName)
            .allMatch(names::contains);
  }
}
