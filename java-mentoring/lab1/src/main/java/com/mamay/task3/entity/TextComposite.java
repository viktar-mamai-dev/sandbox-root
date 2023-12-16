package com.mamay.task3.entity;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements TextComponent {
  private final List<TextComponent> textComponents = new ArrayList<TextComponent>();

  @Override
  public void operation() {
    for (TextComponent c : textComponents) {
      c.operation();
    }
  }

  @Override
  public void addElement(TextComponent c) {
    textComponents.add(c);
  }

  @Override
  public void removeElement(TextComponent c) {
    textComponents.remove(c);
  }

  @Override
  public TextComponent getChild(int index) {
    return textComponents.get(index);
  }

  @Override
  public int size() {
    return textComponents.size();
  }

  @Override
  public String getData() {
    StringBuilder sb = new StringBuilder();
    for (TextComponent comp : textComponents) {
      sb.append(comp.getData());
    }
    return sb.toString();
  }
}
