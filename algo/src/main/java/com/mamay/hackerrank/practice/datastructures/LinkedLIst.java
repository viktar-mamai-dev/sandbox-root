package com.mamay.hackerrank.practice.datastructures;

import java.util.Scanner;

public class LinkedLIst {

  static class SinglyLinkedListNode {
    public int data;
    public SinglyLinkedListNode next;

    public SinglyLinkedListNode(int nodeData) {
      this.data = nodeData;
      this.next = null;
    }
  }

  static class SinglyLinkedList {
    public SinglyLinkedListNode head;
    public SinglyLinkedListNode tail;

    public SinglyLinkedList() {
      this.head = null;
      this.tail = null;
    }

    public void insertNode(int nodeData) {
      SinglyLinkedListNode node = new SinglyLinkedListNode(nodeData);

      if (this.head == null) {
        this.head = node;
      } else {
        this.tail.next = node;
      }

      this.tail = node;
    }
  }

  static void printLinkedList(SinglyLinkedListNode head) {
    SinglyLinkedListNode node = head;
    while (node != null) {
      System.out.println(node.data);
      node = node.next;
    }
  }

  static SinglyLinkedListNode insertNodeAtHead(SinglyLinkedListNode head, int data) {
    SinglyLinkedListNode node = new SinglyLinkedListNode(data);
    node.next = head;
    head = node;
    return head;
  }

  static SinglyLinkedListNode insertNodeAtTail(SinglyLinkedListNode head, int data) {
    if (head == null) {
      head = new SinglyLinkedListNode(data);
      return head;
    }
    SinglyLinkedListNode current = head;
    while (current.next != null) {
      current = current.next;
    }

    current.next = new SinglyLinkedListNode(data);
    return head;
  }

  static SinglyLinkedListNode deleteNode(SinglyLinkedListNode head, int position) {
    if (head == null) {
      return null;
    }

    if (position == 0) {
      return head.next;
    }

    SinglyLinkedListNode current = head;
    int i = 0;
    while (i < position - 1) {
      current = current.next;
      i++;
    }

    if (current.next != null) {
      current.next = current.next.next;
    }

    return head;
  }

  static SinglyLinkedListNode reverse(SinglyLinkedListNode head) {
    if (head == null) {
      return head;
    }

    SinglyLinkedListNode current = head;
    SinglyLinkedListNode next = current.next;
    while (next != null) {
      SinglyLinkedListNode nextNext = next.next;
      next.next = current;
      current = next;
      next = nextNext;
    }
    head.next = null;
    return current;
  }

  static void reversePrint(SinglyLinkedListNode head) {
    if (head == null) return;
    StringBuilder a = new StringBuilder();
    SinglyLinkedListNode node = head;

    while (node.next != null) {
      a.insert(0, node.data + "\n");
      node = node.next;
    }
    a.insert(0, node.data);
    System.out.println(a);
  }

  static boolean compareLists(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
    while (head1 != null) {
      if (head2 == null) {
        return false;
      }

      if (head1.data != head2.data) {
        return false;
      }
      head1 = head1.next;
      head2 = head2.next;
    }

    return head2 == null;
  }

  static int getNode(SinglyLinkedListNode head, int positionFromTail) {
    int size = 0;
    SinglyLinkedListNode current = head;
    while (current != null) {
      current = current.next;
      size++;
    }

    int position = size - positionFromTail;
    int i = 1;
    current = head;
    while (i < position) {
      current = current.next;
      i++;
    }
    return current.data;
  }

  static SinglyLinkedListNode removeDuplicates(SinglyLinkedListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    SinglyLinkedListNode current = null;
    SinglyLinkedListNode next = head;

    while (next != null) {
      current = next;
      next = next.next;
      while (next != null && current.data == next.data) {
        current.next = next.next;
        next = next.next;
      }
    }
    return head;
  }

  static boolean hasCycle(SinglyLinkedListNode head) {
    if (head == null) return false;

    SinglyLinkedListNode slow = head;
    SinglyLinkedListNode fast = head.next;
    while (slow != fast) {
      if (fast == null || fast.next == null) return false;

      slow = slow.next;
      fast = fast.next.next;
    }

    return true;
  }

  static SinglyLinkedListNode mergeLists(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
    // TODO
    SinglyLinkedListNode head = null;
    SinglyLinkedListNode current = null;
    while (head1 != null && head2 != null) {
      SinglyLinkedListNode minNode;
      if (head1.data < head2.data) {
        minNode = head1;
      } else {
        minNode = head2;
      }

      if (head == null) {
        head = minNode;
        current = minNode;
      } else {
        current.next = minNode;
        current = minNode;
      }
      head1 = head1.next;
      head2 = head2.next;
    }

    if (head1 != null) {
      while (head1 != null) {
        if (head == null) {
          head = head1;
          current = head1;
        } else {
          current.next = head1;
        }
        head1 = head1.next;
      }
    }

    if (head2 != null) {
      while (head2 != null) {
        if (head == null) {
          head = head2;
          current = head2;
        } else {
          current.next = head2;
        }
        head2 = head2.next;
      }
    }
    return head;
  }

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    SinglyLinkedList llist = new SinglyLinkedList();

    int llistCount = scanner.nextInt();
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    for (int i = 0; i < llistCount; i++) {
      int llistItem = scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      llist.insertNode(llistItem);
    }

    printLinkedList(llist.head);

    scanner.close();
  }
}
