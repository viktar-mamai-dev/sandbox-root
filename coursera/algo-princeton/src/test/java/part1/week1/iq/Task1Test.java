package part1.week1.iq;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class Task1Test {

  @Test
  public void test1() {
    Task1 main = new Task1(6);

    main.union(1, 5);
    main.union(2, 0);
    main.union(5, 3);
    main.union(2, 4);

    Assertions.assertFalse(main.allFriends());

    main.union(0, 4);
    Assertions.assertFalse(main.allFriends());

    main.union(3, 2);
    Assertions.assertTrue(main.allFriends());
  }
}
