package part1.week3.iq;

import org.junit.jupiter.api.Test;
import part1.ArrayHelper;

import java.util.Arrays;
import java.util.LinkedList;
public class Task3Test {

  @Test
  public void test1() {
    Task3 main = new Task3();
    ArrayHelper<Integer> helper = new ArrayHelper<>();

    LinkedList<Integer> list = new LinkedList<>(Arrays.asList(4, 6, 4, 2, 7, 9, 12, -9, 2));
    for (int i = 0; i < 10; i++) {
      helper.printArray(main.shuffleLinkedList(list));
    }
  }
}
