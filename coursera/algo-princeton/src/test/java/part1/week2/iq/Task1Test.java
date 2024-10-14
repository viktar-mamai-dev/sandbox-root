package part1.week2.iq;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
public class Task1Test {

  @Test
  public void test1() {
    Task1<Integer> main = new Task1<Integer>();
    main.enqueue(1);
    main.enqueue(2);
    Assertions.assertEquals((Integer) 1, main.dequeue());
    main.enqueue(3);
    main.dequeue();
    Assertions.assertEquals((Integer) 3, main.dequeue());
  }
}
