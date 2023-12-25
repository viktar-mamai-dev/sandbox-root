/*
 * Copyright (c) 2023
 */
package com.mamay.task1.util;

import java.util.Random;

public class Generator {
  private static final int DEFAULT_VALUE = 1;
  private static final int MAX_DIGITS = 10;
  private static final Random RAND = new Random();

  public static int generateRandomNumber(int digits) {
    if (digits <= 0 || digits >= MAX_DIGITS) return DEFAULT_VALUE;
    int lowerNum = (int) Math.pow(10, digits - 1);
    int higherNum = (int) Math.pow(10, digits);
    return lowerNum + RAND.nextInt(higherNum - lowerNum);
  }
}
