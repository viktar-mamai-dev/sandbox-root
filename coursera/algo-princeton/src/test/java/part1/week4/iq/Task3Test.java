package part1.week4.iq;

import org.junit.Assert;
import org.junit.Test;

public class Task3Test {

    @Test
    public void test1() {
        Task3 main = new Task3();

        Assert.assertEquals(2, main.getTaxiCabList(20).size());
        Assert.assertEquals(10, main.getTaxiCabList(40).size());
        Assert.assertEquals(12, main.getTaxiCabList(50).size());
    }
}
