package edu.coursera.concurrent.week1;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * An implementation of the ListSet interface that uses Java read-write locks to protect against
 * concurrent accesses.
 *
 * <p>TODO Implement the add, remove, and contains methods below to support correct, concurrent
 * access to this list. Use a Java ReentrantReadWriteLock object to protect against those concurrent
 * accesses. You may refer to SyncList.java for help understanding the list management logic, and
 * for guidance in understanding where to place lock-based synchronization.
 */
class RWCoarseList extends ListSet {
  /*
   * TODO Declare a read-write lock for this class to be used in
   * implementing the concurrent add, remove, and contains methods below.
   */
  private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

  /** Default constructor. */
  public RWCoarseList() {
    super();
  }

  /**
   * {@inheritDoc}
   *
   * <p>TODO Use a read-write lock to protect against concurrent access.
   */
  @Override
  public boolean add(final Integer object) {
    try {
      lock.writeLock().lock();

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
      lock.writeLock().unlock();
    }
  }

  /**
   * {@inheritDoc}
   *
   * <p>TODO Use a read-write lock to protect against concurrent access.
   */
  @Override
  public boolean remove(final Integer object) {
    try {
      lock.writeLock().lock();

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
      lock.writeLock().unlock();
    }
  }

  /**
   * {@inheritDoc}
   *
   * <p>TODO Use a read-write lock to protect against concurrent access.
   */
  @Override
  public boolean contains(final Integer object) {
    try {
      lock.readLock().lock();

      Entry pred = this.head;
      Entry curr = pred.next;

      while (curr.object.compareTo(object) < 0) {
        pred = curr;
        curr = curr.next;
      }
      return object.equals(curr.object);
    } finally {
      lock.readLock().unlock();
    }
  }
}
