/*
 * Copyright (c) 2023
 */
package book1.ch4;

public class Conversions {
  static void play(Long l) {
    final int i = 32_000;
    short s2 = i;
    short s = 10;
    s = 23;
    s = 31_111;
    while (true) {
      if (i >= 3) break;
      if (i >= 3) continue;
    }
  }

  private Conversions() {}

  public static void main(String[] args) {
    play(4l);
    // int can be casted to long only, not to Long
    // long (int) can be autoboxed to Long (Integer)
    // int, long can be casted to Object : int->Integer->Object
    float a = 4.5678f; // float needs f
  }

  void meth1(int a) {
    a = 1;
  }

  void Conversions() {}
}

class MyDate extends java.util.Date {}

class Person {
  void other() {}
}

class Livera extends Person {
  @Override
  public void other() {}
}
