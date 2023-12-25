package com.mamay.task3.comparator;

import com.mamay.task3.entity.TextComponent;
import java.util.Comparator;
import java.util.Map;

public class HashMapValueComparator implements Comparator<TextComponent> {

  private final Map<TextComponent, Integer> map;

  public HashMapValueComparator(Map<TextComponent, Integer> base) {
    this.map = base;
  }

  @Override
  public int compare(TextComponent a, TextComponent b) {
    return Double.compare(map.get(b), map.get(a));
  }
}
