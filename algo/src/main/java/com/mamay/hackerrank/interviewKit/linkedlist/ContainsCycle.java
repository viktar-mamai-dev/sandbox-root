package com.mamay.hackerrank.interviewKit.linkedlist;

public class ContainsCycle {
  boolean hasCycle(Node head) {
    if (head == null) return false;

    Node slow = head;
    Node fast = head.next;
    while (slow != fast) {
      if (fast == null || fast.next == null) return false;

      slow = slow.next;
      fast = fast.next.next;
    }

    return true;
  }

  class Node {
    int data;
    Node next;
  }
}
