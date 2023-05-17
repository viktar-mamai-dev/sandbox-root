package com.mamay.leetcode.practice.easy3;

import java.util.TreeSet;

public class Seq2102 {
  // code this in idea
  private TreeSet<Node> prev, next;

  private class Node implements Comparable<Node> {
    private String name;
    private Integer score;

    public Node(String name, Integer score) {
      this.name = name;
      this.score = score;
    }

    public String getName() {
      return name;
    }

    public Integer getScore() {
      return score;
    }

    @Override
    public int compareTo(Node node) {
      int comp = Integer.compare(node.getScore(), this.score);
      if (comp != 0) return comp;
      return this.name.compareTo(node.getName());
    }

    @Override
    public boolean equals(Object node) {
      if (!(node instanceof Node)) return false;
      if (this == node) return true;
      Node nodeObj = (Node) node;
      return name.equals(nodeObj.getName());
    }

    @Override
    public int hashCode() {
      return name.hashCode();
    }
  }

  public Seq2102() {
    prev = new TreeSet<>();
    next = new TreeSet<>();
  }

  public void add(String name, int score) {
    Node newNode = new Node(name, score);
    if (prev.contains(newNode)) {
      prev.remove(newNode);
      prev.add(newNode);
    } else if (next.contains(newNode)) {
      next.remove(newNode);
      next.add(newNode);
    } else {
      next.add(newNode);
    }

    if (!prev.isEmpty() && !next.isEmpty() && prev.last().compareTo(next.first()) > 0) {
      Node prevLast = prev.pollLast();
      Node nextFirst = next.pollFirst();
      next.add(prevLast);
      prev.add(nextFirst);
    }
  }

  public String get() {
    Node node = next.pollFirst();
    prev.add(node);
    return node.getName();
  }

  public static void main(String[] args) {
    Seq2102 main = new Seq2102();
    main.add("bradford", 2);
    main.add("branford", 3);
    System.out.println(main.get());
    main.add("alps", 2);
    System.out.println(main.get());
    main.add("orland", 2);
    System.out.println(main.get());
    main.add("orlando", 3);
    System.out.println(main.get());
    main.add("alpine", 2);
    System.out.println(main.get());
    System.out.println(main.get());
  }
}
