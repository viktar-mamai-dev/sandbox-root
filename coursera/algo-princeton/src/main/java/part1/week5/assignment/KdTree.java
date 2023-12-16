package part1.week5.assignment;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import java.util.ArrayList;
import java.util.List;

public class KdTree {

  private static final double XMIN = 0.0;
  private static final double XMAX = 1.0;
  private static final double YMIN = 0.0;
  private static final double YMAX = 1.0;

  private Node root;

  public static void main(String[] args) {}

  public void insert(Point2D point) {
    if (point == null) {
      throw new IllegalArgumentException();
    }
    root = insert(root, point, XMIN, YMIN, XMAX, YMAX, 0);
  }

  private Node insert(
      Node node, Point2D p, double xmin, double ymin, double xmax, double ymax, int level) {
    if (node == null) {
      return new Node(p, new RectHV(xmin, ymin, xmax, ymax));
    }

    int cmp = compare(p, node.point, level);

    if (cmp < 0) {
      if (level % 2 == 0) {
        node.left = insert(node.left, p, xmin, ymin, node.point.x(), ymax, level + 1);
      } else {
        node.left = insert(node.left, p, xmin, ymin, xmax, node.point.y(), level + 1);
      }
    } else if (cmp > 0) {
      if (level % 2 == 0) {
        node.right = insert(node.right, p, node.point.x(), ymin, xmax, ymax, level + 1);
      } else {
        node.right = insert(node.right, p, xmin, node.point.y(), xmax, ymax, level + 1);
      }
    }
    node.size = size(node.left) + size(node.right) + 1;
    return node;
  }

  private int compare(Point2D a, Point2D b, int level) {
    if (level % 2 == 0) {
      // Compare x-coordinates
      int cmpResult = Double.compare(a.x(), b.x());

      if (cmpResult == 0) {
        return Double.compare(a.y(), b.y());
      } else {
        return cmpResult;
      }
    } else {
      // Compare y-coordinates
      int cmpResult = Double.compare(a.y(), b.y());

      if (cmpResult == 0) {
        return Double.compare(a.x(), b.x());
      } else {
        return cmpResult;
      }
    }
  }

  public void draw() {
    StdDraw.clear();

    draw(root, 0);
  }

  private void draw(Node node, int level) {
    if (node != null) {

      draw(node.left, level + 1);

      StdDraw.setPenRadius();
      if (level % 2 == 0) {
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.line(node.point.x(), node.rect.ymin(), node.point.x(), node.rect.ymax());
      } else {
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.line(node.rect.xmin(), node.point.y(), node.rect.xmax(), node.point.y());
      }

      StdDraw.setPenColor(StdDraw.BLACK);
      StdDraw.setPenRadius(0.01);
      node.point.draw();

      draw(node.right, level + 1);
    }
  }

  public Point2D nearest(Point2D query) {
    if (query == null) {
      throw new IllegalArgumentException();
    }

    return nearest(root, query, null);
  }

  private Point2D nearest(Node node, Point2D query, Point2D minimum) {
    if (node == null) {
      return minimum;
    }

    if (minimum == null) {
      minimum = node.point;
    }

    if (node.rect.distanceSquaredTo(query) > minimum.distanceSquaredTo(query)) {
      return minimum;
    }

    if (node.point.distanceSquaredTo(query) < minimum.distanceSquaredTo(query)) {
      minimum = node.point;
    }

    if (node.right != null && node.right.rect.contains(query)) {
      minimum = nearest(node.right, query, minimum);
      minimum = nearest(node.left, query, minimum);
    } else {
      minimum = nearest(node.left, query, minimum);
      minimum = nearest(node.right, query, minimum);
    }

    return minimum;
  }

  public Iterable<Point2D> range(RectHV rect) {
    if (rect == null) {
      throw new IllegalArgumentException();
    }

    List<Point2D> list = new ArrayList<>();
    range(list, rect, root);
    return list;
  }

  private void range(List<Point2D> list, RectHV rect, Node node) {
    if (node == null) {
      return;
    }

    if (!rect.intersects(node.rect)) {
      return;
    }

    if (rect.contains(node.point)) {
      list.add(node.point);
    }

    range(list, rect, node.left);
    range(list, rect, node.right);
  }

  public boolean isEmpty() {
    return root == null;
  }

  public boolean contains(Point2D p) {
    if (p == null) {
      throw new IllegalArgumentException();
    }

    return contains(root, p, 0);
  }

  public int size() {
    return size(root);
  }

  private int size(Node node) {
    if (node == null) {
      return 0;
    }

    return node.size;
  }

  private boolean contains(Node node, Point2D p, int level) {
    if (node == null) {
      return false;
    }
    if (node.point.equals(p)) {
      return true;
    }

    int compare = compare(node.point, p, level);
    if (compare < 0) {
      return contains(node.right, p, level + 1);
    }
    return contains(node.left, p, level + 1);
  }

  private class Node {
    public Point2D point;
    public Node left;
    public Node right;
    public RectHV rect;
    public int size = 1;

    public Node(Point2D p, RectHV rect) {
      this.point = p;
      this.rect = rect;
    }
  }
}
