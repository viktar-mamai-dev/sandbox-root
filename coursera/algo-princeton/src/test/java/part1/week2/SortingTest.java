/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

package part1.week2;

import org.junit.Assert;
import org.junit.Test;
import part1.ArrayHelper;

import java.util.Arrays;

public class SortingTest {

    @Test
    public void test1() {
        ArrayHelper<Integer> helper = new ArrayHelper<Integer>();
        Integer[] array = helper.generateRandomArray(20, 30);
        Integer[] insertion = helper.copy(array);
        Integer[] selection = helper.copy(array);
        Sorting main = new Sorting();
        main.insertionSort(insertion);
        main.selectionSort(selection);

        Assert.assertTrue(Arrays.equals(insertion, selection));
    }
}
