package com.mamay.leetcode.practice.easy2;

public class Merge_88 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int idx1 = 0, idx2 = 0;
        while (idx1 + idx2 < m + n) {
            if (idx2 >= n)
                break;
            if (idx1 >= m) {
                while (idx2 < n) {
                    nums1[idx1 + idx2] = nums2[idx2];
                    idx2++;
                }
                break;
            }
            if (nums1[idx1 + idx2] > nums2[idx2]) {
                for (int k = idx2 + m; k > idx1 + idx2; k--) {
                    nums1[k] = nums1[k - 1];
                }
                nums1[idx1 + idx2] = nums2[idx2];
                idx2++;
            } else {
                idx1++;
            }
        }
    }
}
