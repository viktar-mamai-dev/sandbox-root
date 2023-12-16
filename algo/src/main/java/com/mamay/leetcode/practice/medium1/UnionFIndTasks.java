package com.mamay.leetcode.practice.medium1;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class UnionFIndTasks {

  public int maxAreaOfIsland(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    final int dirs[][] = {{1, 0}, {0, 1}};
    UnionFind unionFind = new UnionFind(m * n);
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == 0) continue;
        for (int dir[] : dirs) {
          int i1 = i + dir[0], j1 = j + dir[1];
          if (!isValid(i1, j1, m, n) || grid[i1][j1] == 0) continue;
          unionFind.union(convert(i, j, n), convert(i1, j1, n));
        }
      }
    }

    int res = 0;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        int ij = convert(i, j, n);
        if (grid[i][j] == 0 || !unionFind.isRoot(ij)) continue;
        res = Math.max(res, unionFind.getRankSize(ij));
      }
    }
    return res;
  }

  private int[][] grid;
  private boolean[][] visited;
  private StringBuilder currentIsland;

  public int numDistinctIslands(int[][] grid) {
    this.grid = grid;
    this.visited = new boolean[grid.length][grid[0].length];
    Set<String> islands = new HashSet<>();
    for (int row = 0; row < grid.length; row++) {
      for (int col = 0; col < grid[0].length; col++) {
        currentIsland = new StringBuilder();
        dfs(row, col, '0');
        if (currentIsland.length() == 0) {
          continue;
        }
        islands.add(currentIsland.toString());
      }
    }
    return islands.size();
  }

  private void dfs(int row, int col, char dir) {
    if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length) return;
    if (visited[row][col] || grid[row][col] == 0) return;
    visited[row][col] = true;
    currentIsland.append(dir);
    dfs(row + 1, col, 'D');
    dfs(row - 1, col, 'U');
    dfs(row, col + 1, 'R');
    dfs(row, col - 1, 'L');
    currentIsland.append('0');
  }

  private boolean isLand(int[][] grid1, int i, int j) {
    return grid1[i][j] == 1;
  }

  private boolean isValid(int i1, int j1, int m, int n) {
    return i1 >= 0 && j1 >= 0 && i1 < m && j1 < n;
  }

  private int convert(int i, int j, int n) {
    return i * n + j;
  }

  public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
    int n = source.length;
    UnionFind uf = new UnionFind(n);
    for (int[] a : allowedSwaps) {
      uf.union(a[0], a[1]);
    }

    boolean visited[] = new boolean[n];

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (visited[j]) continue;
        if (source[i] == target[j] && uf.find(i) == uf.find(j)) {
          visited[j] = true;
          break;
        }
      }
    }

    return n - (int) IntStream.range(0, n).filter(i -> visited[i]).count();
  }

  public boolean[] friendRequests(int n, int[][] restrictions, int[][] requests) {
    int m = requests.length;
    boolean[] ans = new boolean[m];
    UnionFind uf = new UnionFind(n);
    for (int i = 0; i < m; ++i) {
      int req1 = uf.find(requests[i][0]), req2 = uf.find(requests[i][1]);
      if (req1 == req2) ans[i] = true;
      else {
        boolean found = false;
        for (int[] r : restrictions) {
          int restr1 = uf.find(r[0]), restr2 = uf.find(r[1]);
          if ((req1 == restr1 && req2 == restr2) || (req2 == restr1 && req1 == restr2)) {
            found = true;
            break;
          }
        }
        if (!found) {
          uf.union(req1, req2);
          ans[i] = true;
        }
      }
    }

    return ans;
  }
}

class UnionFind {
  private final int n;
  // Arr to represent parent of index i
  private final int[] parent;
  // Size to represent the number of nodes in subgxrph rooted at index i
  private final int[] ranks;

  // set parent of every node to itself and size of node to one
  public UnionFind(int n) {
    this.n = n;
    parent = new int[n];
    ranks = new int[n];
    for (int i = 0; i < n; i++) {
      parent[i] = i;
      ranks[i] = 1;
    }
  }

  // Each time we follow a path, find function compresses it further until the path length is
  // greater than or equal to 1.
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
    if (xr == yr) return;
    if (ranks[xr] < ranks[yr]) {
      parent[xr] = parent[yr];
      ranks[yr] += ranks[xr];
    } else {
      parent[yr] = parent[xr];
      ranks[xr] += ranks[yr];
    }
  }

  public boolean isRoot(int i) {
    return i == parent[i];
  }

  public int getRankSize(int i) {
    return ranks[i];
  }
}
