/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

package part1;

import edu.princeton.cs.algs4.StdRandom;
import java.util.List;

public class ArrayHelper<T extends Comparable<T>> {
  public void printArray(T[] array) {
    System.out.println("Printing array....");
    for (T anArray : array) {
      System.out.print(anArray);
      System.out.print(" ");
    }
    System.out.println("\n============================");
  }

  public void printArrays(T[]... arrayList) {
    System.out.println("Printing arrays....");
    for (T[] array1 : arrayList) {
      for (T anArray : array1) {
        System.out.print(anArray);
        System.out.print(" ");
      }
      System.out.println();
    }
    System.out.println("\n============================");
  }

  public void printArray(List<T> array) {
    System.out.println("Printing list....");
    for (T anArray : array) {
      System.out.print(anArray);
      System.out.print(" ");
    }
    System.out.println("\n============================");
  }

  public Integer[] copy(Integer[] array) {
    int n = array.length;
    Integer[] copy = new Integer[n];
    System.arraycopy(array, 0, copy, 0, n);
    return copy;
  }

  public Integer[] generateRandomArray(int size, Integer maxBound) {
    Integer[] array = new Integer[size];
    for (int i = 0; i < size; i++) {
      array[i] = StdRandom.uniform(maxBound);
    }
    return array;
  }

  public boolean isSorted(T[] array) {
    int n = array.length;
    for (int i = 0; i < n - 1; i++) {
      if (array[i].compareTo(array[i + 1]) > 0) {
        return false;
      }
    }
    return true;
  }

  public void shuffle(T[] array) {
    int n = array.length;
    for (int i = 1; i < n; i++) {
      int r = StdRandom.uniform(i);
      swap(array, r, i);
    }
  }

  private void swap(T[] array, int indexI, int indexJ) {
    if (indexI == indexJ) return;
    T temp = array[indexI];
    array[indexI] = array[indexJ];
    array[indexJ] = temp;
  }
}
