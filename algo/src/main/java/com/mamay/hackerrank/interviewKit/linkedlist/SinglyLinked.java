package com.mamay.hackerrank.interviewKit.linkedlist;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SinglyLinked {

  private static final Scanner scanner = new Scanner(System.in);

  public static void printSinglyLinkedList(
      SinglyLinkedListNode node, String sep, BufferedWriter bufferedWriter) throws IOException {
    while (node != null) {
      bufferedWriter.write(String.valueOf(node.data));

      node = node.next;

      if (node != null) {
        bufferedWriter.write(sep);
      }
    }
  }

  private static SinglyLinkedListNode insertNodeAtPosition(
      SinglyLinkedListNode head, int data, int position) {
    SinglyLinkedListNode node = new SinglyLinkedListNode(data);
    if (position == 0) {
      node.next = head;
      head = node;
      return head;
    }
    int i = 0;
    SinglyLinkedListNode currentNode = head;
    while (i < position - 1) {
      currentNode = currentNode.next;
      i++;
    }
    node.next = currentNode.next;
    currentNode.next = node;
    return head;
  }

  private static int findMergeNode(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
    int size1 = 0;
    SinglyLinkedListNode node = head1;
    while (node != null) {
      node = node.next;
      size1++;
    }

    int size2 = 0;
    node = head2;
    while (node != null) {
      node = node.next;
      size2++;
    }

    int diff = Math.abs(size1 - size2);
    if (size1 > size2) {
      while (diff > 0) {
        head1 = head1.next;
        diff--;
      }
    } else {
      while (diff > 0) {
        head2 = head2.next;
        diff--;
      }
    }

    while (head1 != head2) {
      head1 = head1.next;
      head2 = head2.next;
    }
    return head1.data;
  }

  public static void main(String[] args) throws IOException {
    try (BufferedWriter bufferedWriter =
        new BufferedWriter(new FileWriter(new File("output_hackerrank.txt")))) {

      SinglyLinkedList llist = new SinglyLinkedList();

      int llistCount = scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      for (int i = 0; i < llistCount; i++) {
        int llistItem = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        llist.insertNode(llistItem);
      }

      int data = scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      int position = scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      SinglyLinkedListNode llist_head = insertNodeAtPosition(llist.head, data, position);

      printSinglyLinkedList(llist_head, " ", bufferedWriter);
      bufferedWriter.newLine();
    }

    scanner.close();
  }

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
}
