package part1.week3.iq;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

public class Task6Test {

    @Test
    public void test1() {
        Task6 main = new Task6();

        int[] array = { 1, 2, 3, 4, 3, 2, 4, 6, 2, 3, 1, 3, 2, 2, 4 };

        Assert.assertTrue(CollectionUtils.isEmpty(main.findFrequent(array, 6)));
        Assert.assertEquals(Collections.singletonList(2), main.findFrequent(array, 5));
        Assert.assertTrue(CollectionUtils.isEqualCollection(Arrays.asList(2, 3, 4), main.findFrequent(array, 3)));
        Assert.assertTrue(CollectionUtils.isEqualCollection(Arrays.asList(1, 2, 3, 4, 6), main.findFrequent(array, 1)));
    }
}