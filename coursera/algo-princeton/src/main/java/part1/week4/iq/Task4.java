package part1.week4.iq;

import part1.week4.BinarySearchTree;

public class Task4 extends BinarySearchTree<Integer, String> {

    public boolean checkBST() {
        return checkBST(this.root);
    }

    private boolean checkBST(Node node) {
        if (node == null) {
            return true;
        }
        if (node.getLeft() != null && node.getLeft().getKey().compareTo(node.getKey()) > 0) {
            return false;
        }
        if (node.getRight() != null && node.getRight().getKey().compareTo(node.getKey()) < 0) {
            return false;
        }

        return checkBST(node.getLeft()) && checkBST(node.getRight());
    }

    public boolean checkBSTByValue() {
        return checkBSTByValue(this.root);
    }

    private boolean checkBSTByValue(Node node) {
        if (node == null) {
            return true;
        }
        if (node.getLeft() != null && node.getLeft().getValue().compareTo(node.getValue()) > 0) {
            return false;
        }
        if (node.getRight() != null && node.getRight().getValue().compareTo(node.getValue()) < 0) {
            return false;
        }

        return checkBSTByValue(node.getLeft()) && checkBSTByValue(node.getRight());
    }
}
