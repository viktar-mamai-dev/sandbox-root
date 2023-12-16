package course3.week2;

public class Node implements Comparable<Node> {
  int src;
  int dest;
  int weight;

  public Node(int src, int dest, int weight) {
    this.src = src;
    this.dest = dest;
    this.weight = weight;
  }

  @Override
  public int compareTo(Node o) {
    if (this.weight != o.weight) {
      return Integer.compare(this.weight, o.weight);
    }

    if (this.src != o.src) {
      return Integer.compare(this.src, o.src);
    }

    return Integer.compare(this.dest, o.dest);
  }
}
