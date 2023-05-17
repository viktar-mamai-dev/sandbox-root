package com.mamay.leetcode.practice.easy3;

public class RemoveDuplicates_26 {
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        if(n <= 1) return n;
        int a1 = nums[0];
        int count = 1;
        int right = n-1;
        int i;
        for(i = 1; i<=right;) {
            if(nums[i] == a1) {
                bubble(nums, i, right);
                right--;
            } else {
                a1 = nums[i];
                i++;
                count++;
            }
        }
        return count;
    }

    private void bubble(int[] nums, int i, int right) {
        int val = nums[i];
        for(int k = i; k<right; k++) {
            nums[k] = nums[k+1];
        }
        nums[right] = val;
    }
}
