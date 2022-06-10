package com.mamay.leetcode.contest.weeks_90_100.week94;

import java.util.LinkedList;
import java.util.List;

public class LeafSimilar {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public static boolean leafSimilar(TreeNode root1, TreeNode root2) {
		List<Integer> list1 = new LinkedList<>();
		List<Integer> list2 = new LinkedList<>();
		traverse(root1, list1);
		traverse(root2, list2);
		return list1.equals(list2);

	}

	public static void traverse(TreeNode root, List<Integer> list) {
		if (root.left == null && root.right == null) {
			list.add(root.val);
			return;
		}
		if (root.left != null) {
			traverse(root.left, list);
		}

		if (root.right != null) {
			traverse(root.right, list);
		}
	}
}
