/*
 * Copyright (c) 2023
 */
package book1.ch6;

// import book1.ch5.Owl;

public class ExceptionTester {
  public static void main(String[] args) {
    try {
      System.out.println("R");
    } catch (Exception e) { // compile error, because could not be thrown in try block
      System.out.println("Z");
    }

    try {
      my1();
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Z");
    }
    // Owl owl = new Owl();
  }

  // possible:
  public static void my1() throws Exception {
    Exception e = null;
    throw e;
  }
}
