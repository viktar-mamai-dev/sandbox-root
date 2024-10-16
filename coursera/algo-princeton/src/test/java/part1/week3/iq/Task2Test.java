package part1.week3.iq;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class Task2Test {

  @Test
  public void test1() {
    Task2 main = new Task2();
    Assertions.assertEquals(4, main.inversionCount(new int[] {20, 12, 18, 14}));
    Assertions.assertEquals(10, main.inversionCount(new int[] {12, 16, 20, 18, 25, 22, 14, 19}));
    Assertions.assertEquals(19, main.inversionCount(new int[] {22, 32, 15, 28, 12, 24, 10, 20}));
  }
}
