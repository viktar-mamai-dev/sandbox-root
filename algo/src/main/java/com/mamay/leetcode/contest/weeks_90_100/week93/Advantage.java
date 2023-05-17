package com.mamay.leetcode.contest.weeks_90_100.week93;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Advantage {

  public static int[] advantageCount22(int[] A, int[] B) {

    int len = A.length;
    int Ap[] = new int[len];
    boolean usedB[] = new boolean[len];
    Arrays.fill(usedB, false);
    boolean usedA[] = new boolean[len];
    Arrays.fill(usedA, false);
    for (int bi = 0; bi < len; bi++) {
      int aIdx = -1;
      int aMin = Integer.MAX_VALUE;
      for (int ai = 0; ai < len; ai++) {
        if (!usedA[ai] && A[ai] > B[bi] && A[ai] < aMin) {
          aMin = A[ai];
          aIdx = ai;
        }
      }
      if (aIdx != -1) {
        usedA[aIdx] = true;
        usedB[bi] = true;
        Ap[bi] = aMin;
      }
    }

    int ai = 0;
    for (int bi = 0; bi < len; bi++) {
      while (bi < len && usedB[bi]) bi++;
      if (bi == len) break;
      while (ai < len && usedA[ai]) ai++;
      Ap[bi] = A[ai];
      ai++;
    }

    return Ap;
  }

  public static int[] advantageCount(int[] A, int[] B) {
    int len = A.length;
    int res[] = new int[len];
    TreeMap<Integer, Integer> map = new TreeMap<>();

    for (int num : A) {
      int val = map.getOrDefault(num, 0) + 1;
      map.put(num, val);
    }

    for (int i = 0; i < len; i++) {
      Map.Entry<Integer, Integer> entry = map.higherEntry(B[i]);
      if (entry == null) {
        entry = map.firstEntry();
      }
      int key = entry.getKey();
      int val = entry.getValue();
      res[i] = key;
      if (val == 1) {
        map.remove(key);
      } else {
        map.put(key, val - 1);
      }
    }
    return res;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int N = scanner.nextInt();
    int A[] = new int[N];
    int B[] = new int[N];
    for (int bi = 0; bi < N; bi++) {
      A[bi] = scanner.nextInt();
    }
    for (int bi = 0; bi < N; bi++) {
      B[bi] = scanner.nextInt();
    }
    System.out.println(Arrays.toString(advantageCount(A, B)));
    scanner.close();
  }
}
