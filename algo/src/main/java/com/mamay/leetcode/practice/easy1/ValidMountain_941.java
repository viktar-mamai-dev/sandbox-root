package com.mamay.leetcode.practice.easy1;

public class ValidMountain_941 {
  public boolean validMountainArray(int[] A) {
    int n = A.length;
    int i = 0;
    while (i < n - 1) {
      if (A[i] >= A[i + 1]) {
        break;
      }
      i++;
    }

    if (i == n - 1 || i == 0) {
      return false;
    }
    int leftIdx = i;
    i = n - 1;
    while (i >= 1) {
      if (A[i] >= A[i - 1]) {
        break;
      }
      i--;
    }

    return leftIdx == i;
  }
}
