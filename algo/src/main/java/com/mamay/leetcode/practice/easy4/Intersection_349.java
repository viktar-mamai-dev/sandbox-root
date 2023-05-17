package com.mamay.leetcode.practice.easy4;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Intersection_349 {
    public int[] intersection(int[] nums1, int[] nums2) {

        HashMap<Integer, Integer> map1 = new HashMap<>();
        HashMap<Integer, Integer> map2 = new HashMap<>();

        for (int n : nums1) {
            Integer value = map1.getOrDefault(n, 0);
            map1.put(n, value + 1);
        }

        for (int n : nums2) {
            Integer value = map2.getOrDefault(n, 0);
            map2.put(n, value + 1);
        }

        Set<Integer> list = new HashSet<>();
        for (Map.Entry<Integer, Integer> entry : map1.entrySet()) {
            Integer value = entry.getValue();
            int min = Math.min(map2.getOrDefault(entry.getKey(), 0), value);
            for (int i = 0; i < min; i++) {
                list.add(entry.getKey());
            }
        }

        int size = list.size();
        int res[] = new int[size];
        int i = 0;
        for (Integer value : list) {
            res[i++] = value;
        }
        return res;
    }
}
