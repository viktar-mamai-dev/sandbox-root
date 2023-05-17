package com.mamay.leetcode.practice.medium3;

import java.util.Arrays;

public class LUPrefix {

    private final int[] parent;
    private final int n;

    public LUPrefix(int n) {
        this.n = n;
        parent = new int[n + 1];
        Arrays.fill(parent, 0);
    }

    public void upload(int video) {
        parent[video] = video;
        if (video < n) {
            if (parent[video + 1] != 0) {
                int rightParent = find(video + 1);
                parent[video] = rightParent;
            }
        }

        if (video > 1) {
            if (parent[video - 1] != 0) {
                parent[video - 1] = parent[video];
            }
        }
    }

    public int longest() {
        return parent[1] == 0 ? 0 : find(1);
    }

    private int find(int video) {
        while (video != parent[video]) {
            parent[video] = parent[parent[video]];
            video = parent[video];
        }
        return video;
    }
}
