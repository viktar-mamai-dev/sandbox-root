package part1.week1.iq;

import org.junit.Assert;
import org.junit.Test;

public class Task3Test {

    @Test
    public void test1() {
        Task3 main = new Task3(6);

        Assert.assertEquals(2, main.successor(2));
        Assert.assertEquals(4, main.successor(4));

        main.remove(2);
        main.remove(4);
        Assert.assertEquals(3, main.successor(2));
        Assert.assertEquals(5, main.successor(4));
        Assert.assertEquals(1, main.successor(1));

        main.remove(3);
        Assert.assertEquals(5, main.successor(2));

        // removing max element
        main.remove(5);
        Assert.assertEquals(-1, main.successor(3));
    }
}
