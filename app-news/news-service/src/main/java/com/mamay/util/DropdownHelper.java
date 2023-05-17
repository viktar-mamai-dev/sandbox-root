package com.mamay.util;

import java.util.Collection;

public class DropdownHelper {
  /** this method is used on jsp pages to faciliate work drop down lists */
  public static boolean contains(Collection<?> collection, Object o) {
    if (collection == null) {
      return false;
    }
    if (o == null) {
      return false;
    }
    return collection.contains(o);
  }
}
