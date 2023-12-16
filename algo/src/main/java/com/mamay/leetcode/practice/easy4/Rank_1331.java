package com.mamay.leetcode.practice.easy4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Rank_1331 {
  public int[] arrayRankTransform(int[] arr) {
    int n = arr.length;
    HashMap<Integer, List<Integer>> map = new HashMap<>();
    for (int i = 0; i < n; i++) {
      List<Integer> subList = map.get(arr[i]);
      if (subList == null) {
        subList = new ArrayList<>();
      }
      subList.add(i);
      map.put(arr[i], subList);
    }
    Arrays.sort(arr);
    int rank = 0;
    int ranks[] = new int[n];
    int i = 0;
    while (i < n) {
      int a = arr[i];
      rank++;
      for (int idx : map.get(a)) {
        ranks[idx] = rank;
      }
      i++;
      while (i < n && arr[i] == a) i++;
    }

    return ranks;
  }
}
