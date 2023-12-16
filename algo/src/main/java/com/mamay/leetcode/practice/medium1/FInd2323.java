package com.mamay.leetcode.practice.medium1;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FInd2323 {

  private static final int MOD = 1000000007;
  private final HashSet<Character> vowelSet = new HashSet<>(Arrays.asList('a', 'o', 'e', 'i', 'u'));
  private boolean[] usedMaxScore;
  private boolean[] traversed;
  private int max = -1;
  private LinkedList<LinkedList<Integer>> graph;
  private int[] scores;
  private HashMap<Integer, Integer> sumToFrequency;
  private LinkedList<Integer> path = new LinkedList<>();
  private HashSet<String> wordSet;
  private HashMap<String, String> wordMap;
  private List<String> sentenceList;
  private int minLen, maxLen, sLen;
  private HashSet<String> wordDictSet;
  private HashSet<String> allValidRecipes;
  private HashSet<String> supSet;
  private HashMap<String, List<String>> recToIngList;
  private HashSet<String> uniqueSet;
  private HashSet<String> nonUniqueSet;

  public int minimumFlips(TreeNode root, boolean result) {
    if (isNode(root)) {
      return (root.val == 0 ^ result) ? 0 : 1;
    }

    if (root.val == 5) {
      if (root.left != null) return minimumFlips(root.left, !result);
      return minimumFlips(root.right, !result);
    }

    if (root.val == 2) {
      if (result) {
        return Math.min(minimumFlips(root.left, result), minimumFlips(root.right, result));
      }
      return minimumFlips(root.left, result) + minimumFlips(root.right, result);
    }

    if (root.val == 3) {
      if (result) {
        return minimumFlips(root.left, result) + minimumFlips(root.right, result);
      }
      return Math.min(minimumFlips(root.left, result), minimumFlips(root.right, result));
    }

    if (root.val == 4) {
      int leftTrue = minimumFlips(root.left, result);
      int rightTrue = minimumFlips(root.right, result);
      int leftFalse = minimumFlips(root.left, !result);
      int rightFalse = minimumFlips(root.right, !result);
      if (result) {
        return Math.min(leftTrue + rightFalse, leftFalse + rightTrue);
      }
      return Math.min(leftTrue + rightTrue, leftFalse + rightFalse);
    }

    return 0;
  }

  public int minNumber(int[] nums1, int[] nums2) {
    Arrays.sort(nums1);
    Arrays.sort(nums2);
    HashSet<Integer> set =
        Arrays.stream(nums2).boxed().collect(Collectors.toCollection(HashSet::new));
    for (int num : nums1) {
      if (set.contains(num)) return num;
    }
    return nums1[0] < nums2[0] ? (10 * nums1[0] + nums2[0]) : (10 * nums2[0] + nums1[0]);
  }

  public int maximumCostSubstring(String s, String chars, int[] vals) {
    final int LEN = 26;
    int[] arr = new int[LEN];
    for (int i = 0; i < LEN; i++) arr[i] = i + 1;
    char[] charsArr = chars.toCharArray();
    for (int i = 0; i < charsArr.length; i++) {
      char ch = charsArr[i];
      arr[ch - 'a'] = vals[i];
    }
    int max = 0, currentSum = 0;
    for (char ch : s.toCharArray()) {
      int value = arr[ch - 'a'];
      currentSum += value;
      if (currentSum < 0) {
        currentSum = 0;
      } else if (currentSum > max) {
        max = currentSum;
      }
    }
    return max;
  }

  private int minCycle = Integer.MAX_VALUE;

  public int findShortestCycle(int n, int[][] edges) {
    LinkedList<LinkedList<Integer>> graph = new LinkedList<>();
    for (int i = 0; i < n; i++) {
      graph.add(new LinkedList<>());
    }
    for (int[] edge : edges) {
      int v1 = edge[0], v2 = edge[1];
      graph.get(v1).add(v2);
      graph.get(v2).add(v1);
    }
    int[] used = new int[n];
    Arrays.fill(used, 0);
    for (int i = 0; i < n; i++) {
      if (used[i] != 0) continue;
      findShortestCycle(graph, -1, i, used, new HashMap<>());
    }

    return minCycle < Integer.MAX_VALUE ? minCycle : -1;
  }

  private void findShortestCycle(
      LinkedList<LinkedList<Integer>> graph,
      int vertParent,
      int vert1,
      int[] used,
      HashMap<Integer, Integer> currentStack) {
    used[vert1] = 1;
    currentStack.put(vert1, currentStack.size());
    for (int vert2 : graph.get(vert1)) {
      if (vertParent == vert2) continue;
      if (used[vert2] == 1) {
        minCycle = Math.min(minCycle, (currentStack.size() - currentStack.get(vert2)));
      } else {
        findShortestCycle(graph, vert1, vert2, used, currentStack);
      }
    }
    currentStack.remove(vert1);
    used[vert1] = 2;
  }

  public long largestEvenSum(int[] nums, int k) {
    Arrays.sort(nums);
    int len = nums.length;
    Predicate<Integer> evenPredicate = num -> num % 2 == 0;
    Map<Boolean, List<Integer>> minMap =
        Arrays.stream(nums, 0, len - k).boxed().collect(Collectors.partitioningBy(evenPredicate));
    Map<Boolean, List<Integer>> maxMap =
        Arrays.stream(nums, len - k, len).boxed().collect(Collectors.partitioningBy(evenPredicate));
    IntSummaryStatistics stat1 = getStatistics(minMap, true);
    IntSummaryStatistics stat2 = getStatistics(minMap, false);
    IntSummaryStatistics stat3 = getStatistics(maxMap, true);
    IntSummaryStatistics stat4 = getStatistics(maxMap, false);
    long sum = stat3.getSum() + stat4.getSum();
    if (sum % 2 == 0) return sum;
    boolean noMinEven = stat1.getCount() == 0, noMaxEven = stat3.getCount() == 0;
    if (noMinEven && noMaxEven || k == len) return -1;
    if (noMinEven) return sum + stat2.getMax() - stat3.getMin();
    if (noMaxEven) return sum + stat1.getMax() - stat4.getMin();
    return sum + Math.max(stat2.getMax() - stat3.getMin(), stat1.getMax() - stat4.getMin());
  }

  private static IntSummaryStatistics getStatistics(
      Map<Boolean, List<Integer>> minMap, boolean key) {
    return minMap.get(key).stream().mapToInt(i -> i).summaryStatistics();
  }

  public int maxFrequency(int[] nums, int k) {
    int len = nums.length;
    Map<Integer, Integer> map =
        Arrays.stream(nums)
            .boxed()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(x -> 1)));
    List<List<Integer>> list =
        map.keySet().stream()
            .map(key -> Arrays.asList(key, map.get(key)))
            .collect(Collectors.toList());
    Collections.sort(list, Comparator.comparingInt((subList) -> subList.get(0)));
    int result = 1;
    for (int i = 0; i < list.size(); i++) {
      int num = list.get(i).get(0);
      int freq = list.get(i).get(1);
      int kCopy = k;
      for (int j = i - 1; j >= 0 && kCopy > 0; j--) {
        int prevNum = num - list.get(j).get(0);
        int prevFreq = list.get(j).get(1);
        int freqDiff = Math.min(prevFreq, kCopy / prevNum);
        if (freqDiff == 0) break;
        freq += freqDiff;
        kCopy -= (freqDiff * prevNum);
      }
      result = Math.max(result, freq);
    }
    return result;
  }

  public List<List<String>> displayTable(List<List<String>> orders) {
    TreeMap<Integer, TreeMap<String, Integer>> superMap = new TreeMap<>();
    TreeSet<String> foodNames = new TreeSet<>();
    for (List<String> orderList : orders) {
      int tableId = Integer.parseInt(orderList.get(1));
      String foodName = orderList.get(2);
      TreeMap<String, Integer> foodToCustomerMap = superMap.getOrDefault(tableId, new TreeMap<>());
      int customerSize = foodToCustomerMap.getOrDefault(foodName, 0) + 1;
      foodToCustomerMap.put(foodName, customerSize);
      superMap.put(tableId, foodToCustomerMap);
      foodNames.add(foodName);
    }
    List<List<String>> resList = new LinkedList<>();
    int foodCount = foodNames.size();
    List<String> headerList = new ArrayList<>(1 + foodCount);
    headerList.add("Table");
    headerList.addAll(foodNames);
    resList.add(headerList);
    for (int tableId : superMap.keySet()) {
      TreeMap<String, Integer> foodToCustomerMap = superMap.get(tableId);
      List<String> rowList = new ArrayList<>(1 + foodCount);
      rowList.add("" + tableId);
      for (String foodName : foodNames) {
        int count = foodToCustomerMap.getOrDefault(foodName, 0);
        rowList.add("" + count);
      }
      resList.add(rowList);
    }
    return resList;
  }

  public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
    Arrays.sort(slots1, Comparator.comparingInt((int[] a) -> a[0]));
    Arrays.sort(slots2, Comparator.comparingInt((int[] a) -> a[0]));
    int idx1 = 0, idx2 = 0, len1 = slots1.length, len2 = slots2.length;
    while (idx1 < len1 && idx2 < len2) {
      int[] slot1 = slots1[idx1];
      int[] slot2 = slots2[idx2];
      int start = Math.max(slot1[0], slot2[1]), end = Math.min(slot1[1], slot2[1]);
      if (end - start >= duration) {
        return Arrays.asList(start, start + duration);
      }
      if (slot1[0] >= slot2[0]) idx2++;
      else idx1++;
    }
    return Collections.emptyList();
  }

  private static double calculateAmount(double amount) {
    if (amount < 1000) {
      return 0.8 * amount;
    }
    if (amount > 5000) {
      return 0.3 * amount;
    }
    if (amount > 1000) {
      return 0.5 * amount;
    }
    return amount;
  }

  private static double calculateAmount2(double amount) {
    TreeMap<Double, Double> treeMap = new TreeMap<>();
    treeMap.put(1000d, 0.8);
    treeMap.put(5000d, 0.5);
    treeMap.put((double) Integer.MAX_VALUE, 0.3);
    return treeMap.ceilingEntry(amount).getValue();
  }

  public int stoneGameVI(int[] aliceValues, int[] bobValues) {
    int len = aliceValues.length;
    List<List<Integer>> list =
        IntStream.range(0, len)
            .mapToObj(i -> Arrays.asList(aliceValues[i], bobValues[i]))
            .collect(Collectors.toList());
    Collections.sort(
        list, Comparator.comparingInt(subList -> -Math.abs(subList.get(0) - subList.get(1))));
    int sum =
        IntStream.range(0, len)
            .map(i -> i % 2 == 0 ? list.get(i).get(0) : -list.get(i).get(1))
            .sum();
    return Integer.compare(sum, 0);
  }

  public int leastInterval(char[] tasks, int n) {
    List<HashSet<Integer>> list = new LinkedList<>();
    int len = tasks.length;
    PriorityQueue<int[]> queue =
        IntStream.range(0, len)
            .mapToObj(i -> (tasks[i] - 'A'))
            .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(x -> 1)))
            .entrySet()
            .stream()
            .map(entry -> new int[] {entry.getKey(), entry.getValue()})
            .collect(
                Collectors.toCollection(
                    () ->
                        new PriorityQueue<>(
                            Comparator.comparingInt((int[] a) -> a[1]).reversed())));
    while (!queue.isEmpty()) {
      boolean isAdded = false;
      int[] next = queue.poll();
      for (HashSet<Integer> set : list) {
        if (!set.contains(next[0]) && set.size() <= n) {
          set.add(next[0]);
          isAdded = true;
        }
      }
      if (!isAdded) {
        HashSet<Integer> set = new HashSet<>();
        set.add(next[0]);
        list.add(set);
      }
      if (--next[1] > 0) {
        queue.add(next);
      }
    }
    int size = list.size() - 1;
    return size * (n + 1) + list.get(size).size();
  }

  public long makeSubKSumEqual(int[] arr, int k) {
    int len = arr.length;
    int groups = gcd(len, k), numInGroup = len / groups;
    long result = 0l;
    for (int i = 0; i < groups; i++) {
      List<Integer> list = new ArrayList<>(numInGroup);
      for (int j = i; j < len; j += groups) {
        list.add(arr[j]);
      }
      result += incDecCount(list);
    }
    return result;
  }

  private long incDecCount01(List<Integer> list) {
    Collections.sort(list);
    int size = list.size();
    int median = list.get(size / 2);
    long result = 0l;
    for (int value : list) {
      result += Math.abs(value - median);
    }
    if (size % 2 == 0) {
      long result2 = 0l;
      median = list.get(size / 2 - 1);
      for (int value : list) {
        result += Math.abs(value - median);
      }
      result = Math.min(result, result2);
    }
    return result;
  }

  private long incDecCount(List<Integer> list) {
    Collections.sort(list);
    int midIdx = list.size() / 2;
    long result = incDecCount(list, list.get(midIdx));
    return (midIdx % 2 == 0) ? Math.min(result, incDecCount(list, list.get(midIdx - 1))) : result;
  }

  private long incDecCount(List<Integer> list, final int median) {
    return list.stream().mapToLong(value -> Math.abs(value - median)).sum();
  }

  private boolean isNode(TreeNode root) {
    return root.left == null && root.right == null;
  }

  public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
    PriorityQueue<int[]> q =
        new PriorityQueue<>((int[] a1, int[] a2) -> Integer.compare(a2[0], a1[0]));
    int n = nums1.length;
    for (int i = 0; i < n; i++) {
      nums1[i] = Math.abs(nums1[i] - nums2[i]);
    }

    Map<Integer, Long> map =
        Arrays.stream(nums1)
            .boxed()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

    for (Map.Entry<Integer, Long> entry : map.entrySet()) {
      int[] arr = new int[2];
      arr[0] = entry.getKey();
      arr[1] = entry.getValue().intValue();
      q.add(arr);
    }

    int k = k1 + k2;
    while (!q.isEmpty() && k > 0) {
      int[] max1 = q.poll();
      if (q.isEmpty()) {
        int num1 = max1[0];
        int c1 = max1[1];
        int diff = k / c1;
        if (diff >= num1) break; // exit as below zero
        num1 -= diff;
        int c2 = k % c1;
        if (c2 != 0) {
          int num2 = num1 - 1;
          c1 -= c2;
          if (num2 != 0) {
            q.add(new int[] {num2, c2});
          }
        }
        q.add(new int[] {num1, c1});
        k = 0;
      } else {
        int[] max2 = q.peek();
        int num1 = max1[0];
        int c1 = max1[1];
        int diff = Math.min(k / c1, max1[0] - max2[0]);
        num1 -= diff;
        k -= c1 * diff;
        if (num1 == max2[0]) {
          max2 = q.poll();
          max2[1] += c1;
          q.add(max2);
          continue;
        }
        int c2 = k % c1;
        if (c2 != 0) {
          int num2 = num1 - 1;
          c1 -= c2;
          if (num2 != max2[0]) {
            q.add(new int[] {num2, c2});
          } else {
            max2 = q.poll();
            max2[1] += c2;
            q.add(max2);
          }
        }
        q.add(new int[] {num1, c1});
        k = 0;
      }
    }

    long sum = 0L;
    while (!q.isEmpty()) {
      int[] nextElem = q.poll();
      long elem = nextElem[0];
      sum += elem * elem * nextElem[1];
    }
    return sum;
  }

  public boolean makePalindrome(String s) {
    int diffCount = 0;
    int len = s.length();
    for (int i = 0; i < len / 2; i++) {
      if (s.charAt(i) != s.charAt(len - 1 - i)) {
        if (++diffCount > 2) return false;
      }
    }
    return true;
  }

  public int minimumFlipsNewJuly(TreeNode root, boolean result) {
    int[] res = solve(root);
    return result ? res[0] : res[1];
  }

  private int[] solve(TreeNode root) {
    if (isNode(root)) {
      int forTrue = root.val == 1 ? 0 : 1;
      return new int[] {forTrue, 1 - forTrue};
    }

    if (root.val == 5) {
      int[] childRes = root.left != null ? solve(root.left) : solve(root.right);
      return new int[] {childRes[1], childRes[0]};
    }

    int[] leftRes = solve(root.left);
    int[] rightRes = solve(root.right);

    if (root.val == 2) {
      return new int[] {Math.min(leftRes[0], rightRes[0]), leftRes[1] + rightRes[1]};
    }

    if (root.val == 3) {
      return new int[] {leftRes[0] + rightRes[0], Math.min(leftRes[1], rightRes[1])};
    }

    if (root.val == 4) {
      int forTrue = Math.min(leftRes[0] + rightRes[1], leftRes[1] + rightRes[0]);
      int forFalse = Math.min(leftRes[0] + rightRes[0], leftRes[1] + rightRes[1]);
      return new int[] {forTrue, forFalse};
    }

    throw new UnsupportedOperationException("Ex");
  }

  private void main() {
    Map<Object1, Object2> sourceMap = new HashMap<>();
    // populate source map
    Map<String, List<Object2>> targetMap =
        sourceMap.entrySet().stream()
            .collect(
                Collectors.groupingBy(
                    entry -> entry.getKey().getValue(),
                    Collectors.mapping(Map.Entry::getValue, Collectors.toList())));

    Map<String, List<Object2>> targetMap2 =
        sourceMap.keySet().stream()
            .collect(
                Collectors.groupingBy(
                    Object1::getValue, Collectors.mapping(sourceMap::get, Collectors.toList())));
  }

  public int peopleAwareOfSecret(int n, int delay, int forget) {
    LinkedList<int[]> list = new LinkedList<>();
    list.add(new int[] {1, 1});
    int day = 1;
    while (++day <= n) {
      while (!list.isEmpty() && list.peekFirst()[0] + forget <= day) {
        list.pollFirst();
      }

      if (list.isEmpty()) return 0;

      int count = 0;
      for (int[] a : list) {
        if (a[0] + delay > day) break;
        count = (count + a[1]) % MOD;
      }

      if (count != 0) {
        list.add(new int[] {day, count});
      }
    }
    return (int) (list.stream().mapToLong(x -> x[1]).sum() % MOD);
  }

  public int minPathCost(int[][] grid, int[][] moveCost) {
    int m = grid.length;
    int n = grid[0].length;
    int[][] dp = new int[m][n];

    for (int i = 0; i < n; i++) {
      dp[0][i] = grid[0][i];
    }

    for (int i = 1; i < m; i++) {
      for (int j = 0; j < n; j++) {
        int min = Integer.MAX_VALUE;
        for (int k = 0; k < n; k++) {
          min = Math.min(min, dp[i - 1][k] + moveCost[grid[i - 1][k]][j]);
        }
        dp[i][j] = min + grid[i][j];
      }
    }
    return Arrays.stream(dp[m - 1]).min().getAsInt();
  }

  public long distinctNames(String[] ideas) {
    Map<Character, Long> firstToCount = new HashMap<>();
    // Map<Character, List<String>> firstToCount = new HashMap<>();
    int[][] adjMatrix = new int[26][26];
    Map<String, List<Character>> suffixToPrefixes = new HashMap<>();
    for (String idea : ideas) {
      char ch = idea.charAt(0);
      String postfix = idea.substring(1);
      long val = firstToCount.getOrDefault(ch, 0L) + 1;
      firstToCount.put(ch, val);

      List<Character> prefixSet = suffixToPrefixes.get(postfix);
      if (prefixSet == null) {
        prefixSet = new ArrayList<>();
      }
      prefixSet.add(ch);
      suffixToPrefixes.put(postfix, prefixSet);
    }

    for (List<Character> adjList : suffixToPrefixes.values()) {
      int sz = adjList.size();
      for (int i = 0; i < sz - 1; i++) {
        char chI = adjList.get(i);
        for (int j = i + 1; j < sz; j++) {
          char chJ = adjList.get(j);
          adjMatrix[chI - 'a'][chJ - 'a']++;
          adjMatrix[chJ - 'a'][chI - 'a']++;
        }
      }
    }

    ArrayList<Character> list = new ArrayList<>(firstToCount.keySet());
    int sz = list.size();
    long count = 0L;
    List<String> resList = new ArrayList<>();
    for (int i = 0; i < sz - 1; i++) {
      char chI = list.get(i);
      for (int j = i + 1; j < sz; j++) {
        char chJ = list.get(j);
        int misses = adjMatrix[chI - 'a'][chJ - 'a'];
        count += (firstToCount.get(chI) - misses) * (firstToCount.get(chJ) - misses);
      }
    }
    return 2 * count;
  }

  public List<String> distinctNamesHardCoded(String[] ideas) {
    HashSet<String> set = new HashSet<>(Arrays.asList(ideas));

    int n = ideas.length;
    long count = 0L;
    List<String> resList = new ArrayList<>();

    for (int i = 0; i < n - 1; i++) {
      char firstI = ideas[i].charAt(0);
      String suffixI = ideas[i].substring(1);

      for (int j = i + 1; j < n; j++) {
        char firstJ = ideas[j].charAt(0);
        String suffixJ = ideas[j].substring(1);

        if (set.contains(firstI + suffixJ) || set.contains(firstJ + suffixI)) continue;
        // count++;

        resList.add(firstI + suffixJ + " " + firstJ + suffixI);
        resList.add(firstJ + suffixI + " " + firstI + suffixJ);
      }
    }
    // return 2 * count;
    return resList;
  }

  public int[] arrayChange(int[] nums, int[][] operations) {
    Map<Integer, Integer> map = new HashMap<>();
    int n = nums.length;
    for (int i = 0; i < n; i++) {
      map.put(nums[i], i);
    }

    for (int[] op : operations) {
      int value = map.remove(op[0]);
      map.put(op[1], value);
    }

    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
      nums[entry.getValue()] = entry.getKey();
    }
    return nums;
  }

  public double calculateTax(int[][] brackets, int income) {
    if (income == 0) return 0;
    int n = brackets.length;
    double t = 0L;
    for (int i = 0; i < n; i++) {
      int[] br = brackets[i];
      int amount = Math.min(income, br[0]);
      if (i > 0) {
        amount -= brackets[i - 1][0];
      }
      t += amount * br[1];
      if (income <= br[0]) break;
    }
    return t / 100D;
  }

  public int maximumScore(int[] scores, int[][] edges) {
    graph = new LinkedList<>();
    int n = scores.length;
    this.scores = scores;
    for (int i = 0; i < n; i++) {
      graph.add(new LinkedList<>());
    }

    for (int[] edge : edges) {
      graph.get(edge[0]).add(edge[1]);
      graph.get(edge[1]).add(edge[0]);
    }
    usedMaxScore = new boolean[n];
    traversed = new boolean[n];

    Arrays.fill(usedMaxScore, false);
    for (int i = 0; i < n; i++) {
      if (!traversed[i]) dfsMaxScore(i, new LinkedList<>());
    }
    return compatibilityMaxScore;
  }

  private void dfsMaxScore(int vert, LinkedList<Integer> currentPath) {
    usedMaxScore[vert] = true;
    currentPath.addLast(vert);

    if (!traversed[vert]) traversed[vert] = true;

    int sz = currentPath.size();
    if (sz >= 4) {
      int sum = 0;
      for (int i = 0; i < 4; i++) {
        sum += currentPath.get(sz - 1 - i);
      }
      if (sum > compatibilityMaxScore) {
        compatibilityMaxScore = sum;
        // System.out.println(max + " : " + currentPath);
      }
    } else {
      for (int w : graph.get(vert)) {
        if (usedMaxScore[w]) continue;
        dfsMaxScore(w, currentPath);
      }
    }
    currentPath.pollLast();
    usedMaxScore[vert] = false;
  }

  public int waysToMakeFair(int[] nums) {
    int n = nums.length;
    int[] left = new int[n + 1];
    int[] right = new int[n + 1];
    left[0] = 0;
    right[n] = 0;
    for (int i = 1; i <= n; i++) {
      left[i] = left[i - 1] + (i % 2 == 1 ? nums[i] : -nums[i]);
      right[n - i] = right[n + 1 - i] + ((n - i) % 2 == 1 ? nums[n - i] : -nums[n - i]);
    }

    int count = 0;
    for (int i = 1; i <= n; i++) {
      if (left[i] + right[i - 1] == 0) count++;
    }
    return count;
  }

  public int[] smallestTrimmedNumbers(String[] nums, int[][] queries) {
    int m = nums.length;
    int n = nums[0].length();
    StringNode[][] dp = new StringNode[n][m];

    Comparator<StringNode> comparator =
        Comparator.comparing((StringNode s) -> s.str.substring(s.trimmedPrefix))
            .thenComparingInt(s -> s.initIndexInArray);
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        dp[i][j] = new StringNode(nums[j], i, j);
      }
      Arrays.sort(dp[i], comparator);
    }

    int qSize = queries.length;
    int[] res = new int[qSize];
    for (int i = 0; i < qSize; i++) {
      int trimmedPrefix = n - queries[i][1];
      res[i] = dp[trimmedPrefix][queries[i][0] - 1].initIndexInArray();
    }
    return res;
  }

  public String findSmallestRegion(List<List<String>> regions, String region1, String region2) {
    HashMap<String, String> map = new HashMap<>();
    for (List<String> reg : regions) {
      for (int i = 1; i < reg.size(); i++) {
        map.put(reg.get(i), reg.get(0));
      }
    }
    HashSet<String> set = new HashSet<>();
    while (region1 != null) {
      set.add(region1);
      region1 = map.get(region1);
    }
    while (region2 != null) {
      if (set.contains(region2)) return region2;
      region2 = map.get(region2);
    }
    return null;
  }

  public int[] findFrequentTreeSum(TreeNode root) {
    sumToFrequency = new HashMap<>();
    traverse(root);
    int maxFrequency = 0;
    List<Integer> maxValues = new LinkedList<>();
    for (Map.Entry<Integer, Integer> entry : sumToFrequency.entrySet()) {
      int key = entry.getKey();
      int val = entry.getValue();
      if (val > maxFrequency) {
        maxFrequency = val;
        maxValues = new LinkedList<>();
        maxValues.add(key);
      } else if (val == maxFrequency) {
        maxValues.add(key);
      }
    }
    int size = maxValues.size();
    int[] res = new int[size];
    for (int i = 0; i < size; i++) {
      res[i] = maxValues.get(i);
    }
    return res;
  }

  private int traverse(TreeNode root) {
    if (root == null) return 0;
    int sum = traverse(root.left) + traverse(root.right) + root.val;
    int val = sumToFrequency.getOrDefault(sum, 0) + 1;
    sumToFrequency.put(sum, val);
    return sum;
  }

  public int[] numberOfPairs(int[] nums) {
    int n = nums.length;
    int[] res = new int[2];
    HashSet<Integer> set = new HashSet<>();
    for (int num : nums) {
      if (set.contains(num)) {
        res[0]++;
        set.remove(num);
      } else {
        set.add(num);
      }
    }
    res[1] = n - 2 * res[0];
    return res;
  }

  public int maximumSum(int[] nums) {
    HashMap<Integer, PriorityQueue<Integer>> map = new HashMap<>();
    for (int num : nums) {
      int d = digitSum(num);
      PriorityQueue<Integer> q = map.getOrDefault(d, new PriorityQueue<>());
      q.add(num);
      if (q.size() > 2) {
        q.poll();
      }
      map.put(d, q);
    }
    int max = -1;
    for (PriorityQueue<Integer> q : map.values()) {
      if (q.size() < 2) continue;
      max = Math.max(max, q.poll() + q.poll());
    }
    return max;
  }

  private int digitSum(int num) {
    int sum = 0;
    while (num != 0) {
      sum += num % 10;
      num /= 10;
    }
    return sum;
  }

  public String replaceWords(List<String> dictionary, String sentence) {
    Trie trie = new Trie();
    for (String s : dictionary) {
      trie.add(s);
    }

    String[] initArray = sentence.split(" ");
    List<String> resList = new ArrayList<>(initArray.length);
    for (String s : initArray) {
      String prefix = trie.findPrefix(s);
      if (prefix == null) {
        resList.add(s);
      } else {
        resList.add(prefix);
      }
    }
    return String.join(" ", resList);
  }

  public int openLock(String[] deadends, String target) {
    final String INITIAL = "0000";
    if (INITIAL.equals(target)) return 0;
    HashSet<String> set = Arrays.stream(deadends).collect(Collectors.toCollection(HashSet::new));
    if (set.contains(INITIAL)) return -1;
    HashMapAdapter adapter = new HashMapAdapter();
    adapter.put("0000", 0);
    while (!adapter.isEmpty()) {
      String prev = adapter.pollFirst();
      int count = adapter.get(prev);
      for (String next : getNeighbors(prev)) {
        if (set.contains(next) || adapter.containsKey(next)) continue;
        if (next.equals(target)) return count + 1;
        adapter.put(next, count + 1);
      }
    }
    return -1;
  }

  private String[] getNeighbors(String prev) {
    final String[] neighbors = new String[8];
    for (int i = 0; i < 4; i++) {
      char ch = prev.charAt(i);
      char nextCh = '9';
      if (ch > '0') {
        nextCh = (char) (ch - 1);
      }
      neighbors[2 * i] = prev.substring(0, i) + nextCh + prev.substring(i + 1);
      nextCh = '0';
      if (ch < '9') {
        nextCh = (char) (ch + 1);
      }
      neighbors[2 * i + 1] = prev.substring(0, i) + nextCh + prev.substring(i + 1);
    }
    return neighbors;
  }

  public void setZeroes(int[][] matrix) {
    int m = matrix.length, n = matrix[0].length;
    boolean[] rowsZero = new boolean[m];
    boolean[] colsZero = new boolean[n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (matrix[i][j] == 0) {
          rowsZero[i] = true;
          colsZero[j] = true;
        }
      }
    }
    for (int i = 0; i < m; i++) {
      if (rowsZero[i]) {
        for (int j = 0; j < n; j++) {
          matrix[i][j] = 0;
        }
      }
    }
    for (int j = 0; j < n; j++) {
      if (colsZero[j]) {
        for (int i = 0; i < m; i++) {
          matrix[i][j] = 0;
        }
      }
    }
  }

  public int minOperations(int[] nums, int[] numsDivide) {
    Arrays.sort(nums);
    int gcd = numsDivide[0];
    for (int i = 1; i < numsDivide.length; i++) {
      gcd = gcd(gcd, numsDivide[i]);
    }
    for (int i = 0; i < nums.length; i++) {
      int num = nums[i];
      if (gcd % num != 0) continue;
      return i;
    }

    return -1;
  }

  public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
    HashMap<String, TreeMap<Integer, String>> userToWebsites = new HashMap<>();
    int n = username.length;
    for (int i = 0; i < n; i++) {
      String user = username[i];
      int time = timestamp[i];
      String webSite = website[i];
      TreeMap<Integer, String> timeToVisit = userToWebsites.getOrDefault(user, new TreeMap<>());
      timeToVisit.put(time, webSite);
      userToWebsites.put(user, timeToVisit);
    }

    final List<HashSet<StringTriplet>> tripletSetList = new LinkedList<>();
    for (String user : userToWebsites.keySet()) {
      ArrayList<String> timeToVisit = new ArrayList<>(userToWebsites.get(user).values());
      int len = timeToVisit.size();
      HashSet<StringTriplet> set = new HashSet<>();
      for (int i = 0; i < len - 2; i++) {
        for (int i2 = i + 1; i2 < len - 1; i2++) {
          for (int i3 = i2 + 1; i3 < len; i3++) {
            StringTriplet s =
                new StringTriplet(timeToVisit.get(i), timeToVisit.get(i2), timeToVisit.get(i3));
            set.add(s);
          }
        }
      }
      tripletSetList.add(set);
    }
    Map<StringTriplet, Long> tripletToFreq =
        tripletSetList.stream()
            .flatMap(Collection::stream)
            .collect(Collectors.groupingBy(t -> t, Collectors.counting()));
    Comparator<StringTriplet> comparator =
        Comparator.comparing((StringTriplet s) -> -tripletToFreq.get(s))
            .thenComparing((StringTriplet s) -> s.s1)
            .thenComparing((StringTriplet s) -> s.s2)
            .thenComparing((StringTriplet s) -> s.s3)
            .reversed();
    StringTriplet maxTriplet = Collections.max(tripletToFreq.keySet(), comparator);
    final List<String> resList = new ArrayList<>(3);
    resList.add(maxTriplet.s1);
    resList.add(maxTriplet.s2);
    resList.add(maxTriplet.s3);
    return resList;
  }

  public int[] minDifference(int[] nums, int[][] queries) {
    final int MAX_GAP = 1000000;
    int n = nums.length;
    IntSummaryStatistics statistics = Arrays.stream(nums).summaryStatistics();
    int minValue = statistics.getMin();
    int arraySize = statistics.getMax() - minValue + 1;

    int[][] dp = new int[n + 1][arraySize];
    Arrays.fill(dp[0], 0);
    for (int i = 1; i <= n; i++) {
      System.arraycopy(dp[i - 1], 0, dp[i], 0, arraySize);
      dp[i][nums[i - 1] - minValue]++;
    }
    int m = queries.length;
    int[] res = new int[m];
    for (int i = 0; i < m; i++) {
      int left = queries[i][0], right = queries[i][1];
      int j = 0;
      while (j < arraySize && dp[right + 1][j] == dp[left][j]) j++;
      int prevIdx = j, minGap = MAX_GAP;
      while (++j < arraySize) {
        if (dp[right + 1][j] != dp[left][j]) {
          minGap = Math.min(minGap, j - prevIdx);
          prevIdx = j;
        }
      }
      res[i] = minGap == MAX_GAP ? -1 : minGap;
    }
    return res;
  }

  public int wordCount(String[] startWords, String[] targetWords) {
    int m = startWords.length, n = targetWords.length;
    boolean[] res = new boolean[n];
    HashSet<ArrayWrapper> set =
        Arrays.stream(startWords)
            .map(s -> new ArrayWrapper(parse(s)))
            .collect(Collectors.toCollection(HashSet::new));

    for (int k = 0; k < n; k++) {
      int[] sArray = parse(targetWords[k]);
      for (int j = 0; j < 26; j++) {
        if (sArray[j] == 1) {
          sArray[j]--;
          ArrayWrapper w = new ArrayWrapper(sArray);
          if (set.contains(w)) {
            res[k] = true;
            break;
          }
          sArray[j]++;
        }
      }
    }
    return (int) IntStream.range(0, n).filter(j -> res[j]).count();
  }

  private int[] parse(String s) {
    int[] res = new int[26];
    for (char ch : s.toCharArray()) {
      res[ch - 'a']++;
    }
    return res;
  }

  public int minMutation(String start, String end, String[] bank) {
    int len = bank.length;
    HashMap<String, Integer> map = new HashMap<>();
    for (int i = 0; i < len; i++) {
      map.put(bank[i], i);
    }

    if (start.equals(end)) return 0;
    if (!map.containsKey(end)) return -1;

    ArrayDeque<String> deque = new ArrayDeque<>();
    boolean[] used = new boolean[len];
    deque.add(start);
    if (map.containsKey(start)) {
      used[map.get(start)] = true;
    }

    final char[] chars = {'A', 'C', 'G', 'T'};
    int iteration = 1;
    while (!deque.isEmpty()) {
      int sz = deque.size();
      for (int i = 0; i < sz; i++) {
        String prev = deque.pollFirst();
        for (int j = 0; j < prev.length(); j++) {
          char prevCh = prev.charAt(j);
          for (char nextCh : chars) {
            if (nextCh == prevCh) continue;
            String next = prev.substring(0, j) + nextCh + prev.substring(j + 1);
            Integer nextIdx = map.get(next);
            if (nextIdx == null || used[nextIdx]) continue;
            if (end.equals(next)) return iteration;
            used[nextIdx] = true;
            deque.addLast(next);
          }
        }
      }
      iteration++;
    }
    return -1;
  }

  public String bestHand(int[] ranks, char[] suits) {
    if (isFlush(suits)) return "Flush";
    if (isThreeOfAKind(ranks)) return "Three of a Kind";
    if (isPair(ranks)) return "Pair";
    return "High Card";
  }

  private boolean isPair(int[] ranks) {
    HashSet<Integer> set =
        Arrays.stream(ranks).boxed().collect(Collectors.toCollection(HashSet::new));
    return set.size() < 5;
  }

  private boolean isFlush(char[] suits) {
    return suits[0] == suits[1]
        && suits[2] == suits[3]
        && suits[0] == suits[4]
        && suits[0] == suits[2];
  }

  private boolean isThreeOfAKind(int[] ranks) {
    Map<Integer, Long> map =
        Arrays.stream(ranks)
            .boxed()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

    return map.values().stream().anyMatch(x -> x >= 3);
  }

  public long zeroFilledSubarray(int[] nums) {
    long zeroCount = 0;
    long res = 0;
    for (int num : nums) {
      if (num == 0) {
        zeroCount++;
        continue;
      }

      if (zeroCount != 0) {
        res += zeroCount * (zeroCount + 1) / 2;
        zeroCount = 0;
      }
    }

    if (zeroCount != 0) {
      res += zeroCount * (zeroCount + 1) / 2;
    }

    return res;
  }

  public char repeatedCharacter(String s) {
    HashSet<Character> set = new HashSet<>();
    for (char ch : s.toCharArray()) {
      if (set.contains(ch)) return ch;
      set.add(ch);
    }
    return 'a';
  }

  public int equalPairs(int[][] grid) {
    int size = grid.length;
    int res = 0;
    for (int rowNum = 0; rowNum < size; rowNum++) {
      for (int colNum = 0; colNum < size; colNum++) {
        boolean isEqual = true;
        for (int j = 0; j < size; j++) {
          if (grid[rowNum][j] != grid[j][colNum]) {
            isEqual = false;
            break;
          }
        }
        if (isEqual) {
          res++;
        }
      }
    }
    return res;
  }

  public int shortestSequence(int[] rolls, int k) {
    int iteration = 1, i = 0, n = rolls.length;
    while (i < n) {
      HashSet<Integer> set = new HashSet<>();
      while (i < n) {
        set.add(rolls[i++]);
        if (set.size() == k) {
          iteration++;
          break;
        }
      }
    }
    return iteration;
  }

  public int countQuadruples(String firstString, String secondString) {
    int n = secondString.length();
    int maxDiff = Integer.MIN_VALUE, maxCount = 0;
    for (int i = n - 1; i >= 0; i--) {
      char secondCh = secondString.charAt(i);
      Integer firstIdx = firstString.indexOf(secondCh);
      if (firstIdx == -1) continue;
      int curretDiff = i - firstIdx;
      if (curretDiff > maxDiff) {
        maxDiff = curretDiff;
        maxCount = 1;
      } else if (curretDiff == maxDiff) {
        maxCount++;
      }
    }
    return maxCount;
  }

  public String rankTeams(String[] votes) {
    final int n = votes[0].length();
    HashMap<Character, int[]> map = new HashMap<>();
    for (String voteStr : votes) {
      for (int i = 0; i < n; i++) {
        char voteCh = voteStr.charAt(i);
        int[] voteValue = map.getOrDefault(voteCh, new int[n]);
        voteValue[i]++;
        map.put(voteCh, voteValue);
      }
    }
    List<Map.Entry<Character, int[]>> entrySet = new ArrayList<>(map.entrySet());
    entrySet.sort(
        (e1, e2) -> {
          for (int i = 0; i < n; i++) {
            int comp = Integer.compare(e2.getValue()[i], e1.getValue()[i]);
            if (comp != 0) return comp;
          }
          return Character.compare(e1.getKey(), e2.getKey());
        });
    StringBuilder builder = new StringBuilder();
    for (Map.Entry<Character, int[]> entry : entrySet) {
      builder.append(entry.getKey());
    }
    return builder.toString();
  }

  /** count => colors_list color => already_answered_count */
  public int numRabbits(int[] answers) {
    int nextColor = 1;
    HashMap<Integer, Integer> colorToAnswered = new HashMap<>();
    HashMap<Integer, LinkedList<Integer>> countToColors = new HashMap<>();

    for (int answer : answers) {
      answer++;
      LinkedList<Integer> colorList = countToColors.get(answer);
      if (colorList == null) {
        colorList = new LinkedList<>();
        colorList.add(nextColor);
        countToColors.put(answer, colorList);

        colorToAnswered.put(nextColor, 1);
        nextColor++;
      } else {
        int lastColor = colorList.getLast();
        int alreadyAnsweredForLastColor = colorToAnswered.get(lastColor);
        if (alreadyAnsweredForLastColor < answer) {
          colorToAnswered.put(lastColor, alreadyAnsweredForLastColor + 1);
        } else {
          colorList.add(nextColor);
          countToColors.put(answer, colorList);

          colorToAnswered.put(nextColor, 1);
          nextColor++;
        }
      }
    }
    int res = 0;
    for (Map.Entry<Integer, LinkedList<Integer>> e : countToColors.entrySet()) {
      res += e.getKey() * e.getValue().size();
    }
    return res;
  }

  public int findDistance(TreeNode root, int p, int q) {
    if (p == q) return 0;
    dfs(root, p, new LinkedList<>());
    LinkedList<Integer> path1 = new LinkedList<>(path);
    path = new LinkedList<>();
    dfs(root, q, new LinkedList<>());
    LinkedList<Integer> path2 = new LinkedList<>(path);
    HashMap<Integer, Integer> map1 = new HashMap<>(), map2 = new HashMap<>();

    int n1 = path1.size(), n2 = path2.size();
    for (int i = n1 - 1; i >= 0; i--) {
      map1.put(path1.get(i), n1 - 1 - i);
    }

    for (int i = n2 - 1; i >= 0; i--) {
      int nodeVal = path2.get(i);
      if (map1.containsKey(nodeVal)) {
        return map1.get(nodeVal) + n2 - 1 - i;
      }
    }
    return 0;
  }

  private void dfs(TreeNode root, int p, LinkedList<Integer> currentPath) {
    currentPath.addLast(root.val);
    if (root.val == p) {
      path = new LinkedList<>(currentPath);
      currentPath.pollLast();
      return;
    }
    if (root.left != null) {
      dfs(root.left, p, currentPath);
    }

    if (root.right != null) {
      dfs(root.right, p, currentPath);
    }

    currentPath.pollLast();
  }

  public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
    HashMap<Integer, Integer> childToParent = new HashMap<>();
    HashMap<Integer, LinkedList<Integer>> parentToChildren = new HashMap<>();
    int n = pid.size();
    for (int i = 0; i < n; i++) {
      int child = pid.get(i);
      int parent = ppid.get(i);
      childToParent.put(child, parent);
      LinkedList<Integer> children = parentToChildren.getOrDefault(parent, new LinkedList<>());
      children.add(child);
      parentToChildren.put(parent, children);
    }

    HashSet<Integer> res = new HashSet<>();
    LinkedList<Integer> deque = new LinkedList<>();
    deque.add(kill);
    while (!deque.isEmpty()) {
      Integer nextChild = deque.pollFirst();
      res.add(nextChild);
      if (parentToChildren.containsKey(nextChild)) {
        deque.addAll(parentToChildren.get(nextChild));
      }
    }
    return new LinkedList<>(res);
  }

  public int beautySum(String s) {
    int len = s.length();
    int[][] prefixSum = new int[len + 1][26];
    Arrays.fill(prefixSum[0], 0);
    for (int i = 0; i < len; i++) {
      char ch = s.charAt(i);
      System.arraycopy(prefixSum[i], 0, prefixSum[i + 1], 0, 26);
      prefixSum[i][ch - 'a']++;
    }

    int res = 0;
    for (int i = 0; i < len; i++) {
      for (int j = i + 1; j <= len; j++) {
        int maxDiff = 0, minDiff = 501;
        for (int k = 0; k < 26; k++) {
          int diff = prefixSum[j][k] - prefixSum[i][k];
          maxDiff = Math.max(diff, maxDiff);
          minDiff = Math.max(diff, minDiff);
        }
        res += maxDiff - minDiff;
      }
    }
    return res;
  }

  public String[] spellchecker(String[] wordlist, String[] queries) {
    wordSet = Arrays.stream(wordlist).collect(Collectors.toCollection(HashSet::new));
    wordMap = new HashMap<>();

    for (String str : wordlist) {
      String lowerCased = str.toLowerCase();
      if (!wordMap.containsKey(lowerCased)) {
        wordMap.put(lowerCased, str);
      }
      lowerCased = update(lowerCased);
      if (!wordMap.containsKey(lowerCased)) {
        wordMap.put(lowerCased, str);
      }
    }
    return Arrays.stream(queries).map(this::solve).toArray(String[]::new);
  }

  private String solve(String source) {
    if (wordSet.contains(source)) return source;
    String lowerCased = source.toLowerCase();
    if (wordMap.containsKey(lowerCased)) {
      return wordMap.get(lowerCased);
    }
    lowerCased = update(lowerCased);
    if (wordMap.containsKey(lowerCased)) {
      return wordMap.get(lowerCased);
    }
    return "";
  }

  private String update(String str) {
    StringBuilder builder = new StringBuilder();
    int vowelCount = 0;
    for (char ch : str.toCharArray()) {
      if (vowelSet.contains(ch)) {
        vowelCount++;
      } else {
        if (vowelCount > 0) {
          builder.append(vowelCount);
        }
        builder.append(ch);
      }
    }
    return builder.toString();
  }

  public int numBusesToDestination(int[][] routes, int source, int target) {
    if (source == target) return 0;
    int routesCount = routes.length;
    HashSet<Integer>[] routeSet = new HashSet[routesCount];

    for (int i = 0; i < routesCount; i++) {
      routeSet[i] = Arrays.stream(routes[i]).boxed().collect(Collectors.toCollection(HashSet::new));
    }

    HashSet<Integer> sourceBuses = new HashSet<>();
    HashSet<Integer> targetBuses = new HashSet<>();

    HashMap<Integer, List<Integer>> busesMap = new HashMap<>();

    for (int i = 0; i < routesCount - 1; i++) {
      for (int j = i + 1; j < routesCount; j++) {
        if (intersects(routeSet[i], routeSet[j])) {
          List<Integer> iValue = busesMap.getOrDefault(i, new LinkedList<>());
          iValue.add(j);
          busesMap.put(i, iValue);

          List<Integer> jValue = busesMap.getOrDefault(j, new LinkedList<>());
          jValue.add(i);
          busesMap.put(j, jValue);
        }
      }
    }

    for (int i = 0; i < routesCount; i++) {
      if (routeSet[i].contains(source)) sourceBuses.add(i);
      if (routeSet[i].contains(target)) targetBuses.add(i);
    }

    int iteration = 1;
    HashSet<Integer> prevSet = new HashSet<>(sourceBuses);
    HashSet<Integer> visited = new HashSet<>();
    while (!prevSet.isEmpty()) {
      HashSet<Integer> nextSet = new HashSet<>();
      for (int prevBusNumber : prevSet) {
        if (targetBuses.contains(prevBusNumber)) return iteration;
        visited.add(prevBusNumber);
        List<Integer> nextBusNumbers = busesMap.get(prevBusNumber);
        if (nextBusNumbers == null || nextBusNumbers.isEmpty()) continue;
        for (int nextBusNumber : nextBusNumbers) {
          if (visited.contains(nextBusNumber)) continue;
          nextSet.add(nextBusNumber);
        }
      }
      prevSet = nextSet;
      iteration++;
    }
    return -1;
  }

  private boolean intersects(HashSet<Integer> set1, HashSet<Integer> set2) {
    for (int x : set1) {
      if (set2.contains(x)) return true;
    }
    return false;
  }

  public List<String> wordBreak(String s, List<String> wordDict) {
    sentenceList = new LinkedList<>();

    IntSummaryStatistics statistics =
        wordDict.stream().mapToInt(w -> w.length()).summaryStatistics();
    minLen = statistics.getMin();
    maxLen = statistics.getMax();
    sLen = s.length();

    wordDictSet = new HashSet<>(wordDict);
    backtracking(s, 0, new ArrayDeque<>());

    return sentenceList;
  }

  private void backtracking(String s, int startIndex, ArrayDeque<String> stack) {
    if (startIndex == sLen) {
      sentenceList.add(String.join(" ", stack));
      return;
    }

    for (int tempLen = minLen; tempLen <= maxLen; tempLen++) {
      int endIndex = startIndex + tempLen;
      if (endIndex > sLen) return;
      String nextChunk = s.substring(startIndex, endIndex);
      if (!wordDictSet.contains(nextChunk)) continue;
      stack.addLast(nextChunk);
      backtracking(s, endIndex, stack);
      stack.pollLast();
    }
  }

  public int maxEqualFreq(int[] nums) {
    HashMap<Integer, Integer> numToFreq = new HashMap<>();
    HashMap<Integer, Integer> freqToCount = new HashMap<>();
    int maxPrefix = 0;
    for (int i = 0; i < nums.length; i++) {
      int num = nums[i];
      int freq = numToFreq.getOrDefault(num, 0) + 1;
      numToFreq.put(num, freq);

      if (freq > 1) {
        Integer prevFreqValue = freqToCount.remove(freq - 1);
        if (--prevFreqValue > 0) {
          freqToCount.put(freq - 1, prevFreqValue);
        }
      }

      int nextFreqValue = freqToCount.getOrDefault(freq, 0) + 1;
      freqToCount.put(freq, nextFreqValue);

      if (isCheck(freqToCount) && i + 1 > maxPrefix) {
        maxPrefix = i + 1;
      }
    }
    return maxPrefix;
  }

  private boolean isCheck(HashMap<Integer, Integer> map) {
    int size = map.size();
    if (size > 2) return false;

    if (size == 1) {
      int key = 0;
      for (int k : map.keySet()) {
        key = k;
      }
      return key == 1 || map.get(key) == 1;
    }

    ArrayList<Integer> keyList = new ArrayList<>(map.keySet());
    int key1 = keyList.get(0), key2 = keyList.get(1);
    int val1 = map.get(key1), val2 = map.get(key2);

    return (key1 - 1 == key2 && val1 == 1)
        || (key2 - 1 == key1 && val2 == 1)
        || (key1 == 1 && key2 == 1)
        || (key1 == 1 && val1 == 1)
        || (key2 == 1 && val2 == 1);
  }

  public int maxOperations(int[] nums, int k) {
    HashMap<Integer, Integer> map = new HashMap<>();
    int count = 0;
    for (int num : nums) {
      int num1 = k - num;
      if (map.containsKey(num1)) {
        count++;
        int val = map.get(num1);
        if (val == 1) {
          map.remove(num1);
        } else {
          map.put(num1, val - 1);
        }
      } else {
        int val = map.get(num);
        map.put(num, val + 1);
      }
    }
    return count;
  }

  public int[] restoreArray(int[][] adjacentPairs) {
    int n = adjacentPairs.length;
    HashMap<Integer, LinkedList<Integer>> map = new HashMap<>();
    for (int[] pair : adjacentPairs) {
      int p1 = pair[0], p2 = pair[1];
      fillMap(map, p1, p2);
      fillMap(map, p2, p1);
    }

    int prev = findStart(map);
    int[] res = new int[n + 1];
    HashSet<Integer> used = new HashSet<>();
    for (int i = 0; i < n; i++) {
      res[i] = prev;
      used.add(prev);
      LinkedList<Integer> nextList = map.get(prev);
      if (nextList.size() == 1 || !used.contains(nextList.get(0))) {
        prev = nextList.get(0);
      } else {
        prev = nextList.get(1);
      }
    }
    res[n] = prev;
    return res;
  }

  private int findStart(HashMap<Integer, LinkedList<Integer>> map) {
    for (Map.Entry<Integer, LinkedList<Integer>> entry : map.entrySet()) {
      if (entry.getValue().size() == 1) return entry.getKey();
    }
    return 0;
  }

  private void fillMap(HashMap<Integer, LinkedList<Integer>> map, int p1, int p2) {
    LinkedList<Integer> values = map.getOrDefault(p1, new LinkedList<>());
    values.add(p2);
    map.put(p1, values);
  }

  public int numComponents(ListNode head, int[] nums) {
    HashSet<Integer> set =
        Arrays.stream(nums).boxed().collect(Collectors.toCollection(HashSet::new));
    ListNode curr = head;
    int comp = 0;
    boolean isConnected = false;
    while (curr != null) {
      if (set.contains(curr.val)) {
        if (!isConnected) {
          isConnected = true;
          comp++;
        }
      } else {
        if (isConnected) {
          isConnected = false;
        }
      }
      curr = curr.next;
    }
    return comp;
  }

  public int minimumOperations(int[] nums) {
    return Arrays.stream(nums)
        .filter(x -> x != 0)
        .boxed()
        .collect(Collectors.toCollection(HashSet::new))
        .size();
  }

  public int maximumGroups(int[] grades) {
    int size = grades.length;
    long low = 1, high = size;
    while (low != high) {
      long mid = (low + high + 1) / 2;
      if (mid * (mid + 1) / 2 > size) {
        high = mid - 1;
      } else {
        low = mid;
      }
    }
    return (int) low;
  }

  public long[] minimumCosts(int[] regular, int[] express, int expressCost) {
    int n = regular.length;
    long[][] dp = new long[n + 1][2];
    dp[0][0] = dp[0][1] = 0;
    for (int i = 1; i <= n; i++) {
      dp[i][0] = Math.min(dp[i - 1][0] + regular[i - 1], dp[i - 1][1] + express[i - 1]);
      dp[i][1] =
          Math.min(dp[i - 1][0] + regular[i - 1] + expressCost, dp[i - 1][1] + express[i - 1]);
    }

    long[] res = new long[n];
    for (int i = 0; i < n; i++) {
      res[i] = Math.min(dp[i + 1][0], dp[i + 1][1]);
    }
    return res;
  }

  public int lenLongestFibSubseq(int[] arr) {
    int n = arr.length;
    HashSet<Integer> set =
        Arrays.stream(arr).boxed().collect(Collectors.toCollection(HashSet::new));
    int max = 0;
    for (int i = 0; i < n - 2; i++) {
      for (int j = i + 1; j < n - 1; j++) {
        int prev1 = arr[i], prev2 = arr[j], count = 3;
        int next = prev1 + prev2;
        while (set.contains(next)) {
          max = Math.max(max, count++);
          prev1 = prev2;
          prev2 = next;
          next = prev1 + prev2;
        }
      }
    }
    return max;
  }

  public String addBoldTag(String s, String[] dict) {
    boolean[] bold = new boolean[s.length()];
    for (int i = 0, end = 0; i < s.length(); i++) {
      for (String word : dict) {
        if (s.startsWith(word, i)) {
          end = Math.max(end, i + word.length());
        }
      }
      bold[i] = end > i;
    }

    StringBuilder result = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      if (!bold[i]) {
        result.append(s.charAt(i));
        continue;
      }
      int j = i;
      while (j < s.length() && bold[j]) j++;
      result.append("<b>" + s.substring(i, j) + "</b>");
      i = j - 1;
    }

    return result.toString();
  }

  public List<String> findAllRecipes(
      String[] recipes, List<List<String>> ingredients, String[] supplies) {
    allValidRecipes = new HashSet<>();
    supSet = Arrays.stream(supplies).collect(Collectors.toCollection(HashSet::new));
    int n = recipes.length;
    recToIngList = new HashMap<>();
    for (int i = 0; i < n; i++) {
      recToIngList.put(recipes[i], ingredients.get(i));
    }

    for (String recipe : recToIngList.keySet()) {
      if (allValidRecipes.contains(recipe)) continue;
      checkReceipt(recipe);
    }
    return new LinkedList<>(allValidRecipes);
  }

  private boolean checkReceipt(String recipe) {
    for (String ingr : recToIngList.get(recipe)) {
      if (supSet.contains(ingr) || allValidRecipes.contains(ingr)) continue;
      if (!recToIngList.containsKey(ingr)) return false;
      if (!checkReceipt(ingr)) return false;
    }
    allValidRecipes.add(recipe);
    return true;
  }

  public List<String> alertNames(String[] keyName, String[] keyTime) {
    HashMap<String, List<Integer>> map = new HashMap<>();
    int n = keyName.length;
    for (int i = 0; i < n; i++) {
      List<Integer> timeList = map.getOrDefault(keyName[i], new LinkedList<>());
      timeList.add(convertToTime(keyTime[i]));
      map.put(keyName[i], timeList);
    }
    return map.entrySet().stream()
        .filter(entry -> isAlerted(entry.getValue()))
        .map(Map.Entry::getKey)
        .sorted()
        .collect(Collectors.toList());
  }

  private Integer convertToTime(String str) {
    return Integer.parseInt(str.substring(0, 2)) * 60 + Integer.parseInt(str.substring(3, 5));
  }

  private boolean isAlerted(List<Integer> values) {
    int size = values.size();
    Collections.sort(values);
    for (int i = 0; i < size - 2; i++) {
      if (values.get(i + 2) - values.get(i) <= 60) return true;
    }
    return false;
  }

  public int flipgame(int[] fronts, int[] backs) {
    HashSet<Integer> set = new HashSet<>();

    int n = fronts.length;
    for (int i = 0; i < n; i++) {
      if (fronts[i] == backs[i]) {
        set.add(fronts[i]);
      }
    }

    final int EMPTY_VAL = 2002;
    int min = EMPTY_VAL;

    for (int i = 0; i < n; i++) {
      if (!set.contains(fronts[i])) {
        min = Math.min(min, fronts[i]);
      }

      if (!set.contains(backs[i])) {
        min = Math.min(min, backs[i]);
      }
    }
    return min == EMPTY_VAL ? 0 : min;
  }

  public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
    int m = words.length, n = puzzles.length;
    List<Integer> list = new LinkedList<Integer>();
    HashSet<Character>[] wordSetArray = new HashSet[m];
    for (int i = 0; i < m; i++) {
      wordSetArray[i] = new HashSet<>();
      for (char ch : words[i].toCharArray()) {
        wordSetArray[i].add(ch);
      }
    }
    for (String puzzle : puzzles) {
      int count = 0;
      HashSet<Character> puzSet = new HashSet<>();
      for (char ch : puzzle.toCharArray()) puzSet.add(ch);

      for (HashSet<Character> wordSet : wordSetArray) {
        if (!wordSet.contains(puzzle.charAt(0))) continue;
        boolean flag = true;
        for (char ch : wordSet) {
          if (!puzSet.contains(ch)) {
            flag = false;
            break;
          }
        }
        if (flag) {
          count++;
        }
      }

      list.add(count);
    }
    return list;
  }

  public boolean isReflected(int[][] points) {
    HashMap<Integer, Set<Integer>> map = new HashMap<>();
    for (int[] p : points) {
      Set<Integer> list = map.getOrDefault(p[1], new HashSet<>()); // TODO why HashSet
      list.add(p[0]);
      map.put(p[1], list);
    }

    Double xDelim = null;
    for (Set<Integer> set : map.values()) {
      List<Integer> list = new LinkedList<>(set);
      Collections.sort(list);
      int sz = list.size();
      if (sz % 2 == 1) {
        int midVal = list.get(sz / 2);
        if (xDelim == null) {
          xDelim = (double) midVal;
        } else if (xDelim != midVal) {
          return false;
        }
      }
      for (int i = 0; i < sz / 2; i++) {
        double midVal = list.get(0) + list.get(sz - i - 1);
        midVal = midVal / 2;
        if (xDelim == null) {
          xDelim = midVal;
        } else if (xDelim != midVal) {
          return false;
        }
      }
    }
    return true;
  }

  public boolean differByOne(String[] dict) {
    HashSet<String> set = new HashSet<>();
    int n = dict.length, m = dict[0].length();
    for (String str : dict) {
      for (int i = 0; i < m; i++) {
        char prevCh = str.charAt(i);
        for (char ch = 'a'; ch <= 'z'; ch++) {
          if (ch == prevCh) continue;
          String t = str.substring(0, i) + ch + str.substring(i + 1);
          if (set.contains(t)) {
            return true;
          }
        }
      }
      set.add(str);
    }
    return false;
  }

  public int equalDigitFrequency(String s) {
    int len = s.length();
    uniqueSet = new HashSet<>();
    nonUniqueSet = new HashSet<>();
    for (int i = 0; i < len; i++) {
      for (int j = i + 1; j <= len; j++) {
        String t = s.substring(i, j);
        isSIngleFreq(t);
      }
    }
    return uniqueSet.size();
  }

  private void isSIngleFreq(String t) {
    if (uniqueSet.contains(t) || nonUniqueSet.contains(t)) return;
    HashMap<Character, Integer> map = new HashMap<>();
    for (char ch : t.toCharArray()) {
      int value = map.getOrDefault(ch, 0) + 1;
      map.put(ch, value);
    }
    HashSet<Integer> set = new HashSet<>(map.values());
    if (set.size() == 1) {
      uniqueSet.add(t);
    } else {
      nonUniqueSet.add(t);
    }
  }

  public int[][] largestLocal(int[][] grid) {
    int n = grid.length;
    int maxLocal[][] = new int[n - 2][n - 2];
    for (int i = 1; i < n - 1; i++) {
      for (int j = 1; j < n - 1; j++) {
        int max = 0;
        for (int i1 = -1; i1 <= 1; i1++) {
          for (int j1 = -1; j1 <= 1; j1++) {
            max = Math.max(max, grid[i + i1][j + j1]);
          }
        }
        maxLocal[i - 1][j - 1] = max;
      }
    }
    return maxLocal;
  }

  public int edgeScore(int[] edges) {
    int n = edges.length;
    long[] scores = new long[n];
    for (int i = 0; i < n; i++) {
      scores[edges[i]] += i;
    }

    int maxIdx = 0;
    for (int i = 1; i < n; i++) {
      if (scores[i] > scores[maxIdx]) {
        maxIdx = i;
      }
    }
    return maxIdx;
  }

  public String smallestNumber(String pattern) {
    int len = pattern.length();
    int[] arr = new int[len + 1];
    arr[0] = 1;
    for (int i = 0; i < len; i++) {
      char ch = pattern.charAt(i);
      int chVal = ch == 'D' ? arr[i] - 1 : arr[i] + 1;
      arr[i + 1] = chVal;
    }
    HashSet<Integer> visited = new HashSet<>();
    int diff = 1 - (int) Arrays.stream(arr).mapToLong(elem -> elem).min().getAsLong();
    arr[0] += diff;
    visited.add(arr[0]);
    for (int i = 1; i <= len; i++) {
      arr[i] += diff;
      if (visited.contains(arr[i])) {
        if (pattern.charAt(i - 1) == 'I') {
          while (visited.contains(arr[i])) arr[i]++;
        } else {
          while (visited.contains(arr[i])) arr[i]--;
        }
      }
      visited.add(arr[i]);
    }
    return Arrays.toString(arr);
  }

  public int[][] minScore(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    int[][] matrix = new int[m][n];
    int[] maxRow = new int[m];
    int[] maxCol = new int[n];
    Arrays.fill(maxRow, 0);
    Arrays.fill(maxCol, 0);
    PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt((int[] a) -> a[0]));
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        int[] elem = new int[3];
        elem[1] = i;
        elem[2] = j;
        elem[0] = grid[i][j];
        q.add(elem);
      }
    }

    while (!q.isEmpty()) {
      int[] elem = q.poll();
      int i = elem[1], j = elem[2];
      int value = Math.max(maxRow[i], maxCol[j]) + 1;
      matrix[i][j] = value;
      maxRow[i] = value;
      maxCol[j] = value;
    }

    return matrix;
  }

  public int minNumberOfHours(
      int initialEnergy, int initialExperience, int[] energy, int[] experience) {
    int n = energy.length;
    int minEnergy = 0, minExp = 0;
    for (int i = 0; i < n; i++) {
      if (initialEnergy <= energy[i]) {
        minEnergy += energy[i] - minEnergy + 1;
        initialEnergy = 1;
      } else {
        initialEnergy -= energy[i];
      }

      if (initialExperience <= experience[i]) {
        int delta = experience[i] - initialExperience + 1;
        minExp += delta;
        initialExperience += delta;
      }
      initialExperience += experience[i];
    }
    return minEnergy + minExp;
  }

  public int amountOfTime(TreeNode root, int start) {
    ArrayDeque<TreeNode> deque = new ArrayDeque<>();
    TreeNode startNode = find(root, start);
    assert startNode != null;
    deque.add(startNode);
    int iter1 = -1;
    while (!deque.isEmpty()) {
      int size = deque.size();
      for (int i = 0; i < size; i++) {
        TreeNode prevNode = deque.pollFirst();
        assert prevNode != null;
        if (prevNode.left != null) {
          deque.addLast(prevNode.left);
        }
        if (prevNode.right != null) {
          deque.addLast(prevNode.right);
        }
      }
      iter1++;
    }
    if (root.val == start) return iter1;

    int iter2 = -1;
    deque.add(root);
    while (!deque.isEmpty()) {
      int size = deque.size();
      for (int i = 0; i < size; i++) {
        TreeNode prevNode = deque.pollFirst();
        assert prevNode != null;
        if (prevNode.left != null && prevNode.left.val != start) {
          deque.addLast(prevNode.left);
        }
        if (prevNode.right != null && prevNode.right.val != start) {
          deque.addLast(prevNode.right);
        }
      }
      iter2++;
    }
    return Math.max(iter1, iter2);
  }

  private TreeNode find(TreeNode root, int start) {
    if (root == null) return null;
    if (root.val == start) return root;
    TreeNode leftFind = find(root.left, start);
    TreeNode rightFind = find(root.right, start);
    if (leftFind != null) return leftFind;
    if (rightFind != null) return rightFind;
    return null;
  }

  public String largestPalindromic(String num) {
    Integer largestSingle = null;
    int[] arr = new int[10];
    for (char ch : num.toCharArray()) {
      arr[ch - '0']++;
    }

    StringBuilder builder = new StringBuilder();
    for (int i = 9; i >= 1; i--) {
      while (arr[i] >= 2) {
        builder.append(i);
        arr[i] -= 2;
      }

      if (arr[i] == 1 && largestSingle == null) {
        largestSingle = i;
      }
    }

    if (!builder.isEmpty()) {
      while (arr[0] >= 2) {
        builder.append(0);
        arr[0] -= 2;
      }

      if (arr[0] == 1 && largestSingle == null) {
        largestSingle = 0;
      }
    }

    if (builder.isEmpty()) return String.valueOf(largestSingle);

    String str = builder.toString();
    if (largestSingle != null) {
      builder.append(largestSingle);
    }

    builder.append(new StringBuilder(str).reverse());
    return builder.toString();
  }

  public int minimumRecolors(String blocks, int k) {
    final char WHITE = 'W';
    int len = blocks.length();
    int min = 0;
    for (int i = 0; i < k; i++) {
      char ch = blocks.charAt(i);
      if (ch == WHITE) {
        min++;
      }
    }
    int tmp = min;
    for (int i = 1; i + k <= len; i++) {
      char ch = blocks.charAt(i + k - 1);
      if (ch == WHITE) {
        tmp++;
      }

      ch = blocks.charAt(i - 1);
      if (ch == WHITE) {
        if (--tmp < min) min = tmp;
      }
    }
    return min;
  }

  public String shiftingLetters(String s, int[][] shifts) {
    TreeMap<Integer, Integer> map = new TreeMap<>();
    int len = s.length();
    for (int[] shift : shifts) {
      if (shift[2] == 1) {
        update(map, shift[0], 1);
        update(map, shift[1] + 1, -1);
      } else {
        update(map, shift[0], -1);
        update(map, shift[1] + 1, 1);
      }
    }
    int prev = 0;
    TreeMap<Integer, Integer> map1 = new TreeMap<>();
    map1.put(0, 0);
    for (int key : map.keySet()) {
      int next = prev + map.get(key);
      map1.put(key, next);
      prev = next;
    }

    map1.putIfAbsent(len, 0);
    StringBuilder builder = new StringBuilder();
    List<Integer> list = new LinkedList<>(map1.keySet());
    for (int i = 0; i < list.size() - 1; i++) {
      int shiftValue = map1.get(list.get(i));
      for (int idx = list.get(i); idx < list.get(i + 1); idx++) {
        int ch = s.charAt(idx) + shiftValue;
        while (ch > 'z') {
          ch -= 26;
        }

        while (ch < 'a') {
          ch += 26;
        }

        builder.append((char) ch);
      }
    }
    return builder.toString();
  }

  private void update(TreeMap<Integer, Integer> map, int key, int change) {
    map.put(key, map.getOrDefault(key, 0) + change);
  }

  public int[] answerQueries(int[] nums, int[] queries) {
    int n = nums.length, m = queries.length;
    Arrays.sort(nums);
    for (int i = 1; i < n; i++) {
      nums[i] += nums[i - 1];
    }
    int[] res = new int[m];
    for (int i = 0; i < m; i++) {
      int idx = Arrays.binarySearch(nums, queries[i]) + 1;
      res[i] = idx <= 0 ? -idx : idx;
    }
    return res;
  }

  public String removeStars(String s) {
    final char STAR = '*';
    ArrayDeque<Character> stack = new ArrayDeque<>();
    for (char ch : s.toCharArray()) {
      if (ch == STAR) {
        if (stack.isEmpty()) continue;
        stack.pollLast();
      } else {
        stack.addLast(ch);
      }
    }
    final StringBuilder builder = new StringBuilder();
    stack.forEach(builder::append);
    return builder.toString();
  }

  public int garbageCollection(String[] garbage, int[] travel) {
    int m = garbage.length, n = travel.length;
    for (int i = 1; i < n; i++) {
      travel[i] += travel[i - 1];
    }

    int res = Arrays.stream(garbage).mapToInt(String::length).sum();
    for (char ch : Arrays.asList('M', 'G', 'P')) {
      for (int i = m - 1; i > 0; i--) {
        if (garbage[i].indexOf(ch) != -1) {
          res += travel[i - 1];
          break;
        }
      }
    }
    return res;
  }

  public boolean checkDistances(String s, int[] distance) {
    int[] arr = new int[26];
    final int EMPTY = -1;
    Arrays.fill(arr, EMPTY);
    int len = s.length();
    for (int i = 0; i < len; i++) {
      byte idx = (byte) (s.charAt(i) - 'a');
      if (arr[idx] == EMPTY) {
        arr[idx] = i;
      } else if (i - 1 - arr[idx] != distance[idx]) {
        return false;
      }
    }
    return true;
  }

  public int numberOfWays(int startPos, int endPos, int k) {
    int diff = Math.abs(endPos - startPos);
    if (diff > k || (diff + k) % 2 == 1) return 0;
    diff += (k - diff) / 2;
    final BigInteger MOD = BigInteger.valueOf(1000000007);
    BigInteger res =
        factorialHavingLargeResult(k)
            .divide(factorialHavingLargeResult(diff))
            .divide(factorialHavingLargeResult(k - diff))
            .mod(MOD);
    return res.intValue();
  }

  public BigInteger factorialHavingLargeResult(int n) {
    BigInteger result = BigInteger.ONE;
    for (int i = 2; i <= n; i++) result = result.multiply(BigInteger.valueOf(i));
    return result;
  }

  public int partitionString(String s) {
    HashSet<Character> set = new HashSet<>();
    int count = 1;
    for (char ch : s.toCharArray()) {
      if (set.contains(ch)) {
        count++;
        set = new HashSet<>();
      }
      set.add(ch);
    }
    return count;
  }

  public int mostFrequentEven(int[] nums) {
    HashMap<Integer, Integer> map = new HashMap<>();
    for (int num : nums) {
      if (num % 2 == 1) continue;
      map.put(num, map.getOrDefault(num, 0) + 1);
    }

    if (map.isEmpty()) return -1;
    int freq = 0, elem = 0;
    for (int key : map.keySet()) {
      int value = map.get(key);
      if (value > freq) {
        freq = value;
        elem = key;
      } else if (value == freq && key < elem) {
        elem = key;
      }
    }
    return elem;
  }

  public boolean findSubarrays(int[] nums) {
    HashSet<Integer> set = new HashSet<>();
    for (int i = 0; i < nums.length - 1; i++) {
      int sum = nums[i] + nums[i + 1];
      if (set.contains(sum)) return true;
      set.add(sum);
    }
    return false;
  }

  public long countSubarrays(int[] nums) {
    int len = nums.length;
    long res = 0L;
    int tmp = 1;
    for (int i = 1; i < len; i++) {
      if (nums[i] > nums[i - 1]) {
        tmp++;
      } else {
        res += ((long) tmp) * (tmp + 1) / 2;
        tmp = 1;
      }
    }
    return res + ((long) tmp) * (tmp + 1) / 2;
  }

  public int countDaysTogether(
      String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
    LocalDate aAlicaDate = extractDate(arriveAlice);
    LocalDate lAlicaDate = extractDate(leaveAlice);
    LocalDate aBobDate = extractDate(arriveBob);
    LocalDate lBobDate = extractDate(leaveBob);

    if (lAlicaDate.isBefore(aBobDate) || lBobDate.isBefore(aAlicaDate)) return 0;
    return (int)
        retrieveMin(
            minDate(aAlicaDate, lBobDate),
            minDate(aBobDate, lAlicaDate),
            minDate(aAlicaDate, lAlicaDate),
            minDate(aBobDate, lBobDate));
  }

  private long retrieveMin(long... values) {
    long min = Integer.MAX_VALUE - 1;
    for (long value : values) {
      min = Math.min(min, value);
    }
    return min;
  }

  private long minDate(LocalDate date1, LocalDate date2) {
    return ChronoUnit.DAYS.between(date1, date2);
  }

  private LocalDate extractDate(String strDate) {
    return LocalDate.of(
        2021, Integer.parseInt(strDate.substring(0, 2)), Integer.parseInt(strDate.substring(3, 5)));
  }

  public int matchPlayersAndTrainers(int[] players, int[] trainers) {
    int m = players.length, n = trainers.length;
    int i = 0, j = 0, count = 0;
    Arrays.sort(players);
    Arrays.sort(trainers);
    while (i < m && j < n) {
      if (players[i] <= trainers[j]) {
        i++;
        j++;
        count++;
      } else {
        j++;
      }
    }
    return count;
  }

  int cookiesMaxResult = Integer.MAX_VALUE;

  public int distributeCookies(int[] cookies, int k) {
    dfs(cookies, 0, k, new int[k]);
    return cookiesMaxResult;
  }

  void dfs(int[] cookies, int cur, int k, int[] children) {
    if (cur == cookies.length) {
      int max = Arrays.stream(children).max().getAsInt();
      cookiesMaxResult = Math.min(cookiesMaxResult, max);
      return;
    }
    for (int i = 0; i < k; i++) {
      children[i] += cookies[cur];
      dfs(cookies, cur + 1, k, children);
      children[i] -= cookies[cur];
    }
  }

  int compatibilityMaxScore;

  public int maxCompatibilitySum(int[][] students, int[][] mentors) {
    boolean[] visited = new boolean[students.length];
    helper(visited, students, mentors, 0, 0);
    return compatibilityMaxScore;
  }

  public void helper(boolean[] visited, int[][] students, int[][] mentors, int pos, int score) {
    if (pos >= students.length) {
      compatibilityMaxScore = Math.max(compatibilityMaxScore, score);
      return;
    }
    for (int i = 0; i < mentors.length; i++)
      if (!visited[i]) {
        visited[i] = true;
        helper(visited, students, mentors, pos + 1, score + score(students[pos], mentors[i]));
        visited[i] = false;
      }
  }

  public int score(int[] a, int[] b) {
    return (int) IntStream.range(0, a.length).filter(i -> a[i] == b[i]).count();
  }

  public int minCost(int[][] costs) {
    if (costs.length == 0) return 0;
    int[] previousRow = costs[costs.length - 1];

    for (int n = costs.length - 2; n >= 0; n--) {
      int[] currentRow = costs[n].clone();
      // Total cost of painting the nth house red.
      currentRow[0] += Math.min(previousRow[1], previousRow[2]);
      // Total cost of painting the nth house green.
      currentRow[1] += Math.min(previousRow[0], previousRow[2]);
      // Total cost of painting the nth house blue.
      currentRow[2] += Math.min(previousRow[0], previousRow[1]);
      previousRow = currentRow;
    }

    return Math.min(Math.min(previousRow[0], previousRow[1]), previousRow[2]);
  }

  public int maxProduct(String[] words) {
    int len = words.length;
    HashSet<Character>[] setArray = new HashSet[len];
    for (int i = 0; i < len; i++) {
      char[] chArray = words[i].toCharArray();
      setArray[i] =
          IntStream.range(0, chArray.length)
              .mapToObj(j -> chArray[j])
              .collect(Collectors.toCollection(HashSet::new));
    }
    int max = 0;
    for (int i = 0; i < len - 1; i++) {
      for (int j = i + 1; j < len; j++) {
        if (Collections.disjoint(setArray[i], setArray[j])) {
          max = Math.max(max, words[i].length() * words[j].length());
        }
      }
    }
    return max;
  }

  public int maxFont(String text, int w, int h, int[] fonts, FontInfo fontInfo) {
    if (getWidth(text, fonts[0], fontInfo) > w || getHeight(fonts[0], fontInfo) > h) return -1;
    int len = fonts.length, low = 0, high = len - 1;
    while (low < high) {
      int mid = (low + high + 1) / 2;
      if (getWidth(text, fonts[mid], fontInfo) > w || getHeight(fonts[mid], fontInfo) > h) {
        high = mid - 1;
      } else {
        low = mid;
      }
    }
    return low;
  }

  private long getWidth(String text, int font, FontInfo fontInfo) {
    return IntStream.range(0, text.length())
        .mapToLong(i -> fontInfo.getWidth(font, text.charAt(i)))
        .sum();
  }

  private long getHeight(int font, FontInfo fontInfo) {
    return fontInfo.getHeight(font);
  }

  public int maxChunksToSorted(int[] arr) {
    int chunks = 0, maxValue = 0, len = arr.length;
    for (int i = 0; i < len; i++) {
      if (arr[i] > maxValue) {
        maxValue = arr[i];
      }
      if (i == maxValue) {
        chunks++;
      }
    }
    return chunks;
  }

  public int[] assignBikes(int[][] workers, int[][] bikes) {
    int n = workers.length, m = bikes.length;
    int[] res = new int[n];
    Arrays.fill(res, -1);
    boolean[] usedBikes = new boolean[m];
    Arrays.fill(usedBikes, false);
    Comparator<WorkerBike> comparator =
        Comparator.comparingInt(WorkerBike::getDist)
            .thenComparingInt(WorkerBike::getWorkerIdx)
            .thenComparingInt(WorkerBike::getBikeIdx);
    ArrayList<WorkerBike> list = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        list.add(new WorkerBike(i, j, manhattanDistance(workers[i], bikes[j])));
      }
    }
    Collections.sort(list, comparator);
    int resSize = 0, idx = 0;
    while (resSize < n) {
      WorkerBike next = list.get(idx++);
      int workerIdx = next.getWorkerIdx(), bikeIdx = next.getBikeIdx();
      if (res[workerIdx] != -1 || usedBikes[bikeIdx]) continue;
      res[workerIdx] = bikeIdx;
      usedBikes[bikeIdx] = true;
      resSize++;
    }
    return res;
  }

  public int findLongestChain(int[][] pairs) {
    Arrays.sort(
        pairs, Comparator.comparingInt((int[] a) -> a[1]).thenComparingInt((int[] a) -> a[0]));
    int res = 1, lastEnd = pairs[0][1], i = 1, len = pairs.length;
    while (i < len) {
      if (pairs[i][0] <= lastEnd) {
        i++;
        continue;
      }
      lastEnd = pairs[i++][1];
      res++;
    }
    return res;
  }

  final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

  public int maxDistance(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] >= 2) continue;
        findClosestLand(grid, i, j);
      }
    }
    return (int)
            IntStream.range(0, m)
                .mapToLong(i -> IntStream.range(0, n).mapToLong(j -> grid[i][j]).max().getAsLong())
                .max()
                .getAsLong()
        - 1;
  }

  private int findClosestLand(int[][] grid, int i, int j) {
    if (grid[i][j] != 0) return grid[i][j];
    int minDistance = Integer.MAX_VALUE;
    for (int[] d : dirs) {
      int i1 = i + d[0], j1 = j + d[1];
      if (!isValid(grid, i1, j1)) continue;
      minDistance = findClosestLand(grid, i1, j1);
    }
    return minDistance;
  }

  public List<List<Integer>> pacificAtlantic(int[][] heights) {
    int m = heights.length, n = heights[0].length;
    int[][] dp = new int[m][n];
    // 1 - for nothing, 2 - pacific, 3 - atlantic, 0 - both
    for (int[] dp0 : dp) {
      Arrays.fill(dp0, 1);
    }
    Arrays.fill(dp[0], 2);
    Arrays.fill(dp[m - 1], 3);
    for (int i = 1; i <= m - 2; i++) {
      dp[i][0] = 2;
      dp[i][n - 1] = 3;
    }
    dp[0][n - 1] = dp[m - 1][0] = 0;

    for (int d = 2; d <= m + n; d++) {
      for (int i = 1; i <= d - 1; i++) {
        int j = n + 1 - (d - i);
        if (!isValid(heights, i, j)) continue;
        if (i == 0 || heights[i][j] >= heights[i - 1][j]) {
          dp[i][j] *= dp[i - 1][j];
        }
        if (j == n - 1 || heights[i][j] >= heights[i][j + 1]) {
          dp[i][j] *= dp[i][j + 1];
        }
        dp[i][j] %= 6;
      }
    }
    for (int d = 2; d <= m + n; d++) {
      for (int i = 1; i <= d - 1; i++) {
        int j = m + 1 - (d - i);
        if (!isValid(heights, j, i)) continue;
        if (j == m - 1 || heights[j][i] >= heights[j + 1][i]) {
          dp[j][i] *= dp[j + 1][i];
        }
        if (i == 0 || heights[j][i] >= heights[j][i - 1]) {
          dp[j][i] *= dp[j][i - 1];
        }
        dp[j][i] %= 6;
      }
    }

    List<List<Integer>> resList = new ArrayList<>();
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (dp[i][j] == 0) {
          resList.add(Arrays.asList(i, j));
        }
      }
    }
    return resList;
  }

  public String findLongestWord(String s, List<String> dictionary) {
    Comparator<String> comparator =
        Comparator.comparingInt(String::length).reversed().thenComparing(String::compareTo);
    Collections.sort(dictionary, comparator);
    HashMap<Character, TreeSet<Integer>> map = new HashMap<>();
    for (int i = 0; i < s.length(); i++) {
      char ch = s.charAt(i);
      TreeSet<Integer> set = map.getOrDefault(ch, new TreeSet<>());
      set.add(i);
      map.put(ch, set);
    }
    for (String word : dictionary) {
      if (isSolvable(map, word)) {
        return word;
      }
    }
    return "";
  }

  private int[] minStepsDp;

  public int minSteps(int n) {
    if (n == 1) return 0;
    minStepsDp = new int[n + 1];
    return minStepsRec(n);
  }

  public int minStepsRec(int n) {
    if (n == 1) return 0;
    if (minStepsDp[n] != 0) return minStepsDp[n];
    int minSteps = n;
    for (int i = 2; i <= n; i++) {
      if (n % i == 0) {
        minSteps = Math.min(minSteps, i + minStepsRec(n / i));
      }
    }
    return (minStepsDp[n] = minSteps);
  }

  private int maxZigZagPath;

  public int longestZigZag(TreeNode root) {
    if (root == null) return 0;
    maxZigZagPath = 0;
    longestZigZagRec(root);
    return maxZigZagPath;
  }

  private int[] longestZigZagRec(TreeNode root) {
    int[] res = new int[2];
    if (root.left != null) {
      res[0] = 1 + longestZigZagRec(root.left)[1];
      maxZigZagPath = Math.max(maxZigZagPath, res[0]);
    }
    if (root.right != null) {
      res[1] = 1 + longestZigZagRec(root.right)[0];
      maxZigZagPath = Math.max(maxZigZagPath, res[1]);
    }
    return res;
  }

  public int minCostII(int[][] costs) {
    int n = costs.length, k = costs[0].length;
    int[][] dp = new int[n][k];
    dp[0] = costs[0];
    for (int i = 1; i < n; i++) {
      for (int j = 0; j < k; j++) {
        int min = Integer.MAX_VALUE;
        for (int x = 0; x < k; x++) {
          if (x == j) continue;
          min = Math.min(min, dp[i - 1][x]);
        }
        dp[i][j] = costs[i][j] + min;
      }
    }
    return Arrays.stream(dp[n - 1]).min().getAsInt();
  }

  public int pathSum(int[] nums) {
    if (nums == null || nums.length == 0) return 0;
    HashMap<Integer, Integer> map = new HashMap<>();
    HashSet<Integer> leafSet = new HashSet<>();
    leafSet.add(0);
    for (int num : nums) {
      int value = num % 10, levelWithPos = num / 10;
      map.put(levelWithPos, value);
      leafSet.add(levelWithPos);
      leafSet.remove(getParentLevelWithPos(levelWithPos));
    }
    int sum = 0;
    for (Integer leaf : leafSet) {
      while (leaf != 0) {
        sum += map.get(leaf);
        leaf = getParentLevelWithPos(leaf);
      }
    }
    return sum;
  }

  private static int getParentLevelWithPos(int levelWithPos) {
    int level = levelWithPos / 10 - 1;
    if (level == 0) return level;
    return 10 * level + (levelWithPos % 10 + 1) / 2;
  }

  public String countOfAtoms(String formula) {
    HashMap<String, Integer> map = countOfAtomsRec(formula);
    StringBuilder builder = new StringBuilder();
    TreeSet<String> set = new TreeSet<>(map.keySet());
    for (String key : set) {
      builder.append(key);
      Integer value = map.get(key);
      if (value != 1) {
        builder.append(value);
      }
    }
    return builder.toString();
  }

  public HashMap<String, Integer> countOfAtomsRec(String formula) {
    int len = formula.length(), leftIdx = 0, rightIdx = len;
    while (leftIdx < len && formula.charAt(leftIdx) != '(') {
      leftIdx++;
    }
    if (leftIdx == len) {
      return parseToMap(formula);
    }
    while (formula.charAt(rightIdx - 1) != ')') {
      rightIdx--;
    }
    int multiplier = 0;
    int midIdx = rightIdx;
    if (rightIdx < len) {
      char ch = formula.charAt(rightIdx);
      while (Character.isDigit(ch)) {
        multiplier = 10 * multiplier + (ch - '0');
      }
    }
    if (multiplier == 0) {
      multiplier = 1;
    }

    HashMap<String, Integer> resMap = new HashMap<>();
    if (leftIdx > 0) {
      enrich(resMap, parseToMap(formula.substring(0, leftIdx)));
    }
    if (rightIdx < len) {
      enrich(resMap, parseToMap(formula.substring(rightIdx)));
    }
    if (midIdx > ++leftIdx) {
      enrich(resMap, countOfAtomsRec(formula.substring(leftIdx, midIdx)), multiplier);
    }
    return resMap;
  }

  private void enrich(HashMap<String, Integer> map, HashMap<String, Integer> subMap) {
    enrich(map, subMap, 1);
  }

  private void enrich(
      HashMap<String, Integer> map, HashMap<String, Integer> subMap, int multiplier) {
    for (String key : subMap.keySet()) {
      Integer newValue = multiplier * subMap.get(key);
      map.merge(key, newValue, Integer::sum);
    }
  }

  private HashMap<String, Integer> parseToMap(String formula) {
    Pattern pattern = Pattern.compile("([A-Z][a-z]+)([1-9][0-9]*)");
    Matcher matcher = pattern.matcher(formula);
    HashMap<String, Integer> resMap = new HashMap<>();
    while (matcher.find()) {
      String letter = matcher.group(1);
      int mult = matcher.groupCount() > 1 ? Integer.parseInt(matcher.group(2)) : 1;
      resMap.put(letter, mult);
    }
    return resMap;
  }

  public boolean canArrange(int[] arr, int k) {
    Map<Integer, Long> map =
        Arrays.stream(arr)
            .boxed()
            .collect(Collectors.groupingBy(num -> rem(num, k), Collectors.counting()));
    for (int key : map.keySet()) {
      long value = map.get(key);
      if (key == 0) {
        if (value % 2 != 1) return false;
      } else {
        if ((value + map.get(k - key)) % 2 != 1) return false;
      }
    }
    return true;
  }

  private int rem(int num, int k) {
    num = num % k;
    return num >= 0 ? num : num + k;
  }

  public long interchangeableRectangles(int[][] rectangles) {
    Map<RectPair, Long> map =
        Arrays.stream(rectangles)
            .map(convertToPair())
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    return map.values().stream().mapToLong(v -> v * (v - 1) / 2).sum();
  }

  private Function<int[], RectPair> convertToPair() {
    return (int[] arr) -> {
      int gcd = gcd(arr[0], arr[1]);
      return new RectPair(arr[0] / gcd, arr[1] / gcd);
    };
  }

  private int gcd(int a1, int a2) {
    if (a1 > a2) {
      int tmp = a1;
      a1 = a2;
      a2 = tmp;
    }

    while (a1 != 0) {
      int tmp = a1;
      a1 = a2 % a1;
      a2 = tmp;
    }
    return a2;
  }

  private static class RectPair {
    private final long weight, height;

    public RectPair(long weight, long height) {
      this.weight = weight;
      this.height = height;
    }

    public long getWeight() {
      return weight;
    }

    public long getHeight() {
      return height;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      RectPair rectPair = (RectPair) o;
      return weight == rectPair.weight && height == rectPair.height;
    }

    @Override
    public int hashCode() {
      int result = (int) (weight ^ (weight >>> 32));
      return 31 * result + (int) (height ^ (height >>> 32));
    }
  }

  public long[] getDistances(int[] arr) {
    HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
    int length = arr.length;
    for (int i = 0; i < length; i++) {
      HashSet<Integer> set = map.getOrDefault(arr[i], new HashSet<>());
      set.add(i);
      map.put(arr[i], set);
    }

    long[] res = new long[length];
    for (HashSet<Integer> set : map.values()) {
      for (int idx : set) {
        long sum = set.stream().mapToLong(value -> Math.abs(value - idx)).sum();
        res[idx] = sum;
      }
    }
    return res;
  }

  private HashSet<Integer> notCycled;

  public boolean circularArrayLoop(int[] nums) {
    notCycled = new HashSet<>();
    int length = nums.length;
    for (int i = 0; i < length; i++) {
      if (circularArrayLoop(nums, length, i, new HashSet<>())) {
        return true;
      }
    }
    return false;
  }

  public boolean circularArrayLoop(int[] nums, int length, int start, HashSet<Integer> stack) {
    if (stack.contains(start)) return true;
    if (notCycled.contains(start)) return false;
    stack.add(start);
    notCycled.add(start);
    int next = start + nums[start];
    if (next < 0) next += length;
    else if (next >= length) next -= length;
    if (nums[start] * nums[next] < 0) return false;
    return circularArrayLoop(nums, length, next, stack);
  }

  public List<String> invalidTransactions(String[] transactions) {
    final Comparator<Transaction> comparator =
        Comparator.comparingInt(Transaction::getTime).thenComparingInt(Transaction::getIndex);
    int len = transactions.length;
    Map<String, TreeSet<Transaction>> map =
        IntStream.range(0, len)
            .mapToObj(i -> parseTransaction(transactions, i))
            .collect(
                Collectors.groupingBy(
                    Transaction::getName,
                    Collectors.mapping(
                        Function.identity(),
                        Collectors.toCollection(() -> new TreeSet<>(comparator)))));
    ArrayList<String> list = new ArrayList<>();
    for (TreeSet<Transaction> set : map.values()) {
      ArrayList<Transaction> setList = new ArrayList<>(set);
      int size = setList.size();
      for (int i = 0; i < size; i++) {
        Transaction t1 = setList.get(i);
        if (t1.amount > 1000) {
          t1.isValid = false;
          list.add(transactions[t1.index]);
        }
        for (int j = i + 1; j < size; j++) {
          Transaction t2 = setList.get(j);
          if (t1.city.equals(t2.city)) continue;
          if (Math.abs(t1.time - t2.time) <= 60) {
            if (t1.isValid) {
              t1.isValid = false;
              list.add(transactions[t1.index]);
            }
            if (t2.isValid) {
              t2.isValid = false;
              list.add(transactions[t2.index]);
            }
          }
        }
      }
    }
    return list;
  }

  private int getLastTime(Transaction transaction) {
    return transaction == null ? -1000 : transaction.time;
  }

  private Transaction parseTransaction(String[] transactions, int index) {
    String[] arr = transactions[index].split(",");
    return new Transaction(
        arr[0], arr[3], Integer.parseInt(arr[1]), Integer.parseInt(arr[2]), index);
  }

  public int waysToSplit(int[] nums) {
    int MOD = (int) (1e9 + 7);
    int length = nums.length;
    int[] prefixSum = new int[length];
    prefixSum[0] = nums[0];
    for (int i = 1; i < length; ++i) prefixSum[i] = prefixSum[i - 1] + nums[i];

    int res = 0;
    for (int i = 1; i < length - 1; ++i) {
      if (prefixSum[i - 1] > (prefixSum[length - 1] - prefixSum[i - 1]) / 2)
        break; // early termination

      int left = binarySearch(prefixSum, prefixSum[i - 1], i, true);
      int right = binarySearch(prefixSum, prefixSum[i - 1], i, false);

      if (left == -1 || right == -1) continue; // none is satisfied

      res = (res + (right - left + 1) % MOD) % MOD;
    }

    return res;
  }

  private int binarySearch(int[] arr, int leftSum, int index, boolean searchLeft) {
    int length = arr.length;
    int l = index, r = length - 2;
    int res = -1;
    while (l <= r) {
      int m = (r - l) / 2 + l;
      int midSum = arr[m] - arr[index - 1];
      int rightSum = arr[length - 1] - arr[m];
      if (leftSum <= midSum && midSum <= rightSum) {
        res = m;
        if (searchLeft) r = m - 1;
        else l = m + 1;
      } else if (leftSum > midSum) { // shrink left
        l = m + 1;
      } else { // shrink right
        r = m - 1;
      }
    }
    return res;
  }

  public long findScore(int[] nums) {
    PriorityQueue<int[]> q =
        new PriorityQueue<>(
            Comparator.comparingInt((int[] a) -> a[0]).thenComparingInt((int[] a) -> a[1]));
    int len = nums.length;
    for (int i = 0; i < len; i++) {
      q.add(new int[] {nums[i], i});
    }
    long score = 0;
    HashSet<Integer> set = new HashSet<>();
    while (set.size() != len) {
      int[] arr = q.poll();
      if (set.contains(arr[1])) continue;
      set.add(arr[1]);
      score += arr[0];
      if (arr[1] > 0) {
        set.add(arr[1] - 1);
      }
      if (arr[1] > len - 1) {
        set.add(arr[1] + 1);
      }
    }
    return score;
  }

  public int findSmallestInteger(int[] nums, int value) {
    Map<Integer, Long> map =
        Arrays.stream(nums)
            .boxed()
            .map(num -> num % value)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    long min = Integer.MAX_VALUE;
    for (int i = 0; i < value; i++) {
      if (!map.containsKey(i)) return i;
      long count = map.get(i);
      min = Math.min(min, count * value + i);
    }
    return (int) min;
  }

  public boolean checkValidGrid(int[][] grid) {
    int n = grid.length;
    int[][] arr = new int[n * n][2];
    Arrays.fill(arr, null);
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (arr[grid[i][j]] != null) return false;
        arr[grid[i][j]] = new int[] {i, j};
      }
    }
    for (int i = 1; i < n * n; i++) {
      if (!checkNightMove(arr[i - 1], arr[i], n)) return false;
    }
    return true;
  }

  private boolean checkNightMove(int[] arrPrev, int[] arrNext, int n) {
    int x = Math.abs(arrNext[0] - arrPrev[0]), y = Math.abs(arrNext[1] - arrPrev[1]);
    return (x == 1 && y == 2) || (x == 2 && y == 1);
  }

  private int longestPathBetweenTwo = 1;

  public int diameter(Node root) {
    if (root.children.isEmpty()) return 0;
    int firstMax = -1, secondMax = -1;
    for (Node child : root.children) {
      int d = diameter(child);
      if (d > firstMax) {
        firstMax = d;
      } else if (d > secondMax) {
        secondMax = d;
      }
    }
    int path = secondMax != -1 ? firstMax + secondMax + 2 : firstMax + 1;
    longestPathBetweenTwo = Math.max(longestPathBetweenTwo, path);
    return firstMax + 1;
  }

  class Node {
    public int val;
    public List<Node> children;

    public Node() {
      children = new ArrayList<Node>();
    }

    public Node(int _val) {
      val = _val;
      children = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _children) {
      val = _val;
      children = _children;
    }
  }
  ;

  public int[] evenOddBit(int n) {
    int oneCount = 0, zeroCount = 0;
    while (n != 0) {
      if (n % 2 == 1) oneCount++;
      else zeroCount++;
      n /= 2;
    }
    return new int[] {oneCount, zeroCount};
  }

  public long repairCars(int[] ranks, int cars) {
    int len = ranks.length;
    int maxRank = Arrays.stream(ranks).max().getAsInt();
    long maxCarsPerWorker = (cars / len + 1);
    long high = maxRank * maxCarsPerWorker * maxCarsPerWorker;
    long low = 1;
    while (low < high) {
      long mid = (high + low) / 2;
      if (isTimeEnoughForRanks(ranks, cars, mid)) {
        high = mid;
      } else {
        low = mid + 1;
      }
    }
    return low;
  }

  private boolean isTimeEnoughForRanks(int[] ranks, int cars, long mid) {
    long sum = 0;
    for (int rank : ranks) {
      sum += Math.sqrt((double) mid / rank);
      if (sum >= cars) return true;
    }
    return false;
  }

  public int maximizeGreatness(int[] nums) {
    int len = nums.length;
    Arrays.sort(nums);
    int j = 0;
    for (int i = 0; i < len; i++) {
      while (j < len && nums[j] == nums[i]) {
        j++;
      }
      if (j == len) return i;
      j++;
    }
    return len;
  }

  public int distMoney(int money, int children) {
    if (money <= children) return 0;
    money -= children;
    int res = money / 7;
    if (res == 0) return 0;
    return res % 7 == 3 ? res - 1 : res;
  }

  private class Transaction {
    final String name, city;
    final int time, amount, index;
    boolean isValid = true;

    public Transaction(String name, String city, int time, int amount, int index) {
      this.name = name;
      this.city = city;
      this.time = time;
      this.amount = amount;
      this.index = index;
    }

    public String getName() {
      return name;
    }

    public int getTime() {
      return time;
    }

    public int getIndex() {
      return index;
    }
  }

  public List<Integer> peopleIndexes(List<List<String>> favoriteCompanies) {
    int size = favoriteCompanies.size();
    List<Set<String>> listOfSets = new LinkedList<>();
    for (List<String> fc : favoriteCompanies) listOfSets.add(new HashSet<>(fc));
    int[] parent = new int[size];
    for (int i = 0; i < size; i++) parent[i] = i;
    for (int i = 0; i < size; i++)
      for (int j = i + 1; j < size; j++) {
        int iParent = find(parent, i), jParent = find(parent, j);
        Set<String> iSet = listOfSets.get(iParent);
        Set<String> jSet = listOfSets.get(jParent);
        if (contains(iSet, jSet)) parent[jParent] = iParent;
        else if (contains(jSet, iSet)) parent[iParent] = jParent;
      }
    Set<Integer> set = new HashSet<>();
    for (int i : parent) set.add(find(parent, i));
    List<Integer> resList = new LinkedList<>(set);
    Collections.sort(resList);
    return resList;
  }

  public boolean contains(Set<String> a, Set<String> b) {
    if (a.size() <= b.size()) return false;
    return a.containsAll(b);
  }

  public int find(int[] f, int i) {
    while (f[i] != i) {
      f[i] = f[f[i]];
      i = f[i];
    }
    return i;
  }

  public int numFactoredBinaryTrees(int[] A) {
    int MOD = 1_000_000_007;
    int N = A.length;
    Arrays.sort(A);
    long[] dp = new long[N];
    Arrays.fill(dp, 1);

    Map<Integer, Integer> index = new HashMap<>();
    for (int i = 0; i < N; ++i) index.put(A[i], i);

    for (int i = 1; i < N; ++i)
      for (int j = 0; j < i; ++j) {
        if (A[i] % A[j] == 0) { // A[j] is left child
          int right = A[i] / A[j];
          Integer j2 = index.get(right);
          if (j2 != null) {
            dp[i] = (dp[i] + dp[j] * dp[j2]) % MOD;
          }
        }
      }
    return (int) (Arrays.stream(dp).sum() % MOD);
  }

  private boolean isSolvable(HashMap<Character, TreeSet<Integer>> map, String word) {
    Integer lastIdx = -1;
    for (char ch : word.toCharArray()) {
      TreeSet<Integer> set = map.get(ch);
      if (set == null) return false;
      lastIdx = set.ceiling(lastIdx + 1);
      if (lastIdx == null) return false;
    }
    return true;
  }

  private boolean isValid(int[][] arr, int j, int i) {
    return j >= 0 && i >= 0 && j < arr.length && i < arr[0].length;
  }

  private int manhattanDistance(int[] worker, int[] bike) {
    return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
  }

  private class WorkerBike {
    private final int workerIdx, bikeIdx, dist;

    public WorkerBike(int workerIdx, int bikeIdx, int dist) {
      this.workerIdx = workerIdx;
      this.bikeIdx = bikeIdx;
      this.dist = dist;
    }

    public int getWorkerIdx() {
      return workerIdx;
    }

    public int getBikeIdx() {
      return bikeIdx;
    }

    public int getDist() {
      return dist;
    }
  }

  private int getMaxValueForChunk(int[] arr, int start) {
    int maxValue = start, currentValue = start;
    while (true) {
      int nextValue = arr[currentValue];
      arr[currentValue] = -arr[currentValue] - 1;
      if (nextValue < 0) {
        return maxValue;
      }
      maxValue = Math.max(maxValue, nextValue);
      currentValue = nextValue;
    }
  }

  interface FontInfo {
    // Return the width of char ch when fontSize is used.
    int getWidth(int fontSize, char ch);

    // Return Height of any char when fontSize is used.
    int getHeight(int fontSize);
  }

  private static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(int val) {
      this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }

  private static class Object2 {}

  private static class Object1 {
    private final String value;

    public Object1(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }

  private record StringNode(String str, int trimmedPrefix, int initIndexInArray) {}

  private static class TrieNode {
    private final HashMap<Character, TrieNode> childNodes = new HashMap<>();
    private final char value;
    private boolean isEndWord = false;

    public TrieNode(char value) {
      this.value = value;
    }

    public TrieNode get(char ch) {
      return this.childNodes.get(ch);
    }

    public void add(char ch, TrieNode childNode) {
      childNodes.put(ch, childNode);
    }

    public boolean isEndWord() {
      return isEndWord;
    }

    public void setEndWord(boolean endWord) {
      isEndWord = endWord;
    }
  }

  private static class Trie {
    private static final TrieNode root = new TrieNode('#');

    public void add(String str) {
      TrieNode current = root;
      for (char ch : str.toCharArray()) {
        TrieNode childNode = current.get(ch);
        if (childNode == null) {
          childNode = new TrieNode(ch);
          current.add(ch, childNode);
        }
        if (childNode.isEndWord()) return;
        current = childNode;
      }
      current.setEndWord(true);
    }

    public String findPrefix(String str) {
      int i = 0;
      TrieNode current = root;
      for (char ch : str.toCharArray()) {
        TrieNode childNode = current.get(ch);
        if (childNode == null) return null;
        i++;
        if (childNode.isEndWord()) return str.substring(0, i);
        current = childNode;
      }
      return null;
    }
  }

  private static class HashMapAdapter extends HashMap<String, Integer> {

    private final Queue<String> q = new ArrayDeque<>();

    @Override
    public Integer put(String key, Integer value) {
      q.add(key);
      return super.put(key, value);
    }

    public String pollFirst() {
      return q.poll();
    }

    @Override
    public boolean isEmpty() {
      return q.isEmpty();
    }
  }

  private record StringTriplet(String s1, String s2, String s3) {}

  private static class ArrayWrapper {
    int[] arr;

    public ArrayWrapper(int[] arr) {
      int n = arr.length;
      this.arr = new int[n];
      System.arraycopy(arr, 0, this.arr, 0, n);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      ArrayWrapper that = (ArrayWrapper) o;

      return Arrays.equals(arr, that.arr);
    }

    @Override
    public int hashCode() {
      return Arrays.hashCode(arr);
    }
  }

  private static class NumberContainers {

    private final HashMap<Integer, TreeSet<Integer>> mapSet;
    private final HashMap<Integer, Integer> map;

    public NumberContainers() {
      mapSet = new HashMap<>();
      map = new HashMap<>();
    }

    public void change(int index, int number) {
      Integer prevNumber = map.get(index);
      if (prevNumber != null) {
        if (prevNumber == number) return;
        mapSet.get(prevNumber).remove(index);
      }
      map.put(index, number);
      TreeSet<Integer> numSet = mapSet.getOrDefault(number, new TreeSet<>());
      numSet.add(index);
      mapSet.put(number, numSet);
    }

    public int find(int number) {
      TreeSet<Integer> set = mapSet.get(number);
      if (set == null || set.isEmpty()) return -1;
      return set.first();
    }
  }

  private static class Solution {

    private static final Random RAND = new Random();
    private final HashMap<Integer, LinkedList<Integer>> map = new HashMap<>();

    public Solution(int[] nums) {
      for (int i = 0; i < nums.length; i++) {
        int num = nums[i];
        LinkedList<Integer> idxes = map.getOrDefault(num, new LinkedList<Integer>());
        idxes.add(i);
        map.put(num, idxes);
      }
    }

    public int pick(int target) {
      LinkedList<Integer> idxes = map.get(target);
      int len = idxes.size();
      return idxes.get(RAND.nextInt(len));
    }
  }

  private static class PhoneDirectory {

    private final int MAX_NUMBERS;
    private final PriorityQueue<Integer> q = new PriorityQueue<>();
    private final HashSet<Integer> set = new HashSet<>();
    private int nextNumber;

    public PhoneDirectory(int maxNumbers) {
      MAX_NUMBERS = maxNumbers;
      nextNumber = 1;
    }

    public int get() {
      int value = -1;
      if (q.isEmpty()) {
        if (nextNumber < MAX_NUMBERS) value = nextNumber++;
      } else {
        value = q.poll();
      }
      if (value != -1) set.add(value);
      return value;
    }

    public boolean check(int number) {
      return !set.contains(number);
    }

    public void release(int number) {
      if (!set.contains(number)) return;
      set.remove(number);
      q.add(number);
    }
  }

  class FoodRatings {

    private final HashMap<String, Integer> foodToRating = new HashMap<>();
    private final HashMap<String, String> foodToCuisine = new HashMap<>();
    private final HashMap<String, TreeMap<Integer, TreeSet<String>>> cuisineToRating =
        new HashMap<>();

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
      int len = foods.length;
      for (int i = 0; i < len; i++) {
        foodToRating.put(foods[i], ratings[i]);
        foodToCuisine.put(foods[i], cuisines[i]);

        TreeMap<Integer, TreeSet<String>> treeMap =
            cuisineToRating.getOrDefault(cuisines[i], new TreeMap<>());
        TreeSet<String> treeSet = treeMap.getOrDefault(ratings[i], new TreeSet<>());
        treeSet.add(foods[i]);
        treeMap.put(ratings[i], treeSet);

        cuisineToRating.put(cuisines[i], treeMap);
      }
    }

    public void changeRating(String food, int newRating) {
      Integer prevRating = foodToRating.put(food, newRating);
      if (prevRating != null && prevRating == newRating) return;

      String cuisine = foodToCuisine.get(food);

      TreeMap<Integer, TreeSet<String>> treeMap =
          cuisineToRating.getOrDefault(cuisine, new TreeMap<>());
      TreeSet<String> treeSet = treeMap.getOrDefault(prevRating, new TreeSet<>());
      treeSet.remove(food);
      if (treeSet.isEmpty()) {
        treeMap.remove(prevRating);
      } else {
        treeMap.put(prevRating, treeSet);
      }
      TreeSet<String> newSet = treeMap.getOrDefault(newRating, new TreeSet<>());
      newSet.add(food);
      treeMap.put(newRating, newSet);

      cuisineToRating.put(cuisine, treeMap);
    }

    public String highestRated(String cuisine) {
      return cuisineToRating.get(cuisine).lastEntry().getValue().first();
    }
  }

  class StringIterator {

    private final LinkedList<Character> charList = new LinkedList<>();
    private final LinkedList<Integer> freqList = new LinkedList<>();

    public StringIterator(String compressedString) {
      Matcher matcher = Pattern.compile("[a-zA-Z]|(\\d)+").matcher(compressedString);
      while (matcher.find()) {
        String group = matcher.group();
        charList.add(group.charAt(0));
        matcher.find();
        freqList.add(Integer.parseInt(matcher.group()));
      }
    }

    public char next() {
      if (!hasNext()) return ' ';
      int freq = freqList.get(0);
      char ch = charList.get(0);
      if (freq > 1) {
        freqList.set(0, freq - 1);
      } else {
        freqList.remove(0);
        charList.remove(0);
      }
      return ch;
    }

    public boolean hasNext() {
      return !charList.isEmpty();
    }
  }

  public int longestContinuousSubstring(String s) {
    int len = s.length();
    int startIdx = 0;
    int max = 1;
    for (int i = 1; i < len; i++) {
      char ch = s.charAt(i);
      if (ch == s.charAt(i - 1) + 1) continue;
      max = Math.max(max, i - startIdx);
      startIdx = i;
    }
    return Math.max(max, len - startIdx);
  }

  public TreeNode reverseOddLevels(TreeNode root) {
    ArrayDeque<TreeNode> deque = new ArrayDeque<>();
    deque.add(root);
    while (!deque.isEmpty()) {
      List<TreeNode> list = new LinkedList<>();
      int size = deque.size();
      if (deque.peekFirst().left == null) break;
      for (int i = 0; i < size; i++) {
        TreeNode parent = deque.pollFirst();
        list.add(parent.left);
        list.add(parent.right);
      }

      size = list.size();
      for (int i = 0; i < size / 2; i++) {
        int tmpValue = list.get(i).val;
        list.get(i).val = list.get(size - 1 - i).val;
        list.get(size - 1 - i).val = tmpValue;
      }

      if (list.get(0).left == null) break;

      for (int i = 0; i < size; i++) {
        TreeNode parent = list.get(i);
        deque.addLast(parent.left);
        deque.addLast(parent.right);
      }
    }
    return root;
  }
}
