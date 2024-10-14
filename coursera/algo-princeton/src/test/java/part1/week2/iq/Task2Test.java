package part1.week2.iq;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
public class Task2Test {

  @Test
  public void test1() {
    Task2<Integer> main = new Task2<Integer>();
    main.push(2000);
    main.push(1000);
    main.push(500);
    Assertions.assertEquals((Integer) 2000, main.getMax());

    main.push(3000);
    main.push(4000);
    Assertions.assertEquals((Integer) 4000, main.getMax());

    main.pop();
    main.pop();
    Assertions.assertEquals((Integer) 2000, main.getMax());
  }
}
