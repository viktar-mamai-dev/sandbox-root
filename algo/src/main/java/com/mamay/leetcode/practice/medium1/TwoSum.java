package com.mamay.leetcode.practice.medium1;

import java.util.HashMap;
import java.util.Map;

class TwoSum {
  private final HashMap<Integer, Integer> map;

  /** Initialize your data structure here. */
  public TwoSum() {
    this.map = new HashMap<Integer, Integer>();
  }

  /** Add the number to an internal data structure.. */
  public void add(int number) {
    int value = map.getOrDefault(number, 0) + 1;
    map.put(number, value);
  }

  /** Find if there exists any pair of numbers which sum is equal to the value. */
  public boolean find(int value) {
    for (Map.Entry<Integer, Integer> entry : this.map.entrySet()) {
      int complement = value - entry.getKey();
      if (complement != entry.getKey()) {
        if (this.map.containsKey(complement)) return true;
      } else {
        if (entry.getValue() > 1) return true;
      }
    }
    return false;
  }
}
