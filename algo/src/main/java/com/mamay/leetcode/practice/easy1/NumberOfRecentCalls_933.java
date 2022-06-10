package com.mamay.leetcode.practice.easy1;

import java.util.ArrayList;
import java.util.List;

public class NumberOfRecentCalls_933 {
}

class RecentCounter {

    private List<Integer> pingList = new ArrayList<>();

    public RecentCounter() {

    }

    public int ping(int t) {
        int counter = 1;
        int i = pingList.size() - 1;
        while (i >= 0 && pingList.get(i) + 3000 >= t) {
            counter++;
            i--;
        }
        pingList.add(t);
        return counter;
    }
}