package com.mamay.leetcode.practice.easy4;

import java.util.Arrays;

public class FindKBeauty {
  private static final int BIL = 1_000_000_000;

  public static void main(String[] args) {
    FindKBeauty main = new FindKBeauty();
    System.out.println(main.maximumWhiteTiles(new int[][] {{10, 11}, {1, 1}}, 2));
    System.out.println(main.maximumWhiteTiles(new int[][] {{1, BIL}}, BIL));
  }

  public int maximumWhiteTiles(int[][] tiles, int carpetLen) {
    int minTile = Integer.MAX_VALUE, maxTile = Integer.MIN_VALUE;
    for (int[] tile : tiles) {
      minTile = Math.min(minTile, tile[0]);
      maxTile = Math.max(maxTile, tile[1]);
    }
    boolean[] used = new boolean[maxTile - minTile + 1];
    Arrays.fill(used, false);
    for (int[] tile : tiles) {
      for (int i = tile[0]; i <= tile[1]; i++) used[i - minTile] = true;
    }

    int idx = minTile;
    int currentCover = 0;
    while (idx <= Math.min(maxTile, minTile + carpetLen - 1)) {
      if (used[idx - minTile]) currentCover++;
      idx++;
    }
    int maxCover = currentCover;
    while (idx <= maxTile) {
      if (used[idx - minTile]) currentCover++;
      if (used[idx - carpetLen - minTile]) currentCover--;
      maxCover = Math.max(maxCover, currentCover);
      idx++;
    }
    return maxCover;
  }
}
