package com.mamay.core.pack1;

import java.util.Arrays;
import java.util.Spliterator;

/**
 * Created by admin on 9/26/2014.
 */
public class ArrayRunner {
    public static void main(String[] args) {
        int[] array = {5, 7, 8, 9, 6};
        Spliterator<Integer> spliterator = Arrays.spliterator(array);
        spliterator.forEachRemaining(System.out::println);
        System.out.println((-7) % 3);
    }
}
