package com.mamay.core.pack3;

import java.util.Arrays;
import java.util.Spliterator;

/**
 * Created by admin on 9/26/2014.
 */
public class ArrayRunner {
    public static void main(String[] args) {
        int[] s, a;
        int a1, s1[];

        int b[] = new int[10];
        Integer b1[] = new Integer[10];

        int c[] = {1, 2, 3, 4, 5, 6};
        int c1[] = new int[]{1, 2, 3, 4, 5, 6};
        int len = c.length;
        Object ob = c;
        float f[] = new float[6];
        //  f = (float[])c;
        f = (float[]) ob;
        int[][] m = {
                {1, 2, 3},
                {4, 5, 6, 7,},
                {8, 7, 6, 5, 4}
        };

        int[] array = {5, 7, 8, 9, 6};
        Spliterator<Integer> spliterator = Arrays.spliterator(array);
        spliterator.forEachRemaining(System.out::println);
    }
}
