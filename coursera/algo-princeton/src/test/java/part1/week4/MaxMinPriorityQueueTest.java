package part1.week4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class MaxMinPriorityQueueTest {
  private MaxMinPriorityQueue queue;

  @BeforeEach
  public void init() {
    queue = createMaxQueue();
  }

  @Test
  public void testSize() {
    Assertions.assertEquals(10, queue.size());
  }

  @Test
  public void testIsEmpty() {
    queue.printArray();
    Assertions.assertFalse(queue.isEmpty());
  }

  @Test
  public void testRetrieveMax() {
    queue = createMinQueue();
    queue.printArray();
    Assertions.assertEquals(10, queue.retrieveHead());
    queue.insert(6);
    queue.insert(105);
    queue.printArray();
    Assertions.assertEquals(6, queue.retrieveHead());
  }

  @Test
  public void testInsert() {
    Assertions.assertEquals(10, queue.size());
    queue.insert(25);
    queue.insert(45);
    queue.insert(65);
    queue.insert(115);
    queue.insert(5);
    Assertions.assertEquals(15, queue.size());
  }

  @Test
  public void testDeleteMax() {
    Assertions.assertEquals(10, queue.size());
    Assertions.assertEquals(100, queue.deleteHead());
    Assertions.assertEquals(90, queue.deleteHead());
    Assertions.assertEquals(90, queue.deleteHead());
    Assertions.assertEquals(7, queue.size());
    queue.insert(115);
    queue.insert(120);
    queue.insert(125);
    Assertions.assertEquals(125, queue.deleteHead());
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
