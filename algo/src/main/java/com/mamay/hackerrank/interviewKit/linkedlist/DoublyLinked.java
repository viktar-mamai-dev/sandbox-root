package com.mamay.hackerrank.interviewKit.linkedlist;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DoublyLinked {

    static class DoublyLinkedListNode {
        public int data;
        public DoublyLinkedListNode next;
        public DoublyLinkedListNode prev;

        public DoublyLinkedListNode(int nodeData) {
            this.data = nodeData;
            this.next = null;
            this.prev = null;
        }
    }

    static class DoublyLinkedList {
        public DoublyLinkedListNode head;
        public DoublyLinkedListNode tail;

        public DoublyLinkedList() {
            this.head = null;
            this.tail = null;
        }

        public void insertNode(int nodeData) {
            DoublyLinkedListNode node = new DoublyLinkedListNode(nodeData);

            if (this.head == null) {
                this.head = node;
            } else {
                this.tail.next = node;
                node.prev = this.tail;
            }

            this.tail = node;
        }
    }

    public static void printDoublyLinkedList(DoublyLinkedListNode node, String sep, BufferedWriter bufferedWriter) throws IOException {
        while (node != null) {
            bufferedWriter.write(String.valueOf(node.data));

            node = node.next;

            if (node != null) {
                bufferedWriter.write(sep);
            }
        }
    }

    private static DoublyLinkedListNode sortedInsert(DoublyLinkedListNode head, int data) {
        DoublyLinkedListNode insert = new DoublyLinkedListNode(data);
        if (head == null) {
            head = insert;
            return head;
        }
        if (data < head.data) {
            insert.next = head;
            head.prev = insert;
            head = insert;
            return head;
        }
        DoublyLinkedListNode prevNode = head;
        DoublyLinkedListNode currentNode = head;
        while (currentNode != null && data >= currentNode.data) {
            prevNode = currentNode;
            currentNode = currentNode.next;
        }
        if (currentNode == null) {
            prevNode.next = insert;
            insert.prev = prevNode;
            return head;
        }

        insert.prev = prevNode;
        insert.next = currentNode;
        prevNode.next = insert;
        currentNode.prev = insert;
        return head;
    }

    private static DoublyLinkedListNode reverse(DoublyLinkedListNode head) {
        if (head == null) {
            return head;
        }

        DoublyLinkedListNode current = head;
        while (current.next != null) {
            DoublyLinkedListNode next = current.next;
            current.next = current.prev;
            current.prev = next;
            current = next;
        }

        current.next = current.prev;
        return current;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("output_hackerrank.txt")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            DoublyLinkedList llist = new DoublyLinkedList();

            int llistCount = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < llistCount; i++) {
                int llistItem = scanner.nextInt();
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                llist.insertNode(llistItem);
            }

            int data = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            DoublyLinkedListNode llist1 = sortedInsert(llist.head, data);

            printDoublyLinkedList(llist1, " ", bufferedWriter);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
