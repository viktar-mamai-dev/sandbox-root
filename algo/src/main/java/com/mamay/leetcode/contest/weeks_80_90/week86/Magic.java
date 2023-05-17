package com.mamay.leetcode.contest.weeks_80_90.week86;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Magic {

  public static int numMagicSquaresInside(int[][] grid) {
    int count = 0;
    int rows = grid.length;
    int cols = grid.length;
    for (int i = 0; i < rows - 2; i++) {
      for (int j = 0; j < cols - 2; j++) {
        if (isMagic(grid, i, j)) count++;
      }
    }
    return count;
  }

  private static boolean isMagic(int grid[][], int iLeft, int jTop) {
    final int FIFTEEN = 15;
    if (grid[iLeft][jTop] + grid[iLeft + 1][jTop] + grid[iLeft + 2][jTop] != FIFTEEN) {
      return false;
    }
    if (grid[iLeft][jTop + 1] + grid[iLeft + 1][jTop + 1] + grid[iLeft + 2][jTop + 1] != FIFTEEN) {
      return false;
    }
    if (grid[iLeft][jTop + 2] + grid[iLeft + 1][jTop + 2] + grid[iLeft + 2][jTop + 2] != FIFTEEN) {
      return false;
    }
    if (grid[iLeft][jTop] + grid[iLeft][jTop + 1] + grid[iLeft][jTop + 2] != FIFTEEN) {
      return false;
    }
    if (grid[iLeft + 1][jTop] + grid[iLeft + 1][jTop + 1] + grid[iLeft + 1][jTop + 2] != FIFTEEN) {
      return false;
    }
    if (grid[iLeft + 2][jTop] + grid[iLeft + 2][jTop + 1] + grid[iLeft + 2][jTop + 2] != FIFTEEN) {
      return false;
    }
    if (grid[iLeft][jTop] + grid[iLeft + 1][jTop + 1] + grid[iLeft + 2][jTop + 2] != FIFTEEN) {
      return false;
    }
    if (grid[iLeft][jTop + 2] + grid[iLeft + 1][jTop + 1] + grid[iLeft + 2][jTop] != FIFTEEN) {
      return false;
    }
    boolean used[] = new boolean[16];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        int val = grid[iLeft + i][jTop + j];
        if (!used[val]) {
          used[val] = true;
        }
      }
    }
    for (int i = 0; i < 9; i++) {
      if (!used[i]) return false;
    }
    return true;
  }

  private static final String INPUT_FILE = "input.txt";
  private static final String OUTPUT_FILE = "output.txt";

  public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(new File(INPUT_FILE));
    int q = scanner.nextInt();
    scanner.nextLine();

    try (PrintWriter writer = new PrintWriter(new FileWriter(new File(OUTPUT_FILE)))) {
      for (int qi = 0; qi < q; qi++) {
        int matrix[][] = readMatrix(scanner);

        writer.println(numMagicSquaresInside(matrix));
      }
      scanner.close();
    }
  }

  private static int[][] readMatrix(Scanner scanner) {
    List<String> lines = new ArrayList<>();
    while (scanner.hasNextLine()) {
      lines.add(scanner.nextLine());
    }
    int len = lines.size();
    int res[][] = new int[len][];
    for (int k = 0; k < len; k++) {
      String tokens[] = lines.get(k).replaceAll("[|]|,", "").split(" ");
      int len2 = tokens.length;
      res[k] = new int[len2];
      for (int m = 0; m < len2; m++) {
        res[k][m] = Integer.parseInt(tokens[m]);
      }
    }
    System.out.println("Size of matrix is: " + len + " * " + res[0].length);
    return res;
  }
}
