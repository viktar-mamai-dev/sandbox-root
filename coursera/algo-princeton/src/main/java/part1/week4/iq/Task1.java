package part1.week4.iq;

import part1.week4.MaxMinPriorityQueue;

public class Task1 {

    private MaxMinPriorityQueue maxQueue;
    private MaxMinPriorityQueue minQueue;

    private int n;

    public Task1() {
        maxQueue = new MaxMinPriorityQueue(new int[0], true);
        minQueue = new MaxMinPriorityQueue(new int[0], false);

        n = 0;
    }

    public void insert(int x) {
        maxQueue.insert(x);
        if (n % 2 == 0) {
            if (minQueue.isEmpty()) {
                n++;
                return;
            } else if (maxQueue.retrieveHead() > minQueue.retrieveHead()) {
                int maxRoot = maxQueue.deleteHead();
                int minRoot = minQueue.deleteHead();
                maxQueue.insert(minRoot);
                minQueue.insert(maxRoot);
            }
        } else {
            minQueue.insert(maxQueue.deleteHead());
        }
        n++;
    }

    public double getMedian() {
        if (n % 2 == 0) {
            return (maxQueue.retrieveHead() + minQueue.retrieveHead()) / 2.0;
        }
        return maxQueue.retrieveHead();
    }
}
