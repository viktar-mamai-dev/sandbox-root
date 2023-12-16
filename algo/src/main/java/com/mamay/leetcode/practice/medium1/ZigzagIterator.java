package com.mamay.leetcode.practice.medium1;

import java.util.Iterator;
import java.util.List;

public class ZigzagIterator {
  private final Iterator<Integer> iter1, iter2;
  private boolean isFirst;

  public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
    iter1 = v1.iterator();
    iter2 = v2.iterator();
    isFirst = true;
  }

  public int next() {
    if (isFirst) {
      isFirst = false;
      if (iter1.hasNext()) {
        return iter1.next();
      } else {
        return iter2.next();
      }
    } else {
      isFirst = true;
      if (iter2.hasNext()) {
        return iter2.next();
      } else {
        return iter1.next();
      }
    }
  }

  public boolean hasNext() {
    return iter1.hasNext() || iter2.hasNext();
  }
}
