package com.mamay.leetcode.practice.medium2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class RotateImage_48 {

  public static void rotate(int[][] matrix) {
    int n = matrix.length;
    int rotateCount = n / 2;
    for (int j = 0; j < rotateCount; j++) {
      for (int i = j; i < n - j - 1; i++) {
        rotate(matrix, n, j, i);
      }
    }
  }

  private static void rotate(int[][] matrix, int n, int j, int i) {

    int temp = matrix[j][i];
    matrix[j][i] = matrix[n - i - 1][j];
    matrix[n - i - 1][j] = matrix[n - j - 1][n - i - 1];
    matrix[n - j - 1][n - i - 1] = matrix[i][n - j - 1];
    matrix[i][n - j - 1] = temp;
  }

  private static final String INPUT_FILE = "input.txt";
  private static final String OUTPUT_FILE = "output.txt";

  public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(new File(INPUT_FILE));
    int q = scanner.nextInt();
    scanner.nextLine();

    try (PrintWriter writer = new PrintWriter(new FileWriter(new File(OUTPUT_FILE)))) {
      for (int qi = 0; qi < q; qi++) {
        int size = scanner.nextInt();
        scanner.nextLine();

        int matrix[][] = readMatrixWithSize(scanner, size);
        rotate(matrix);
        writer.println(Arrays.deepToString(matrix));
      }
      scanner.close();
    }
  }

  private static int[][] readMatrixWithSize(Scanner scanner, int size) {

    int res[][] = new int[size][size];
    for (int k = 0; k < size; k++) {
      String tokens[] = scanner.nextLine().replaceAll("]|\\[", "").split(",");
      int len2 = tokens.length;
      res[k] = new int[len2];
      for (int m = 0; m < len2; m++) {
        res[k][m] = Integer.parseInt(tokens[m].trim());
      }
    }
    System.out.println("Size of matrix is: " + size);
    return res;
  }
}
