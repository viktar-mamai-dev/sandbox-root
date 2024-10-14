package part1.week1.iq;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import part1.Constants;
public class Task5Test {
  @Test
  public void test1() {
    Task5 main = new Task5();
    Assertions.assertEquals(5, main.bitonicSearch(new int[] {10, 12, 14, 16, 18, 15, 11}, 15));
    Assertions.assertEquals(1, main.bitonicSearch(new int[] {10, 12, 14, 16, 18, 15, 11}, 12));
    Assertions.assertEquals(0, main.bitonicSearch(new int[] {10, 12, 14, 16, 18, 15, 11}, 10));
    Assertions.assertEquals(6, main.bitonicSearch(new int[] {10, 12, 14, 16, 18, 15, 11}, 11));
    Assertions.assertEquals(
        Constants.MINUS_ONE, main.bitonicSearch(new int[] {10, 12, 14, 16, 18, 15, 11}, 13));
  }
}
