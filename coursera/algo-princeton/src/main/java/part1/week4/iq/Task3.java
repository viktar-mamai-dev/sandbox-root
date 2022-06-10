package part1.week4.iq;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.List;

/**
 * Taxicab numbers. A taxicab number is an integer that can be expressed as the sum of two cubes of positive integers in two different ways:
 * a^3 + b^3 = c^3 + d^3
 * Design an algorithm to find all taxicab numbers with a, b, c, and d less than n
 */
public class Task3 {
    public List<Long> getTaxiCabList(int n) {
        // initialize priority queue
        MinPQ<Node> pq = new MinPQ<Node>();
        for (int i = 1; i <= n; i++) {
            pq.insert(new Node(i, i));
        }

        List<Long> resultList = new ArrayList<>();

        // enumerate sums in ascending order, look for repeated sums
        int run = 1;
        Node prev = new Node(0, 0);   // sentinel
        while (!pq.isEmpty()) {
            Node curr = pq.delMin();

            // current sum is same as previous sum
            if (prev.sum == curr.sum) {
                run++;
                if (run == 2) {
                    StdOut.print(prev.sum + " = " + prev);
                    resultList.add(prev.sum);
                }
                StdOut.print(" = " + curr);
            } else {
                if (run > 1)
                    StdOut.println();
                run = 1;
            }
            prev = curr;

            if (curr.j < n)
                pq.insert(new Node(curr.i, curr.j + 1));
        }
        if (run > 1)
            StdOut.println();

        return resultList;
    }

    class Node implements Comparable<Node> {
        private final int i;
        private final int j;
        private final long sum;   // i^3 + j^3, cached to avoid recomputation

        Node(int i, int j) {
            this.sum = (long) i * i * i + (long) j * j * j;
            this.i = i;
            this.j = j;
        }

        // compare by i^3 + j^3, breaking ties by i
        public int compareTo(Node that) {
            if (this.sum < that.sum)
                return -1;
            else if (this.sum > that.sum)
                return +1;
            else
                return Integer.compare(this.i, that.i);
        }

        public String toString() {
            return i + "^3 + " + j + "^3";
        }
    }
}
