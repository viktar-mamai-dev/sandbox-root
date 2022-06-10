package com.mamay.leetcode.practice.easy1;

public class MergeTwo_21 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode p = l1;
        ListNode q = l2;
        ListNode res = new ListNode(0);

        ListNode node = res;
        ListNode current = null;
        while (p != null || q != null) {
            if (p == null) {
                current = q;
                q = q.next;
            } else if (q == null) {
                current = p;
                p = p.next;
            } else {
                if (p.val <= q.val) {
                    current = p;
                    p = p.next;
                } else {
                    current = q;
                    q = q.next;
                }
            }
            node.next = current;
            node = current;
        }
        return res.next;
    }
}

