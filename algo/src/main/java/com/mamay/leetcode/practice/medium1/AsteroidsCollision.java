package com.mamay.leetcode.practice.medium1;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Paths;
import java.util.*;

public class AsteroidsCollision {

    public static void main(String[] args) {
        AsteroidsCollision main = new AsteroidsCollision();

        try (Scanner scanner = new Scanner(Paths.get("leetcode\\input.txt"));
             /*BufferedWriter writer = new BufferedWriter(new FileWriter
                     (new File("src\\main\\resources\\output4123.txt")))*/) {
            int q = scanner.nextInt();
            //writer.write(12324);
            scanner.nextLine();
            for (int i = 0; i < q; i++) {
                String str = scanner.next();
                ArrayList<List<Integer>> graph = new ArrayList<>();

                System.out.println(main.smallestStringWithSwaps(str, graph));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

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

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (List<Integer> pair : pairs) {
            int v1 = pair.get(0);
            int v2 = pair.get(1);
            if (v1 == v2) continue;
            graph.get(v1).add(v2);
            graph.get(v2).add(v1);
        }

        ArrayList<ArrayList<Integer>> indexList = new ArrayList<>();
        boolean used[] = new boolean[n];
        Arrays.fill(used, false);

        for (int i = 0; i < n; i++) {
            if (used[i]) continue;

            ArrayList<Integer> list = new ArrayList<>();
            LinkedList<Integer> list1 = new LinkedList<>();
            list1.add(i);
            while (!list1.isEmpty()) {
                LinkedList<Integer> list2 = new LinkedList<>();
                for (int v : list1) {
                    used[v] = true;
                    list.add(v);
                    for (int w : graph.get(v)) {
                        if (used[w]) continue;
                        list2.add(w);

                    }
                }
                list1 = list2;
            }

            indexList.add(list);
        }

        char res[] = new char[n];
        for (ArrayList<Integer> list : indexList) {
            ArrayList<Character> charList = new ArrayList<Character>();
            for (int idx : list) {
                charList.add(s.charAt(idx));
            }

            Collections.sort(list);
            Collections.sort(charList);
            for (int i = 0; i < list.size(); i++) {
                res[list.get(i)] = charList.get(i);
            }
        }

        return new String(res);
    }

    public int findLucky(int[] arr) {
        HashMap<Short, Short> map = new HashMap<>();
        for (int num : arr) {
            short freq = map.getOrDefault((short) num, (short) 0);
            map.put((short) num, (short) (freq + 1));
        }
        int lucky = -1;
        for (HashMap.Entry<Short, Short> e : map.entrySet()) {
            short key = e.getKey();
            if (key == e.getValue()) {
                if (key > lucky) {
                    lucky = key;
                }
            }
        }
        return lucky;
    }

    public int minDeletions(String s) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        for (int num : getChar(s)) {
            queue.add(num);
        }
        int prev = queue.poll();
        int res = 0;
        while (!queue.isEmpty() && prev > 1) {
            int next = queue.poll();
            if (next >= prev) {
                res += (next - prev + 1);
                prev--;
            } else {
                prev = next;
            }
            prev = next;
        }
        while (!queue.isEmpty()) {
            res += queue.poll();
        }
        return res;
    }

    private int[] getChar(String s) {
        int arr[] = new int[26];
        for (char ch : s.toCharArray()) {
            arr[ch - 'a']++;
        }
        return arr;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        String s = new BigInteger(marshal(l1)).add(new BigInteger(marshal(l2))).toString();
        return unmarchal(s);
    }

    private String marshal(ListNode l) {
        StringBuilder builder = new StringBuilder();
        while (l != null) {
            builder.append(l.val);
            l = l.next;
        }
        return builder.toString();
    }

    private ListNode unmarchal(String s) {
        ListNode l = new ListNode(0);
        ListNode current = l;
        for (int i = 0; i < s.length(); i++) {
            current.next = new ListNode(Character.getNumericValue(s.charAt(i)));
            current = current.next;
        }
        return l.next;
    }

    public int minimumDistance2(String word) {
        int len = word.length();
        int dp[][] = new int[len][len];
        for (int i = 0; i < len; i++) {
            dp[0][i] = 0;
        }

        int minimum = 10000;
        for (int k = 1; k < len; k++) {
            char ch = word.charAt(k);
            minimum = 10000;
            for (int i = 0; i < k; i++) {
            }
            System.out.println("Minimum typing " + k + " chars is : " + minimum);
        }

        minimum = 10000;
        for (int i = 0; i < len; i++) {
            if (dp[len - 1][i] < minimum) minimum = dp[len - 1][i];
        }
        return minimum;
    }

    private int weight(String word, int k, int i) {
        int sum = 0;
        for (int j = k; j < i; j++) {
            sum += distance(word.charAt(k) - 'A', word.charAt(i) - 'A');
        }
        return sum;
    }

    public int minimumDistance(String word) {
        int len = word.length();
        int dp[][][] = new int[len + 1][26][26];
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                dp[0][i][j] = 0;
            }
        }

        int minimum = 10000;
        for (int k = 1; k <= len; k++) {
            char ch = word.charAt(k - 1);
            minimum = 10000;
            for (int i = 0; i < 26; i++) {
                int dist1 = distance(ch - 'A', i);
                for (int j = 0; j < 26; j++) {
                    int dist2 = distance(ch - 'A', j);
                    dp[k][i][j] = Math.min(dist1, dist2) + dp[k - 1][i][j];
                    minimum = Math.min(minimum, dp[k][i][j]);
                }
            }

            printMatrix(dp[k]);
            System.out.println("Minimum typing " + k + " chars is : " + minimum);
        }

        minimum = 10000;
        int last = word.charAt(len - 1) - 'A';
        for (int i = 0; i < 26; i++) {
            if (dp[len][last][i] < minimum) minimum = dp[len][last][i];
            if (dp[len][i][last] < minimum) minimum = dp[len][i][last];
        }
        return minimum;
    }

    private void printMatrix(int[][] ints) {
        for (int i = 0; i < ints.length; i++) {
            for (int k = 0; k < ints[i].length; k++) {
                System.out.print(ints[i][k] + " ");
            }
            System.out.println();
        }
        System.out.println("*********************************");
    }

    private int distance(int num1, int num2) {
        int i1 = num1 / 6;
        int i2 = num2 / 6;
        int j1 = num1 % 6;
        int j2 = num2 % 6;
        return Math.abs(i1 - i2) + Math.abs(j1 - j2);
    }

    public int countSubstrings(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        int minLen = Math.min(sLen, tLen);
        int count = 0;
        for (int i = 1; i <= minLen; i++) {
            for (int j = 0; j <= sLen - i; j++) {
                String sSub = s.substring(j, j + i);
                for (int k = 0; k <= tLen - i; k++) {
                    String tSub = t.substring(k, k + i);
                    if (isSub(sSub, tSub)) {
                        System.out.println("Collision between " + sSub + " and " + tSub);
                        count++;
                    }
                }
            }
        }
        System.out.println("##########################");
        return count;
    }

    private boolean isSub(String s, String t) {
        int count = 0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) != t.charAt(i)) {
                count++;
                if (count > 1) return false;
            }
        }
        return count == 1;
    }

    public int[] frequencySort(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 1) return nums;
        int len = nums.length;
        int freq[] = new int[201];
        for (int num : nums) {
            freq[num + 100]++;
        }

        TreeMap<Integer, TreeSet<Integer>> map = new TreeMap<>();
        int res[] = new int[len];
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] == 0) continue;
            TreeSet<Integer> set = map.getOrDefault(freq[i], new TreeSet<>(Comparator.reverseOrder()));
            set.add(i - 100);
            map.put(freq[i], set);
        }
        int idx = 0;
        for (Map.Entry<Integer, TreeSet<Integer>> entry : map.entrySet()) {
            for (int val : entry.getValue()) {
                for (int i = 0; i < entry.getKey(); i++)
                    res[idx++] = val;
            }
        }
        return res;
    }

    public int maxWidthOfVerticalArea(int[][] points) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int p[] : points) {
            set.add(p[0]);
        }
        if (set.size() == 1) return 0;
        ArrayList<Integer> list = new ArrayList<>(set);
        int max = 0;
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i + 1) - list.get(i) > max)
                max = list.get(i + 1) - list.get(i);
        }
        return max;
    }

    public int[] asteroidCollision(int[] asteroids) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int a : asteroids) {
            if (a > 0) deque.addLast(a);
            else if (deque.peek() != null) {
                while (deque.peekLast() != null && deque.peekLast() > 0 && deque.peekLast() + a < 0) {
                    deque.pollLast();
                }
                if (deque.isEmpty()) {
                    deque.addLast(a);
                } else if (deque.peekLast() + a == 0) {
                    deque.pollLast();
                } else if (deque.peekLast() < 0) {
                    deque.addLast(a);
                }
            } else {
                deque.addLast(a);
            }
        }
        int len = deque.size();
        int res[] = new int[len];
        for (int i = 0; i < len; i++) {
            res[i] = deque.pollFirst();
        }
        return res;
    }
}
