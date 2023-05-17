/*
 * Copyright (c) 2023
 */
package book1;

import static java.lang.System.out;

public class InheritanceTester {
  public static void main(String[] args) {
    new D().meth();
  }
}

interface A {
  int bi = 20;

  public default void m2() {
    out.println("A");
  }
}

interface B extends A {
  int bi = 10;

  public default void m1() {
    out.println("B");
  }
}

class C implements B, A {
  public void m1() {
    // TODO super.m2(); // super can't be used with interface methods
    out.println("C");
  }
}

class D {
  void meth() {
    C c = new C();
    c.m1();
    // out.println(c.bi); // actually compile time errorr - ambiguous call
  }
}
