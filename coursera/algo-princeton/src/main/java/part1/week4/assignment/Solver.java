package part1.week4.assignment;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
import java.util.ArrayDeque;

public class Solver {

  private Node lastNode;

  public Solver(Board initial) {
    if (initial == null) {
      throw new IllegalArgumentException("Passing null to the constructor");
    }
    solve(initial);
  }

  public static void main(String[] args) {
    In in = new In(args[0]);
    int n = in.readInt();
    int[][] blocks = new int[n][n];
    for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) blocks[i][j] = in.readInt();

    // long time = System.currentTimeMillis();
    Board initial = new Board(blocks);
    Solver solver = new Solver(initial);

    if (!solver.isSolvable()) StdOut.println("No solution possible");
    else {
      StdOut.println("Minimum number of moves = " + solver.moves());
      for (Board board : solver.solution()) StdOut.println(board);
    }
    /*System.out.println("Time: " + (System.currentTimeMillis() - time));
    StdOut.println("Minimum number of moves = " + solver.moves());
    StdOut.println("Minimum number of 11111 = " + Board.movesCount1);
    StdOut.println("Minimum number of 22222 = " + Board.movesCount2);
    StdOut.println("Minimum number of 33333 = " + Board.movesCount3);*/
  }

  private void solve(Board initial) {
    Node root = new Node(initial);

    MinPQ<Node> trueQueue = new MinPQ<Node>();
    trueQueue.insert(root);

    MinPQ<Node> falseQueue = new MinPQ<Node>();
    falseQueue.insert(new Node(root.board.twin()));

    while (true) {
      Node trueNode = trueQueue.delMin();
      if (trueNode.board.isGoal()) {
        lastNode = trueNode;
        return;
      }

      Node falseNode = falseQueue.delMin();
      // System.out.println("Next false board:\n" + falseNode);
      if (falseNode.board.isGoal()) {
        lastNode = null;
        return;
      }
      for (Board neiBoard : trueNode.board.neighbors()) {
        if (trueNode.predecessor == null || !neiBoard.equals(trueNode.predecessor.board))
          trueQueue.insert(new Node(neiBoard, trueNode));
      }
      for (Board neiBoard : falseNode.board.neighbors()) {
        if (falseNode.predecessor == null || !neiBoard.equals(falseNode.predecessor.board))
          falseQueue.insert(new Node(neiBoard, falseNode));
      }
    }
  }

  public boolean isSolvable() {
    return lastNode != null;
  }

  private Iterable<Board> createSolution(Node trueNode) {
    ArrayDeque<Board> resultList = new ArrayDeque<>();
    Node preNode = trueNode;

    while (preNode != null) {
      resultList.addFirst(preNode.board);
      preNode = preNode.predecessor;
    }
    return resultList;
  }

  public int moves() {
    if (lastNode != null) return lastNode.numberOfMoves;
    return -1;
  }

  public Iterable<Board> solution() {
    if (lastNode != null) {
      return createSolution(lastNode);
    }
    return null;
  }

  private class Node implements Comparable<Node> {

    private Board board;
    private Node predecessor;
    private int numberOfMoves = 0;
    private int cachedPriority = -1;

    public Node(Board board) {
      this.board = board;
    }

    public Node(Board board, Node predecessor) {
      this.board = board;
      this.predecessor = predecessor;
      this.numberOfMoves = predecessor.numberOfMoves + 1;
    }

    private int priority() {
      if (cachedPriority == -1) {
        cachedPriority = numberOfMoves + board.manhattan();
      }

      return cachedPriority;
    }

    @Override
    public int compareTo(Node that) {
      return Integer.compare(this.priority(), that.priority());
    }
  }
}
