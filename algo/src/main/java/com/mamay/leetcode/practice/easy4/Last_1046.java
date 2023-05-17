package com.mamay.leetcode.practice.easy4;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeMap;

public class Last_1046 {
  public int lastStoneWeight(int[] stones) {
    int n = stones.length;
    PriorityQueue<Integer> q = new PriorityQueue<>(n, Collections.reverseOrder());
    for (int s : stones) {
      q.add(s);
    }

    while (q.size() > 1) {
      int x = Math.abs(q.poll() - q.poll());
      if (x != 0) {
        q.add(x);
      }
    }

    if (!q.isEmpty()) {
      return q.poll();
    }
    return 0;
  }

  public int calPoints(String[] ops) {
    ArrayDeque<Integer> d = new ArrayDeque<>();
    for (String op : ops) {
      if ("C".equals(op)) {
        d.pollLast();
      } else if ("D".equals(op)) {
        d.addLast(2 * d.getLast());
      } else if ("+".equals(op)) {
        int v1 = d.pollLast();
        int v2 = d.pollLast();
        d.addLast(v1);
        d.addLast(v2);
        d.addLast(v1 + v2);
      } else {
        d.add(Integer.parseInt(op));
      }
    }
    int sum = 0;
    Integer v1;
    while ((v1 = d.pollLast()) != null) {
      sum += v1;
    }
    return sum;
  }

  public String reverseVowels(String s) {

    char[] arr = s.toCharArray();
    HashSet<Character> set = new HashSet<>();
    set.add('a');
    set.add('e');
    set.add('i');
    set.add('o');
    set.add('u');
    int l = 0;
    int n = arr.length;
    int r = n - 1;
    while (l < r) {
      while (l < n && !set.contains(arr[l])) {
        l++;
      }
      if (l >= r) break;
      while (r >= 0 && !set.contains(arr[r])) {
        r--;
      }
      if (l >= r) break;
      char temp = arr[l];
      arr[l] = arr[r];
      arr[r] = temp;
      l++;
      r--;
    }
    return new String(arr);
  }

  public String getHint(String secret, String guess) {
    int bulls = 0, cows = 0;
    int arr1[] = new int[10];
    int arr2[] = new int[10];

    int len = secret.length();
    for (int i = 0; i < len; i++) {
      int ch1 = Character.getNumericValue(secret.charAt(i));
      int ch2 = Character.getNumericValue(guess.charAt(i));
      if (ch1 == ch2) bulls++;
      else {
        arr1[ch1]++;
        arr2[ch2]++;
      }
    }
    for (int i = 0; i <= 9; i++) {
      cows += Math.min(arr1[i], arr2[i]);
    }
    return bulls + "A" + cows + "B";
  }

  public boolean checkStraightLine(int[][] coordinates) {
    int n = coordinates.length;
    if (n <= 2) return true;

    bubbleSort(coordinates, n);
    int c[][] = coordinates;
    for (int i = 2; i < n - 1; i++) {
      if ((c[i][1] - c[i - 1][1]) * (c[i - 1][0] - c[i - 2][0])
          == (c[i - 1][1] - c[i - 2][1]) * (c[i][0] - c[i - 1][0])) return false;
    }
    return true;
  }

  private void bubbleSort(int arr[][], int n) {
    for (int i = 0; i < n - 1; i++) {
      boolean swapped = false;
      for (int j = 0; j < n - i - 1; j++) {
        if (arr[j][0] > arr[j + 1][0]) {
          int temp[] = arr[j];
          arr[j] = arr[j + 1];
          arr[j + 1] = temp;
          swapped = true;
        }
      }

      if (!swapped) break;
    }
  }

  public String reverseStr(String s, int k) {
    int idx = 0;
    int len = s.length();
    StringBuilder builder = new StringBuilder();
    while (idx < len) {
      StringBuilder temp = new StringBuilder();
      temp.append(s.substring(idx, Math.min(idx + k, len)));
      builder.append(temp.reverse().toString());
      idx += k;
      if (idx < len) {
        builder.append(s.substring(idx, Math.min(idx + k, len)));
        idx += k;
      }
    }
    return builder.toString();
  }

  // 119. Pascal triangle 1
  public List<Integer> getRow(int rowIndex) {
    List<Integer> list = new ArrayList<>();
    int c = 1;
    list.add(c);
    for (int i = 1; i <= rowIndex; i++) {
      c = c * (rowIndex + 1 - i) / i;
      list.add(c);
    }
    return list;
  }

  // 118. Pascal triangle 2
  public List<List<Integer>> generate(int numRows) {
    List<List<Integer>> list = new ArrayList<>();
    if (numRows == 0) return list;
    List<Integer> subList = new ArrayList<>();
    subList.add(1);
    list.add(subList);
    for (int i = 1; i < numRows; i++) {
      subList = new ArrayList<>();
      subList.add(1);
      List<Integer> prevList = list.get(i - 1);
      for (int k = 1; k < i; k++) {
        subList.add(prevList.get(k - 1) + prevList.get(k));
      }
      subList.add(1);
      list.add(subList);
    }
    return list;
  }

  public int minimumTotal(List<List<Integer>> triangle) {
    if (triangle.size() == 1) {
      return triangle.get(0).get(0);
    }
    int prev[] = new int[] {triangle.get(0).get(0)};
    int next[];
    int size = triangle.size();
    for (int i = 1; i < size; i++) {
      next = new int[i + 1];
      next[0] = prev[0] + triangle.get(i).get(0);
      next[i] = prev[i - 1] + triangle.get(i).get(i);
      for (int k = 1; k < i; k++) {
        next[k] = triangle.get(i).get(k) + Math.min(prev[k - 1], prev[k]);
      }
      prev = next;
    }
    int min = prev[0];
    for (int i = 1; i < size; i++) {
      if (min > prev[i]) min = prev[i];
    }
    return min;
  }

  public String customSortString(String S, String T) {
    int i = 0;
    int n2 = T.length();
    char res[] = new char[n2];
    Arrays.fill(res, '0');
    for (char ch : T.toCharArray()) {
      int idx = S.indexOf(ch);
      if (idx != -1) {
        res[idx] = ch;
      } else {
        while (res[i] != '0') i++;
        res[i++] = ch;
      }
    }
    return new String(res);
  }

  public boolean isAdditiveNumber(String S) {
    int len = S.length();
    for (int i = 1; i <= len / 2; i++) {
      String a1str = S.substring(0, i);
      if (a1str.length() > 1 && a1str.charAt(0) == '0') continue;
      long a1 = Long.parseLong(a1str);
      if (a1 >= Integer.MAX_VALUE) break;

      for (int j = 1; j <= (len - i) / 2; j++) {
        String a2str = S.substring(i, i + j);
        if (a2str.length() > 1 && a2str.charAt(0) == '0') continue;
        long a2 = Long.parseLong(a2str);
        if (a2 >= Integer.MAX_VALUE) break;

        if (isFib(a1, a2, S.substring(i + j))) {
          return true;
        }
      }
    }

    return false;
  }

  private boolean isFib(long a1, long a2, String s) {
    long prev = a2;
    int i = 0;
    long next = a1 + a2;
    int len = s.length();
    String nextStr = String.valueOf(next);
    while (i < len && s.startsWith(nextStr, i)) {
      if (next > Integer.MAX_VALUE) return false;
      i += nextStr.length();
      next = next + prev;
      prev = next - prev;
      nextStr = String.valueOf(next);
    }
    return i == len;
  }

  int solve(int[] c) {
    Arrays.sort(c);
    if (c[0] != 0) return -1;
    long res = 1;
    int idx = 0;
    int n = c.length;
    for (int i = 0; i < n; i++) {
      while (idx < n && c[idx] <= i) {
        idx++;
      }
      int num = idx - i;
      if (num <= 0) return -1;
      res = mult(res, num);
    }
    return (int) res;
  }

  long mult(long a, long b) {
    int MODULO = 100000;
    return a * b % MODULO;
  }

  static long lights2(int n) {
    if (n == 1) return 1;
    return BigInteger.valueOf(2)
        .pow(n)
        .subtract(BigInteger.ONE)
        .mod(BigInteger.valueOf(100000))
        .longValue();
  }

  static long lights(int n) {
    if (n == 1) return 1;
    BigInteger[] cache = new BigInteger[n + 1];
    BigInteger sum = BigInteger.valueOf(n + 1);
    cache[1] = BigInteger.ONE;
    for (int i = 2; i <= n; i++) {
      cache[i] = cache[i - 1].multiply(BigInteger.valueOf(i));
    }

    for (int i = 2; i < n; i++) {
      sum = sum.add(cache[n].divide(cache[i]).divide(cache[n - i]));
    }

    sum = sum.mod(BigInteger.valueOf(100000));
    return sum.longValue();
  }

  class FirstUnique {

    private HashSet<Integer> unique = new HashSet<>();
    private HashSet<Integer> duplicated = new HashSet<>();
    private ArrayDeque<Integer> deque = new ArrayDeque<>();

    public FirstUnique(int[] nums) {
      for (int num : nums) add(num);
    }

    public int showFirstUnique() {
      while (!deque.isEmpty()) {
        int value = deque.getFirst();
        if (unique.contains(value)) return value;
        else {
          deque.pollFirst();
        }
      }
      return -1;
    }

    public void add(int value) {
      if (unique.contains(value)) {
        unique.remove(value);
        duplicated.add(value);
      } else if (!duplicated.contains(value)) {
        unique.add(value);
        deque.addLast(value);
      }
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

  private static int[][] readMatrix(Scanner scanner) {
    List<String> lines = new ArrayList<>();
    while (scanner.hasNextLine()) {
      lines.add(scanner.nextLine());
    }
    int len = lines.size();
    int res[][] = new int[len][];
    for (int k = 0; k < len; k++) {
      String tokens[] = lines.get(k).replaceAll("\\[|\\]", "").split(",");
      int len2 = tokens.length;
      res[k] = new int[len2];
      for (int m = 0; m < len2; m++) {
        res[k][m] = Integer.parseInt(tokens[m]);
      }
    }
    System.out.println("Size of matrix is: " + len + " * " + res[0].length);
    return res;
  }

  void printShortestPath(int n, int i_start, int j_start, int i_end, int j_end) {
    // Print the distance along with the sequence of moves.
    int x1 = Math.abs(i_start - i_end);
    int y1 = Math.abs(j_start - j_end);
    if (x1 % 2 == 1) {
      System.out.println("Impossible");
      return;
    }
    if ((y1 - x1 / 2) % 2 == 1) {
      System.out.println("Impossible");
      return;
    }
    int x2 = i_start, y2 = j_start;
    int count = 0;
    StringBuilder b = new StringBuilder();
    StringBuilder b2 = new StringBuilder();
    while (x2 > i_end) {
      if (y2 >= j_end) {
        b.insert(0, "UL ");
        x2 -= 2;
        y2--;
      } else {
        b.append("UR ");
        x2 -= 2;
        y2++;
      }
      count++;
    }

    while (x2 < i_end) {
      if (y2 > j_end) {
        b2.append("LL ");
        x2 += 2;
        y2--;
      } else {
        b2.insert(0, "LR ");
        x2 += 2;
        y2++;
      }
      count++;
    }
    while (y2 < j_end) {
      b.append("R ");
      y2 += 2;
      count++;
    }
    while (y2 > j_end) {
      b2.append("L ");
      y2 -= 2;
      count++;
    }
    System.out.println(count);
    b.append(b2.toString());
    System.out.println(b.toString().trim());
  }

  static int solve2(int[] a) {
    int oddCount = 0;
    int evenCount = 0;
    for (int elem : a) {
      if (elem % 2 == 0) evenCount++;
      else oddCount++;
    }
    BigInteger two = BigInteger.valueOf(2);
    BigInteger mod = BigInteger.valueOf(1000000007);

    if (oddCount == 0) {
      oddCount = 1;
    }
    BigInteger res1 = two.modPow(BigInteger.valueOf(oddCount).subtract(BigInteger.ONE), mod);
    BigInteger res2 = two.modPow(BigInteger.valueOf(evenCount), mod).subtract(BigInteger.ONE);
    BigInteger res3 = res1.abs().subtract(BigInteger.ONE);
    return (int) res1.multiply(res2).add(res3).mod(mod).longValue();
  }

  public int[] createTargetArray(int[] nums, int[] index) {
    int len = nums.length;
    ArrayList<Integer> list = new ArrayList<Integer>(len);
    for (int i = 0; i < len; i++) {
      list.add(index[i], nums[i]);
    }
    for (int i = 0; i < len; i++) {
      nums[i] = list.get(i);
    }
    return nums;
  }

  public boolean checkValidString(String s) {

    int a = 0, b = 0;
    for (char ch : s.toCharArray()) {
      if (ch == '(') a++;
      if (ch == ')') a--;
      if (ch == '*') b++;
      if (a < 0) {
        if (b > 0) {
          b--;
          a++;
        } else return false;
      }
    }
    a = 0;
    b = 0;
    for (int i = s.length() - 1; i >= 0; i--) {
      char ch = s.charAt(i);
      if (ch == '(') a--;
      if (ch == ')') a++;
      if (ch == '*') b++;
      if (a < 0) {
        if (b > 0) {
          b--;
          a++;
        } else return false;
      }
    }
    return true;
  }

  public int rangeBitwiseAnd(int m, int n) {
    if (m == 0) return 0;
    int len1 = Integer.toBinaryString(m).length();
    int len2 = Integer.toBinaryString(n).length();
    if (len1 != len2) return 0;
    int val = (int) Math.pow(2, len1 - 1);
    return val + rangeBitwiseAnd(m - val, n - val);
  }

  public boolean canJump(int[] nums) {
    int len = nums.length;
    if (len == 1) return true;
    boolean used[] = new boolean[len];
    ArrayDeque<Integer> d = new ArrayDeque<>();
    d.add(0);
    while (!d.isEmpty()) {
      int start = d.pollFirst();
      for (int i = 1; i <= nums[start]; i++) {
        int next = start + i;
        if (next == len - 1) return true;
        if (!used[next]) {
          used[next] = true;
          d.add(next);
        }
      }
    }
    return false;
  }

  public int search(int[] nums, int target) {
    int pivotIdx = 0;
    int len = nums.length;
    for (int i = 1; i < len; i++) {
      if (nums[i] < nums[pivotIdx]) pivotIdx = i;
    }

    if (pivotIdx == 0) return Arrays.binarySearch(nums, 0, len, target);
    int idx = Arrays.binarySearch(nums, 0, pivotIdx, target);
    if (idx != -1) return idx;
    idx = Arrays.binarySearch(nums, pivotIdx, len, target);
    if (idx != -1) return idx;
    return -1;
  }

  public List<String> printVertically(String s) {
    String a[] = s.split(" ");
    int maxLen = Arrays.stream(a).map(s1 -> s1.length()).max(Integer::compare).get();
    List<String> res = new ArrayList<String>();
    for (int i = 0; i < maxLen; i++) {
      StringBuilder builder = new StringBuilder();
      for (String elem : a) {
        if (i >= elem.length()) builder.append(" ");
        else builder.append(elem.charAt(i));
      }
      String str = ('#' + builder.toString()).trim().substring(1);
      res.add(str);
    }
    return res;
  }

  public String removeKdigits(String num, int k) {
    if (num.length() == k) return "0";
    int count = 0;
    int i = 0;
    while (i < num.length() - 1 && count < k) {
      if (num.charAt(i) > num.charAt(i + 1)) {
        num = num.substring(0, i) + num.substring(i + 1);
        count++;
        if (i > 0) i--;
      } else {
        i++;
      }
    }
    i = 0;
    while (i < num.length() && num.charAt(i) == '0') i++;
    if (i >= num.length()) return "0";
    num = num.substring(i);
    return num.substring(0, num.length() - k + count);
  }

  public int twoCitySchedCost(int[][] costs) {
    int n = costs.length;
    boolean used[] = new boolean[n];
    int sum = 0;
    Arrays.fill(used, false);
    int minIdx1 = findMinIdx(costs, 0, used);
    int minIdx2 = findMinIdx(costs, 1, used);
    for (int i = 0; i < n; i++) {
      if (costs[minIdx1][0] <= costs[minIdx2][1]) {}
    }
    return sum;
  }

  public String rankTeams(String[] votes) {
    StringBuilder builder = new StringBuilder();
    final int ALPHABET_LENGTH = 26;
    int n = votes[0].length();
    int mas[][] = new int[n][];
    for (int i = 0; i < n; i++) {
      int arr[] = new int[ALPHABET_LENGTH];

      int maxIdx = 0;
      for (int j = 1; j < ALPHABET_LENGTH; j++) {
        if (arr[j] > arr[maxIdx]) maxIdx = arr[j];
        // else if (arr[j] == )
      }
    }
    return builder.toString();
  }

  int findMinIdx(int[][] costs, int i, boolean used[]) {
    int minIdx = 0;
    while (used[minIdx]) minIdx++;
    for (int k = minIdx; k < costs.length; k++) {
      if (!used[k] && costs[k][i] < costs[minIdx][i]) minIdx = k;
    }
    return minIdx;
  }

  public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
    paint(image, sr, sc, newColor, image.length, image[0].length, image[sr][sc]);
    return image;
  }

  private void paint(int[][] image, int sr, int sc, int newColor, int m, int n, int parentColor) {
    if (sr >= m || sr < 0 || sc >= n || sc < 0) return;
    if (image[sr][sc] != parentColor) return;
    image[sr][sc] = newColor;
    paint(image, sr + 1, sc, newColor, m, n, parentColor);
    paint(image, sr, sc + 1, newColor, m, n, parentColor);
    paint(image, sr - 1, sc, newColor, m, n, parentColor);
    paint(image, sr, sc - 1, newColor, m, n, parentColor);
  }

  public final TreeNode getTargetCopy(
      final TreeNode original, final TreeNode cloned, final TreeNode target) {
    if (cloned == null) return null;
    if (cloned.val == target.val) return cloned;
    TreeNode node = getTargetCopy(original, cloned.left, target);
    if (node != null) return node;
    return getTargetCopy(original, cloned.right, target);
  }

  private int sumEven;

  public int sumEvenGrandparent(TreeNode root) {
    sumEven = 0;
    traverse(root);
    return sumEven;
  }

  private void traverse(TreeNode root) {
    if (root == null) return;
    if (root.val % 2 == 0) sumEven += grandparentsSum(root);
    traverse(root.left);
    traverse(root.right);
  }

  private int grandparentsSum(TreeNode root) {
    int localSum = 0;
    if (root.left != null) {
      if (root.left.left != null) {
        localSum += root.left.left.val;
      }
      if (root.left.right != null) {
        localSum += root.left.right.val;
      }
    }
    if (root.right != null) {
      if (root.right.left != null) {
        localSum += root.right.left.val;
      }
      if (root.right.right != null) {
        localSum += root.right.right.val;
      }
    }
    return localSum;
  }

  private List<TreeNode> dpList[];

  public List<TreeNode> allPossibleFBT(int N) {
    if (N % 2 == 0) return new ArrayList<>();
    dpList = new ArrayList[N + 1];
    return construct(N);
  }

  private List<TreeNode> construct(int N) {

    if (dpList[N] != null) return dpList[N];

    List<TreeNode> list = new ArrayList<>();
    if (N == 1) {
      list.add(new TreeNode(0));
    } else {
      for (int i = 1; i < N / 2; i += 2) {
        List<TreeNode> leftNodeList = construct(i);
        List<TreeNode> rightNodeList = construct(N - i - 1);
        for (TreeNode leftNode : leftNodeList) {
          for (TreeNode rightNode : rightNodeList) {
            list.add(new TreeNode(0, leftNode, rightNode));
            list.add(new TreeNode(0, rightNode, leftNode));
          }
        }
      }

      if (N / 2 % 2 == 1) {
        List<TreeNode> nodeList = construct(N / 2);
        for (TreeNode leftNode : nodeList) {
          for (TreeNode rightNode : nodeList) {
            list.add(new TreeNode(0, leftNode, rightNode));
          }
        }
      }
    }

    dpList[N] = list;
    return dpList[N];
  }

  class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(int x) {
      val = x;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }

  class StockSpanner {

    private List<Integer> priceList = new ArrayList<>();
    private ArrayDeque<Integer> resList = new ArrayDeque<>();

    public StockSpanner() {}

    public int next(int price) {
      priceList.add(price);
      int size = priceList.size();
      if (size == 1) {
        resList.add(1);
      } else {
        int prev = priceList.get(size - 2);
        if (price < prev) {
          resList.add(1);
        } else if (price == prev) {
          resList.add(resList.peekLast() + 1);
        } else {
          int count = resList.peekLast() + 1;
          for (int i = size - 1 - count; i >= 0; i--) {
            if (priceList.get(i) <= price) count++;
            else break;
          }
          resList.add(count);
        }
      }
      return resList.peekLast();
    }
  }

  private int count = 0;

  public int kthSmallest(TreeNode root, int k) {
    if (root == null) return -1;
    int leftNode = kthSmallest(root.left, k);
    if (leftNode != -1) return leftNode;
    if (++count == k) return root.val;
    return kthSmallest(root.right, k);
  }

  // 1305
  private List<Integer> inorder(TreeNode root) {
    LinkedList<Integer> resList = new LinkedList<>();
    LinkedList<TreeNode> linkedList = new LinkedList<TreeNode>();
    TreeNode currentNode = root;

    if (root == null) return resList;

    while (currentNode != null || !linkedList.isEmpty()) {
      while (currentNode != null) {
        linkedList.push(currentNode);
        currentNode = currentNode.left;
      }
      currentNode = linkedList.pop();
      resList.add(currentNode.val);
      currentNode = currentNode.right;
    }
    return resList;
  }

  public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
    List<Integer> list1 = inorder(root1);
    List<Integer> list2 = inorder(root2);
    int idx1 = 0, idx2 = 0;
    int sz1 = list1.size(), sz2 = list2.size();
    if (sz2 == 0) return list1;
    if (sz1 == 0) return list2;

    List<Integer> resList = new ArrayList<>(list1.size() + list2.size());
    while (idx1 < sz1 || idx2 < sz2) {
      if (list1.get(idx1) <= list2.get(idx2)) {
        resList.add(list1.get(idx1++));
        if (idx1 == sz1) {
          while (idx2 < sz2) {
            resList.add(list2.get(idx2++));
          }
        }
      } else {
        resList.add(list2.get(idx2++));
        if (idx2 == sz2) {
          while (idx1 < sz1) {
            resList.add(list1.get(idx1++));
          }
        }
      }
    }
    return resList;
  }

  public static void main(String[] args) throws Exception {
    Path inputPath = Paths.get("leetcode", "src", "main", "resources", "input.txt");
    Path outputPath = Paths.get("leetcode", "src", "main", "resources", "output.txt");
    Last_1046 main = new Last_1046();
    try (Scanner scanner = new Scanner(inputPath)) {
      int q = scanner.nextInt();
      scanner.nextLine();

      try (PrintWriter writer = new PrintWriter(new FileWriter(outputPath.toFile()))) {
        for (int qi = 0; qi < q; qi++) {
          int[][] graph = readMatrix(scanner);
          List<List<Integer>> resList = main.allPathsSourceTarget(graph);
          for (List<Integer> list : resList) {}
        }
      }

      String[] folder = scanner.nextLine().replaceAll("\\[|\\]|\"", "").split(",");
      long startTime = System.currentTimeMillis();
      System.out.println(main.removeSubfolders(folder));
      long finishTime = System.currentTimeMillis();
      System.out.println("Time " + (finishTime - startTime));
      System.out.println(main.removeSubfoldersSorted(folder));
      System.out.println("Time " + (System.currentTimeMillis() - finishTime));
    }
  }

  public List<String> removeSubfoldersSorted(String[] folder) {
    int n = folder.length;
    LinkedList<String> folderResList = new LinkedList<>();

    Arrays.sort(folder);
    int start = 0;
    String currentStr = folder[0];
    folderResList.add(currentStr);
    while (start <= n - 2) {

      int i = start + 1;
      while (i <= n - 1) {
        if (!(folder[i] + "/").startsWith((currentStr + "/"))) {
          break;
        }
        i++;
      }

      start = i;
      if (start <= n - 1) {
        currentStr = folder[start];
        folderResList.add(currentStr);
      }
    }
    return folderResList;
  }

  public List<String> removeSubfolders(String[] folder) {
    int len = folder.length;
    ArrayList<String> list = new ArrayList<>(Arrays.asList(folder));
    Collections.sort(list);
    for (int i = 0; i < list.size() - 1; i++) {
      String a = list.get(i);
      for (int j = i + 1; j < list.size(); j++) {
        String b = list.get(j);
        if ((b + "/").startsWith(a + "/")) {
          list.remove(b);
          j--;
        }
        if ((a + "/").startsWith(b + "/")) {
          list.remove(a);
          i--;
          break;
        }
      }
    }
    /*ArrayList<String> res = new ArrayList<String>();
    for (String subFolder : folder) {
        if (subFolder != null) res.add(subFolder);
    }*/
    return list;
  }

  List<List<Integer>> resList = new LinkedList<List<Integer>>();

  public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
    dfs(graph, 0, new LinkedList<Integer>());
    return resList;
  }

  private void dfs(int[][] graph, int s, List<Integer> list) {
    list.add(s);
    int n = graph.length;
    if (s == n - 1) {
      resList.add(new LinkedList<>(list));
    }

    for (int v : graph[s]) {
      if (s != v) dfs(graph, v, list);
    }

    list.remove((Integer) s);
  }

  private HashMap<BrickNode, Integer> brickCache;

  public int furthestBuilding(int[] heights, int bricks, int ladders) {
    brickCache = new HashMap<>();
    return solve(heights, 0, bricks, ladders);
  }

  private int solve(int[] heights, int pos, int bricks, int ladders) {
    final BrickNode brickNode = new BrickNode(bricks, ladders, pos);
    if (brickCache.containsKey(brickNode)) {
      return brickCache.get(brickNode);
    }
    int n = heights.length;
    int i = pos;
    while (i < n - 1 && heights[i] >= heights[i + 1]) {
      i++;
    }
    if (i == n - 1) {
      setCache(brickNode, n - 1);
      // brickCache.put(brickNode, n - 1);
      return n - 1;
    }

    int val = i;
    if (ladders > 0) {
      val = solve(heights, i + 1, bricks, ladders - 1);
    }
    int diffBrick = bricks - heights[i + 1] + heights[i];
    if (diffBrick >= 0) {
      val = Math.max(val, solve(heights, i + 1, diffBrick, ladders));
    }
    setCache(brickNode, n - 1);
    // brickCache.put(brickNode, val);
    return val;
  }

  private void setCache(BrickNode brickNode, int val) {
    System.out.println("Setting val " + val + " for pos: " + brickNode.pos);
    brickCache.put(brickNode, val);
  }

  class BrickNode {
    int b, l, pos;

    public BrickNode(int b, int l, int pos) {
      this.b = b;
      this.l = l;
      this.pos = pos;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      BrickNode brickNode = (BrickNode) o;

      if (b != brickNode.b) return false;
      if (l != brickNode.l) return false;
      return pos == brickNode.pos;
    }

    @Override
    public int hashCode() {
      int result = b;
      result = 31 * result + l;
      result = 31 * result + pos;
      return result;
    }
  }

  public int maxDistToClosest(int[] seats) {
    int n = seats.length;
    int left[] = new int[n];
    int right[] = new int[n];
    Arrays.fill(left, n);
    Arrays.fill(right, n);

    for (int i = 0; i < n; i++) {
      if (seats[i] == 1) {
        left[i] = 0;
      } else if (i > 0) {
        left[i] = left[i - 1] + 1;
      }

      if (seats[n - 1 - i] == 1) {
        right[n - 1 - i] = 0;
      } else if (i > 0) {
        right[n - 1 - i] = right[n - i] + 1;
      }
    }

    int res = 0;
    for (int i = 0; i < n; i++) {
      res = Math.max(res, Math.min(left[i], right[i]));
    }
    return res;
  }

  int maxV[][];
  int dp[][];

  public int mctFromLeafValues(int[] arr) {
    int n = arr.length;
    dp = new int[n][n];
    maxV = new int[n][n];
    int res = solve(arr, 0, n - 1);
    return res;
  }

  int solve(int[] arr, int st, int end) {
    if (dp[st][end] != 0) return dp[st][end];

    if (st == end) {
      maxV[st][st] = arr[st];
      dp[st][end] = 0;
      return 0;
    }
    int minSum = Integer.MAX_VALUE;
    for (int i = st + 1; i <= end; i++) {
      int sum = solve(arr, st, i - 1) + solve(arr, i, end) + maxV[st][i - 1] * maxV[i][end];
      if (sum < minSum) {
        minSum = sum;
        maxV[st][end] = Math.max(maxV[st][i - 1], maxV[i][end]);
      }
    }
    dp[st][end] = minSum;
    return minSum;
  }

  public boolean isTransformable(String s, String t) {
    int arr1[] = sToArray(s);
    int arr2[] = sToArray(t);
    if (!Arrays.equals(arr1, arr2)) return false;

    for (int i = 2; i <= s.length(); i++) {
      HashMap<Byte, ArrayList<Byte>> a1 = convert(s.substring(0, i));
      HashMap<Byte, ArrayList<Byte>> a2 = convert(t.substring(0, i));

      for (HashMap.Entry<Byte, ArrayList<Byte>> entry2 : a2.entrySet()) {
        if (!containsAll(a1.get(entry2.getKey()), entry2.getValue())) {
          return false;
        }
      }
    }

    return true;
  }

  private boolean containsAll(ArrayList<Byte> list1, ArrayList<Byte> list2) {
    if (list1 == null || list1.size() < list2.size()) return false;

    int arr[] = new int[10];
    for (Byte val : list1) {
      arr[val]++;
    }

    for (Byte val : list2) {
      arr[val]--;
      if (arr[val] < 0) return false;
    }
    return true;
  }

  HashMap<Byte, ArrayList<Byte>> convert(String s) {
    HashMap<Byte, ArrayList<Byte>> map = new HashMap<>();
    int len = s.length();
    for (int i = 0; i < len - 1; i++) {
      byte ival = (byte) Character.getNumericValue(s.charAt(i));
      for (int j = i + 1; j < len; j++) {
        byte jval = (byte) Character.getNumericValue(s.charAt(j));
        if (ival > jval) {
          ArrayList<Byte> value = map.getOrDefault(ival, new ArrayList<>());
          value.add(jval);
          map.put(ival, value);
        }
      }
    }
    return map;
  }

  int[] sToArray(String s) {
    int arr[] = new int[10];
    for (char ch : s.toCharArray()) {
      arr[ch - '0']++;
    }
    return arr;
  }

  public int[] bestCoordinate(int[][] towers, int radius) {

    int maxXY[] = new int[2];
    double maxSignal = -1;
    for (int xi = 0; xi <= 50; xi++) {
      for (int yi = 0; yi <= 50; yi++) {
        double sum = 0;
        for (int tower[] : towers) {
          sum =
              sum
                  + (int)
                      (((double) tower[2])
                          / (1
                              + Math.sqrt(
                                  (xi - tower[0]) * (xi - tower[0])
                                      + (yi - tower[1]) * (yi - tower[1]))));
        }
        if (sum > maxSignal) {
          System.out.println(sum + " at " + xi + " : " + yi);
          maxSignal = sum;
          maxXY = new int[] {xi, yi};
        }
      }
    }
    return maxXY;
  }

  public void rotate(int[] nums, int k) {
    int n = nums.length;
    int res[] = new int[n];
    k = k % n;
    for (int i = 0; i < n; i++) {
      int idx = (i + n - k) % n;
      res[i] = nums[idx];
    }
    nums = res;
    System.out.println(Arrays.toString(nums));
  }

  public int arrayNesting(int[] nums) {
    int max = 0;
    int i = 0;
    int len = nums.length;
    boolean used[] = new boolean[len];
    while (i < len) {
      if (used[i]) {
        i++;
        continue;
      }
      int prev = i;
      used[prev] = true;
      int next = nums[i];
      int m = 1;
      while (nums[prev] != nums[next] && !used[next]) {
        used[next] = true;
        m++;
        next = nums[next];
      }
      if (m > max) max = m;
      i++;
    }
    return max;
  }

  public int findMinFibonacciNumbers(int n) {
    int fibCache[] = new int[46];
    fibCache[0] = 1;
    fibCache[1] = 1;
    for (int i = 2; i < fibCache.length; i++) {
      fibCache[i] = fibCache[i - 1] + fibCache[i - 2];
    }
    int count = 0;
    int right = 45;
    while (n != 0) {
      int left = 1;
      while (left != right) {
        int mid = (left + right + 1) / 2;
        if (fibCache[mid] == n) {
          left = mid;
          right = mid;
        } else if (fibCache[mid] < n) {
          left = mid;
        } else {
          right = mid - 1;
        }
      }
      n -= fibCache[left];
      count++;
    }
    return count;
  }

  private boolean isFibonacci(int n) {
    long d = 5 * ((long) n) * n + 4;
    long s = (long) Math.sqrt(d);
    if (s * s == d) return true;
    d -= 8;
    s = (long) Math.sqrt(d);
    return s * s == d;
  }

  public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
    int RC = R * C;
    int res[][] = new int[RC][2];
    int len = Math.max(r0, R - r0 - 1) + Math.max(c0, C - c0 - 1);
    int idx = 0;
    res[idx++] = new int[] {r0, c0};
    for (int i = 1; i <= len; i++) {
      for (int j = 0; j < i; j++) {
        if (isValid(R, C, r0 - (i - j), c0 + j)) {
          res[idx++] = new int[] {r0 - (i - j), c0 + j};
        }

        if (isValid(R, C, r0 + j, c0 + (i - j))) {
          res[idx++] = new int[] {r0 + j, c0 + (i - j)};
        }

        if (isValid(R, C, r0 + (i - j), c0 - j)) {
          res[idx++] = new int[] {r0 + (i - j), c0 - j};
        }

        if (isValid(R, C, r0 - j, c0 - (i - j))) {
          res[idx++] = new int[] {r0 - j, c0 - (i - j)};
        }
      }
    }
    return res;
  }

  private boolean isValid(int R, int C, int i, int j) {
    return i >= 0 && i < R && j >= 0 && j < C;
  }

  class RecentCounter {

    ArrayDeque<Integer> deque;

    public RecentCounter() {
      deque = new ArrayDeque<>();
    }

    public int ping(int t) {
      while (deque.peekFirst() != null && deque.peekFirst() < t - 3000) {
        deque.pollFirst();
      }
      deque.addLast(t);
      return deque.size();
    }
  }

  public boolean canThreePartsEqualSum(int[] A) {
    int sum = Arrays.stream(A).sum();
    int n = A.length;
    if (sum % 3 != 0) return false;
    int start = 0;
    int sum1 = A[start++];
    while (start < n && sum1 != sum / 3) {
      sum1 += A[start++];
    }
    if (start == n && sum1 != sum / 3) return false;
    int end = n - 1;
    sum1 = A[end--];
    while (end >= start && sum1 != sum / 3) {
      sum1 += A[end--];
    }
    return sum1 == sum / 3 && end >= start;
  }

  public List<Boolean> prefixesDivBy5(int[] A) {
    ArrayList<Boolean> res = new ArrayList<Boolean>();
    BigInteger num = BigInteger.ZERO;
    final BigInteger FIVE = BigInteger.valueOf(5);
    final BigInteger ZERO = BigInteger.ZERO;
    for (int a : A) {
      num = num.multiply(BigInteger.valueOf(2L)).add(BigInteger.valueOf(a));
      res.add(num.mod(FIVE).compareTo(ZERO) == 0);
    }
    return res;
  }

  public String thousandSeparator(int n) {
    String str = String.valueOf(n);
    int len = str.length();
    int a = len / 3;
    int b = len % 3;
    StringBuilder builder = new StringBuilder();
    if (b != 0) builder.append(str.substring(0, b));
    for (int i = 0; i < a; i++) {
      if (i != 0 || b != 0) builder.append('.');
      int startIdx = b + i * 3;
      builder.append(str.substring(startIdx, startIdx + 3));
    }
    return builder.toString();
  }

  private void solve11() {
    TreeNode root1 = new TreeNode(2);
    root1.left = new TreeNode(1);
    root1.right = new TreeNode(4);

    TreeNode root2 = new TreeNode(1);
    root2.left = new TreeNode(0);
    root2.right = new TreeNode(3);

    System.out.println(getAllElements(root1, root2));
  }

  public TreeNode removeLeafNodes(TreeNode root, int target) {
    if (root == null || (root.left == null && root.right == null && root.val == target))
      return null;

    root.left = removeLeafNodes(root.left, target);
    root.right = removeLeafNodes(root.right, target);

    if (root.left == null && root.right == null && root.val == target) return null;

    return root;
  }

  public int[] findRedundantConnection(int[][] edges) {
    HashSet<Integer> set = new HashSet<>();
    int size = edges.length;
    boolean used[] = new boolean[size];
    int i = 1;
    set.add(edges[0][0]);
    set.add(edges[0][1]);
    while (i < size) {
      if (used[i]) {
        i++;
        continue;
      }
      for (int k = i; k < size; k++) {
        if (used[k]) continue;
        if (set.contains(edges[k][0]) && set.contains(edges[k][1])) return edges[k];
        else if (set.contains(edges[k][0])) {
          set.add(edges[k][1]);
          used[k] = true;
          break;
        } else if (set.contains(edges[k][1])) {
          set.add(edges[k][0]);
          used[k] = true;
          break;
        }
      }
    }
    return null;
  }

  public String frequencySort(String s) {
    HashMap<Character, Integer> map1 = new HashMap<>();
    for (char ch : s.toCharArray()) {
      Integer val = map1.getOrDefault(ch, 0);
      map1.put(ch, val + 1);
    }
    StringBuilder builder = new StringBuilder();
    TreeMap<Integer, List<Character>> treeMap = new TreeMap<>(Comparator.reverseOrder());
    for (Map.Entry<Character, Integer> entry : map1.entrySet()) {
      List<Character> list = treeMap.getOrDefault(entry.getValue(), new ArrayList<>());
      list.add(entry.getKey());
      treeMap.put(entry.getValue(), list);
    }
    for (Map.Entry<Integer, List<Character>> entry : treeMap.entrySet()) {
      for (char ch : entry.getValue()) {
        for (int i = 0; i < entry.getKey(); i++) {
          builder.append(ch);
        }
      }
    }
    return builder.toString();
  }

  public int maxUncrossedLines(int[] A, int[] B) {
    return 0;
  }

  /*public boolean possibleBipartition(int N, int[][] dislikes) {
      int size = dislikes.length;
      if (size <= 1) return true;
      int arr[] = new int[N + 1];
      Arrays.fill(arr, 0);
      for (int[] dislike : dislikes) {
          int u = dislike[0];
          int v = dislike[1];
          if (arr[u] != 0 && arr[v] != 0) {
              int p1 = getParent(arr, u);
              int p2 = getParent(arr, v);
              if (p1 == p2) return false;
              else {
                  arr[p2] = p1;
              }
          } else if (arr[u] != 0) {

          } else if (arr[v] != 0) {

          } else {
              arr[u] = u;
              arr[v] = v;
          }
      }
  }*/

  private int countPalindromes;

  public int pseudoPalindromicPaths(TreeNode root) {
    countPalindromes = 0;
    traverse(root, new ArrayList<>());
    return countPalindromes;
  }

  private void traverse(TreeNode root, ArrayList<Integer> list) {
    if (root == null) return;
    list.add(root.val);
    if (root.left == null && root.right == null && isPalindrome(list)) count++;
    traverse(root.left, list);
    traverse(root.right, list);
  }

  private boolean isPalindrome(ArrayList<Integer> list) {
    int arr[] = new int[9];
    for (int v : list) {
      arr[v - 1]++;
    }
    boolean isOdd = false;
    for (int v : arr) {
      if (v % 2 == 1) {
        if (isOdd) return false;
        isOdd = true;
      }
    }
    return true;
  }

  private boolean usedVertices[];
  private boolean recursiveStack[];
  private ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();

  public boolean canFinish(int numCourses, int[][] prerequisites) {
    adjList = new ArrayList<>(numCourses);
    for (int i = 0; i < numCourses; i++) {
      adjList.add(new ArrayList<>());
    }
    for (int p[] : prerequisites) {
      adjList.get(p[0]).add(p[1]);
    }

    usedVertices = new boolean[numCourses];
    recursiveStack = new boolean[numCourses];
    Arrays.fill(usedVertices, false);
    Arrays.fill(recursiveStack, false);

    for (int i = 0; i < numCourses; i++) {
      if (isCycle(i)) return true;
    }
    return false;
  }

  boolean isCycle(int start) {
    if (recursiveStack[start]) return true;
    if (usedVertices[start]) return false;
    usedVertices[start] = true;
    recursiveStack[start] = true;

    for (int v : adjList.get(start)) {
      if (isCycle(v)) return true;
    }

    recursiveStack[start] = false;
    return false;
  }

  int arithmeticSum(int value, int multiplier) {
    int n = (value - 1) / multiplier;
    return (2 * multiplier + multiplier * (n - 1)) * n / 2;
  }

  public int maxCoins(int[] piles) {
    PriorityQueue<Integer> queue = new PriorityQueue<>(piles.length, Comparator.reverseOrder());
    for (int pile : piles) {
      queue.add(pile);
    }
    int sum = 0;
    for (int i = 0; i < piles.length / 3; i++) {
      queue.poll();
      sum += queue.poll();
    }
    return sum;
  }
}
