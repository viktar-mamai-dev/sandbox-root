package com.mamay.leetcode.practice.easy4;

public class RecoverBST {

  public static void main(String[] args) {
    TreeNode n1 = new TreeNode(1);
    TreeNode n2 = new TreeNode(3);
    TreeNode n3 = new TreeNode(2);
    n1.left = n2;
    n2.right = n3;

    RecoverBST main = new RecoverBST();
    main.recoverTree(n1);
    return;
  }

  public void recoverTree(TreeNode root) {
    solve(root);
  }

  TreeNode[] solve(TreeNode root) {
    TreeNode res[] = new TreeNode[2];
    if (root.left != null) {
      TreeNode[] leftArr = solve(root.left);
      TreeNode right = leftArr[1];
      if (right.val > root.val) {
        int t = root.val;
        root.val = right.val;
        right.val = t;
      }
      res[0] = leftArr[0];
    }

    if (root.right != null) {
      TreeNode[] rightArr = solve(root.right);
      TreeNode right = rightArr[0];
      if (right.val < root.val) {
        int t = root.val;
        root.val = right.val;
        right.val = t;
      }
      res[1] = rightArr[1];
    }

    if (root.left == null) {
      res[0] = root;
    }

    if (root.right == null) {
      res[1] = root;
    }

    return res;
  }

  static class TreeNode {
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
}
