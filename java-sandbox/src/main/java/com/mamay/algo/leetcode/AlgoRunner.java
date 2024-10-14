package com.mamay.algo.leetcode;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
}

class neighborSum {

  private final int[][] grid;
  private final HashMap<Integer, int[]> map = new HashMap<>();

  public neighborSum(int[][] grid) {
    this.grid = grid;
    for (int i = 0; i < grid.length; i++) {
      for (int k = 0; k < grid[0].length; k++) {
        map.put(grid[i][k], new int[] {i, k});
      }
    }
  }

  public int adjacentSum(int value) {
    int[] indexes = map.get(value);
    int i = indexes[0], k = indexes[1];
    return getValue(i - 1, k) + getValue(i + 1, k) + getValue(i, k - 1) + getValue(i, k + 1);
  }

  public int diagonalSum(int value) {
    int[] indexes = map.get(value);
    int i = indexes[0], k = indexes[1];
    return getValue(i - 1, k - 1)
        + getValue(i + 1, k + 1)
        + getValue(i + 1, k - 1)
        + getValue(i - 1, k + 1);
  }

  private int getValue(int rowIdx, int colIdx) {
    if (rowIdx < 0 || rowIdx >= grid.length || colIdx < 0 || colIdx >= grid[0].length) {
      return 0;
    }
    return grid[rowIdx][colIdx];
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

  public List<Integer> stableMountains(int[] height, int threshold) {
    int len = height.length;
    return IntStream.range(1, len).filter(i -> height[i - 1] > threshold).boxed().toList();
  }

  public boolean reportSpam(String[] message, String[] bannedWords) {
    Set<String> bannedSet = Arrays.stream(bannedWords).collect(Collectors.toSet());
    long count = Arrays.stream(message).filter(bannedSet::contains).count();
    return count >= 2;
  }

  public long maxScore(int[] a, int[] b) {
    int len = b.length;
    long[][] dp = new long[len][4];
    dp[0][0] = Long.valueOf(a[0]) * b[0];
    for (int i = 1; i < len; i++) {
      dp[i][0] = Math.max(dp[i - 1][0], Long.valueOf(a[0]) * b[i]);
    }
    for (int i = 1; i < 4; i++) {
      dp[i][i] = dp[i - 1][i - 1] + Long.valueOf(a[i]) * b[i];
      for (int k = i + 1; k < len; k++) {
        dp[k][i] = +Math.max(dp[k - 1][i], dp[k - 1][i - 1] + Long.valueOf(a[i]) * b[k]);
      }
    }
    return dp[len - 1][3];
  }

  public int[] getSneakyNumbers(int[] nums) {
    HashSet<Integer> set = new HashSet<>();
    int[] res = new int[2];
    int idx = 0;
    for (int num : nums) {
      if (set.contains(num)) {
        res[idx++] = num;
      } else {
        set.add(num);
      }
    }
    return res;
  }

  public long findMaximumScore(List<Integer> nums) {
    int len = nums.size();
    if (len == 1) {
      return 0;
    }
    long[] dp = new long[len];
    dp[0] = 0;
    for (int i = 1; i < len; i++) {
      final int i1 = i;
      dp[i] =
          IntStream.range(0, i)
              .mapToLong(k -> Long.valueOf(nums.get(k)) * (i1 - k) + dp[k])
              .max()
              .getAsLong();
    }
    return dp[len - 1];
  }

  public int maxPossibleScore(int[] start, int d) {
    Arrays.sort(start);
    int len = start.length;
    IntSummaryStatistics statistics =
        IntStream.range(0, len - 1).map(i -> start[i + 1] - start[i]).summaryStatistics();
    int high = statistics.getMax();
    int low = statistics.getMin();
    System.out.println("low = " + low + " high = " + high);
    while (low < high) {
      int mid = (low + high + 1) / 2;
      if (isPossibleScore(start, d, mid)) {
        low = mid;
        System.out.println("Setting low to " + low);
      } else {
        high = mid - 1;
        System.out.println("Setting high to " + high);
      }
    }
    return low;
  }

  private boolean isPossibleScore(int[] start, int d, int mid) {
    int len = start.length;
    int prev = start[0];
    for (int i = 1; i < len; i++) {
      int next = prev + mid;
      if (next < start[i]) {
        prev = start[i];
      } else if (next > start[i] + d) {
        return false;
      } else {
        prev = next;
      }
    }
    return true;
  }
}

class ListNode {
  int val;
  ListNode next;

  ListNode() {}

  ListNode(int val) {
    this.val = val;
  }

  ListNode(int val, ListNode next) {
    this.val = val;
    this.next = next;
  }
}
