package com.mamay.leetcode.contest.weeks_100_110.week103;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class SmallestRange1 {

  public int smallestRangeI(int[] A, int K) {
    Arrays.sort(A);
    int len = A.length;

    int diff = A[len - 1] - A[0];

    int newMin = A[0] + K, newMax = A[len - 1] - K;
    if (newMax < newMin) {
      int temp = newMax;
      newMax = newMin;
      newMin = temp;
    }
    for (int i = 1; i < len; i++) {
      int diff1 = A[i] + K - newMin;
      int diff2 = newMax - A[i] + K;
      if (Math.abs(diff1) < Math.abs(diff2)) {
        A[i] += K;
        newMax = Math.max(newMax, A[i]);
      } else {
        A[i] -= K;
        newMin = Math.min(newMin, A[i]);
      }
      if (newMax - newMin >= diff) {
        return diff;
      }
    }
    return newMax - newMin;
  }

  private static final String INPUT_FILE = "input.txt";
  private static final String OUTPUT_FILE = "output.txt";

  public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(new File(INPUT_FILE));
    int q = scanner.nextInt();
    scanner.nextLine();

    SmallestRange1 main = new SmallestRange1();
    try (PrintWriter writer = new PrintWriter(new FileWriter(new File(OUTPUT_FILE)))) {
      for (int qi = 0; qi < q; qi++) {
        // TODO
        int array[] = readArray(scanner);

        int k = scanner.nextInt();
        scanner.nextLine();

        writer.println(main.smallestRangeI(array, k));
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
