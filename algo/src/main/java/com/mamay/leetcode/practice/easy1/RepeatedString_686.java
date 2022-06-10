package com.mamay.leetcode.practice.easy1;

public class RepeatedString_686 {
    public int repeatedStringMatch(String A, String B) {
        if (A.contains(B)) {
            return 1;
        }

        StringBuilder builder = new StringBuilder(A);
        int q = 1;
        while (builder.length() < B.length()) {
            builder.append(A);
            q++;
        }
        if (builder.indexOf(B) != -1) {
            return q;
        }
        builder.append(A);
        if (builder.indexOf(B) != -1) {
            return 1 + q;
        }
        return -1;
    }
}
