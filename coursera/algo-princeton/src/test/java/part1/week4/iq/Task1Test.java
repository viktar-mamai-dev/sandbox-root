package part1.week4.iq;

import org.junit.Assert;
import org.junit.Test;

public class Task1Test {

    private static final double DELTA = 0.001;

    private Task1 doubleQueue = new Task1();

    @Test
    public void testSort() {
        doubleQueue.insert(50);
        doubleQueue.insert(30);
        Assert.assertEquals(40, doubleQueue.getMedian(), DELTA);

        doubleQueue.insert(60);
        doubleQueue.insert(10);
        Assert.assertEquals(40, doubleQueue.getMedian(), DELTA);

        doubleQueue.insert(45);
        Assert.assertEquals(45, doubleQueue.getMedian(), DELTA);

        doubleQueue.insert(80);
        doubleQueue.insert(95);
        Assert.assertEquals(50, doubleQueue.getMedian(), DELTA);
    }
}
