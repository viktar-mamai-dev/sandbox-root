package part1.week4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MaxMinPriorityQueueTest {
    private MaxMinPriorityQueue queue;

    @Before
    public void init() {
        queue = createMaxQueue();
    }

    @Test
    public void testSize() {
        Assert.assertEquals(10, queue.size());
    }

    @Test
    public void testIsEmpty() {
        queue.printArray();
        Assert.assertFalse(queue.isEmpty());
    }

    @Test
    public void testRetrieveMax() {
        queue = createMinQueue();
        queue.printArray();
        Assert.assertEquals(10, queue.retrieveHead());
        queue.insert(6);
        queue.insert(105);
        queue.printArray();
        Assert.assertEquals(6, queue.retrieveHead());
    }

    @Test
    public void testInsert() {
        Assert.assertEquals(10, queue.size());
        queue.insert(25);
        queue.insert(45);
        queue.insert(65);
        queue.insert(115);
        queue.insert(5);
        Assert.assertEquals(15, queue.size());
    }

    @Test
    public void testDeleteMax() {
        Assert.assertEquals(10, queue.size());
        Assert.assertEquals(100, queue.deleteHead());
        Assert.assertEquals(90, queue.deleteHead());
        Assert.assertEquals(90, queue.deleteHead());
        Assert.assertEquals(7, queue.size());
        queue.insert(115);
        queue.insert(120);
        queue.insert(125);
        Assert.assertEquals(125, queue.deleteHead());
    }

    @Test
    public void testSort() {
        queue.insert(103);
        queue.insert(43);
        queue.insert(3);
        queue.sort();
        queue.printArray();
    }

    private MaxMinPriorityQueue createMaxQueue() {

        int[] keys = {10, 20, 30, 40, 50, 60, 70, 90, 90, 100};
        return new MaxMinPriorityQueue(keys, true);

    }

    private MaxMinPriorityQueue createMinQueue() {

        int[] keys = {100, 90, 80, 70, 60, 50, 40, 30, 20, 10};
        return new MaxMinPriorityQueue(keys, false);

    }
}
