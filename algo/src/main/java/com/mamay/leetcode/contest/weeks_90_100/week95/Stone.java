package com.mamay.leetcode.contest.weeks_90_100.week95;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Stone {

  public static boolean stoneGame(int[] piles) {
    int len = piles.length;
    int i1 = 0, i2 = len - 1;
    int alexSum = 0, leeSum = 0;
    for (int i = 0; i < len; i++) {
      int val;
      if (i1 == i2) {
        val = piles[i1];
      } else {
        int left = Math.max(piles[i1] - piles[i1 + 1], piles[i1] - piles[i2]);
        int right = Math.max(piles[i2] - piles[i2 - 1], piles[i2] - piles[i1]);

        if (left >= right) {
          val = piles[i1++];
        } else {
          val = piles[i2--];
        }
      }
      if (i % 2 == 0) {
        alexSum += val;
      } else {
        leeSum += val;
      }
    }
    return alexSum > leeSum;
  }

  private static final String INPUT_FILE = "input.txt";
  private static final String OUTPUT_FILE = "output.txt";

  public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(new File(INPUT_FILE));
    int q = scanner.nextInt();
    scanner.nextLine();

    try (PrintWriter writer = new PrintWriter(new FileWriter(new File(OUTPUT_FILE)))) {
      for (int qi = 0; qi < q; qi++) {
        // TODO
        int array[] = readArray(scanner);

        writer.println(stoneGame(array));
      }
      scanner.close();
    }
  }

  private static int[] readArray(Scanner scanner) {
    String tokens[] = scanner.nextLine().replaceAll("\\[|\\]", "").split(",");
    int len = tokens.length;
    int res[] = new int[len];
    for (int k = 0; k < len; k++) {
      res[k] = Integer.parseInt(tokens[k]);
    }
    System.out.println("Length of array is: " + len);
    return res;
  }
}
