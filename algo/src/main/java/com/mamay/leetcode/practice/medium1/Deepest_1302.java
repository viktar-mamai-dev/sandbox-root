package com.mamay.leetcode.practice.medium1;

public class Deepest_1302 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int deepestLeavesSum(TreeNode root) {
        int height = height(root);
        return sum(root, 1, height);
    }

    private int sum(TreeNode root, int level, int height) {
        if (root == null) return 0;
        if (level == height) return root.val;
        return sum(root.left, level + 1, height) + sum(root.right, level + 1, height);
    }

    private int height(TreeNode node) {
        if (node == null) return 0;
        if (node.right == null && node.left == null) return 1;
        if (node.right == null) return height(node.left) + 1;
        if (node.left == null) return height(node.right) + 1;
        return Math.max(height(node.left), height(node.right)) + 1;
    }
}
