package com.mamay.inspection.utility;

public class Checker {

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean inRange(int digitCount, int number) {
        if (digitCount <= 0 || digitCount >= 10) {
            return false;
        }
        double lowNumber = Math.pow(10, digitCount - 1);
        double upperNumber = Math.pow(10, digitCount);
        return number >= lowNumber && number < upperNumber;
    }

}
