package com.mamay.leetcode.practice.medium1;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.stream.Collectors;
import lombok.Data;

public class Sum2416 {
  public int[] sumPrefixScores(String[] words) {
    int len = words.length;
    Trie trie = new Trie();
    for (String w : words) {
      trie.add(w);
    }
    int[] resArray = new int[len];
    for (int i = 0; i < len; i++) {
      resArray[i] = trie.find(words[i]);
    }
    return resArray;
  }

  public int countDistinctIntegers(int[] nums) {
    HashSet<Integer> unique =
        Arrays.stream(nums).boxed().collect(Collectors.toCollection(HashSet::new));
    HashSet<Integer> res = new HashSet<>();
    for (int x : unique) {
      res.add(x);
      res.add(reverse(x));
    }
    return res.size();
  }

  private static class OddIntComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer o1, Integer o2) {
      if (o1 % 2 == 1 && o2 % 2 == 0) return -1;
      if (o1 % 2 == 0 && o2 % 2 == 1) return 1;
      return Integer.compare(o1, o2);
    }
  }

  public long makeSimilar(int[] nums, int[] target) {
    final OddIntComparator comparator = new OddIntComparator();
    nums = Arrays.stream(nums).boxed().sorted(comparator).mapToInt(i -> i).toArray();
    target = Arrays.stream(target).boxed().sorted(comparator).mapToInt(i -> i).toArray();
    long sum = 0L;
    for (int i = 0; i < nums.length; i++) {
      int diff = nums[i] - target[i];
      if (diff > 0) sum += diff;
    }
    return sum / 2;
  }

  public int numberOfNodes(int n, int[] queries) {
    final int[] arr = new int[n + 1];
    Arrays.fill(arr, 0);
    for (int q : queries) {
      arr[q]++;
    }
    for (int i = 2; i < arr.length; i++) {
      arr[i] += arr[i / 2];
    }
    return (int) Arrays.stream(arr).filter(i -> i % 2 == 1).count();
  }

  public int numberOfNodes22(int n, int[] queries) {
    final int[] arr = new int[n + 1];
    Arrays.fill(arr, 0);
    for (int q : queries) {
      flip(arr, q);
    }
    return (int) Arrays.stream(arr).filter(i -> i == 1).count();
  }

  private void flip(int[] arr, int i) {
    if (i >= arr.length) return;
    arr[i] = 1 - arr[i];
    flip(arr, 2 * i);
    flip(arr, 2 * i + 1);
  }

  public String kthSmallestPath(int[] destination, int k) {
    char[] chStr = new char[destination[0] + destination[1]];
    Arrays.fill(chStr, 'H');
    int idxStart = destination[1] - k / destination[0];
    k = k % destination[0];
    for (int i = idxStart + k; i < idxStart + destination[0]; i++) {
      chStr[i] = 'V';
    }
    if (k != 0) {
      for (int i = idxStart - 1; i < idxStart - 1 + k; i++) {
        chStr[i] = 'V';
      }
    }
    return new String(chStr);
  }

  private int reverse(int x) {
    int y = 0;
    while (x != 0) {
      y = 10 * y + x % 10;
      x /= 10;
    }
    return y;
  }

  public String[] sortPeople(String[] names, int[] heights) {
    int n = names.length;
    TreeMap<Integer, String> map = new TreeMap<>(Comparator.reverseOrder());
    for (int i = 0; i < n; i++) {
      map.put(heights[i], names[i]);
    }
    return map.values().toArray(new String[n]);
  }

  public List<Integer> goodIndices(int[] nums, int k) {
    int n = nums.length;
    int[] left = new int[n];
    int[] right = new int[n];
    left[0] = 1;
    right[n - 1] = 1;
    for (int i = 1; i < n; i++) {
      if (nums[i] <= nums[i - 1]) {
        left[i] = left[i - 1] + 1;
      } else {
        left[i] = 1;
      }

      if (nums[n - i - 1] <= nums[n - i]) {
        right[n - i - 1] = right[n - i] + 1;
      } else {
        right[n - i - 1] = 1;
      }
    }
    List<Integer> indexList = new ArrayList<>();
    for (int i = k; i <= n - k - 1; i++) {
      if (left[i - 1] >= k && right[i + 1] >= k) {
        indexList.add(i);
      }
    }
    return indexList;
  }

  public int minimumOperations(int[] nums) {
    int len = nums.length;
    int count = 0;
    int leftIdx = 0, rightIdx = len - 1;
    while (leftIdx + 1 < rightIdx) {
      if (nums[leftIdx] == nums[rightIdx]) {
        leftIdx++;
        rightIdx--;
        continue;
      }
      count++;
      if (nums[leftIdx] < nums[rightIdx]) {
        nums[leftIdx + 1] += nums[leftIdx];
        leftIdx++;
      } else {
        nums[rightIdx - 1] += nums[rightIdx];
        rightIdx--;
      }
    }
    if (nums[leftIdx] != nums[rightIdx]) {
      count++;
    }
    return count;
  }

  public boolean equalFrequency(String word) {
    HashMap<Character, Integer> charToFreq = new HashMap<>();
    HashMap<Integer, Integer> freqToCount = new HashMap<>();
    for (char ch : word.toCharArray()) {
      charToFreq.put(ch, charToFreq.getOrDefault(ch, 0) + 1);
    }

    for (int freq : charToFreq.values()) {
      freqToCount.put(freq, freqToCount.getOrDefault(freq, 0) + 1);
    }

    if (freqToCount.size() >= 3) return false;
    if (freqToCount.size() == 1) {
      int key = new ArrayList<>(freqToCount.keySet()).get(0);
      return key == 1 || freqToCount.get(key) == 1;
    }
    ArrayList<Integer> keyList = new ArrayList<>(freqToCount.keySet());
    Collections.sort(keyList);
    return (keyList.get(0) + 1 == keyList.get(1) && freqToCount.get(keyList.get(1)) == 1)
        || (keyList.get(0) == 1 && freqToCount.get(keyList.get(0)) == 1);
  }

  public int commonFactors(int a, int b) {
    int x = gcd(a, b);
    int res = 1;
    for (int i = 2; i <= x; i++) {
      if (x % i != 0) continue;
      int tmp = 1;
      while (x % i == 0) {
        tmp++;
        x /= i;
      }
      res *= tmp;
    }
    return res;
  }

  public long makeIntegerBeautiful(long n, int target) {
    List<Byte> initList = toList(n);
    List<Byte> list = new ArrayList<>(initList);
    int len = list.size();
    for (int i = 0; i < len; i++) {
      int sum = list.stream().mapToInt(x -> x).sum();
      if (sum <= target) {
        return toLong(list) - n;
      }
      list.set(i, (byte) 0);
      while (i < len - 1 && list.get(i + 1) == 9) {
        i++;
        list.set(i, (byte) 0);
      }
      if (i < len - 1) {
        list.set(i + 1, (byte) (list.get(i + 1) + 1));
      } else {
        list.add((byte) 1);
      }
    }
    return toLong(list) - n;
  }

  private List<Byte> toList(long n) {
    List<Byte> resList = new ArrayList<>();
    while (n != 0) {
      resList.add((byte) (n % 10));
      n /= 10;
    }
    return resList;
  }

  private long toLong(List<Byte> list) {
    long res = 0L;
    for (int i = list.size() - 1; i >= 0; i--) {
      res = 10 * res + list.get(i);
    }
    return res;
  }

  public int destroyTargets(int[] nums, int space) {
    HashMap<Integer, Integer> remToCount = new HashMap<>();
    HashMap<Integer, Integer> remToMinValue = new HashMap<>();
    int maxRem = 0, maxFreq = 0;
    for (int num : nums) {
      int rem = num % space;
      int count = remToCount.getOrDefault(rem, 0) + 1;
      remToCount.put(rem, count);

      Integer minValue = remToMinValue.get(rem);
      if (minValue == null || minValue > num) {
        remToMinValue.put(rem, num);
      }

      if (count > maxFreq) {
        maxFreq = count;
        maxRem = rem;
      } else if (count == maxFreq && remToMinValue.get(maxRem) > remToMinValue.get(rem)) {
        maxRem = rem;
      }
    }
    return remToMinValue.get(maxRem);
  }

  public long totalCost(int[] costs, int k, int candidates) {
    new HashMap<Integer, Integer>() {
      {
        put(1, 1);
        put(2, 2);
      }
    };

    long res = 0L;
    int len = costs.length;
    PriorityQueue<Integer> leftQ = new PriorityQueue<>();
    PriorityQueue<Integer> rightQ = new PriorityQueue<>();
    int iLeft;
    for (iLeft = 0; iLeft < len && iLeft < candidates; iLeft++) {
      leftQ.add(costs[iLeft]);
    }
    int iRight;
    for (iRight = len - 1; iRight >= candidates && iRight >= len - candidates; iRight--) {
      rightQ.add(costs[iRight]);
    }

    for (int i = 0; i < k; i++) {
      Integer leftMin = leftQ.poll();
      Integer rightMin = rightQ.poll();

      if (leftMin == null || (rightMin != null && leftMin > rightMin)) {
        res += rightMin;
        if (iLeft <= iRight) {
          rightQ.add(costs[iRight--]);
        }
      } else {
        res += leftMin;
        if (iLeft <= iRight) {
          leftQ.add(costs[iLeft++]);
        }
      }
    }
    return res;
  }

  private int gcd(int a1, int a2) {
    while (a2 != 0) {
      int tmp = a2;
      a2 = a1 % a2;
      a1 = tmp;
    }
    return a1;
  }

  public int[] productQueries(int n, int[][] queries) {
    ArrayList<Integer> list = new ArrayList<>();
    final BigInteger MOD = BigInteger.valueOf(1000000007);
    list.add(0);
    int currPow = 1;
    while (n != 0) {
      if (n % 2 == 1) {
        list.add(currPow);
      }
      currPow++;
      n /= 2;
    }
    for (int i = 1; i < list.size(); i++) {
      list.set(i, list.get(i) + list.get(i - 1));
    }
    int m = queries.length;
    int[] res = new int[m];
    final BigInteger TWO = BigInteger.TWO;
    for (int i = 0; i < m; i++) {
      BigInteger power = BigInteger.valueOf(list.get(queries[i][1] + 1) - list.get(queries[i][0]));
      res[i] = TWO.modPow(power, MOD).intValue();
    }
    return res;
  }

  public int minimumSplits(int[] nums) {
    int res = 1;
    int x = nums[0];
    int n = nums.length;
    for (int i = 1; i < n; i++) {
      x = gcd(x, nums[i]);
      if (x == 1) {
        res++;
        x = nums[i];
      }
    }
    return res;
  }

  public int maxSum(int[][] grid) {
    int m = grid.length, n = grid[0].length;

    int max = 0;
    for (int i = 0; i < m - 2; i++) {
      for (int j = 0; j < n - 2; j++) {
        int sum =
            grid[i][j]
                + grid[i][j + 1]
                + grid[i][j + 2]
                + grid[i + 1][j + 1]
                + grid[i + 2][j]
                + grid[i + 2][j + 1]
                + grid[i + 2][j + 2];
        max = Math.max(sum, max);
      }
    }
    return max;
  }

  public int deleteString(String s) {
    int len = s.length();
    int[] dp = new int[len];
    dp[len - 1] = 1;
    for (int i = len - 2; i >= 0; i--) {
      int maxPrefixTmp = 0;
      for (int j = 1; j <= (len - i) / 2; j++) {
        if (!s.substring(i, i + j).equals(s.substring(i + j, i + 2 * j))) continue;
        maxPrefixTmp = Math.max(maxPrefixTmp, dp[i + j]);
      }
      dp[i] = maxPrefixTmp + 1;
    }
    return dp[0];
  }

  public int hardestWorker(int n, int[][] logs) {
    int longestDuration = logs[0][1], resId = logs[0][0];
    int m = logs.length;
    for (int i = 1; i < m; i++) {
      int duration = logs[i][1] - logs[i - 1][1];
      if (duration < longestDuration) continue;
      if (duration > longestDuration) {
        longestDuration = duration;
        resId = logs[i][0];
      } else if (logs[i][0] < resId) {
        resId = logs[i][0];
      }
    }
    return resId;
  }

  private class ARComparator implements Comparator<AnalyticsReport> {
    @Override
    public int compare(AnalyticsReport r1, AnalyticsReport r2) {
      Integer order1 = r1.getReportOrder();
      Integer order2 = r2.getReportOrder();
      if (order1 > 0) {
        if (order2 > 0) {
          return Integer.compare(order1, order2);
        }
        return -1;
      }
      if (order2 > 0) {
        return 1;
      }
      return r1.getReportName().compareTo(r2.getReportName());
    }
  }
}

@Data
class AnalyticsReport implements Serializable {
  private static final long serialVersionUID = 202302201800L;
  private Long id;
  private String reportSectionName;
  private String reportName; // {1} : A, b, D; {2} : X, t, n
  private String reportLink;
  private String reportDescription;
  private Integer reportSectionOrder; // 0
  private Integer reportOrder;
}

class TrieNode {
  private final TrieNode[] childNodes;
  private int value;

  public TrieNode() {
    this.value = 0;
    childNodes = new TrieNode[26];
  }

  public TrieNode get(char ch) {
    int idx = ch - 'a';
    if (this.childNodes[idx] == null) {
      this.childNodes[idx] = new TrieNode();
    }
    return this.childNodes[idx];
  }

  public int getValue() {
    return value;
  }

  public void inc() {
    this.value++;
  }
}

class Trie {
  private final TrieNode root = new TrieNode();

  public void add(String str) {
    TrieNode current = root;
    for (char ch : str.toCharArray()) {
      current = current.get(ch);
      current.inc();
    }
  }

  public int find(String str) {
    int count = 0;
    TrieNode current = root;
    for (char ch : str.toCharArray()) {
      current = current.get(ch);
      count += current.getValue();
    }
    return count;
  }
}
