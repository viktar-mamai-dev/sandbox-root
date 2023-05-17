package com.mamay.leetcode.practice.easy4;

class MinStack {

    private Node tail;

    public MinStack() {

    }

    public void push(int x) {
        tail = new Node(tail, x);
    }

    public void pop() {
        tail = tail.prev;
    }

    public int top() {
        return tail.value;
    }

    public int getMin() {
        return tail.minValue;
    }

    class Node {
        int value;
        Node prev;
        int minValue;

        Node(Node prev, int value) {
            this.prev = prev;
            this.value = value;
            this.minValue = value;
            if (prev != null && prev.minValue < this.minValue) {
                this.minValue = prev.minValue;
            }
        }
    }

}

