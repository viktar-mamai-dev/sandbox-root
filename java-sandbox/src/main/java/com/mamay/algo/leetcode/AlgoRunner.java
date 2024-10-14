package com.mamay.algo.leetcode;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AlgoRunner {
  /* add your code here */

  private <T> Map<T, Long> toCountingMap(Stream<T> stream) {
    return stream.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
  }

  private int min(int... lens) {
    long min = Integer.MAX_VALUE;
    for (int len : lens) {
      min = Math.min(min, len);
    }
    return (int) min;
  }

  private void swap(long[] arr1, long[] arr2) {
    int size = arr1.length;
    long[] temp = new long[size];
    System.arraycopy(arr1, 0, temp, 0, size);
    System.arraycopy(arr2, 0, arr1, 0, size);
    System.arraycopy(temp, 0, arr2, 0, size);
  }

  private void dec(Map<Integer, Integer> map, int key) {
    int value = map.get(key) - 1;
    if (value == 0) map.remove(key);
    else map.put(key, value);
  }

  private void inc(Map<Integer, Integer> map, int key) {
    int value = map.getOrDefault(key, 0) + 1;
    map.put(key, value);
  }

  private int toIdx(int i1, int i2, int columnCount) {
    return i1 * columnCount + i2;
  }

  private int gcd(int a, int b) {
    if (a == b) return a;
    if (a < b) {
      int tmp = a;
      a = b;
      b = tmp;
    }

    while (b != 0) {
      int tmp = b;
      b = a % b;
      a = tmp;
    }
    return a;
  }

  private boolean isPrime(int x) {
    if (x == 1) return false;
    if (x == 2 || x == 3) return true;
    if (x % 2 == 0) return false;
    for (int y = 3; y <= Math.sqrt(x); y += 2) {
      if (x % y == 0) return false;
    }
    return true;
  }

  public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
    PriorityQueue<long[]> queue =
            new PriorityQueue<>(Comparator.comparingLong((long[] a) -> a[0] * a[1]));
    for (int workerTime : workerTimes) {
      queue.add(new long[] {workerTime, 1});
    }
    long res = 0l;
    for (int i = 0; i < mountainHeight; i++) {
      long[] min = queue.poll();
      res += min[1] * min[0];
      min[1]++;
      queue.add(min);
    }
    long max = Integer.MIN_VALUE;
    while (!queue.isEmpty()) {
      long[] min = queue.poll();
      max = Math.max(max, min[0] * (min[1] - 1));
    }
    return max - 1;
  }
}

