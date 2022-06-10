package com.mamay.leetcode.practice.medium1;

import java.math.BigInteger;

public class AddTwo_2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        BigInteger a1 = marshal(l1);
        BigInteger a2 = marshal(l2);
        BigInteger a3 = a1.add(a2);
        return unmarshal(a3);
    }

    private BigInteger marshal(ListNode l2) {
        StringBuilder builder = new StringBuilder();
        while (l2 != null) {
            builder.append(l2.val);
            l2 = l2.next;
        }
        return new BigInteger(builder.reverse().toString());
    }

    private ListNode unmarshal(BigInteger b) {
        ListNode res = new ListNode(0);
        ListNode current = res;
        char[] charArray = b.toString().toCharArray();
        int n = charArray.length;
        for (int i = n - 1; i >= 0; i--) {
            ListNode l = new ListNode(Character.getNumericValue(charArray[i]));
            current.next = l;
            current = l;
        }
        return res.next;
    }

}

