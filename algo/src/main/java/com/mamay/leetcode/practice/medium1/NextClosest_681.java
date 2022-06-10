package com.mamay.leetcode.practice.medium1;

import java.util.HashSet;
import java.util.Set;

public class NextClosest_681 {
    public String nextClosestTime(String time) {
        int cur = 60 * Integer.parseInt(time.substring(0, 2));
        cur += Integer.parseInt(time.substring(3));
        Set<Integer> allowed = new HashSet<>();
        for (char c : time.toCharArray())
            if (c != ':') {
                allowed.add(c - '0');
            }

        for (int i = cur + 1; ; i++) {
            i = i % (24 * 60);
            int[] digits = new int[]{i / 60 / 10, i / 60 % 10, i % 60 / 10, i % 60 % 10};
            boolean flag = true;
            for (int d : digits) if (!allowed.contains(d)) flag = false;
            if (flag) {
                return String.format("%02d:%02d", i / 60, i % 60);
            }
        }
    }
}
