package part1.week1.iq;

import part1.week1.QuickUnionFind;

/**
 * method find() to the union-find data type so that find(i) returns the largest element in the
 * connected component containing i. The operations, union(), connected(), and find() should all
 * take logarithmic time or better.
 *
 * <p>For example, if one of the connected components is {1,2,6,9}, then the find() method should
 * return 9 for each of the four elements in the connected components
 */
public class Task2 extends QuickUnionFind {
  private final int[] greatest;

  public Task2(int n) {
    super(n);
    greatest = new int[n];
    for (int i = 0; i < n; i++) {
      greatest[i] = i;
    }
  }

  @Override
  public void union(int node1, int node2) {
    int root1 = root(node1);
    int root2 = root(node2);

    if (root1 == root2) return;

    if (rank[root1] < rank[root2]) { // swap
      int tmp = root1;
      root1 = root2;
      root2 = tmp;
    }
    parent[root2] = root1;
    rank[root1] += rank[root2];
    // if new subtree has root greater than super-tree then change it
    if (greatest[root2] > greatest[root1]) {
      greatest[root1] = greatest[root2];
    }
  }

  // greatest element in the subset is stored in the root of this subset
  public int find(int node) {
    return greatest[root(node)];
  }
}
