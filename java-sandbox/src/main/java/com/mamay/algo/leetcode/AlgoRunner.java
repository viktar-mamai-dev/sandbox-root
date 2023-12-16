package com.mamay.algo.leetcode;

import java.io.Closeable;
import java.io.IOException;
import java.util.Arrays;

public class AlgoRunner {
    /* add your code here */

    private int gcd(int a, int b) {
        if (a == b) return a;
        if (a < b) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        while (b != 0) {
            int tmp = b;
            b = a % b;
            a = tmp;
        }
        return a;
    }
}

