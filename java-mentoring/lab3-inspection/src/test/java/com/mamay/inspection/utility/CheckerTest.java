package com.mamay.inspection.utility;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CheckerTest {

    @Test
    public void isIntegerTest() {
        String notInt = "65ab";
        boolean result = Checker.isInteger(notInt);
        assertFalse(result);
        notInt = "87_567";
        result = Checker.isInteger(notInt);
        assertFalse(result);
        String number = "087";
        result = Checker.isInteger(number);
        assertTrue(result);
    }

    @Test
    public void isRangeTest() {
        boolean result = Checker.inRange(-1, 10);
        assertFalse(result);
        result = Checker.inRange(3, 1000);
        assertFalse(result);
        result = Checker.inRange(5, 82397);
        assertTrue(result);
    }

}
