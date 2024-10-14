package com.mamay.algo.leetcode;

import java.util.Arrays;

class UnionFind {

  private int[] parent;
  private int[] rank;

  public UnionFind(int size) {
    parent = new int[size];
    rank = new int[size];
    Arrays.fill(rank, 1);
    for (int i = 0; i < size; i++) {
      parent[i] = i;
    }
  }

  public int find(int i) {
    while (parent[i] != i) {
      parent[i] = parent[parent[i]]; // Skip one level
      i = parent[i]; // Move to the new level
    }
    return i;
  }

  public void union(int xr, int yr) {
    xr = find(xr);
    yr = find(yr);
    if (xr == yr) {
      return;
    }
    if (rank[xr] < rank[yr]) {
      parent[xr] = parent[yr];
      rank[yr] += rank[xr];
    } else {
      parent[yr] = parent[xr];
      rank[xr] += rank[yr];
    }
  }
}
