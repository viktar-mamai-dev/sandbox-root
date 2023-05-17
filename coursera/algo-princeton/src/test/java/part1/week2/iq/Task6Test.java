package part1.week2.iq;

import org.junit.Assert;
import org.junit.Test;
import part1.ArrayHelper;
import part1.week2.PebbleColor;

public class Task6Test {

  @Test
  public void test1() {
    PebbleColor[] actualArray =
        new PebbleColor[] {
          PebbleColor.RED, PebbleColor.WHITE, PebbleColor.WHITE, PebbleColor.BLUE,
          PebbleColor.BLUE, PebbleColor.BLUE, PebbleColor.RED, PebbleColor.RED,
          PebbleColor.WHITE, PebbleColor.WHITE, PebbleColor.BLUE, PebbleColor.RED,
          PebbleColor.BLUE, PebbleColor.RED, PebbleColor.WHITE
        };

    PebbleColor[] expectedArray =
        new PebbleColor[] {
          PebbleColor.RED, PebbleColor.RED, PebbleColor.RED, PebbleColor.RED,
          PebbleColor.RED, PebbleColor.WHITE, PebbleColor.WHITE, PebbleColor.WHITE,
          PebbleColor.WHITE, PebbleColor.WHITE, PebbleColor.BLUE, PebbleColor.BLUE,
          PebbleColor.BLUE, PebbleColor.BLUE, PebbleColor.BLUE
        };

    // RED, WHITE, WHITE, BLUE, BLUE, BLUE, RED, RED, WHITE, WHITE, BLUE, RED, BLUE, RED, WHITE
    Task6 main = new Task6(actualArray);

    ArrayHelper<PebbleColor> helper = new ArrayHelper<>();
    final int ITERATIONS = 15;
    for (int k = 0; k < ITERATIONS; k++) {
      main.shuffle();
      helper.printArray(main.getArray());

      main.sort();

      helper.printArray(main.getArray());

      Assert.assertArrayEquals(expectedArray, main.getArray());
    }
  }
}
