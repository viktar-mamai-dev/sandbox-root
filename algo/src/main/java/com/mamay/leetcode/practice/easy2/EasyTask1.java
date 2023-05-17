package com.mamay.leetcode.practice.easy2;

import java.util.HashMap;

public class EasyTask1 {

    public int rearrangeCharacters(String s, String target) {
        HashMap<Character, Integer> sMap = new HashMap<>();
        HashMap<Character, Integer> targetMap = new HashMap<>();
        for (char ch : s.toCharArray()) {
            sMap.put(ch, sMap.getOrDefault(ch, 0) + 1);
        }

        for (char ch : target.toCharArray()) {
            targetMap.put(ch, targetMap.getOrDefault(ch, 0) + 1);
        }

        int res = Integer.MAX_VALUE;
        for (char ch : targetMap.keySet()) {
            int tVal = targetMap.get(ch);
            Integer sVal = sMap.get(ch);
            if (sVal == null) return 0;
            res = Math.min(res, sVal / tVal);
            if (res == 0) return res;
        }
        return res;
    }

    public int totalSteps(int[] nums) {
        int n = nums.length;
        Node root = new Node(0);
        Node current = root;
        for (int i = n - 1; i >= 0; i--) {
            current.next = new Node(nums[i]);
            current = current.next;
        }

        int iter = 0;
        while (true) {
            boolean flag = true;
            Node prev = root;
            current = prev.next;
            while (current.next != null) {
                if (current.val < current.next.val) {
                    prev.next = current.next;
                    current = current.next;
                    flag = false;
                } else {
                    prev = current;
                    current = current.next;
                }
            }
            if (flag) break;
            iter++;
        }
        return iter;
    }

    private static class Node {
        public Node next;
        private final int val;

        public Node(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }
    }
}
