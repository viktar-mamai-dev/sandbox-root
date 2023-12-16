/*
 * Copyright (c) 2023
 */
package com.mamay;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BorderIterator<T extends Number & Comparable<T>> {

  public static void main(String[] args) {
    Iterator<Integer> iterator = Arrays.asList(1, 3, 5, 7, 9, 12, 17).iterator();
    BorderIterator<Integer> borderIterator = new BorderIterator<>();
    Iterator<Integer> outputIterator = borderIterator.convert(iterator, 10);
    borderIterator.print(outputIterator);
  }

  private Iterator<T> convert(Iterator<T> inputIterator, T num) {

    Iterator<T> iter2 =
        new Iterator<T>() {

          T next;
          boolean hasNext;

          @Override
          public boolean hasNext() {
            hasNext = true;
            if (!inputIterator.hasNext()) {
              hasNext = false;
              return false;
            }

            try {
              next = inputIterator.next();
            } catch (NoSuchElementException e) {
              hasNext = false;
              return false;
            }

            if (next.compareTo(num) > 0) {
              hasNext = false;
              return false;
            }
            return true;
          }

          @Override
          public T next() {
            if (!hasNext) {
              throw new NoSuchElementException();
            }

            return next;
          }
        };
    return iter2;
  }

  private void print(Iterator<T> iterator) {
    while (iterator.hasNext()) {
      System.out.print(iterator.next().toString() + "  ");
    }
    System.out.println("=====END=====");
  }
}
