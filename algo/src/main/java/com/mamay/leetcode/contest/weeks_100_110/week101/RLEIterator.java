package com.mamay.leetcode.contest.weeks_100_110.week101;

public class RLEIterator {

  private int a[];
  private int len;

  public RLEIterator(int[] A) {
    this.a = A;
    this.len = a.length;
  }

  public int next(int n) {

    for (int i = 0; i < len; i++) {
      if (a[i] == 0) continue;
      int diff = Math.min(n, a[i]);
      a[i] -= diff;
      n -= diff;
      if (n == 0) return a[i + 1];
    }
    return -1;
  }
}
