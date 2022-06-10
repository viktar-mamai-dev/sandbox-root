package part1.week1;

public class QuickUnionFind {
    protected int[] id;
    // this array is used for balancing trees while merging 2 trees into 1
    protected int[] size;

    public QuickUnionFind(int n) {
        id = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
            size[i] = 1;
        }
    }

    // complexity = O(logn)
    protected int root(int node) {
        int i = node;
        while (i != id[i]) {
            i = id[i];
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

        if (size[root1] < size[root2]) {
            id[root1] = root2;
            size[root2] += size[root1];
        } else {
            id[root2] = root1;
            size[root1] += size[root2];
        }
    }
}
