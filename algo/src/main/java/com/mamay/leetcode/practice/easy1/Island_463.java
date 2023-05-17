package com.mamay.leetcode.practice.easy1;

public class Island_463 {
  public int islandPerimeter(int[][] grid) {
    if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
    int sum = 0;
    int m = grid.length;
    int n = grid[0].length;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == 1) sum += getPerimeter(grid, i, j);
      }
    }
    return sum;
  }

  private int getPerimeter(int[][] grid, int i, int j) {
    int sum = 0;
    int m = grid.length;
    int n = grid[0].length;
    if (i == 0 || grid[i - 1][j] == 0) sum++;
    if (i == m - 1 || grid[i + 1][j] == 0) sum++;
    if (j == 0 || grid[i][j - 1] == 0) sum++;
    if (j == m - 1 || grid[i][j + 1] == 0) sum++;
    return sum;
  }
}
