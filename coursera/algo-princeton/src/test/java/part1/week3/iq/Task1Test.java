package part1.week3.iq;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import part1.ArrayHelper;

import java.util.Arrays;

public class Task1Test {

    @Test
    public void test1() {
        final int n = 10;

        Task1 main = new Task1();

        ArrayHelper<Integer> helper = new ArrayHelper<Integer>();
        for (int i = 0; i < 10; i++) {
            Integer[] array = generateRandomArray(n, 30);
            Integer[] res = main.merge(array);
            helper.printArray(res);
            helper.isSorted(array);
        }
    }

    private Integer[] generateRandomArray(int size, int maxBound) {
        Integer[] array = new Integer[2 * size];
        for (int i = 0; i < 2 * size; i++) {
            array[i] = StdRandom.uniform(maxBound);
        }
        Arrays.sort(array, 0, size);
        Arrays.sort(array, size, 2 * size);
        return array;
    }
}