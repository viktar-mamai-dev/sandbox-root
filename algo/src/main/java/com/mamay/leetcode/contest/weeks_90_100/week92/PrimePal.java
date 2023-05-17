package com.mamay.leetcode.contest.weeks_90_100.week92;

import java.util.Scanner;

public class PrimePal {

  public static int primePalindrome(int N) {
    int m = N;
    boolean flag = false;
    while (!flag) {
      flag = isPal(m) && isPrime(m);
      m++;
    }
    return m - 1;
  }

  private static boolean isPal(long N) {
    StringBuilder builder = new StringBuilder(String.valueOf(N));
    int m = Integer.valueOf(builder.reverse().toString());
    return N == m;
  }

  private static boolean isPrime(long N) {
    if (N == 1) return false;
    if (N == 2) return true;
    if (N % 2 == 0) return false;
    for (int k = 3; k <= Math.sqrt(N); k += 2) {
      if (N % k == 0) return false;
    }
    return true;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int N = scanner.nextInt();
    System.out.println(primePalindrome(N));
    scanner.close();
  }
}
