/*
 * Copyright (c) 2023
 */
package com.mamay.lambdas.pack1;

public class Lambda2 {

  public static void main(String[] args) {
    Converter<String, Integer> integerConverter1 = Integer::valueOf;
    Integer converted1 = integerConverter1.convert("123");
    System.out.println(converted1); // result: 123

    // method reference
    Converter<String, Integer> integerConverter2 = Integer::valueOf;
    Integer converted2 = integerConverter2.convert("123");
    System.out.println(converted2); // result: 123

    Something something = new Something();

    Converter<String, String> stringConverter = something::startsWith;
    String converted3 = stringConverter.convert("Java");
    System.out.println(converted3); // result J
  }

  @FunctionalInterface
  public static interface Converter<F, T> {
    T convert(F from);
  }

  static class Something {
    String startsWith(String s) {
      return String.valueOf(s.charAt(0));
    }
  }
}
