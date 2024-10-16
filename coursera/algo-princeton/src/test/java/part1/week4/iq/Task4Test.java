package part1.week4.iq;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class Task4Test {

  @Test
  public void test1() {
    for (int i = 0; i < 10; i++) {
      Task4 main = createTree();
      Assertions.assertTrue(main.checkBST());
      // most likely Values won't be in order
      System.out.println("Checking BST by value: " + main.checkBSTByValue());
    }
  }

  private Task4 createTree() {
    List<Integer> keys = Arrays.asList(10, 20, 30, 40, 50, 60, 10, 30, 60);
    List<String> values =
        Arrays.asList(
            "Italy",
            "Germany",
            "France",
            "Spain",
            "Portugal",
            "Russia",
            "Belgium",
            "Denmark",
            "Sweden");
    Collections.shuffle(keys);
    Collections.shuffle(values);

    Task4 tree = new Task4();
    for (int i = 0; i < keys.size(); i++) {
      tree.put(keys.get(i), values.get(i));
    }
    return tree;
  }
}
