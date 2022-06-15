package com.mamay.task1.util;

import java.util.Random;

public class Generator {
    private final static int DEFAULT_VALUE = 1;
    private final static int MAX_DIGITS = 10;

    public static int generateRandomNumber(int digits) {
        if (digits <= 0 || digits >= MAX_DIGITS)
            return DEFAULT_VALUE;
        int lowerNum = (int) Math.pow(10, digits - 1);
        int higherNum = (int) Math.pow(10, digits);
        Random r = new Random();
        return lowerNum + r.nextInt(higherNum - lowerNum);
    }
}
