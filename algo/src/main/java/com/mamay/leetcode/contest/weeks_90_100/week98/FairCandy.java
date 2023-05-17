package com.mamay.leetcode.contest.weeks_90_100.week98;

import java.util.Arrays;

public class FairCandy {

  public int[] fairCandySwap(int[] A, int[] B) {
    int s1 = Arrays.stream(A).reduce(0, (a1, a2) -> a1 + a2);
    int s2 = Arrays.stream(B).reduce(0, (a1, a2) -> a1 + a2);
    int diff = (s2 - s1) / 2;

    for (int i1 = 0; i1 < A.length; i1++) {
      for (int i2 = 0; i2 < B.length; i2++) {
        if (A[i1] + diff == B[i2]) {
          return new int[] {A[i1], B[i2]};
        }
      }
    }
    return null;
  }
}
