package part1.week4.iq;

import edu.princeton.cs.algs4.StdRandom;
import part1.week4.MaxMinPriorityQueue;

/**
 * Randomized priority queue. Describe how to add the methods sample() and delRandom() to our binary heap implementation.
 * The two methods return a key that is chosen uniformly at random among the remaining keys, with the latter method
 * also removing that key. The sample() method should take constant time; the delRandom() method should take
 * logarithmic time
 */
public class Task2 extends MaxMinPriorityQueue {
    public Task2(int[] a) {
        super(a, true);
    }

    public int sample() {
        int index = 1 + StdRandom.uniform(size());
        return array[index];
    }

    public int delRandom() {
        int index = StdRandom.uniform(size());
        int value = array[1 + index];
        swap(index, size - 1);
        size--;
        sink(index);
        return value;
    }
}
