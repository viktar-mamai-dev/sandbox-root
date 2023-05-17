package part1.week4;

import java.util.Arrays;

public class MaxMinPriorityQueue {
  protected int[] array;
  protected int size;
  private final int isMaxHeap;

  public MaxMinPriorityQueue(int[] a, boolean isMax) {
    this.isMaxHeap = isMax ? -1 : 1;
    size = a.length + 1;
    this.array = new int[size]; // array[0] - useless element
    System.arraycopy(a, 0, array, 1, size - 1);
    for (int k = size - 1; k > 1; k--) swim(k);
    for (int k = 1; k <= size / 2; k++) sink(k);
  }

  public int size() {
    return size - 1;
  }

  public boolean isEmpty() {
    return size == 1;
  }

  public int retrieveHead() {
    return array[1];
  }

  public void insert(int value) {
    autoEnlarge();
    array[size] = value;
    swim(size);
    size++;
  }

  private void swim(int k) {
    while (k > 1 && Integer.compare(array[k / 2], array[k]) == isMaxHeap) {
      swap(k, k / 2);
      k /= 2;
    }
  }

  private void autoEnlarge() {
    if (size >= array.length) {
      int[] newItems = new int[size * 2];
      System.arraycopy(array, 0, newItems, 0, size);
      array = newItems;
    }
  }

  public int deleteHead() {
    int max = array[1];
    swap(1, size - 1);
    size--;
    sink(1);
    // array[size + 1] = null;
    return max;
  }

  protected void sink(int k) {
    while (2 * k < size) {
      int child = 2 * k;
      if (child + 1 < size && Integer.compare(array[child], array[child + 1]) == -1) {
        child++;
      }
      if (Integer.compare(array[child], array[k]) == isMaxHeap || array[child] == array[k]) {
        break;
      }
      swap(k, child);
      k = child;
    }
  }

  public void sort() {
    for (int k = size / 2; k >= 1; k--) {
      sink(k);
    }

    int n = size;
    for (int i = 0; i < n - 1; i++) {
      swap(1, size - 1);
      size--;
      sink(1);
    }
    size = n;
  }

  protected void swap(int indexI, int indexJ) {
    if (indexI == indexJ) return;
    int temp = array[indexI];
    array[indexI] = array[indexJ];
    array[indexJ] = temp;
  }

  public void printArray() {
    int[] res = new int[size - 1];
    System.arraycopy(this.array, 1, res, 0, size - 1);
    System.out.println(Arrays.toString(res));
  }
}
