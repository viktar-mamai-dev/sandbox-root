package part1.week4.iq;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class Task1Test {

  private static final double DELTA = 0.001;

  private Task1 doubleQueue = new Task1();

  @Test
  public void testSort() {
    doubleQueue.insert(50);
    doubleQueue.insert(30);
    Assertions.assertEquals(40, doubleQueue.getMedian(), DELTA);

    doubleQueue.insert(60);
    doubleQueue.insert(10);
    Assertions.assertEquals(40, doubleQueue.getMedian(), DELTA);

    doubleQueue.insert(45);
    Assertions.assertEquals(45, doubleQueue.getMedian(), DELTA);

    doubleQueue.insert(80);
    doubleQueue.insert(95);
    Assertions.assertEquals(50, doubleQueue.getMedian(), DELTA);
  }
}
