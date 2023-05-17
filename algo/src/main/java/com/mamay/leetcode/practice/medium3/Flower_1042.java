package com.mamay.leetcode.practice.medium3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

public class Flower_1042 {
  public int[] gardenNoAdj(int N, int[][] paths) {
    if (N == 1) return new int[] {1};
    ArrayList<Integer> graph[] = new ArrayList[N];
    for (int i = 0; i < N; i++) {
      graph[i] = new ArrayList<>();
    }
    for (int path[] : paths) {
      int v1 = path[0] - 1, v2 = path[1] - 1;
      graph[v1].add(v2);
      graph[v2].add(v1);
    }

    int[] res = new int[N];
    Arrays.fill(res, 0);
    res[0] = 1;
    ArrayDeque<Integer> deq = new ArrayDeque<>();
    deq.add(graph[0].get(0));
    boolean used[] = new boolean[N];
    Arrays.fill(used, false);
    used[0] = true;
    while (!deq.isEmpty()) {
      int k = deq.pollFirst();
      used[k] = true;
      ArrayList<Integer> flowerTypes = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
      for (int v1 : graph[k]) {
        flowerTypes.remove((Integer) res[v1]);
      }
      res[k] = flowerTypes.get(0);
      for (int v1 : graph[k]) {
        if (!used[v1] && !deq.contains(v1)) {
          deq.addLast(v1);
        }
      }
    }
    return res;
  }

  public static void main(String[] args) {
    Flower_1042 main = new Flower_1042();
    System.out.println(
        Arrays.toString(
            main.gardenNoAdj(5, new int[][] {{3, 4}, {2, 3}, {5, 4}, {5, 1}, {1, 3}, {2, 4}})));
  }
}
