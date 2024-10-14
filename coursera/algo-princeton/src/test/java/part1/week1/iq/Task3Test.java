package part1.week1.iq;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
public class Task3Test {

  @Test
  public void test1() {
    Task3 main = new Task3(6);

    Assertions.assertEquals(2, main.successor(2));
    Assertions.assertEquals(4, main.successor(4));

    main.remove(2);
    main.remove(4);
    Assertions.assertEquals(3, main.successor(2));
    Assertions.assertEquals(5, main.successor(4));
    Assertions.assertEquals(1, main.successor(1));

    main.remove(3);
    Assertions.assertEquals(5, main.successor(2));

    // removing max element
    main.remove(5);
    Assertions.assertEquals(-1, main.successor(3));
  }
}
