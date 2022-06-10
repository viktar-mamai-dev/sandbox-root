package part1.week3;

import org.junit.Assert;
import org.junit.Test;
import part1.ArrayHelper;

import java.util.Arrays;

public class SortingTest {

    @Test
    public void test1() {
        ArrayHelper<Integer> helper = new ArrayHelper<Integer>();
        //Integer[] array = helper.generateRandomArray(20, 30);
        Integer[] array = {15, 23, 3, 0, 7, 25, 2, 2, 7, 23, 9, 4, 0, 13, 5, 18, 23, 21, 6, 3};
        helper.printArray(array);
        Integer[] quick = helper.copy(array);
        Integer[] merge = helper.copy(array);
        Sorting main = new Sorting();
        main.quickSort(quick);
        main.mergeSort(merge);
        helper.printArrays(merge, quick);

        Assert.assertTrue(Arrays.equals(merge, quick));
    }
}
