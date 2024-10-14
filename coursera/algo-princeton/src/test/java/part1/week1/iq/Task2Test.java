package part1.week1.iq;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
public class Task2Test {

  @Test
  public void test1() {
    Task2 main = new Task2(6);

    Assertions.assertEquals(2, main.find(2));
    Assertions.assertEquals(4, main.find(4));

    main.union(2, 5);
    main.union(4, 3);
    main.union(1, 0);

    Assertions.assertEquals(5, main.find(2));
    Assertions.assertEquals(5, main.find(5));
    Assertions.assertEquals(1, main.find(0));
    Assertions.assertEquals(4, main.find(3));

    main.union(1, 2);
    main.union(5, 3);

    Assertions.assertEquals(5, main.find(0));
    Assertions.assertEquals(5, main.find(4));
  }
}
