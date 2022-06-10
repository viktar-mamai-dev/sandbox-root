package com.mamay.leetcode.practice.easy1;

public class CanPlaceFowers_605 {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int len = flowerbed.length;
        int count = 0;

        int i = 0;
        int localCount = 1;
        while (i < len) {
            while (i < len && flowerbed[i] == 0) {
                localCount++;
                i++;
                if (i >= len) {
                    localCount++;
                }
            }
            count += (localCount - 1) / 2;
            if (count >= n) {
                return true;
            }
            i++;
            localCount = 0;
        }
        return false;
    }
}
