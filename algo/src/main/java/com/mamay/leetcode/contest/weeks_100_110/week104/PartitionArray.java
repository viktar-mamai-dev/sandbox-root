package com.mamay.leetcode.contest.weeks_100_110.week104;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class PartitionArray {
  private static final String INPUT_FILE = "input.txt";
  private static final String OUTPUT_FILE = "output.txt";

  private static int solve(int[] A) {
    int n = A.length;
    int border = 0;
    int i = 1;
    int topSoFar = A[0];
    int topAll = A[0];
    while (i < n) {
      if (A[i] < topSoFar) {
        border = i;
        topSoFar = topAll;
      } else {
        topAll = Math.max(topAll, A[i]);
      }
      i++;
    }
    return border + 1;
  }

  public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(new File(INPUT_FILE));
    int q = scanner.nextInt();
    scanner.nextLine();

    try (PrintWriter writer = new PrintWriter(new FileWriter(new File(OUTPUT_FILE)))) {
      for (int qi = 0; qi < q; qi++) {

        int matrix[] = readIntegerArray(scanner);

        writer.println(solve(matrix));
      }
      scanner.close();
    }
  }

  private static int[] readIntegerArray(Scanner scanner) {
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
