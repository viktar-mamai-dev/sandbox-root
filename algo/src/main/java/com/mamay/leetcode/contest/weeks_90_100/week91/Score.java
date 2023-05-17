package com.mamay.leetcode.contest.weeks_90_100.week91;

import java.util.Scanner;

public class Score {

  public static int matrixScore(int[][] A) {
    int rowCount = A.length;
    for (int i = 0; i < rowCount; i++) {
      if (A[i][0] == 0) {
        flipArray(A[i]);
      }
    }
    int colCount = A[0].length;
    char[][] ch = new char[rowCount][colCount];
    for (int i = 0; i < colCount; i++) {
      boolean flag = zeroGreater(A, i);
      for (int k = 0; k < rowCount; k++) {
        if (flag) A[k][i] = 1 - A[k][i];
        ch[k][i] = A[k][i] == 0 ? '0' : '1';
      }
    }

    int sum = 0;
    for (int i = 0; i < rowCount; i++) {
      sum += Integer.parseInt(String.valueOf(ch[i]), 2);
    }
    return sum;
  }

  private static boolean zeroGreater(int[][] A, int colNum) {
    int rowCount = A.length;
    int count = 0;
    for (int i = 0; i < rowCount; i++) {
      if (A[i][colNum] == 0) count++;
    }
    return count > rowCount / 2;
  }

  private static void flipArray(int[] d) {
    int len = d.length;
    for (int i = 0; i < len; i++) {
      d[i] = 1 - d[i];
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int m = scanner.nextInt();
    int n = scanner.nextInt();
    int a[][] = new int[m][n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        a[i][j] = scanner.nextInt();
      }
    }
    System.out.println(matrixScore(a));
    scanner.close();
  }
}
