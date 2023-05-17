package com.mamay.leetcode.contest.weeks_80_90.week87;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Longest {

  public static int longestMountain(int[] A) {

    int len = A.length;
    if (len < 3) return 0;
    boolean climb = false;
    boolean descent = false;
    int climbLen = 0;
    int totalLen = 0;
    int longestMount = 0;
    for (int i = 1; i < len; i++) {
      if (A[i] > A[i - 1]) {
        if (climb) climbLen++;
        else {
          if (descent) {
            if (totalLen > longestMount) longestMount = totalLen;
            descent = false;
            totalLen = 0;
          }
          climb = true;
          climbLen = 2;
        }
      } else if (A[i] < A[i - 1]) {
        if (descent) totalLen++;
        else if (climb) {
          climb = false;
          descent = true;
          totalLen = climbLen + 1;
        }
      } else {
        climb = false;
        descent = false;
        if (totalLen > longestMount) longestMount = totalLen;
        totalLen = 0;
      }
    }
    if (descent) {
      if (totalLen > longestMount) longestMount = totalLen;
    }
    return longestMount;
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

        // int matrix[][] = readGraph(scanner);

        writer.println(longestMountain(array));
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
