package com.mamay.leetcode.practice.medium1;

public class Remove_19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode node = head;
        int size = 0;
        while (node != null) {
            size++;
            node = node.next;
        }

        int idx = size - n;
        if (idx == 0) return head.next;
        ListNode prev = null;
        node = head;
        int i = 0;
        while (i < idx) {
            i++;
            prev = node;
            node = node.next;
        }
        if (node == null) return head;
        prev.next = node.next;
        return head;
    }
}