package part1.week1.iq;

import org.junit.Assert;
import org.junit.Test;

public class Task6Test {

    @Test
    public void test1() {
        Task6 main = new Task6();
        Assert.assertEquals(2, main.eggDrop(20, 3));
    }
}
