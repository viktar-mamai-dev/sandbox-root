package part1.week4.iq;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class Task3Test {

  @Test
  public void test1() {
    Task3 main = new Task3();

    Assertions.assertEquals(2, main.getTaxiCabList(20).size());
    Assertions.assertEquals(10, main.getTaxiCabList(40).size());
    Assertions.assertEquals(12, main.getTaxiCabList(50).size());
  }
}
