package com.mamay.leetcode.practice.easy2;

public class Codec {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(200);
        root.left = new TreeNode(100);
        root.right = new TreeNode(500);
        root.left.right = new TreeNode(150);
        root.right.left = new TreeNode(300);

        Codec codec = new Codec();
        String rootStr = codec.serialize(root);
        System.out.println(rootStr);
        TreeNode newRoot = codec.deserialize(rootStr);
        System.out.println(newRoot.val);
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder builder = new StringBuilder();
        preOrderSerialize(root, builder);
        return builder.toString();
    }

    private void preOrderSerialize(TreeNode root, StringBuilder builder) {
        if (root == null) return;
        builder.append(root.val).append(",");
        preOrderSerialize(root.left, builder);
        preOrderSerialize(root.right, builder);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty()) return null;
        String[] arr = data.split(",");
        return preOrderDeSerialize(arr, 0, arr.length - 1);
    }

    private TreeNode preOrderDeSerialize(String arr[], int begin, int end) {
        if (begin > end) return null;
        int val = Integer.parseInt(arr[begin]);
        TreeNode root = new TreeNode(val);
        int i = begin + 1;
        while (i <= end && Integer.parseInt(arr[i]) < val) {
            i++;
        }
        root.left = preOrderDeSerialize(arr, begin + 1, i - 1);
        root.right = preOrderDeSerialize(arr, i, end);
        return root;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}