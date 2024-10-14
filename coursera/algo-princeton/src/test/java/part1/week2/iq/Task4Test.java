package part1.week2.iq;

import edu.princeton.cs.algs4.Point2D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class Task4Test {

  @Test
  public void test1() {
    Task4 main = new Task4();
    Point2D[] a = {new Point2D(2, 3), new Point2D(4, 3), new Point2D(2, 4), new Point2D(3, 3)};
    Point2D[] b = {new Point2D(3, 2), new Point2D(4, 2), new Point2D(3, 3), new Point2D(4, 3)};
    Assertions.assertEquals(2, main.intersectionCount(a, b));

    a = new Point2D[] {new Point2D(2, 2), new Point2D(3, 3), new Point2D(4, 4)};
    b = new Point2D[] {new Point2D(2, 2), new Point2D(3, 3), new Point2D(4, 4)};
    Assertions.assertEquals(3, main.intersectionCount(a, b));
  }
}
