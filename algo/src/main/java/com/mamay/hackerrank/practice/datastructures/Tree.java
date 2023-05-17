package com.mamay.hackerrank.practice.datastructures;

class Node {
  Node left;
  Node right;
  int data;

  Node(int data) {
    this.data = data;
    left = null;
    right = null;
  }
}

class Solution {
  public static void inOrder(Node root) {
    if (root == null) return;
    inOrder(root.left);
    System.out.print(root.data + " ");
    inOrder(root.right);
  }

  public static int height(Node root) {
    if (root == null) {
      return -1;
    }

    if (root.left == null && root.right == null) {
      return 0;
    }

    return Math.max(height(root.left), height(root.right)) + 1;
  }

  public static Node lca(Node root, int v1, int v2) {
    if (root.data > v1 && root.data > v2) return lca(root.left, v1, v2);
    else if (root.data < v1 && root.data < v2) return lca(root.right, v1, v2);
    return root;
  }

  public static Node insert(Node root, int data) {
    if (root == null) {
      return new Node(data);
    } else {
      Node cur;
      if (data <= root.data) {
        cur = insert(root.left, data);
        root.left = cur;
      } else {
        cur = insert(root.right, data);
        root.right = cur;
      }
      return root;
    }
  }
}
