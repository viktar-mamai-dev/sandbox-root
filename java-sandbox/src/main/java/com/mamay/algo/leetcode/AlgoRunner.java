package com.mamay.algo.leetcode;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.*;

public class AlgoRunner {
    /* add your code here */

    public int countSymmetricIntegers(int low, int high) {
        return (int) IntStream.rangeClosed(low, high)
                .filter(this::isSymmetric)
                .count();
    }

    private boolean isSymmetric(int num) {
        String numStr = String.valueOf(num);
        int n = numStr.length();

        if (n % 2 != 0) {
            return false; // Odd-length numbers are never symmetric
        }

        int sum1 = getSum(0, n / 2, numStr);
        int sum2 = getSum(n / 2, n, numStr);
        return sum1 == sum2;
    }

    private int getSum(int start, int end, String numStr) {
        return IntStream.range(start, end)
                .map(i -> Character.getNumericValue(numStr.charAt(i)))
                .sum();
    }

    public boolean checkStrings(String s1, String s2) {
        int len = s1.length();
        Map<Integer, Long> evenMap1 = toCountingMap(collectToStream(s1, isEven()));
        Map<Integer, Long> evenMap2 = toCountingMap(collectToStream(s2, isEven()));
        Map<Integer, Long> oddMap1 = toCountingMap(collectToStream(s1, isEven().negate()));
        Map<Integer, Long> oddMap2 = toCountingMap(collectToStream(s1, isEven().negate()));
        return evenMap1.equals(evenMap2) && oddMap1.equals(oddMap2);
    }

    private Stream<Integer> collectToStream(String str, Predicate<Integer> p) {
        return IntStream.range(0, str.length()).boxed().filter(isEven()).map(i -> str.charAt(i) - 'a');
    }

    private Map<Integer, Long> toCountingMap(Stream<Integer> stream) {
        return stream.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public int minOperations(int[] nums) {
        Map<Integer, Long> map = toCountingMap(Arrays.stream(nums).boxed());
        Set<Long> set = new HashSet<>(map.values());
        if (set.contains(1L)) {
            return -1;
        }
        int sum = 0;
        for (Long value : set) {
            sum += splitCount3(value);
        }
        return sum;
    }

    public long maximumTripletValue(int[] nums) {
        int len = nums.length;
        long max = 0;
        for (int i1 = 0; i1 < len - 2; i1++) {
            for (int i2 = i1 + 1; i2 < len - 1; i2++) {
                for (int i3 = i2 + 1; i3 < len; i3++) {
                    max = Math.max(max, ((long) nums[i1] - nums[i2]) * nums[i3]);
                }
            }
        }
        return max;
    }

    public int minProcessingTime(List<Integer> processorTime, List<Integer> tasks) {
        PriorityQueue<Integer> processorQueue = new PriorityQueue<>();
        processorQueue.addAll(processorTime);
        PriorityQueue<Integer> taskQueue = new PriorityQueue<>(reverseOrder());
        taskQueue.addAll(tasks);
        int max = 0;
        while (!processorQueue.isEmpty()) {
            int time = processorQueue.poll() + taskQueue.poll();
            for (int i = 0; i < 3; i++) {
                taskQueue.poll();
            }
            max = Math.max(max, time);
        }
        return max;
    }

    public int differenceOfSums(int n, int m) {
        return IntStream.rangeClosed(1, n).filter(i -> i % m != 0).sum()
                - IntStream.rangeClosed(1, n).filter(i -> i % m == 0).sum();
    }

    public List<Integer> lastVisitedIntegers(List<String> words) {
        LinkedList<String> list = new LinkedList<>();
        LinkedList<Integer> resList = new LinkedList<>();
        int currentIdx = -1;
        for (String word : words) {
            if ("prev".equals(word)) {
                if (currentIdx >= 0) {
                    resList.add(Integer.parseInt(list.get(currentIdx)));
                } else {
                    resList.add(-1);
                }
                currentIdx--;
            } else {
                list.add(word);
                currentIdx = list.size() - 1;
            }
        }
        return resList;
    }

    public List<String> getWordsInLongestSubsequence(int n, String[] words, int[] groups) {
        List<Integer> list = new ArrayList<>();
        int prev = -1;
        for (int i = 0; i < groups.length; i++) {
            if (groups[i] != prev) {
                list.add(i);
                prev = groups[i];
            }
        }
        return list.stream().map(i -> words[i]).collect(Collectors.toList());
    }

    private long splitCount3(long value) {
        if (value % 3 == 0) {
            return value / 3;
        }
        if (value % 2 == 0) {
            return value / 3 + 1;
        }
        return (value - 4) / 3 + 2;
    }

    private Predicate<Integer> isEven() {
        return x -> x % 2 == 0;
    }

    private long minDistanceGrid = Long.MAX_VALUE;

    public int minimumMoves(int[][] grid) {
        Map<Integer, Integer> emptyMap = new HashMap<>();
        Map<Integer, Integer> fullMap = new HashMap<>();
        for (int i = 0; i < 3; i++) {
            for (int i2 = 0; i2 < 3; i2++) {
                if (grid[i][i2] == 1) continue;
                int i3 = toIdx(i, i2);
                if (grid[i][i2] == 0) {
                    emptyMap.put(i3, 1);
                } else {
                    fullMap.put(i3, grid[i][i2]);
                }
            }
        }
        if (emptyMap.isEmpty()) {
            return 0;
        }
        calcMinDistanceRec(emptyMap, fullMap, 0l);
        return (int) minDistanceGrid;
    }

    private void calcMinDistanceRec(Map<Integer, Integer> emptyMap,
                                    Map<Integer, Integer> fullMap,
                                    long currentDistance) {
        Set<Integer> keys1 = emptyMap.keySet();
        Set<Integer> keys2 = fullMap.keySet();
        if (emptyMap.isEmpty()) {
            minDistanceGrid = Math.min(minDistanceGrid, currentDistance);
        }
        for (int key1 : keys1) {
            int value1 = emptyMap.get(key1);
            if (value1 == 0) {
                continue;
            }
            dec(emptyMap, key1);
            for (int key2 : keys2) {
                int value2 = emptyMap.get(key2);
                if (value2 == 0) {
                    continue;
                }
                dec(fullMap, key2);
                long distance = Math.abs(key1 % 3 - key2 % 3) + Math.abs(key1 / 3 - key2 / 3);
                calcMinDistanceRec(emptyMap, fullMap, currentDistance + distance);
                inc(fullMap, key2);
            }
            inc(emptyMap, key1);
        }
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

    private int toIdx(int i, int i2) {
        return i * 3 + i2;
    }


    public long maxSum(List<Integer> nums, int m, int k) {
        int len = nums.size();
        Map<Integer, Integer> map = new HashMap<>();
        long sum = 0;
        for (int i = 0; i < m; i++) {
            Integer key = nums.get(i);
            int value = map.getOrDefault(key, 0) + 1;
            map.put(key, value);
            sum += key;
        }
        long max = 0;
        if (map.size() >= k) {
            max = sum;
        }
        for (int i = m; i < len; i++) {
            int key = nums.get(i - m);
            sum -= key;
            int value = map.get(key) - 1;
            if (value == 0) {
                map.remove(key);
            } else {
                map.put(key, value);
            }
            key = nums.get(i);
            sum += key;
            value = map.getOrDefault(key, 0) + 1;
            map.put(key, value);
            if (map.size() > k) {
                max = sum;
            }
        }
        return max;
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

    public int minLengthAfterRemovals(List<Integer> nums) {
        int len = nums.size();
        SetPair[] setPairs = new SetPair[len];
        for (int i = 0; i < len; i++) {
            setPairs[i] = new SetPair();
        }
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (nums.get(j) > nums.get(i)) {
                    setPairs[i].addOut(j);
                    setPairs[j].addIn(i);
                }
            }
        }

        Map<Boolean, List<Integer>> map = IntStream.range(0, len).boxed()
                .collect(Collectors.partitioningBy(i -> setPairs[i].getOutcomingVerts().isEmpty()));
        LinkedList<Integer> endingVerts = new LinkedList<>(map.get(true));
        Set<Integer> notEndingVerts = new HashSet<>(map.get(false));
        int pairCount = 0;
        while (!endingVerts.isEmpty() && !notEndingVerts.isEmpty()) {
            int v1 = endingVerts.pollFirst();
            if (!setPairs[v1].getIncomingVerts().isEmpty()) {
                int v2 = setPairs[v1].getIncomingVerts().pollFirst();
                setPairs[v2].getOutcomingVerts().remove(v1);
                if (setPairs[v2].getOutcomingVerts().isEmpty()) {
                    notEndingVerts.remove(v2);
                    endingVerts.addLast(v2);
                }
                pairCount++;
            }
        }
        return len - 2 * pairCount;
    }

    private int countSetBits(int n) {
        int count = 0;
        while (n > 0) {
            count += n & 1;
            n >>= 1;
        }
        return count;
    }

    public int sumIndicesWithKSetBits(List<Integer> nums, int k) {
        return IntStream.range(0, nums.size()).filter(i -> k == countSetBits(i))
                .map(nums::get).sum();
    }

    public String maximumOddBinaryNumber(String s) {
        int len = s.length();
        int oneCount = (int) IntStream.range(0, len).filter(i -> s.charAt(i) == '1').count();
        if (oneCount == 0) {
            return s;
        }
        StringBuilder builder = new StringBuilder();
        builder.append('1');
        builder.append("0".repeat(len - oneCount));
        oneCount--;
        builder.append("1".repeat(oneCount));
        return builder.toString();
    }

    public int minOperations(List<Integer> nums, int k) {
        int len = nums.size();
        HashSet<Integer> set = new HashSet<>();
        for (int i = len - 1; i >= 0; i--) {
            int num = nums.get(i);
            if (num > k) {
                continue;
            }
            set.add(num);
            if (set.size() == k) {
                return len - i;
            }
        }
        return -1; // never happen
    }

    public long maximumSumOfHeights(List<Integer> maxHeights) {
        int len = maxHeights.size();
        return IntStream.range(0, len).mapToLong(i -> maximumSumOfHeights(maxHeights, i))
                .max().getAsLong();
    }

    private long maximumSumOfHeights(List<Integer> maxHeights, int idx) {
        int prevMax = maxHeights.get(idx);
        long sum = prevMax;
        for (int i = idx - 1; i >= 0; i--) {
            prevMax = Math.min(prevMax, maxHeights.get(i));
            sum += prevMax;
        }
        prevMax = maxHeights.get(idx);
        for (int i = idx + 1; i < maxHeights.size(); i++) {
            prevMax = Math.min(prevMax, maxHeights.get(i));
            sum += prevMax;
        }
        return sum;
    }

    public int[] minEdgeReversals(int n, int[][] edges) {
        int[] res = new int[n];
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        Arrays.fill(res, 0);
        List<List<Integer>> graph = new ArrayList<>();
        Collections.fill(graph, new ArrayList<>());
        for (int[] edge : edges) {
            res[edge[1]] = 1;
            graph.get(edge[0]).add(edge[1]);
        }
        int startVert = findStartVert(res);
        arrayDeque.addLast(startVert);
        int level = 0;
        while (!arrayDeque.isEmpty()) {
            int size = arrayDeque.size();
            for (int i = 0; i < size; i++) {
                int nextVert = arrayDeque.pollFirst();
                res[nextVert] = level;
                for (int w : graph.get(nextVert)) {
                    arrayDeque.addLast(w);
                }
            }
            ++level;
        }
        return res;
    }

    private int findStartVert(int[] res) {
        return IntStream.range(0, res.length).filter(i -> res[i] == 0).findFirst().orElseThrow();
    }
}

class SetPair {
    private final LinkedList<Integer> incomingVerts = new LinkedList<>();
    private final Set<Integer> outcomingVerts = new HashSet<>();

    public void addIn(Integer incomingVert) {
        incomingVerts.add(incomingVert);
    }

    public void addOut(Integer outcomingVert) {
        outcomingVerts.add(outcomingVert);
    }

    public boolean removeIn(Integer incomingVert) {
        return incomingVerts.remove(incomingVert);
    }

    public boolean removeOut(Integer outcomingVert) {
        return outcomingVerts.add(outcomingVert);
    }

    public LinkedList<Integer> getIncomingVerts() {
        return incomingVerts;
    }

    public Set<Integer> getOutcomingVerts() {
        return outcomingVerts;
    }
}