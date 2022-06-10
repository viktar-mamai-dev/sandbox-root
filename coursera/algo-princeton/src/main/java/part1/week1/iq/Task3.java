package part1.week1.iq;

import part1.Constants;
import part1.week1.QuickUnionFind;

/**
 * Given a set of nn integers S ={0,1,...,n−1} and a sequence of requests of the following form:
 * Remove x from S Find the successor of x: the smallest y in S such that y≥x. design a data type so
 * that all operations (except construction) take logarithmic time or better in the worst case.
 */
public class Task3 extends QuickUnionFind {
    private int[] successors;

    public Task3(int n) {
        super(n);
        successors = new int[n];
        for (int i = 0; i < n; i++) {
            successors[i] = i;
        }
    }

    @Override
    public void union(int node1, int node2) {
        int root1 = root(node1);
        int root2 = root(node2);

        if (size[root1] < size[root2]) {
            id[root1] = root2;
            size[root2] += size[root1];
        }
        else {
            id[root2] = root1;
            size[root1] += size[root2];
            // if new root is from deleted item then set his successor for the subtree root
            successors[root1] = successors[root2];
        }
    }

    // removing means union of two sets
    public void remove(int x) {
        if (x < id.length - 1) {
            union(x, x + 1);
        }
        else {
            successors[root(x)] = Constants.MINUS_ONE;
        }
    }

    // successor is stored in the root of the set containg this item x
    public int successor(int x) {
        return successors[root(x)];
    }
}
