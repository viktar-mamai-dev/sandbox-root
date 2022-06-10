package part1.week1.iq;

import org.junit.Assert;
import org.junit.Test;
import part1.Constants;

public class Task5Test {
    @Test
    public void test1() {
        Task5 main = new Task5();
        Assert.assertEquals(5, main.bitonicSearch(new int[] { 10, 12, 14, 16, 18, 15, 11 }, 15));
        Assert.assertEquals(1, main.bitonicSearch(new int[] { 10, 12, 14, 16, 18, 15, 11 }, 12));
        Assert.assertEquals(0, main.bitonicSearch(new int[] { 10, 12, 14, 16, 18, 15, 11 }, 10));
        Assert.assertEquals(6, main.bitonicSearch(new int[] { 10, 12, 14, 16, 18, 15, 11 }, 11));
        Assert.assertEquals(Constants.MINUS_ONE,
                            main.bitonicSearch(new int[] { 10, 12, 14, 16, 18, 15, 11 }, 13));
    }
}
