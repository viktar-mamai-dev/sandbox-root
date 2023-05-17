package part1.week3.iq;

import org.junit.Assert;
import org.junit.Test;
import part1.ArrayHelper;

public class Task4Test {

  @Test
  public void test1() {
    Task4 main = new Task4();
    String[] initArray = {"a", "b", "c", "d", "e", "f", "g", "h", "k"};
    int n = initArray.length;

    String[] nuts = new String[n];
    String[] bolts = new String[n];
    System.arraycopy(initArray, 0, nuts, 0, n);
    System.arraycopy(initArray, 0, bolts, 0, n);

    ArrayHelper<String> helper = new ArrayHelper<>();
    for (int i = 0; i < 10; i++) {
      helper.shuffle(nuts);
      helper.shuffle(bolts);

      main.solve(nuts, bolts);

      Assert.assertArrayEquals(nuts, bolts);
      helper.printArray(nuts);
      helper.printArray(bolts);
      Assert.assertTrue(helper.isSorted(nuts));
    }
  }
}
