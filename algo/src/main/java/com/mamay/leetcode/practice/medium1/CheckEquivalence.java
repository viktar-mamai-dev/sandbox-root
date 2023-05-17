package com.mamay.leetcode.practice.medium1;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CheckEquivalence {

    private int[] binaryExpressionArr;

    public boolean checkEquivalence(Node root1, Node root2) {
        final int LEN = 26;
        binaryExpressionArr = new int[LEN];
        Arrays.fill(binaryExpressionArr, 0);
        traverseBinaryExpressionTree(root1);
        int[] arr = new int[LEN];
        System.arraycopy(binaryExpressionArr, 0, arr, 0, LEN);
        Arrays.fill(binaryExpressionArr, 0);
        traverseBinaryExpressionTree(root2);
        return Arrays.equals(binaryExpressionArr, arr);
    }

    private void traverseBinaryExpressionTree(Node root) {
        if (root == null) return;
        if (isLeaf(root)) {
            binaryExpressionArr[root.val - 'a']++;
            return;
        }
        traverseBinaryExpressionTree(root.left);
        traverseBinaryExpressionTree(root.right);
    }

    private boolean isLeaf(Node root) {
        return root.left == null && root.right == null;
    }

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        HashSet<Integer> hashSet = Arrays.stream(to_delete).boxed().collect(Collectors.toCollection(HashSet::new));
        return dfsDelNodes(root, hashSet);
    }

    private List<TreeNode> dfsDelNodes(TreeNode root, HashSet<Integer> set) {
        List<TreeNode> resList = new LinkedList<>();
        if (set.contains(root.val)) {
            set.remove(root.val);
            if (root.left != null) {
                resList.addAll(dfsDelNodes(root.left, set));
            }
            if (root.right != null) {
                resList.addAll(dfsDelNodes(root.right, set));
            }
            return resList;
        }
        resList.add(root);
        if (root.left != null && set.contains(root.left.val)) {
            TreeNode left = root.left;
            root.left = null;
            resList.addAll(dfsDelNodes(left, set));
        }
        if (root.right != null && set.contains(root.right.val)) {
            TreeNode right = root.right;
            root.right = null;
            resList.addAll(dfsDelNodes(right, set));
        }
        return resList;
    }

    public int[][] findFarmland(int[][] land) {
        List<int[]> resultList = new ArrayList<>();
        for (int i = 0; i < land.length; i++)
            for (int j = 0; j < land[0].length; j++)
                if (land[i][j] == 1 && (i == 0 || land[i - 1][j] == 0) && (j == 0 || land[i][j - 1] == 0)) {
                    int i2 = i, j2 = j;
                    while (i2 < land.length && land[i2][j] == 1) i2++;
                    while (j2 < land[0].length && land[i][j2] == 1) j2++;
                    resultList.add(new int[]{i, j, i2 - 1, j2 - 1}); // we can have j = j2-1; after here as improvement
                }
        return resultList.toArray(new int[0][]);
    }

    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        Collections.fill(graph, new ArrayList<>());
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        short[] used = new short[n];
        Arrays.fill(used, (short) 0); // 0 not processed, 1 - in queue, 2 - processed
        ArrayDeque<GraphNode> deque = new ArrayDeque<>();
        GraphNode root = new GraphNode(0);
        deque.add(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                GraphNode vNode = deque.pollFirst();
                int v = vNode.value;
                used[v] = (short) 2;
                if (hasApple.get(v)) {
                    vNode.isApple = true;
                }
                for (int w : graph.get(v)) {
                    if (used[w] != 0) continue;
                    GraphNode wNode = new GraphNode(w);
                    vNode.childNodes.add(wNode);
                    deque.addLast(wNode);
                    used[w] = (short) 1;
                }
            }
        }
        return 2 * traverseAppleTree(root);
    }

    private int traverseAppleTree(GraphNode root) {
        if (root == null) return 0;
        int totalNodeTime = 0;
        for (GraphNode childNode : root.childNodes) {
            int childTime = traverseAppleTree(childNode);
            if (childTime > 0) {
                totalNodeTime += (1 + childTime);
            } else if (childNode.isApple) {
                totalNodeTime++;
            }
        }
        return totalNodeTime;
    }

    private static class GraphNode {
        int value;
        List<GraphNode> childNodes = new ArrayList<>();
        boolean isApple = false;

        public GraphNode(int value) {
            this.value = value;
        }
    }

    private double maxAverageSubtreeValue;

    public double maximumAverageSubtree(TreeNode root) {
        maxAverageSubtreeValue = 0;
        dfsMaximumAverageSubtree(root);
        return maxAverageSubtreeValue;
    }

    public int[] dfsMaximumAverageSubtree(TreeNode root) {
        if (root == null) return new int[]{0, 0};
        int[] right = dfsMaximumAverageSubtree(root.right);
        int[] left = dfsMaximumAverageSubtree(root.left);
        int sum = root.val + right[0] + left[0];
        int count = 1 + right[1] + left[1];
        maxAverageSubtreeValue = Math.max(maxAverageSubtreeValue, (double) sum / count);
        return new int[]{sum, count};
    }

    public List<List<String>> printTree(TreeNode root) {
        int height = calcHeight(root);
        int power = (int) Math.pow(2, height);
        String[][] arr = new String[height][2 * power - 1];
        traverse(root, 0, power - 1, arr, power);
        return Arrays.stream(arr).map(Arrays::asList).collect(Collectors.toList());
    }

    private void traverse(TreeNode root, int row, int col, String[][] arr, int power) {
        if (root == null) return;
        arr[row][col] = String.valueOf(root.val);
        power /= 2;
        traverse(root.left, row + 1, col - power, arr, power);
        traverse(root.left, row + 1, col + power, arr, power);
    }

    private int calcHeight(TreeNode root) {
        if (root == null) return 0;
        return Math.max(calcHeight(root.right), calcHeight(root.left)) + 1;
    }

    public int deleteTreeNodes(int nodes, int[] parent, int[] value) {
        HashMap<Integer, SumNode> map = new HashMap<>();
        int n = parent.length;
        SumNode root = new SumNode(value[0]);
        map.put(0, root);
        for (int i = 1; i < n; i++) {
            SumNode node = new SumNode(value[i]);
            SumNode parentNode = map.get(parent[i]);
            parentNode.childNodes.add(node);
            map.put(i, node);
        }
        propagateSum(root);
        if (root.sum == 0) return 0;
        delete0subTrees(root);
        return countNodes(root);
    }

    private int countNodes(SumNode root) {
        if (root == null) return 0;
        return 1 + root.childNodes.stream().mapToInt(this::countNodes).sum();
    }

    private void delete0subTrees(SumNode root) {
        if (root == null) return;
        Iterator<SumNode> iterator = root.childNodes.iterator();
        while (iterator.hasNext()) {
            SumNode childNode = iterator.next();
            if (childNode.sum == 0) iterator.remove();
            else delete0subTrees(childNode);
        }
    }

    private long propagateSum(SumNode root) {
        if (root == null) return 0;
        long sum = root.childNodes.stream().mapToLong(this::propagateSum).sum();
        root.sum += sum;
        return root.sum;
    }

    public int swimInWater(int[][] grid) {
        int N = grid.length;
        int low = grid[0][0], high = N * N;
        while (low < high) {
            int middle = low + (high - low) / 2;
            if (!possible(middle, grid)) {
                low = middle + 1;
            } else {
                high = middle;
            }
        }
        return low;
    }

    final int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public boolean possible(int T, int[][] grid) {
        GridPair.gridSize = grid.length;
        GridPair zeroPair = new GridPair(0, 0);

        GridSet gridSet = new GridSet();
        gridSet.add(zeroPair);

        while (!gridSet.isEmpty()) {
            GridPair currentPair = gridSet.pop();
            if (currentPair.isRightBottom()) return true;

            for (int[] dir : dirs) {
                GridPair nextPair = new GridPair(currentPair.row + dir[0], currentPair.col + dir[1]);
                if (nextPair.isValid() && !gridSet.contains(nextPair) && getValue(grid, nextPair) <= T) {
                    gridSet.add(nextPair);
                }
            }
        }

        return false;
    }

    private List<Integer> traversalList;

    public int[] findMode(TreeNode root) {
        traversalList = new LinkedList<>();
        traverse(root);
        Map<Integer, Long> map = traversalList.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        List<Integer> maxValueList = new LinkedList<>();
        int max = 0;
        for (int key : map.keySet()) {
            long value = map.get(key);
            if (value > max) {
                max = (int) value;
                maxValueList = new LinkedList<>();
                maxValueList.add(key);
            } else if (value == max) {
                maxValueList.add(key);
            }
        }
        return maxValueList.stream().mapToInt(Integer::intValue).toArray();
    }

    private void traverse(TreeNode root) {
        if (root == null) return;
        traversalList.add(root.val);
        traverse(root.left);
        traverse(root.right);
    }

    public int numSimilarGroups(String[] strs) {
        int len = strs.length;
        UnionFind unionFind = new UnionFind(len);
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (isSimilar(strs[i], strs[j])) {
                    unionFind.union(i, j);
                }
            }
        }
        return (int) IntStream.range(0, len).filter(unionFind::isRoot).count();
    }

    private boolean isSimilar(String str1, String str2) {
        if (str1.equals(str2)) return true;
        int diffCount = 0, len = str1.length();
        char diffCh1 = '*', diffCh2 = '*';
        for (int i = 0; i < len; i++) {
            if (str1.charAt(i) == str2.charAt(i)) continue;
            if (++diffCount > 2) return false;
            if (diffCount == 1) {
                diffCh1 = str1.charAt(i);
                diffCh2 = str2.charAt(i);
            } else if (str1.charAt(i) != diffCh2 || str2.charAt(i) != diffCh1) {
                return false;
            }
        }
        return diffCount != 1;
    }

    private LinkedList<String> expandList;
    public String[] expand(String s) {
        expandList = new LinkedList<>();
        recExpand(s, 0, new StringBuilder());
        return expandList.toArray(new String[expandList.size()]);
    }

    private void recExpand(String str, int idx, StringBuilder builder) {
        int builderLen = builder.length();
        while (idx < str.length() && str.charAt(idx) != '{') {
            builder.append(str.charAt(idx++));
        }
        if (idx == str.length()) {
            expandList.add(builder.toString());
            builder.delete(builderLen, builder.length());
            return;
        }
        int idxLast = str.indexOf('}', ++idx);
        for (String ch : str.substring(idx, idxLast).split(",")) {
            builder.append(ch);
            recExpand(str, idxLast + 1, builder);
            builder.deleteCharAt(builder.length() - 1);
        }
        builder.delete(builderLen, builder.length());
    }

    public int longestSubarray(int[] nums) {
        int len = nums.length;
        int prev = nums[0], count = 1;
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 1; i < len; i++) {
            int num = nums[i];
            if (num == prev) {
                count++;
            } else {
                if (prev == 1) {
                    list.add(count);
                } else {
                    list.add(-count);
                }
                count = 1;
                prev = num;
            }
        }
        int max = list.stream().filter(i -> i > 0).max(Integer::compare).orElse(1);
        if (list.size() == 1) {
            return max;
        }
        ++max;
        int i = list.get(1) < 0 ? 1 : 2;
        while (i < list.size() - 1) {
            if (list.get(i) > 1) continue;
            int oneCount = list.get(i - 1) + list.get(i + 1) + 1;
            max = Math.max(max, oneCount);
            i += 2;
        }
        return max;
    }

    private long maxProduct;
    private long totalSumOfTree;

    public int maxProduct(TreeNode root) {
        totalSumOfTree = traverseProduct(root);
        maxProduct = 0;
        traverseProduct(root);
        return (int) (maxProduct % 1_000_000_007);
    }

    private long traverseProduct(TreeNode root) {
        if (root == null) return 0;
        long leftSum = traverseProduct(root.left), rightSum = traverseProduct(root.right);
        if (totalSumOfTree > 0)
            maxProduct = Math.max(maxProduct, Math.max(leftSum * (totalSumOfTree - leftSum),
                    rightSum * (totalSumOfTree - rightSum)));
        return root.val + leftSum + rightSum;
    }

    private boolean[][] usedValidPath;

    public boolean hasValidPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        usedValidPath = new boolean[m][n];
        return dfsValidPath(grid, 0, 0);
    }

    private boolean dfsValidPath(int[][] grid, int row, int col) {
        int m = grid.length, n = grid[0].length;
        if (row == m - 1 && col == n - 1) return true;
        usedValidPath[row][col] = true;
        int[][] nextCoords = getNextCoords(grid, row, col);
        for (int[] nextCoord : nextCoords) {
            int nextRow = nextCoord[0], nextCol = nextCoord[1];
            if (!isValid(nextRow, nextCol, m, n) || usedValidPath[nextRow][nextCol]) continue;
            int[][] prevCoords = getNextCoords(grid, nextRow, nextCol);
            if ((prevCoords[0][0] == row && prevCoords[0][1] == col)
                    || (prevCoords[1][0] == row || prevCoords[1][1] == col)) {
                if (dfsValidPath(grid, nextRow, nextCol)) return true;
            }
        }
        return false;
    }

    private boolean isValid(int nextRow, int nextCol, int m, int n) {
        return nextRow >= 0 && nextRow < m && nextCol >= 0 && nextCol < n;
    }

    private int totalNodes;
    private List<Long> scoreList;

    public int countHighestScoreNodes(int[] parents) {
        totalNodes = parents.length;
        scoreList = new LinkedList<>();
        HashMap<Integer, List<Integer>> childMap = new HashMap<>();
        for (int i = 0; i < totalNodes; i++) {
            int p = parents[i];
            List<Integer> list = childMap.getOrDefault(p, new LinkedList<>());
            list.add(i);
            childMap.put(p, list);
        }
        traverseScore(childMap, 0);
        long max = 0;
        int count = 0;
        for (long value : scoreList) {
            if (value > max) {
                max = value;
                count = 1;
            } else if (value == max) {
                count++;
            }
        }
        return count;
    }

    private int traverseScore(HashMap<Integer, List<Integer>> childMap, int node) {
        int childNodeSize = 1;
        List<Integer> childList = childMap.get(node);
        long score = 1;
        if (childList != null) {
            for (int childNode : childList) {
                int childMult = traverseScore(childMap, childNode);
                childNodeSize += childMult;
                score *= childMult;
            }
        }
        scoreList.add((totalNodes - childNodeSize) * score);
        return childNodeSize + 1;
    }

    private int[][] getNextCoords(int[][] grid, int row, int col) {
        switch (grid[row][col]) {
            case 1:
                return new int[][]{{row, col - 1}, {row, col + 1}};
            case 2:
                return new int[][]{{row - 1, col}, {row + 1, col}};
            case 3:
                return new int[][]{{row, col - 1}, {row + 1, col}};
            case 4:
                return new int[][]{{row, col + 1}, {row + 1, col}};
            case 5:
                return new int[][]{{row, col - 1}, {row - 1, col}};
            case 6:
                return new int[][]{{row, col + 1}, {row - 1, col}};
        }
        return null;
    }
    /*
    1 which means a street connecting the left cell and the right cell.
2 which means a street connecting the upper cell and the lower cell.
3 which means a street connecting the left cell and the lower cell.
4 which means a street connecting the right cell and the lower cell.
5 which means a street connecting the left cell and the upper cell.
6 which means a street connecting the right cell and the upper cell.
     */


    private char[] destPath, startPath;

    public String getDirections(TreeNode root, int startValue, int destValue) {
        traverse(root, startValue, destValue, new LinkedList<>());
        int startLen = startPath.length, destLen = destPath.length, minLen = Math.min(startLen, destLen);
        int i = 0;
        while (i < minLen && startPath[i] == destPath[i]) {
            i++;
        }
        char[] resPath = new char[startLen + destLen - 2 * i];
        Arrays.fill(resPath, 'U');
        int j = startLen - i;
        while (i < destLen) {
            resPath[j++] = destPath[i++];
        }
        return new String(resPath);
    }

    private int univalSubtreesCount;

    public int countUnivalSubtrees(TreeNode root) {
        univalSubtreesCount = 0;
        dfsCountUnivalSubtrees(root);
        return univalSubtreesCount;
    }

    public boolean dfsCountUnivalSubtrees(TreeNode root) {
        if (root == null) return true;
        boolean left = dfsCountUnivalSubtrees(root.left);
        boolean right = dfsCountUnivalSubtrees(root.right);
        if (!left || !right) return false;
        if (root.left != null && root.val != root.left.val) return false;
        if (root.right != null && root.val != root.right.val) return false;
        univalSubtreesCount++;
        return true;
    }

    private int[] isSafeNode; // 1 - terminal, 2 - safe, 3 - not safe, 4- in stack

    public List<Integer> eventualSafeNodes(int[][] graph) {
        Arrays.fill(isSafeNode, 0);
        int n = graph.length;
        for (int i = 0; i < n; i++) {
            if (isSafeNode[i] == 0) {
                dfsSafeNodes(graph, i);
            }
        }
        return IntStream.range(0, n).boxed().filter(this::calcSafeNode).collect(Collectors.toList());
    }

    private boolean calcSafeNode(Integer i) {
        return isSafeNode[i] == 1 || isSafeNode[i] == 2;
    }

    private int dfsSafeNodes(int[][] graph, int node) {
        if (isSafeNode[node] == 4) return (isSafeNode[node] = 3);
        if (isSafeNode[node] != 0) return isSafeNode[node];
        if (graph[node].length == 0) {
            return (isSafeNode[node] = 1);
        }
        isSafeNode[node] = 4;
        boolean isSafeCurrNode = true;
        for (int w : graph[node]) {
            if (w == node) continue;
            int wSafe = dfsSafeNodes(graph, w);
            if (wSafe == 3) {
                isSafeCurrNode = false;
            }
        }
        return isSafeCurrNode ? (isSafeNode[node] = 2) : (isSafeNode[node] = 3);
    }

    /*
    Hostname label must be from 1 to 63 characters long, including the dots,
    may contain only the ASCII letters from 'a' to 'z',
    digits  from '0' to '9' and the hyphen-minus character ('-').
     */
    private HashSet<String> visitedLinkSet;

    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        visitedLinkSet = new HashSet<>();
        String startHost = getHostName(startUrl);
        dfsCrawl(startUrl, startHost, htmlParser);
        return new ArrayList<>(visitedLinkSet);
    }

    private void dfsCrawl(String startUrl, String startHost, HtmlParser htmlParser) {
        visitedLinkSet.add(startUrl);
        for (String destUrl : htmlParser.getUrls(startUrl)) {
            String destHost = getHostName(destUrl);
            if (!startHost.equals(destHost)) continue;
            if (!visitedLinkSet.contains(destUrl)) {
                dfsCrawl(destUrl, destHost, htmlParser);
            }
        }
    }

    private String getHostName(String url) {
        URI uri = null;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return uri.getHost();
    }

    interface HtmlParser {
        List<String> getUrls(String url);
    }

    private void traverse(TreeNode root, int startValue, int destValue, LinkedList<Character> list) {
        if (root == null) return;
        int size = list.size();
        if (root.val == destValue) {
            destPath = new char[size];
            for (int i = 0; i < size; i++) {
                destPath[i] = list.get(i);
            }
            return;
        } else if (root.val == startValue) {
            startPath = new char[size];
            for (int i = 0; i < size; i++) {
                startPath[i] = list.get(i);
            }
            return;
        }
        list.add('L');
        traverse(root.left, startValue, destValue, list);
        list.remove(size);
        list.add('R');
        traverse(root.right, startValue, destValue, list);
        list.remove(size);
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        return traverse(root, targetSum);
    }

    private boolean traverse(TreeNode root, int targetSum) {
        if (root == null) return false;
        targetSum -= root.val;
        if (isLeaf(root)) {
            return targetSum == 0;
        }
        return traverse(root.left, targetSum) || traverse(root.right, targetSum);
    }

    private boolean isLeaf(TreeNode root) {
        return root.left == null && root.right == null;
    }

    private static class GridSet extends HashSet<GridPair> {
        private final Stack<GridPair> stack = new Stack<>();

        @Override
        public boolean add(GridPair gridPair) {
            return stack.add(gridPair) & super.add(gridPair);
        }

        public GridPair pop() {
            return stack.pop();
        }

        @Override
        public boolean isEmpty() {
            return stack.isEmpty();
        }
    }

    private int getValue(int[][] grid, GridPair nextPair) {
        return grid[nextPair.row][nextPair.col];
    }

    private static class GridPair {
        static int gridSize;
        final int row, col;

        public GridPair(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public boolean isValid() {
            return row >= 0 && row < gridSize && col >= 0 && col < gridSize;
        }

        public boolean isRightBottom() {
            return row == gridSize - 1 && col == gridSize - 1;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GridPair gridPair = (GridPair) o;
            return row == gridPair.row && col == gridPair.col;
        }

        @Override
        public int hashCode() {
            return 31 * row + col;
        }
    }

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        TreeNode root2 = find(root, subRoot.val);
        if (root2 == null) return false;
        return treeEquals(root2, subRoot);
    }

    private TreeNode find(TreeNode root, int value) {
        if (root == null) return null;
        if (root.val == value) return root;
        TreeNode leftNode = find(root.left, value);
        if (leftNode != null) return leftNode;
        return find(root.right, value);
    }

    private boolean treeEquals(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2 == null;
        } else if (root2 == null) {
            return false;
        }
        return treeEquals(root1.left, root2.left) && treeEquals(root1.right, root2.right);
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        UnionFind unionFind = new UnionFind(n);
        HashMap<String, Integer> emailToAccountId = new HashMap<>();
        for (int i = 0; i < n; i++) {
            List<String> account = accounts.get(i);
            for (int k = 1; k < account.size(); k++) {
                String email = account.get(k);
                if (emailToAccountId.containsKey(email)) {
                    unionFind.union(i, emailToAccountId.get(email));
                } else {
                    emailToAccountId.put(email, i);
                }
            }
        }
        HashMap<Integer, HashSet<String>> accountIdToEmailSet = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int parentId = unionFind.find(i);
            HashSet<String> emailSet = accountIdToEmailSet.getOrDefault(parentId, new HashSet<>());
            List<String> emailList = accounts.get(i);
            emailSet.addAll(emailList.subList(1, emailList.size()));
            accountIdToEmailSet.put(parentId, emailSet);
        }
        List<List<String>> resList = new ArrayList<>(accountIdToEmailSet.size());
        for (Integer accountId : accountIdToEmailSet.keySet()) {
            ArrayList<String> emailList = new ArrayList<>(accountIdToEmailSet.get(accountId));
            Collections.sort(emailList);
            ArrayList<String> subList = new ArrayList<>();
            subList.add(accounts.get(accountId).get(0));
            subList.addAll(emailList);
            resList.add(subList);
        }
        return resList;
    }

    public void recoverTree(TreeNode root) {
        dfsRecoverTree(root);
    }

    public boolean dfsRecoverTree(TreeNode parentNode) {
        if (parentNode == null) return false;
        if (parentNode.left != null) {
            if (parentNode.val < parentNode.left.val) {
                int tmp = parentNode.val;
                parentNode.val = parentNode.left.val;
                parentNode.left.val = tmp;
                return true;
            }
            if (dfsRecoverTree(parentNode.left)) {
                return true;
            }
        }

        if (parentNode.right != null) {
            if (parentNode.val > parentNode.right.val) {
                int tmp = parentNode.val;
                parentNode.val = parentNode.right.val;
                parentNode.right.val = tmp;
                return true;
            }
            if (dfsRecoverTree(parentNode.right)) {
                return true;
            }
        }

        return false;
    }

    public boolean areSentencesSimilarTwo(String[] sentence1, String[] sentence2,
                                          List<List<String>> similarPairs) {
        if (sentence1.length != sentence2.length) return false;
        int n = sentence1.length;
        StringUnionFind unionFind = new StringUnionFind();
        for (List<String> pair : similarPairs) {
            unionFind.union(pair.get(0), pair.get(1));
        }
        for (int i = 0; i < n; i++) {
            if (sentence1[i].equals(sentence2[i])) continue;
            String p1 = unionFind.find(sentence1[i]);
            String p2 = unionFind.find(sentence2[i]);
            if (!p1.equals(p2)) {
                return false;
            }
        }
        return true;
    }

    private int totalNodesForPartition;
    private boolean canPartitionEqually;

    public boolean checkEqualTree(TreeNode root) {
        totalNodesForPartition = 0;
        canPartitionEqually = false;
        totalNodesForPartition = dfsCheckEqualTree(root);
        return canPartitionEqually;
    }

    private int dfsCheckEqualTree(TreeNode root) {
        if (root == null) return 0;
        int tempNodes = dfsCheckEqualTree(root.left) + dfsCheckEqualTree(root.right) + 1;
        if (totalNodesForPartition > 0 && tempNodes * 2 == totalNodesForPartition) {
            canPartitionEqually = true;
        }
        return tempNodes;
    }

    public int minDistance(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
        int minAdditionalDist = Integer.MAX_VALUE;
        int res = 0;
        for (int[] nut : nuts) {
            int dist = distance(nut, tree);
            res += 2 * dist;
            minAdditionalDist = Math.min(minAdditionalDist, distance(nut, squirrel) - dist);
        }
        return res;
    }

    private int distance(int[] nut, int[] tree) {
        return Math.abs(tree[0] - nut[0]) + Math.abs(tree[1] - nut[1]);
    }

    public int monotoneIncreasingDigits(int n) {
        char[] arr = String.valueOf(n).toCharArray();
        int len = arr.length;
        for (int i = len - 1; i > 1; i--) {
            if (arr[i] <= arr[i - 1]) {
                for (int j = i; j < len; j++) {
                    arr[j] = (char) ('0' + 10 + j - len);
                }
            }
        }
        return Integer.parseInt(new String(arr));
    }

    public int numberOfBoomerangs(int[][] points) {
        int len = points.length;
        HashMap<Long, Integer> map = new HashMap<>();
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                long key = distanceCoord(points[i], points[j]);
                int value = map.getOrDefault(key, 0);
                map.put(key, value + 1);
            }
        }
        int res = 0;
        for (int value : map.values()) {
            res += value * (value - 1);
        }
        return res;
    }

    private long distanceCoord(int[] p1, int[] p2) {
        long d1 = Math.abs(p1[0] - p2[0]), d2 = Math.abs(p1[1] - p2[1]);
        return d1 * d1 + d2 * d2;
    }

    public int[] missingRolls(int[] rolls, int mean, int n) {
        int rollSum = Arrays.stream(rolls).sum();
        int m = rolls.length, totalSum = (n + m) * mean, remSum = totalSum - rollSum;
        if (remSum < n || remSum > 6 * n) return new int[0];
        int[] resArr = new int[n];
        int meanRem = remSum / n;
        Arrays.fill(resArr, meanRem++);
        for (int i = 0; i < remSum % n; i++) {
            resArr[i] = meanRem;
        }
        return resArr;
    }

    public int lastRemaining(int n) {
        ArrayList<Integer> list = IntStream.rangeClosed(1, n).boxed()
                .collect(Collectors.toCollection(ArrayList::new));
        boolean leftToRight = true;
        while (list.size() != 1) {
            int size = list.size();
            ArrayList<Integer> newList = new ArrayList<>(size / 2);
            for (int i = 0; i < size; i += 2) {
                int j = leftToRight ? i : size - i - 1;
                newList.add(list.get(j));
            }
            list = newList;
            if (!leftToRight) Collections.reverse(list);
            leftToRight = !leftToRight;
        }
        return list.get(0);
    }

    public int[] prisonAfterNDays(int[] cells, int n) {
        int len = cells.length;
        while (--n >= 0) {
            int[] arr = new int[len];
            Arrays.fill(cells, 0);
            for (int i = 1; i <= len - 2; i++) {
                arr[i] = 1 - (cells[i - 1] ^ cells[i + 1]);
            }
            cells = arr;
        }
        return cells;
    }

    public int trailingZeroes(int n) {
        if (n < 5) return 0;
        return n / 5 + trailingZeroes(n / 5);
    }

    private boolean find(TreeNode root, int val, StringBuilder builder) {
        if (root.val == val)
            return true;
        if (root.left != null && find(root.left, val, builder))
            builder.append("L");
        else if (root.right != null && find(root.right, val, builder))
            builder.append("R");
        return builder.length() > 0;
    }

    public String getDirectionsLeetcoded(TreeNode root, int startValue, int destValue) {
        StringBuilder startBuilder = new StringBuilder(), destBuilder = new StringBuilder();
        find(root, startValue, startBuilder);
        find(root, destValue, destBuilder);
        int destLen = destBuilder.length(), startLen = startBuilder.length(), maxLen = Math.min(destLen, startLen), i;
        for (i = 0; i < maxLen; i++) {
            if (startBuilder.charAt(startLen - i - 1) == destBuilder.charAt(destLen - i - 1)) break;
        }
        return "U".repeat(startLen - i) + destBuilder.reverse().substring(i);
    }

    public int findRotateSteps(String ring, String key) {
        int ringLen = ring.length();
        Map<Character, LinkedList<Integer>> map = new HashMap<>();
        for (int i = 0; i < ringLen; i++) {
            char ch = ring.charAt(i);
            LinkedList<Integer> list = map.getOrDefault(ch, new LinkedList<>());
            list.add(i);
            map.put(ch, list);
        }
        return key.length() + findRotateSteps(map, key, 0, 0, ringLen);
    }

    public int findRotateSteps(Map<Character, LinkedList<Integer>> map, String key,
                               int currentIndex, int keyIndex, int ringLen) {
        if (keyIndex >= key.length()) return 0;
        char ch = key.charAt(keyIndex);
        int minDistance = Integer.MAX_VALUE;
        for (int nextIndex : map.get(ch)) {
            int distance = idxDistance(currentIndex, nextIndex, ringLen);
            minDistance = Math.min(minDistance, distance +
                    findRotateSteps(map, key, nextIndex, keyIndex + 1, ringLen));
        }
        return minDistance;
    }

    private int idxDistance(int currentIndex, int keyIndex, int ringLen) {
        int distance = Math.abs(currentIndex - keyIndex);
        return Math.min(distance, ringLen - distance);
    }

    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        Map<Integer, List<Integer>> childMap = IntStream.range(0, n)
                .boxed().collect(Collectors.groupingBy(i -> manager[i]));
        return numOfMinutes(headID, childMap, informTime);
    }

    private int numOfMinutes(int nodeId, Map<Integer, List<Integer>> childMap, int[] informTime) {
        List<Integer> childList = childMap.get(nodeId);
        if (childList == null || childList.isEmpty()) return 0;
        int minTime = Integer.MAX_VALUE;
        for (int chidlId : childList) {
            minTime = Math.min(minTime, numOfMinutes(chidlId, childMap, informTime));
        }
        return informTime[nodeId] + minTime;
    }

    private List<List<Integer>> pathSumList;

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        pathSumList = new LinkedList<>();
        dfsPathSum(root, targetSum, new LinkedList<>());
        return pathSumList;
    }

    private void dfsPathSum(TreeNode root, int targetSum, LinkedList<Integer> currentPath) {
        if (root == null) return;
        currentPath.addLast(root.val);
        targetSum -= root.val;
        if (isLeaf(root)) {
            if (targetSum == 0) {
                pathSumList.add(new LinkedList<>(currentPath));
            }
        } else {
            dfsPathSum(root.left, targetSum, currentPath);
            dfsPathSum(root.right, targetSum, currentPath);
        }
        currentPath.pollLast();
    }

    public int minimumTime(int[] hens, int[] grains) {
        Arrays.sort(hens);
        Arrays.sort(grains);
        int low = 0, high = 1_500_000_000;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (minimumTimeBinarySearch(hens, grains, mid))
                high = mid;
            else
                low = mid + 1;
        }
        return low;
    }

    public boolean minimumTimeBinarySearch(int[] hens, int[] grains, int time) {
        int n = hens.length, m = grains.length, henIdx = 0, grainIdx = 0;
        // h is pointer for current leftmost hen in hens array.
        // g is pointer for the current leftmost un-eaten grain in grains array.
        while (henIdx < n && grainIdx < m) {
            // Current hen at index h will travel from [henLeft, henRight].
            int henLeft, henRight;

            // If there is a grain to the of current hen,
            // it will have to eat this grain.
            int currentHen = hens[henIdx];
            int currentGrain = grains[grainIdx];
            if (currentGrain < currentHen) {
                // Time left after going to left for this grain
                int timeLeft = time - (currentHen - currentGrain);
                // If the grain is too far, timeLeft will be negative,
                // it is not possible to eat this grain in given time.
                if (timeLeft < 0) return false;

                henLeft = currentGrain;

                // Hen can first go left, or go right and come back to left.
                henRight = Math.max(henLeft + timeLeft, currentHen + timeLeft / 2);
            }
            // If there is no grain to left, just go right.
            else {
                henLeft = currentHen;
                henRight = currentHen + time;
            }
            // Consume all grains in the range [henLeft, henRight].
            while (grainIdx < m && currentGrain >= henLeft && currentGrain <= henRight) {
                grainIdx++;
            }
            henIdx++;
        }
        // Return true if all grains have been consumed.
        return grainIdx == m;
    }

    private boolean isBalanced;

    public boolean isBalanced(TreeNode root) {
        isBalanced = true;
        dfsIsBalanced(root);
        return isBalanced;
    }

    private int dfsIsBalanced(TreeNode root) {
        if (root == null) return 0;
        int left = dfsIsBalanced(root.left);
        if (!isBalanced) return 0;
        int right = dfsIsBalanced(root.right);
        if (!isBalanced) return 0;
        if (Math.abs(right - left) > 1) {
            isBalanced = false;
        }
        return Math.max(left, right) + 1;
    }

    private int longestConsecutive;

    public int[] findRightInterval(int[][] intervals) {
        int n = intervals.length;
        for (int i = 0; i < n; i++) {
            int start = intervals[i][0], end = intervals[i][1];
            intervals[i] = new int[]{start, end, i};
        }
        int[] res = new int[n];
        Arrays.sort(intervals, Comparator.comparingInt((int[] a1) -> a1[0]));
        for (int i = 0; i < n; i++) {
            res[intervals[i][2]] = findRightInterval(intervals, i);
        }
        return res;
    }

    private int findRightInterval(int[][] intervals, int idx) {
        for (int i = idx + 1; i < intervals.length; i++) {
            if (intervals[i][0] >= intervals[idx][1]) return intervals[i][2];
        }
        return -1;
    }

    public int findBestValue(int[] arr, int target) {
        int n = arr.length;
        Arrays.sort(arr);
        int sum = Arrays.stream(arr).sum();
        int low = 0, high = n - 1;
        if (sum <= target) return high;
        int[] prefixSum = new int[n + 1];
        prefixSum[0] = 0;
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + arr[i];
        }
        // minimum val that resulting sum > target
        while (low < high) {
            int mid = low + (high - low) / 2;
            int tempSum = prefixSum[mid] + (n - mid) * arr[mid];
            if (tempSum >= target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        target -= prefixSum[low];
        int count = n - low;
        int val = target / (n - low);
        return Math.abs(val * count - target) <= Math.abs(++val * count - target) ? (val - 1) : val;
    }

    public int minAreaRect(int[][] points) {
        HashMap<Integer, List<Integer>> xMap = new HashMap<>(), yMap = new HashMap<>();
        for (int[] point : points) {
            List<Integer> yList = xMap.getOrDefault(point[0], new LinkedList<>());
            yList.add(point[1]);
            xMap.put(point[0], yList);
            List<Integer> xList = yMap.getOrDefault(point[1], new LinkedList<>());
            xList.add(point[0]);
            yMap.put(point[1], xList);
        }
        List<Integer> xCoordList = new LinkedList<>(xMap.keySet());
        int xSize = xCoordList.size();
        long minArea = Long.MAX_VALUE;
        for (int i = 0; i < xSize - 1; i++) {
            int x1 = xCoordList.get(i);
            List<Integer> yCoordList = xMap.get(x1);
            if (yCoordList.size() <= 1) continue;
            int ySize = yCoordList.size();
            for (int j1 = 0; j1 < ySize - 1; j1++) {
                int y1 = yCoordList.get(j1);
                for (int j2 = j1 + 1; j2 < ySize; j2++) {
                    int y2 = yCoordList.get(j1 + 1);
                    List<Integer> xCoordList1 = yMap.get(y1), xCoordList2 = yMap.get(y2);
                    boolean flag = false;
                    long minDistance = Integer.MAX_VALUE;
                    for (int x2 : xCoordList1) {
                        for (int x3 : xCoordList2) {
                            if (x2 != x3) continue;
                            flag = true;
                            minDistance = Math.min(minDistance, Math.abs(x2 - x1));
                        }
                    }
                    if (!flag) continue;
                    minArea = Math.min(minArea, minDistance * Math.abs(y2 - y1));
                }
            }
        }
        return minArea < Long.MAX_VALUE ? (int) minArea : 0;
    }

    private enum Direction {
        INC, DEC, ANY;
    }

    private List<Integer> lexicalOrderList;

    public List<Integer> lexicalOrder(int n) {
        lexicalOrderList = new ArrayList<>();
        dfsLexicalOrder(0, n);
        return lexicalOrderList;
    }

    public void dfsLexicalOrder(int prevNum, int n) {
        prevNum *= 10;
        for (int i = 0; i <= 9; i++) {
            if (prevNum == 0 && i == 0) continue;
            int nextNum = prevNum + i;
            if (nextNum > n) break;
            lexicalOrderList.add(nextNum);
            dfsLexicalOrder(nextNum, n);
        }
    }

    public int diagonalPrime(int[][] nums) {
        int n = nums.length;
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (isPrime(nums[i][i])) {
                max = Math.max(max, nums[i][i]);
            }
            if (isPrime(nums[i][n - i - 1])) {
                max = Math.max(max, nums[i][n - i - 1]);
            }
        }
        return max;
    }

    private static boolean isPrime(long num) {
        if (num == 1)
            return false;
        if (num == 2)
            return true;
        if (num % 2 == 0)
            return false;
        for (int k = 3; k <= Math.sqrt(num); k += 2) {
            if (num % k == 0)
                return false;
        }
        return true;
    }

    public long[] distance(int[] nums) {
        int len = nums.length;
        Map<Integer, List<Integer>> map = IntStream.range(0, len).boxed()
                .collect(Collectors.groupingBy(i -> nums[i]));
        long[] res = new long[len];
        for (List<Integer> list : map.values()) {
            Collections.sort(list);
            int size = list.size();
            long[] prefixSum = new long[size + 1];
            prefixSum[0] = 0;
            ;
            for (int i = 1; i <= size; i++) {
                prefixSum[i] = prefixSum[i - 1] + list.get(i - 1);
            }
            for (int i = 1; i <= size; i++) {
                Integer idx = list.get(i - 1);
                res[idx] = prefixSum[size] + i * idx - 2 * prefixSum[i] - (size - i) * idx;
            }
        }
        return res;
    }

    public String breakPalindrome(String palindrome) {
        char[] arr = palindrome.toCharArray();
        int len = arr.length;
        if (len == 1) return "";
        for (int i = 0; i < len / 2; i++) {
            if (arr[i] == 'a') continue;
            arr[i] = 'a';
            return new String(arr);
        }
        arr[len / 2 - 1] = 'a';
        return new String(arr);
    }

    public int maxSumDivThree(int[] nums) {
        ArrayList<LinkedList<Integer>> listOfLists = new ArrayList<>(2);
        listOfLists.add(Arrays.stream(nums).filter(num -> num % 3 == 1)
                .boxed().collect(Collectors.toCollection(LinkedList::new))); // 1
        listOfLists.add(Arrays.stream(nums).filter(num -> num % 3 == 2)
                .boxed().collect(Collectors.toCollection(LinkedList::new))); // 2
        int sum = Arrays.stream(nums).sum();
        List<Integer> remCountList = listOfLists.stream().map(subList -> subList.size() % 3)
                .collect(Collectors.toList());
        final int minRemCount = remCountList.stream().min(Integer::compareTo).get();
        remCountList = remCountList.stream().map(remCount -> remCount - minRemCount).collect(Collectors.toList());
        boolean allNulls = remCountList.stream().allMatch(remCount -> remCount == 0);
        if (allNulls) return sum;
        int remIdx = IntStream.range(0, remCountList.size()).boxed()
                .min(Comparator.comparingInt(remCountList::get)).get();
        int remCount = remCountList.get(remIdx);
        int sum1 = listOfLists.get(remIdx).stream().sorted()
                .limit(remCount).mapToInt(i -> i).sum();
        remCount = 3 - remCount;
        remIdx = 1 - remIdx;
        if (listOfLists.get(remIdx).size() < remCount) return sum - sum1;
        int sum2 = listOfLists.get(remIdx).stream().sorted()
                .limit(remCount).mapToInt(i -> i).sum();
        return sum - Math.min(sum1, sum2);
    }

    public int integerReplacement(int n) {
        int res = 0;
        while (n % 2 == 0) {
            res++;
            n /= 2;
        }
        if (n == 1) return res;
        return res + Math.min(integerReplacement(n + 1), integerReplacement(n - 1));
    }

    public int minDifference(int[] nums) {
        final int MIN_SIZE = 4;
        int len = nums.length;
        if (len <= MIN_SIZE) return 0;
        Arrays.sort(nums);
        ArrayList<Integer> list = new ArrayList<>(MIN_SIZE);
        for (int i = 0; i < MIN_SIZE; i++) {
            list.add(nums[len - MIN_SIZE] - nums[i]);
        }
        return list.stream().min(Integer::compareTo).get();
    }

    public int convertArray(int[] nums) {
        List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
        long min = Math.min(checkList(list, 1), checkList(list, -1));
        Collections.reverse(list);
        return (int) Math.min(min, Math.min(checkList(list, 1), checkList(list, -1)));
    }

    private long checkList(List<Integer> initialList, int isInc) {
        ArrayList<Integer> list = new ArrayList<>(initialList);
        int size = list.size();
        long res = 0;
        for (int i = 1; i < size; i++) {
            Integer prev = list.get(i - 1);
            Integer next = list.get(i);
            int sign = Integer.compare(prev, next);
            if (sign * isInc < 0) {
                res += Math.abs(next - prev);
                list.set(i, prev);
            }
        }
        return res;
    }

    public boolean isCompleteTree(TreeNode root) {
        ArrayDeque<TreeNode> deque = new ArrayDeque<>();
        deque.add(root);
        int levelNodes = 1;
        while (!deque.isEmpty()) {
            int size = deque.size();
            boolean toStop = false;
            for (int i = 0; i < size; i++) {
                TreeNode nextNode = deque.pollFirst();
                if (nextNode.left != null) {
                    if (toStop) return false;
                    deque.add(nextNode.left);
                } else toStop = true;
                if (nextNode.right != null) {
                    if (toStop) return false;
                    deque.add(nextNode.right);
                } else toStop = true;
            }
            if (!deque.isEmpty() && size != levelNodes) return false;
            levelNodes <<= 1;
        }
        return true;
    }

    private int closestLeafValue, closestLeafDistance;
    private HashMap<Integer, Integer> closestLeafMap;

    public int findClosestLeaf(TreeNode root, int k) {
        closestLeafDistance = Integer.MAX_VALUE;
        closestLeafValue = -1;
        closestLeafMap = new HashMap<>();
        dfsFindClosestLeaf(root, closestLeafMap);
        return closestLeafValue;
    }

    public int isWinner(int[] player1, int[] player2) {
        int comp = Integer.compare(bowlingScore(player1), bowlingScore(player2));
        if (comp < 0) return 2;
        if (comp > 0) return 1;
        return 0;
    }

    private int bowlingScore(int[] player) {
        int sum = 0;
        for (int i = 0; i < player.length; i++) {
            int mult = 1;
            for (int j = i - 1; j >= 0 && j >= i - 2; j--) {
                if (player[j] == 10) mult = 2;
            }
            sum += player[i] * mult;
        }
        return sum;
    }

    public int[] distinctDifferenceArray(int[] nums) {
        int n = nums.length;
        Map<Integer, Long> map1 = Arrays.stream(nums).boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        HashMap<Integer, Integer> map2 = new HashMap<>();
        int[] res = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            res[i] = map2.size() - map1.size();
            map2.put(nums[i], map2.getOrDefault(nums[i], 0) + 1);
            long val = map1.get(nums[i]) - 1;
            if (val == 0) {
                map1.remove(nums[i]);
            } else {
                map1.put(nums[i], val);
            }
        }
        return res;
    }

    private int minIncrements;

    public int minIncrements(int n, int[] cost) {
        minIncrements = 0;
        dfsMinIncrements(n, cost, 1);
        return minIncrements;
    }

    private void dfsMinIncrements(int n, int[] cost, int idx) {
        if (2 * idx > n) return;
        dfsMinIncrements(n, cost, 2 * idx);
        dfsMinIncrements(n, cost, 2 * idx + 1);
        minIncrements += Math.abs(cost[2 * idx] - cost[2 * idx - 1]);
        cost[idx] += Math.max(cost[2 * idx], cost[2 * idx - 1]);
    }

    public int findTheLongestSubstring(String s) {
        int len = s.length();
        HashMap<Character, Integer> vowelSet = new HashMap<>();
        vowelSet.put('a', 0);
        vowelSet.put('e', 1);
        vowelSet.put('i', 2);
        vowelSet.put('o', 3);
        vowelSet.put('u', 4);
        int size = vowelSet.size();
        int[][][] dp = new int[len][len][size];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j + i < len; j++) {
                char ch = s.charAt(j + i);
                if (i > 0) {
                    System.arraycopy(dp[j][i + j - 1], 0, dp[j][i + j], 0, size);
                }
                if (vowelSet.containsKey(ch)) {
                    dp[j][i + j][vowelSet.get(ch)]++;
                }
            }
        }
        for (int i = len - 1; i >= 0; i--) {
            for (int j = 0; j + i < len; j++) {
                if (isEvenVowels(dp[j][j + i])) return i + 1;
            }
        }
        // unreachable
        return 0;
    }

    private boolean isEvenVowels(int[] arr) {
        return Arrays.stream(arr).allMatch(i -> i % 2 == 0);
    }

    public int minFlipsMonoIncr(String s) {
        int len = s.length();
        int[][] dp = new int[len][2];
        if (s.charAt(0) == '0') {
            dp[0][1]++;
        } else {
            dp[0][0]++;
        }
        for (int i = 1; i < len; i++) {
            char ch = s.charAt(i);
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][1]);
            dp[i][0] = dp[i - 1][0];
            if (ch == '1') {
                dp[i][0]++;
            } else {
                dp[i][1]++;
            }
        }
        return Math.min(dp[len - 1][0], dp[len - 1][1]);
    }

    public boolean doesValidArrayExist(int[] derived) {
        return Arrays.stream(derived).boxed().reduce((x1, x2) -> x1 ^ x2).get() == 0;
    }

    public int maxTastiness(int[] price, int[] tastiness, int maxAmount, int maxCoupons) {
        int n = price.length;

        int[][][] dp = new int[n + 1][maxAmount + 1][maxCoupons + 1];
        // 3 dimentional array to save max tastiness at dp[x][y][z],
        // which is max tastiness at xth fruit with y amount of total money and z amount of total coupons.

        for (int x = 1; x <= n; x++) {   // iterate over n fruits (1 indexed for convinience).
            for (int y = 0; y <= maxAmount; y++) {   // iterate over amount from 0 to maxAmount.
                for (int z = 0; z <= maxCoupons; z++) {  // iterate over coupons from 0 to maxCoupon.

                    // option 1 - don't buy, then tastiness will be the same as previous state
                    // i.e. previous fruit with same money and coupon.
                    dp[x][y][z] = dp[x - 1][y][z];

                    // option 2 - if you have sufficient money,
                    // buy and take max of previous state i.e. previous fruit with less money.
                    if (y >= price[x - 1])
                        dp[x][y][z] = Math.max(dp[x][y][z], tastiness[x - 1] + dp[x - 1][y - price[x - 1]][z]);

                    // option 3 - if you have coupon and half money required,
                    // buy and take max of previous state i.e. previous fruit with less money and one less coupon.
                    if (z > 0 && y >= price[x - 1] / 2)
                        dp[x][y][z] = Math.max(dp[x][y][z], tastiness[x - 1] + dp[x - 1][y - price[x - 1] / 2][z - 1]);
                }
            }
        }
        // answer is dp[max fruits][max money][max coupon]
        return dp[n][maxAmount][maxCoupons];
    }

    public ListNode[] splitCircularLinkedList(ListNode list) {
        int count = 1;
        ListNode current = list;
        while (current.next != list) {
            ++count;
            current = current.next;
        }
        current = list;
        for (int i = 1; i < (count + 1) / 2; i++) {
            current = current.next;
        }
        ListNode root2 = current.next;
        current.next = list;
        current = root2;
        while (current.next != null) {
            current = current.next;
        }
        current.next = root2;
        return new ListNode[]{list, root2};
    }

    private List<List<Integer>> combineList;

    public List<List<Integer>> combine(int n, int k) {
        combineList = new LinkedList<>();
        combineRecursive(n, k, 1, new LinkedList<>());
        return combineList;
    }

    private void combineRecursive(int n, int k, int start, LinkedList<Integer> list) {
        if (k == 0) {
            combineList.add(new LinkedList<>(list));
            return;
        }
        if (start > n) {
            return;
        }
        list.addLast(start);
        combineRecursive(n, k - 1, start + 1, list);
        list.pollLast();
        combineRecursive(n, k, start + 1, list);
    }

    public List<String> removeInvalidParentheses(String s) {
        int leftBracketDeletions = 0, rightBracketDeletions = 0;
        int count = 0, len = s.length();
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                count++;
            } else if (--count < 0) {
                rightBracketDeletions = Math.max(rightBracketDeletions, -count);
            }
        }
        count = 0;
        for (int i = len - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            if (ch == ')') {
                count++;
            } else if (--count < 0) {
                leftBracketDeletions = Math.max(leftBracketDeletions, -count);
            }
        }
        removeInvalidParenthesesSet = new HashSet<>();
        removeInvalidParentheses(s.toCharArray(), 0, rightBracketDeletions, leftBracketDeletions, new StringBuilder(), 0);
        return new ArrayList<>(removeInvalidParenthesesSet);
    }

    private HashSet<String> removeInvalidParenthesesSet;

    private void removeInvalidParentheses(char[] arr, int idx, int rightBracketDeletions,
                                          int leftBracketDeletions, StringBuilder builder, int count) {
        if (idx == arr.length) {
            if (rightBracketDeletions == 0 && leftBracketDeletions == 0) {
                removeInvalidParenthesesSet.add(builder.toString());
            }
            return;
        }
        int newCount = arr[idx] == '(' ? count + 1 : count - 1;
        if (newCount >= 0) {
            builder.append(arr[idx]);
            removeInvalidParentheses(arr, idx + 1, rightBracketDeletions, leftBracketDeletions, builder, newCount);
            builder.deleteCharAt(builder.length());
        }
        if (arr[idx] == '(') {
            if (leftBracketDeletions > 0) {
                removeInvalidParentheses(arr, idx + 1, rightBracketDeletions,
                        leftBracketDeletions - 1, builder, count);
            }
        } else {
            if (rightBracketDeletions > 0) {
                removeInvalidParentheses(arr, idx + 1, rightBracketDeletions - 1,
                        leftBracketDeletions, builder, count);
            }
        }
    }

    public int minSwaps(String s) {
        final char LEFT_BRACKET = '[', RIGHT_BRACKET = ']';
        int count = 0, swaps = 0;
        for (char ch : s.toCharArray()) {
            if (ch == LEFT_BRACKET) {
                count++;
            } else if (--count < 0) {
                count = 1;
                swaps++;
            }
        }
        return swaps;
    }


    public int[] colorTheArray(int n, int[][] queries) {
        int qLen = queries.length;
        int[] colors = new int[n];
        Arrays.fill(colors, 0);
        int[] res = new int[qLen];
        if (n == 1) {
            Arrays.fill(res, 0);
            return res;
        }
        int adjCount = 0;
        for (int i = 0; i < qLen; i++) {
            int[] query = queries[i];
            int idx = query[0], color = query[1];
            if (color != colors[idx]) {
                if (idx > 0) {
                    if (colors[idx] != 0 && colors[idx] == colors[idx - 1]) adjCount--;
                    if (color == colors[idx - 1]) adjCount++;
                }
                if (idx < n - 1) {
                    if (colors[idx] != 0 && colors[idx] == colors[idx + 1]) adjCount--;
                    if (color == colors[idx + 1]) adjCount++;
                }
                colors[idx] = color;
            }
            res[i] = adjCount;
        }
        return res;
    }

    public int firstCompleteIndex(int[] arr, int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[][] dp = new int[m * n][2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[mat[i][j] - 1] = new int[]{i, j};
            }
        }
        int[] rows = new int[m];
        int[] cols = new int[n];
        for (int i = 0; i < arr.length; i++) {
            int[] idxes = dp[arr[i] - 1];
            if (++rows[idxes[0]] == n || ++cols[idxes[1]] == m) return i;
        }
        return -1;
    }

    private void dfsFindClosestLeaf(TreeNode root, HashMap<Integer, Integer> closestLeafMap) {
        if (root == null) return;
        if (isLeaf(root)) {
            closestLeafMap.put(root.val, 0);
        } else {
            int minDistance = Integer.MAX_VALUE;
            if (root.left != null) {
                dfsFindClosestLeaf(root.left, closestLeafMap);
                minDistance = 1 + closestLeafMap.get(root.left.val);
            }
            if (root.right != null) {
                dfsFindClosestLeaf(root.right, closestLeafMap);
                minDistance = Math.min(minDistance, 1 + closestLeafMap.get(root.right.val));
            }
            closestLeafMap.put(root.val, minDistance);
        }
    }

    public boolean isEvenOddTree(TreeNode root) {
        boolean oddLevel = true;
        ArrayDeque<TreeNode> deque = new ArrayDeque<>();
        deque.add(root);
        while (!deque.isEmpty()) {
            int prev = Integer.MAX_VALUE;
            if (oddLevel) {
                prev = -prev;
            }
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode nextNode = deque.pollFirst();
                int comp = Integer.compare(prev, nextNode.val);
                if (oddLevel) {
                    if (comp >= 0) return false;
                } else if (comp <= 0) return false;
                prev = nextNode.val;
                if (nextNode.left != null) deque.add(nextNode.left);
                if (nextNode.right != null) deque.add(nextNode.right);
            }
            oddLevel = !oddLevel;
        }
        return true;
    }

    public int kEmptySlots(int[] bulbs, int k) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < bulbs.length; i++) {
            int bulbNumber = bulbs[i];
            Integer x = set.floor(bulbNumber - 1);
            if (x != null && bulbNumber - x == k + 1) return i + 1;
            x = set.ceiling(bulbNumber + 1);
            if (x != null && -bulbNumber + x == k + 1) return i + 1;
        }
        return -1;
    }

    public int minimumEffort(int[][] tasks) {
        int actualSum = Arrays.stream(tasks).mapToInt((int[] task) -> task[0]).sum();
        int maxMinimum = Arrays.stream(tasks).mapToInt((int[] task) -> task[1]).min().getAsInt();
        int res = Math.max(actualSum, maxMinimum), current = res;
        Comparator<int[]> comparator = Comparator.comparing((int[] task) -> (double) task[1] / task[0])
                .thenComparing((int[] task) -> task[1]);
        Arrays.sort(tasks, comparator);
        for (int[] task : tasks) {
            if (current < task[1]) {
                res += task[1] - current;
                current = task[1];
            }
            current -= task[0];
        }
        return res;
    }

    public int minimizeMax(int[] A, int p) {
        Arrays.sort(A);
        int n = A.length, left = 0, right = A[n - 1] - A[0];
        while (left < right) {
            int mid = (left + right) / 2, k = 0;
            for (int i = 1; i < n && k < p; ++i) {
                if (A[i] - A[i - 1] <= mid) {
                    k++;
                    i++;
                }
            }
            if (k >= p)
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }

    class StringUnionFind {
        // Arr to represent parent of index i
        private final HashMap<String, String> parentMap = new HashMap<>();
        // Size to represent the number of nodes in subgxrph rooted at index i
        private final HashMap<String, Integer> rankMap = new HashMap<>();

        // Each time we follow a path, find function compresses it further until the path length is greater than or equal to 1.
        public String find(String str) {
            while (parentMap.containsKey(str)) {
                str = parentMap.get(str);
            }
            return str;
        }

        public void union(String xr, String yr) {
            xr = find(xr);
            yr = find(yr);
            if (xr.equals(yr)) return;
            int totalRank = rankMap.get(yr) + rankMap.get(xr);
            if (rankMap.get(xr) < rankMap.get(yr)) {
                parentMap.put(xr, yr);
                rankMap.put(yr, totalRank);
            } else {
                parentMap.put(yr, xr);
                rankMap.put(xr, totalRank);
            }
        }

        public boolean isRoot(String str) {
            return !parentMap.containsKey(str);
        }

        public int getRankSize(String str) {
            return rankMap.get(str);
        }
    }

    private static class SumNode {
        int val;
        long sum;
        List<SumNode> childNodes = new ArrayList<>();

        public SumNode(int val) {
            this.val = val;
            this.sum = val;
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    class Node {
        char val;
        Node left;
        Node right;

        Node() {
            this.val = ' ';
        }

        Node(char val) {
            this.val = val;
        }

        Node(char val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
