package part1.week2.assignment;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private static final int DEFAULT_SIZE = 2;
    private static final double RESIZE_COEFF = 2;
    private static final double SHRINK_COEFF = 0.25;
    private Item[] items;
    private int size = 0;

    public RandomizedQueue() {
        items = (Item[]) new Object[DEFAULT_SIZE];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Deque.addFirst couldn't add null value to deque");
        }
        autoEnlarge();

        items[size++] = item;
    }

    private void autoEnlarge() {
        if (size == items.length) {
            resize(RESIZE_COEFF);
        }
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("RandomizedQueue.dequeue no elements in queue");
        }
        int index = randomIdex();
        Item item = items[index];
        items[index] = items[size - 1];
        items[--size] = null;

        autoShrink();

        return item;
    }

    private void autoShrink() {
        if (size != 0 && size <= SHRINK_COEFF * items.length) {
            resize(SHRINK_COEFF);
        }
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("RandomizedQueue.sample no elements in queue");
        }
        return items[randomIdex()];
    }

    private void resize(double coeff) {
        int newSize = (int) (coeff * items.length);
        Item[] newItems = (Item[]) new Object[newSize];
        for (int i = 0; i < size; i++) {
            newItems[i] = items[i];
        }
        items = newItems;
    }

    private int randomIdex() {
        return StdRandom.uniform(0, size);
    }

    @Override
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private final Item[] iteratorItems;
        private int index = 0;
        private final int iteratorItemsSize;

        public RandomizedQueueIterator() {
            this.iteratorItemsSize = size;
            iteratorItems = (Item[]) new Object[items.length];
            System.arraycopy(items, 0, iteratorItems, 0, items.length);
            StdRandom.shuffle(iteratorItems, 0, iteratorItemsSize);
        }

        @Override
        public boolean hasNext() {
            return index < iteratorItemsSize;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("RandomizedQueue.dequeue no elements in queue");
            }
            return iteratorItems[index++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("RandomizedQueueIterator.remove unsupported operation");
        }
    }
}
