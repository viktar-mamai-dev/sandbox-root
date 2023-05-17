package part1.week5;

public class RedBlackBST<Key extends Comparable<Key>, Value> {

  private static final boolean BLACK = false;
  private static final boolean RED = true;

  private Node root;

  private boolean isRed(Node x) {
    return x != null && x.color == RED;
  }

  public Value get(Key key) {
    Node x = root;
    while (x != null) {
      int cmp = key.compareTo(x.key);
      if (cmp < 0) x = x.left;
      else if (cmp > 0) x = x.right;
      else return x.value;
    }
    return null;
  }

  private Node rotateLeft(Node h) {
    Node x = h.right;
    h.right = x.left;
    x.left = h;
    x.color = h.color;
    h.color = RED;
    return x;
  }

  private Node rotateRight(Node h) {
    Node x = h.left;
    h.left = x.right;
    x.right = h;
    x.color = h.color;
    h.color = RED;
    return x;
  }

  private void flipColors(Node h) {
    h.color = RED;
    h.left.color = BLACK;
    h.right.color = BLACK;
  }

  public void put(Key key, Value value) {
    root = put(root, key, value);
  }

  private Node put(Node h, Key key, Value value) {
    if (h == null) return new Node(key, value, RED);

    int cmp = key.compareTo(h.key);
    if (cmp < 0) h.left = put(h.left, key, value);
    else if (cmp > 0) h.right = put(h.right, key, value);
    else h.value = value;

    if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h); // lean left
    if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h); // balance 4-node
    if (isRed(h.left) && isRed(h.right)) flipColors(h); // split 4-node

    return h;
  }

  private class Node {
    Key key;
    Value value;
    Node left;
    Node right;
    boolean color; // TRUE for RED

    public Node(Key key, Value value, boolean color) {
      this.key = key;
      this.value = value;
      this.color = color;
    }
  }
}
