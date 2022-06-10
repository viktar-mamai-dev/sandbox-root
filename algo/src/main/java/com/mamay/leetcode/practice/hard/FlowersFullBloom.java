package com.mamay.leetcode.practice.hard;

import java.util.*;

public class FlowersFullBloom {
    public int[] fullBloomFlowers(int[][] flowers, int[] persons) {
        Map<Integer, Integer> map = new LinkedHashMap<>();
        map.put(0, 0);
        for (int[] flower : flowers) {
            map.put(flower[0], map.getOrDefault(flower[0], 0) + 1);
            map.put(flower[1] + 1, map.getOrDefault(flower[1] + 1, 0) - 1);
        }

        List<Integer> list = new ArrayList<>(map.keySet());
        Collections.sort(list);

        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        int prevKey = list.get(0);
        int prevValue = map.get(prevKey);
        treeMap.put(prevKey, prevValue);

        for (int i = 1; i < list.size(); i++) {
            int nextKey = list.get(i);
            int nextValue = map.get(nextKey) + prevValue;
            treeMap.put(nextKey, nextValue);
            prevValue = nextValue;
        }

        int m = persons.length;
        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            res[i] = treeMap.floorEntry(persons[i]).getValue();
        }
        return res;
    }


}
