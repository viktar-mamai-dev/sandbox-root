package part1.week4;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<Key extends Comparable<Key>, V> {
    protected Node root = null;

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        return node.getCount();
    }

    public void put(Key key, V value) {
        if (root == null) {
            root = new Node(key, value);
            return;
        }
        root = put(root, key, value);
    }

    private Node put(Node node, Key key, V value) {
        if (node == null) {
            return new Node(key, value);
        }

        if (node.key.compareTo(key) < 0) {
            node.right = put(node.right, key, value);
        } else if (node.key.compareTo(key) > 0) {
            node.left = put(node.left, key, value);
        } else {
            node.value = value;
        }
        node.count = 1 + size(node.left) + size(node.right);
        return node;
    }

    public V get(Key key) {
        Node x = root;
        while (x != null) {
            int comp = x.key.compareTo(key);
            if (comp == 0) {
                return x.value;
            }
            if (comp > 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        return null;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    public Node delete(Node node, Key key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = delete(node.left, key);
        } else if (cmp > 0) {
            node.right = delete(node.right, key);
        } else {
            if (node.right == null) return node.left;
            else if (node.left == null) return node.right;

            Node t = node;
            node = min(t.right);
            node.right = deleteMin(t.right);
            node.left = t.left;
        }
        node.count = 1 + size(node.left) + size(node.right);
        return node;
    }

    private Node min(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        node.count = 1 + size(node.left) + size(node.right);
        return node;
    }

    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) {
            return null;
        }
        return x.key;
    }

    private Node floor(Node node, Key key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            return node;
        } else if (cmp > 0) {
            Node floorNode = floor(node.right, key);
            return floorNode != null ? floorNode : node;
        } else {
            return floor(node.left, key);
        }
    }

    public int rank(Key key) {
        return rank(root, key);
    }

    private int rank(Node node, Key key) {
        if (node == null) {
            return 0;
        }

        if (key.compareTo(node.key) == 0) {
            return size(node.left);
        } else if (key.compareTo(node.key) > 0) {
            return 1 + size(node.left) + rank(node.right, key);
        } else {
            return rank(node.left, key);
        }
    }

    public Iterator<Node> iterator() {
        return new TreeIterator();
    }

    private class TreeIterator implements Iterator<Node> {
        private int size = root.getCount();
        private int index;
        private Object[] array = new Object[size];

        public TreeIterator() {
            preOrderTraversal(root);
            index = 0;
        }

        private void preOrderTraversal(Node node) {
            if (node == null) {
                return;
            }
            array[index++] = node;
            preOrderTraversal(node.left);
            preOrderTraversal(node.right);
        }

        @Override
        public boolean hasNext() {
            return index != size;
        }

        @Override
        public Node next() {
            index++;
            return (Node) array[index - 1];
        }
    }

    public Iterable<Key> keys() {
        Queue<Key> list = new LinkedList<>();
        inorderTraversal(root, list);
        return list;
    }

    private void inorderTraversal(Node node, Queue<Key> list) {
        if (node == null) {
            return;
        }
        inorderTraversal(node.left, list);
        list.add(node.key);
        inorderTraversal(node.right, list);
    }

    public class Node {
        private Key key;
        private V value;
        private int count = 1;

        private Node left;
        private Node right;

        Node(Key key, V value) {
            this.key = key;
            this.value = value;
        }

        public Key getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public int getCount() {
            return count;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }
}
