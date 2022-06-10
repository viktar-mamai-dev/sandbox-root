package com.mamay.leetcode.practice.easy1;

import java.io.IOException;
import java.util.*;

public class Heaters_475 {

    private static final String INPUT_FILE = "input.txt";
    private static final String OUTPUT_FILE = "output.txt";

    private int binarySearch(int h, int[] heaters) {
        int high = heaters.length - 1;
        int low = 0;
        while (low < high) {
            int mid = (low + high) / 2;
            int val = heaters[mid];
            if (h == val) return mid;
            if (val < h) low = mid + 1;
            else high = mid - 1;
        }
        return high;
    }


    public int shipWithinDays(int[] weights, int D) {
        int max = Arrays.stream(weights).sum();
        int l = 1, h = max;
        while (l != h) {
            int m = (l + h) / 2;
            boolean flag = solve(weights, m, D);
            if (flag) h = m;
            else l = m + 1;
        }
        return l;
    }

    private boolean solve(int[] weights, int m, int D) {
        int sum = 0, count = 1, i = 0;
        int n = weights.length;
        while (i < n) {
            if (sum + weights[i] <= m) sum += weights[i];
            else {
                if (weights[i] > m) return false;
                if (++count > D) return false;
                sum = weights[i];
            }
            i++;
        }
        return count <= D;
    }

    static class TimeMap {

        private HashMap<String, TreeMap<Integer, String>> map;

        public TimeMap() {
            map = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            TreeMap<Integer, String> treeMap = map.get(key);
            if (treeMap == null) {
                treeMap = new TreeMap<>();
            }

            treeMap.put(timestamp, value);
            map.put(key, treeMap);
        }

        public String get(String key, int timestamp) {
            TreeMap<Integer, String> treeMap = map.get(key);
            Map.Entry<Integer, String> entry = treeMap.floorEntry(timestamp);
            if (entry == null) return "";
            return entry.getValue();
        }
    }

    public int mySqrt(int x) {
        if (x == 1) return 1;
        long l = 1, h = x - 1;
        while (l != h) {
            System.out.println("l= " + l + " h= " + h);
            long m = (l + h + 1) / 2;
            long y = m * m;
            if (y == x) return (int) m;
            else if (y < x) {
                l = m;
            } else {
                h = m - 1;
            }
        }
        return (int) l;
    }


    public int arrangeCoins(int n) {
        long k = n;
        long l = 1;
        while (k != l) {
            long m = (k + l + 1) / 2;
            long p = m * (m + 1) / 2;
            if (p == n) return (int) m;
            else if (p > n) k = m - 1;
            else l = m;
        }
        return (int) k;
    }

    private static int[] readIntegerArray(Scanner scanner) {
        String tokens[] = scanner.nextLine().replaceAll("\\[|\\]", "").split(",");
        int len = tokens.length;
        int res[] = new int[len];
        for (int k = 0; k < len; k++) {
            res[k] = Integer.parseInt(tokens[k].trim());
        }
        System.out.println("Length of array is: " + len);
        return res;
    }

    public int numSpecialEquivGroups(String[] A) {
        int n = A.length;
        int count = 0;
        boolean used[] = new boolean[n];
        Arrays.fill(used, false);
        int i = 0;
        while (i < n) {
            while (i < n && used[i]) i++;
            if (i >= n) break;
            used[i] = true;
            count++;
            for (int j = i + 1; j < n; j++) {
                if (used[j]) continue;
                if (similar(A, i, j)) used[j] = true;
            }
            i++;
        }
        return count;
    }

    private boolean similar(String[] a, int i, int j) {
        int arr[] = new int[26];
        int len = a[i].length();
        for (int k = 0; k < len; k++) {
            arr[a[i].charAt(k) - 'a']++;
            arr[a[j].charAt(k) - 'a']--;
        }
        for (int val : arr) if (val != 0) return false;
        return true;
    }


    private HashMap<Integer, Integer> map = new HashMap<>();
    private HashSet<Integer> set = new HashSet<>();

    public int brokenCalc(int X, int Y) {
        if (X >= Y) return X - Y;
        if (X <= 0) return -1;
        if (set.contains(X)) {
            if (map.containsKey(X)) return map.get(X);
            return -1;
        }
        set.add(X);
        int steps = brokenCalc(2 * X, Y);
        int steps2 = brokenCalc(X - 1, Y);
        if (steps2 != -1 && steps2 < steps || steps == -1) {
            steps = steps2;
        }
        if (steps == -1) return -1;
        steps++;
        map.put(X, steps);
        return steps;
    }

    public int nthSuperUglyNumber(int n, int[] primes) {

        if (n == 1) return 1;

        HashSet<Integer> primeFactors = new HashSet<>();
        for (int p : primes) {
            if (p % 2 == 0) {
                primeFactors.add(2);
                while (p % 2 == 0) {
                    p /= 2;
                }
            }
            for (int i = 3; i <= p; i += 2) {
                if (p % i == 0) {
                    primeFactors.add(i);
                    while (p % i == 0) {
                        p /= i;
                    }
                }
            }
        }
        PriorityQueue<Long> q = new PriorityQueue<>();
        for (int p : primeFactors) {
            q.add((long) p);
        }

        for (int i = 0; i < n - 2; i++) {
            long min = q.peek();
            for (int p : primes) {
                long newVal = min * p;
                if (!q.contains(newVal))
                    q.add(p * min);
            }
            q.poll();
        }

        return (int) q.poll().longValue();
    }

    public List<List<String>> findDuplicate(String[] paths) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String path : paths) {
            int blankIdx = path.indexOf(' ');
            int openParIdx = path.indexOf('(');
            int closedParIdx = path.indexOf(')');
            String folderPath = path.substring(0, blankIdx);
            while (openParIdx != -1) {
                String filePath = path.substring(blankIdx + 1, openParIdx);
                String content = path.substring(openParIdx + 1, closedParIdx);
                List<String> list = map.get(content);
                if (list == null) list = new ArrayList<>();
                list.add(folderPath + "/" + filePath);
                map.put(content, list);
                blankIdx = path.indexOf(' ', blankIdx + 1);
                openParIdx = path.indexOf('(', openParIdx + 1);
                closedParIdx = path.indexOf(')', closedParIdx + 1);
            }
        }
        List<List<String>> res = new ArrayList<>();
        for (List<String> value : map.values()) {
            if (value.size() > 1) res.add(value);
        }
        return res;
    }

    public int minPathSum(int[][] grid) {
        return solve(grid, grid.length, grid[0].length, 0, 0);
    }

    private int solve(int[][] g, int m, int n, int i, int j) {
        if (i >= m || j >= n) return Integer.MAX_VALUE;
        if (i == m - 1 && j == n - 1) return g[i][j];
        int res = Integer.MAX_VALUE;
        if (i < m - 1) res = solve(g, m, n, i + 1, j);
        if (j < n - 1) res = Math.min(res, solve(g, m, n, i, j + 1));
        return g[i][j] + res;
    }

    public static void main(String[] args) throws IOException {
        Heaters_475 main = new Heaters_475();
        int grid[][] = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(main.minPathSum(grid));

        System.out.println(main.subarraySum(new int[]{-1, -1, 1}, 1));
    }

    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> freq = new HashMap<>();
        int sum = 0;
        int n = nums.length;
        int sums[] = new int[nums.length];
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            sums[i] = sum;
            Integer val = freq.getOrDefault(sum, 0);
            freq.put(sum, val + 1);
        }
        int count = 0;

        if (k == 0) {
            for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
                int val = entry.getValue();
                if (entry.getKey() == 0) {
                    count += (val + 1) * val / 2;
                } else {
                    count += val - 1;
                }
            }
            return count;
        }
        for (int i = 0; i < n; i++) {
            if (sums[i] == k) count++;
            for (int j = i + 1; j < n; j++) {
                if (sums[j] - sums[i] == k) count++;
            }
        }
        return count;
    }

    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        int m = binaryMatrix.dimensions.get(0);
        int n = binaryMatrix.dimensions.get(1);
        int end = n;
        for (int i = 0; i < m; i++) {
            int idx = binarySearch(binaryMatrix, i, end);
            if (idx == 0) return 0;
            if (idx < end) end = idx;
        }
        return end == n ? -1 : end;
    }

    private int binarySearch(BinaryMatrix bm, int row, int end) {
        int l = 0, h = end;
        while (l != h) {
            int mid = (l + h) / 2;
            int midValue = bm.get(row, mid);
            if (midValue == 0) l = mid + 1;
            else h = mid;
        }
        return h;
    }

    interface BinaryMatrix {
        public int get(int row, int col);

        public List<Integer> dimensions = null;
    }

    public int findJudge(int N, int[][] trust) {
        HashSet<Integer> setArray[] = new HashSet[N];
        for (int i = 0; i < N; i++) {
            setArray[i] = new HashSet<>();
        }

        for (int t[] : trust) {
            setArray[t[0] - 1].add(t[1] - 1);
        }

        int trustIdx = -1;
        for (int i = 0; i < N; i++) {
            HashSet<Integer> set = setArray[i];
            if (set.isEmpty()) {
                if (trustIdx != -1) return -1;
                trustIdx = i;
            }
        }

        for (int i = 0; i < N; i++) {
            if (i != trustIdx) {
                if (!setArray[i].contains(trustIdx)) return -1;
            }
        }
        return trustIdx + 1;
    }
}
