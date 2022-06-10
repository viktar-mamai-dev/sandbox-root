package com.mamay.leetcode.practice.easy2;

import java.util.TreeSet;

class ExamRoom {


    private TreeSet<Integer> set = new TreeSet<>();
    private int n;

    public ExamRoom(int N) {
        this.n = N;
        set = new TreeSet<>();
    }

    public int seat() {
        int pos = 0;
        if (set.size() > 0) {
            int prev = -1;
            int dist = set.first();
            for (int stud : set) {
                if (prev != -1) {
                    int d = (stud - prev) / 2;
                    if (d > dist) {
                        dist = d;
                        pos = prev + d;
                    }
                }
                prev = stud;
            }
            if (n - 1 - set.last() > dist) {
                pos = n-1;
            }
        }
        set.add(pos);
        return pos;
    }

    public void leave(int p) {
        set.remove(p);
    }
}
