package com.mamay.leetcode.contest.weeks_100_110.week100;

public class IncreasingOrder {

  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  public TreeNode increasingBST(TreeNode root) {
    return recursion(root);
  }

  private TreeNode recursion(TreeNode initNode) {
    TreeNode resultNode;
    if (initNode.left == null && initNode.right == null) {
      resultNode = initNode;
    } else if (initNode.left == null) {
      initNode.right = recursion(initNode.right);
      resultNode = initNode;
    } else if (initNode.right == null) {
      resultNode = recursion(initNode.left);
      TreeNode biggestLeft = findBiggest(resultNode);
      biggestLeft.right = initNode;
      initNode.left = null;
      initNode.right = null;
    } else {
      resultNode = recursion(initNode.left);
      TreeNode biggestLeft = findBiggest(resultNode);
      biggestLeft.right = initNode;
      initNode.right = recursion(initNode.right);
      initNode.left = null;
    }
    return resultNode;
  }

  private TreeNode findBiggest(TreeNode node) {
    while (node.right != null) {
      node = node.right;
    }
    return node;
  }
}
