package part1.week2.iq;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class Task5Test {

  @Test
  public void test1() {
    Task5 main = new Task5();
    int[] a = {1, 2, 3, 4, 5, 6};
    int[] b = {5, 4, 1, 6, 3, 2};
    Assertions.assertTrue(main.isPermutation(a, b));

    a = new int[] {1, 3, 3, 1, 5, 6};
    b = new int[] {1, 3, 5, 6, 3, 3};
    Assertions.assertFalse(main.isPermutation(a, b));
  }
}
