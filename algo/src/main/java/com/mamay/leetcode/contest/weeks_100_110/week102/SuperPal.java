package com.mamay.leetcode.contest.weeks_100_110.week102;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SuperPal {

  // идея в том, чтобы сразу создавать палиндром, а потом проверять только его квадрат
  // получается намного быстрее
  public int superpalindromesInRange(String sL, String sR) {
    long L = Long.valueOf(sL);
    long R = Long.valueOf(sR);
    int MAGIC = (int) Math.pow(10, 18 * 0.25);
    int ans = 0;

    // count odd length;
    for (int k = 1; k < MAGIC; ++k) {
      StringBuilder sb = new StringBuilder(Integer.toString(k));
      for (int i = sb.length() - 2; i >= 0; --i) sb.append(sb.charAt(i));
      long v = Long.valueOf(sb.toString());
      v *= v;
      if (v > R) break;
      if (v >= L && isPal2(v)) ans++;
    }

    // count even length;
    for (int k = 1; k < MAGIC; ++k) {
      StringBuilder sb = new StringBuilder(Integer.toString(k));
      for (int i = sb.length() - 1; i >= 0; --i) sb.append(sb.charAt(i));
      long v = Long.valueOf(sb.toString());
      v *= v;
      if (v > R) break;
      if (v >= L && isPal2(v)) ans++;
    }

    return ans;
  }

  public int superpalindromesInRange2(String L, String R) {

    long l = (long) Math.ceil(Math.sqrt(Long.parseLong(L)));
    long r = (long) Math.floor(Math.sqrt(Long.parseLong(R)));

    int count = 0;
    for (long k = l; k <= r; k++) {
      if (isPal2(k) && isPal2(k * k)) {
        count++;
      }
    }
    return count;
  }

  private boolean isPal2(long k) {

    long copy = k;
    long val = 0;
    while (copy > 0) {
      val = 10 * val + copy % 10;
      copy /= 10;
    }

    return val == k;
  }

  // очень медленное по скорости
  private boolean isPal(long k) {
    String s = String.valueOf(k);
    int len = s.length();
    for (int i = 0; i < len / 2; i++) {
      if (s.charAt(i) != s.charAt(len - 1 - i)) {
        return false;
      }
    }
    return true;
  }

  private static final String INPUT_FILE = "input.txt";
  private static final String OUTPUT_FILE = "output.txt";

  public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(new File(INPUT_FILE));
    int q = scanner.nextInt();
    scanner.nextLine();

    SuperPal s = new SuperPal();
    try (PrintWriter writer = new PrintWriter(new FileWriter(new File(OUTPUT_FILE)))) {
      for (int qi = 0; qi < q; qi++) {
        long startTime = System.currentTimeMillis();
        String L = scanner.next();
        String R = scanner.next();

        writer.println(s.superpalindromesInRange(L, R));
        System.out.println("Time: " + (System.currentTimeMillis() - startTime));
      }
      scanner.close();
    }
  }
}
