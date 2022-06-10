package part1.week2.iq;

import org.junit.Assert;
import org.junit.Test;

public class Task3Test {

    @Test
    public void test1() {
        Task3<Double> main = new Task3<Double>();

        main.createArrayCheckedType(Double.class, 10);
        main.put(0, 10.23);
        Assert.assertEquals((Double) 10.23, main.get(0));

        main.createArrayUncheckedType(10);
        main.put(1, 10.23);
        Assert.assertEquals((Double) 10.23, main.get(1));
    }
}
