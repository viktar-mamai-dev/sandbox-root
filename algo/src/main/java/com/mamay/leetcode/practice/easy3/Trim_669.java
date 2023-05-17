package com.mamay.leetcode.practice.easy3;

public class Trim_669 {
    public TreeNode trimBST(TreeNode root, int L, int R) {
        while (root != null &&  (root.val < L || root.val > R))
            root = deleteRec(root, root.val);
        if (root == null)
            return root;
        if (root.left != null)
            root.left = trimBST(root.left, L, R);
        if (root.right != null)
            root.right = trimBST(root.right, L, R);
        return root;
    }

    private TreeNode deleteRec(TreeNode node, int value) {
        if (node == null)
            return null;
        if (node.val > value) {
            node.left = deleteRec(node.left, value);
        } else if (node.val < value) {
            node.right = deleteRec(node.right, value);
        } else {
            if (node.left == null)
                return node.right;
            if (node.right == null)
                return node.left;

            TreeNode temp = node;
            TreeNode min = min(temp.right);
            node.val = min.val;
            node.right = deleteRec(node.right, min.val);
        }
        return node;
    }

    private TreeNode min(TreeNode root) {
        TreeNode minv = root;
        while (root.left != null) {
            minv = root.left;
            root = root.left;
        }
        return minv;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        Trim_669 main = new Trim_669();
        root = main.trimBST(root, 3, 4);
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
