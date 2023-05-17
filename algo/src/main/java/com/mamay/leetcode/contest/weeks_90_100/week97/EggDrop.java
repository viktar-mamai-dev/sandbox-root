package com.mamay.leetcode.contest.weeks_90_100.week97;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class EggDrop {

  private static int[][] arr;

  public static int superEggDrop(int k, int n) {
    oper = 0;
    if (k == 1) return n;
    if (n == 1) return 1;
    arr = new int[k + 1][n + 1];
    return dp(k, n);
  }

  private static int oper;

  private static int dp(int k, int n) {
    oper++;
    if (arr[k][n] != 0) {
      return arr[k][n];
    }
    int ans;
    if (n == 0) {
      ans = 0;
    } else if (k == 1) {
      ans = n;
    } else {
      int low = 1, high = n;
      while (low + 1 < high) {
        int x = (low + high) / 2;
        int t1 = dp(k - 1, x);
        int t2 = dp(k, n - x);
        if (t1 < t2) {
          low = x;
        } else if (t1 > t2) {
          high = x;
        } else {
          low = high = x;
        }
      }

      ans =
          1
              + Math.min(
                  Math.max(dp(k - 1, low - 1), dp(k, n - low)),
                  Math.max(dp(k - 1, high - 1), dp(k, n - high)));
    }
    arr[k][n] = ans;
    return ans;
  }

  private static final String INPUT_FILE = "input.txt";
  private static final String OUTPUT_FILE = "output.txt";

  public static void main(String[] args) throws IOException {
    try (Scanner scanner = new Scanner(new File(INPUT_FILE))) {
      int q = scanner.nextInt();
      scanner.nextLine();

      try (PrintWriter writer = new PrintWriter(new FileWriter(new File(OUTPUT_FILE)))) {
        for (int qi = 0; qi < q; qi++) {
          int k = scanner.nextInt();
          int n = scanner.nextInt();

          writer.println(superEggDrop(k, n));
          System.out.println(oper++);
        }
      }
    }
  }
}
