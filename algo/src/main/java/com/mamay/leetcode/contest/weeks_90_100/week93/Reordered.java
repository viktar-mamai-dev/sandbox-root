package com.mamay.leetcode.contest.weeks_90_100.week93;

import java.util.Arrays;
import java.util.Scanner;

public class Reordered {

  public static boolean reorderedPowerOf2(int N) {

    char numbers[] = String.valueOf(N).toCharArray();
    int len = numbers.length;
    boolean used[] = new boolean[len];
    Arrays.fill(used, false);
    char newNum[] = new char[len];
    return rec(numbers, len, used, 0, newNum);
  }

  private static boolean rec(
      char[] numbers, int len, boolean used[], int usedCount, char newNum[]) {
    if (len == usedCount) {
      int val = Integer.parseInt(String.valueOf(newNum));
      return (val & (val - 1)) == 0;
    }

    for (int k = 0; k < len; k++) {
      if (!used[k] && (usedCount != 0 || numbers[k] != '0')) {
        used[k] = true;
        newNum[usedCount] = numbers[k];
        boolean flag = rec(numbers, len, used, usedCount + 1, newNum);
        if (flag) return true;
        used[k] = false;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int N = scanner.nextInt();
    System.out.println(reorderedPowerOf2(N));
    scanner.close();
  }
}
