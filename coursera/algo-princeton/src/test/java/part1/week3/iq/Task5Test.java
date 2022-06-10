package part1.week3.iq;

import org.junit.Assert;
import org.junit.Test;

public class Task5Test {

    @Test
    public void test1() {
        Task5 main = new Task5();
        int[] a = {10, 15, 20, 30, 45};
        int[] b = {12, 17, 22, 28, 30, 46, 49};
        Assert.assertEquals(10, main.rank(a, b, 1));
        Assert.assertEquals(17, main.rank(a, b, 4));
        Assert.assertEquals(49, main.rank(a, b, 12));
    }
}