package part1.week4.iq;

import edu.princeton.cs.algs4.StdOut;
import java.util.ArrayList;
import java.util.List;
import part1.week4.BinarySearchTree;

public class Task5 extends BinarySearchTree<Integer, String> {

  public List<Node> morrisTraversal() {
    return morrisTraversal(this.root);
  }

  private List<Node> morrisTraversal(Node node) {
    List<Node> list = new ArrayList<>();
    while (node != null) {

      // If left child is null, print the current node data. Move to right child.
      if (node.getLeft() == null) {
        StdOut.print(node.getKey() + " ");
        list.add(node);
        node = node.getRight();
      } else {
        // Find inorder predecessor
        Node current = node.getLeft();
        while (current.getRight() != null && current.getRight() != node) {
          current = current.getRight();
        }

        // If the right child of inorder predecessor already points to this node
        if (current.getRight() == node) {
          current.setRight(null);
          node = node.getRight();
        }

        // If right child doesn't point to this node, then print this node and make right child
        // point to this node
        else {
          StdOut.print(node.getKey() + " ");
          list.add(node);
          current.setRight(node);
          node = node.getLeft();
        }
      }
    }
    return list;
  }
}
