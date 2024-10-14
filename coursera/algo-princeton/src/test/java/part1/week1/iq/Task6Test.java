package part1.week1.iq;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class Task6Test {

  @Test
  public void test1() {
    Task6 main = new Task6();
    Assertions.assertEquals(2, main.eggDrop(20, 3));
  }
}
