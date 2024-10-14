package part1.week1.iq;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
public class Task4Test {
  @Test
  public void test1() {
    Task4 main = new Task4();
    Assertions.assertTrue(main.sum3InArray(new int[] {20, -10, 30, -20, 12}, 0));
    Assertions.assertFalse(main.sum3InArray(new int[] {20, -12, 30, -20, 12}, 0));
    Assertions.assertTrue(main.sum3InArray(new int[] {-10, 10, 20, -10}, 20));
    Assertions.assertFalse(main.sum3InArray(new int[] {-20, -10, 30, -20, 12}, -20));
  }
}
