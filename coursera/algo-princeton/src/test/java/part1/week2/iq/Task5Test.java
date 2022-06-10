package part1.week2.iq;

import edu.princeton.cs.algs4.Point2D;
import org.junit.Assert;
import org.junit.Test;

public class Task5Test {

    @Test
    public void test1() {
        Task5 main = new Task5();
        int[] a = { 1, 2, 3, 4, 5, 6 };
        int[] b = { 5, 4, 1, 6, 3, 2 };
        Assert.assertTrue(main.isPermutation(a, b));

        a = new int[] { 1, 3, 3, 1, 5, 6 };
        b = new int[] { 1, 3, 5, 6, 3, 3 };
        Assert.assertFalse(main.isPermutation(a, b));
    }
}
