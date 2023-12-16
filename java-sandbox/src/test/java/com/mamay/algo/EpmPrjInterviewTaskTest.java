package com.mamay.algo;

import static com.mamay.EpmPrjInterviewTask.Month;
import static com.mamay.EpmPrjInterviewTask.calculateDayOfYear;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class EpmPrjInterviewTaskTest {

  @Test
  public void test1() {
    System.out.println(calculateDayOfYear(Month.AUG, 1, 2022));
    assertThrows(RuntimeException.class, () -> calculateDayOfYear(Month.JAN, 32, 2022));
    assertThrows(RuntimeException.class, () -> calculateDayOfYear(Month.JAN, -2, 2022));
  }
}
