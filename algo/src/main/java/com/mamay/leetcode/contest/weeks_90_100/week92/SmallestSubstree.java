package com.mamay.leetcode.contest.weeks_90_100.week92;

public class SmallestSubstree {

	private static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public static TreeNode subtreeWithAllDeepest(TreeNode root) {
		int d = getDepth(root.right) - getDepth(root.left);
		if (d == 0)
			return root;
		return subtreeWithAllDeepest(d > 0 ? root.right : root.left);
	}

	private static int getDepth(TreeNode tree) {
		if (tree == null)
			return 0;
		return Math.max(getDepth(tree.right), getDepth(tree.left)) + 1;
	}

	public static void main(String[] args) {
		// [3,5,1,6,2,0,8,null,null,7,4]
		TreeNode n1 = new TreeNode(3);
		TreeNode n2 = new TreeNode(5);
		TreeNode n3 = new TreeNode(1);
		TreeNode n4 = new TreeNode(6);
		TreeNode n5 = new TreeNode(2);
		TreeNode n6 = new TreeNode(0);
		TreeNode n7 = new TreeNode(8);
		TreeNode n8 = new TreeNode(7);
		TreeNode n9 = new TreeNode(4);
		n1.left = n2;
		n1.right = n3;
		n2.left = n4;
		n2.right = n5;
		n3.left = n6;
		n3.right = n7;
		n5.left = n8;
		n5.right = n9;

		System.out.println(subtreeWithAllDeepest(n1).val);

	}
}
