package com.mamay.inspection.utility;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CheckerTest {

  @Test
  public void isIntegerTest() {
    Assertions.assertFalse(Checker.isInteger("65ab"));
    assertFalse(Checker.isInteger("87_567"));
    assertTrue(Checker.isInteger("087"));
  }

  @Test
  public void isRangeTest() {
    assertFalse(Checker.inRange(-1, 10));
    assertFalse(Checker.inRange(3, 1000));
    assertTrue(Checker.inRange(5, 82397));
  }
}
