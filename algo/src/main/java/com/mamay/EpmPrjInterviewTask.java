package com.mamay;

public class EpmPrjInterviewTask {
  public static int dayOfYear(int month, int dayOfMonth, int year) {
    if (month == 2) {
      dayOfMonth += 31;
    } else if (month == 3) {
      dayOfMonth += 59;
    } else if (month == 4) {
      dayOfMonth += 90;
    } else if (month == 5) {
      dayOfMonth += 31 + 28 + 31 + 30;
    } else if (month == 6) {
      dayOfMonth += 31 + 28 + 31 + 30 + 31;
    } else if (month == 7) {
      dayOfMonth += 31 + 28 + 31 + 30 + 31 + 30;
    } else if (month == 8) {
      dayOfMonth += 31 + 28 + 31 + 30 + 31 + 30 + 31;
    } else if (month == 9) {
      dayOfMonth += 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31;
    } else if (month == 10) {
      dayOfMonth += 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30;
    } else if (month == 11) {
      dayOfMonth += 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31;
    } else if (month == 12) {
      dayOfMonth += 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31 + 31;
    }
    return dayOfMonth;
  }

  public static enum Month {
    JAN(31),
    FEB(28),
    MARCH(31),
    APR(30),
    MAY(31),
    JUN(30),
    JUL(31),
    AUG(31);

    private final int DAYS;

    Month(int maxDays) {
      this.DAYS = maxDays;
    }

    public int getMaxDays() {
      return this.DAYS;
    }
  }

  public static int calculateDayOfYear(Month month, int dayOfMonth, int year) {
    if (year < 0 || month == null || dayOfMonth > month.getMaxDays() || dayOfMonth <= 0)
      throw new RuntimeException("Invalid");
    int resultDay = dayOfMonth;
    for (Month m : Month.values()) {
      if (m.equals(month)) break;
      resultDay += m.getMaxDays();
    }
    if (isVisokosnyy(month, year)) {
      resultDay += 1;
    }
    return resultDay;
  }

  private static boolean isVisokosnyy(Month month, int year) {
    if (month.ordinal() <= 1) return false;
    if (year % 4 != 0) return false;
    if (year % 100 != 0) return true;
    return year % 400 == 0;
  }
}
