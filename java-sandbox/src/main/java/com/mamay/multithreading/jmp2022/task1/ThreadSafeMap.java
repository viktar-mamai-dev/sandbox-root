package com.mamay.multithreading.jmp2022.task1;

import java.util.HashMap;
import java.util.Map;

class ThreadSafeMap extends HashMap<Integer, Integer> {
  public ThreadSafeMap(Map<Integer, Integer> map) {
    super(map);
  }

  @Override
  public synchronized Integer put(Integer key, Integer value) {
    return super.put(key, value);
  }

  @Override
  public synchronized Integer get(Object key) {
    return super.get(key);
  }

  @Override
  public synchronized Integer remove(Object key) {
    return super.remove(key);
  }
}
