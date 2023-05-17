package part1.week1;

public class QuickUnionFind {
    protected int[] parent;
    // this array is used for balancing trees while merging 2 trees into 1
    protected int[] rank;

    public QuickUnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    // complexity = O(logn)
    protected int root(int node) {
        int i = node;
        while (i != parent[i]) {
            i = parent[i];
        }
        return i;
    }

    // complexity = O(logn)
    public boolean connected(int root1, int root2) {
        return root(root1) == root(root2);
    }

    // complexity = O(logn)
    public void union(int node1, int node2) {
        int root1 = root(node1);
        int root2 = root(node2);

        if (root1 == root2) return;

        if (rank[root1] < rank[root2]) {
            parent[root1] = root2;
            rank[root2] += rank[root1];
        } else {
            parent[root2] = root1;
            rank[root1] += rank[root2];
        }
    }
}
