package part1.week2.assignment;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node head;
    private Node tail;
    private int size = 0;

    public Deque() {

    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Deque.addFirst couldn't add null value to deque");
        }

        Node oldHead = head;
        head = new Node(item);
        head.next = oldHead;
        if (!isEmpty()) {
            oldHead.prev = head;
        } else {
            tail = head;
        }
        size++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Deque.addLast couldn't add null value to deque");
        }

        Node oldTail = tail;
        tail = new Node(item);
        tail.prev = oldTail;
        if (!isEmpty()) {
            oldTail.next = tail;
        } else {
            head = tail;
        }
        size++;
    }

    public Item removeFirst() {
        if (head == null) {
            throw new NoSuchElementException("Deque.removeFirst couldn't remove as Deque is empty");
        }
        Item item = head.value;
        if (size > 1) {
            head = head.next;
            head.prev = null;
        } else {
            head = null;
            tail = null;
        }
        size--;
        return item;
    }

    public Item removeLast() {
        if (tail == null) {
            throw new NoSuchElementException("Deque.removeLast couldn't remove as Deque is empty");
        }
        Item item = tail.value;
        if (size > 1) {
            tail = tail.prev;
            tail.next = null;
        } else {
            head = null;
            tail = null;
        }
        size--;
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (current == null) {
                throw new NoSuchElementException("DequeIterator.next no more elements");
            }
            Item value = current.value;
            current = current.next;
            return value;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("DequeIterator.remove unsupported operation");
        }
    }

    private class Node {
        Node next;
        Node prev;
        Item value;

        public Node(Item item) {
            this.value = item;
        }
    }
}


