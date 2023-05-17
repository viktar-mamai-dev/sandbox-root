/*
 * Copyright (c) 2023
 */
package book1;

import static java.lang.System.out;

import book1.ch5.Owl;

public class StringNullTester {
  public static void main(String[] args) {
    out.println(getNull() + 1); // null1
    Long l1 = 10l;
    Number f1 = 10;
    int i2 = 10;
    long l2 = 10;
    double d2 = 10.0;
    float f2 = 10.0f;
    IndexOutOfBoundsException exc1 = new ArrayIndexOutOfBoundsException();
    StringIndexOutOfBoundsException exc2 = new StringIndexOutOfBoundsException();
    out.println(
        (i2 == d2)
            + " "
            + (l2 == f2)
            + " "
            + (f1 == l1)
            + " : "
            + (exc1 == exc2)); // true true false

    Owl owl = new Owl();
  }

  private static String getNull() {
    return null;
  }
}
