package part1.week1.iq;

import org.junit.Assert;
import org.junit.Test;

public class Task4Test {
    @Test
    public void test1() {
        Task4 main = new Task4();
        Assert.assertTrue(main.sum3InArray(new int[] { 20, -10, 30, -20, 12 }, 0));
        Assert.assertFalse(main.sum3InArray(new int[] { 20, -12, 30, -20, 12 }, 0));
        Assert.assertTrue(main.sum3InArray(new int[] { -10, 10, 20, -10 }, 20));
        Assert.assertFalse(main.sum3InArray(new int[] { -20, -10, 30, -20, 12 }, -20));
    }
}
