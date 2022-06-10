package com.mamay.leetcode.practice.easy1;

public class Reverse_206 {


    private ListNode root;

    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        recursive(head);
        return root;
    }

    private ListNode recursive(ListNode node) {
        if (node.next == null) {
            root = node;
            return node;
        }

        ListNode node1 = recursive(node.next);
        node1.next = node;
        return node;
    }

}