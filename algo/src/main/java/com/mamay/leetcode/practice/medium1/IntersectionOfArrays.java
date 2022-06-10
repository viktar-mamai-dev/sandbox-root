package com.mamay.leetcode.practice.medium1;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IntersectionOfArrays {
    public List<Integer> intersection(int[][] nums) {
        HashSet<Integer> set = Arrays.stream(nums[0]).boxed()
                .collect(Collectors.toCollection(HashSet::new));

        for (int i = 1; i < nums.length; i++) {
            set = intersect(set, nums[i]);
        }

        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list);
        return list;
    }

    private HashSet<Integer> intersect(HashSet<Integer> set, int[] arr) {
        return Arrays.stream(arr).boxed().filter(set::contains)
                .collect(Collectors.toCollection(HashSet::new));
    }
}
