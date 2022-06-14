package edu.coursera.concurrent.week1;

import java.util.concurrent.locks.ReentrantLock;

/**
 * An implementation of the ListSet interface that uses Java locks to
 * protect against concurrent accesses.
 * <p>
 * TODO Implement the add, remove, and contains methods below to support
 * correct, concurrent access to this list. Use a Java ReentrantLock object
 * to protect against those concurrent accesses. You may refer to
 * SyncList.java for help understanding the list management logic, and for
 * guidance in understanding where to place lock-based synchronization.
 */
class CoarseList extends ListSet {
    /*
     * TODO Declare a lock for this class to be used in implementing the
     * concurrent add, remove, and contains methods below.
     */
    private final ReentrantLock lock = new ReentrantLock();

    /**
     * Default constructor.
     */
    public CoarseList() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(final Integer object) {
        try {
            lock.lock();

            Entry pred = this.head;
            Entry curr = pred.next;

            while (curr.object.compareTo(object) < 0) {
                pred = curr;
                curr = curr.next;
            }

            if (object.equals(curr.object)) {
                return false;
            } else {
                final Entry entry = new Entry(object);
                entry.next = curr;
                pred.next = entry;
                return true;
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     */
    @Override
    public boolean remove(final Integer object) {
        try {
            lock.lock();

            Entry pred = this.head;
            Entry curr = pred.next;

            while (curr.object.compareTo(object) < 0) {
                pred = curr;
                curr = curr.next;
            }

            if (object.equals(curr.object)) {
                pred.next = curr.next;
                return true;
            } else {
                return false;
            }
        } finally {
            lock.unlock();
        }

    }

    /**
     * {@inheritDoc}
     * <p>
     */
    @Override
    public boolean contains(final Integer object) {
        try {
            lock.lock();

            Entry pred = this.head;
            Entry curr = pred.next;

            while (curr.object.compareTo(object) < 0) {
                pred = curr;
                curr = curr.next;
            }
            return object.equals(curr.object);
        } finally {
            lock.unlock();
        }

    }
}
