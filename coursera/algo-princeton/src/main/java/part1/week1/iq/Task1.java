package part1.week1.iq;

import part1.Constants;
import part1.week1.QuickUnionFind;

/**
 * Social network connectivity. Given a social network containing n members and a log file
 * containing m timestamps at which times pairs of members formed friendships, design an algorithm
 * to determine the earliest time at which all members are connected (i.e., every member is a friend
 * of a friend of a friend ... of a friend). Assume that the log file is sorted by timestamp and
 * that friendship is an equivalence relation. The running time of your algorithm should be m * logn
 * or better and use extra space proportional to n
 */
public class Task1 extends QuickUnionFind {
    private int components;

    public Task1(int n) {
        super(n);
        components = n;
    }

    @Override
    public void union(int node1, int node2) {
        int root1 = root(node1);
        int root2 = root(node2);

        if (root1 == root2) return;
        components--;

        if (size[root1] < size[root2]) {
            id[root1] = root2;
            size[root2] += size[root1];
        }
        else {
            id[root2] = root1;
            size[root1] += size[root2];
        }
    }

    public boolean allFriends() {
        return components == Constants.ONE;
    }
}
