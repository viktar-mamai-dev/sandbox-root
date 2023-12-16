/*
 * Copyright (c) 2023
 */
package com.mamay.memoryerrors;

import java.util.HashMap;
import java.util.Map;

public class KeylessEntry {

  static class Key {
    Integer id;

    Key(Integer id) {
      this.id = id;
    }

    @Override
    public int hashCode() {
      return id.hashCode();
    }
  }

  public static void main(String[] args) {
    Map m = new HashMap();
    while (true)
      for (int i = 0; i < 10000; i++)
        if (!m.containsKey(new Key(i))) m.put(new Key(i), "Number:" + i);
  }
}
