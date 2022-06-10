package course1;

import java.util.Random;

import static java.lang.System.out;

public class Karger {
    public static void main(String[] a) {
        Karger main = new Karger();
        main.runSolution();
    }

    private int kargerMinCut(Graph graph) {
        int V = graph.V, E = graph.E;
        Edge[] edge = graph.edge;

        Subset[] subsets = new Subset[V];

        for (int v = 0; v < V; ++v) {
            subsets[v] = new Subset(v, 0);
        }

        int vertices = V;
        while (vertices > 2) {
            int i = new Random().nextInt(E);

            // Find vertices (or sets) of two corners of current edge
            int subset1 = find(subsets, edge[i].src);
            int subset2 = find(subsets, edge[i].dest);
            if (subset1 != subset2) {
                out.println("Contracting edge " + edge[i].src + " : " + edge[i].dest);
                vertices--;
                Union(subsets, subset1, subset2);
            }
        }

        // Now we have two vertices (or subsets) left in the contracted graph, so count the edges between two components and return the count.
        int cutedges = 0;
        for (int i = 0; i < E; i++) {
            int subset1 = find(subsets, edge[i].src);
            int subset2 = find(subsets, edge[i].dest);
            if (subset1 != subset2)
                cutedges++;
        }

        return cutedges;
    }

    private int find(Subset subsets[], int i) {
        // find root and make root as parent of i (path compression)
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);

        return subsets[i].parent;
    }

    private void Union(Subset subsets[], int x, int y) {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        // Attach smaller rank tree under root of high rank tree (Union by Rank)
        if (subsets[xroot].rank < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[xroot].rank > subsets[yroot].rank)
            subsets[yroot].parent = xroot;

            // If ranks are same, then make one as root and increment its rank by one
        else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }

    private void runSolution() {
        int V = 4;  // Number of vertices in graph
        int E = 5;  // Number of edges in graph

        Graph graph = new Graph(V, E);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);

        out.println("Cut found by Karger's randomized algo is " + kargerMinCut(graph));
    }

    class Edge {
        int src, dest;

        public Edge(int src, int dest) {
            this.src = src;
            this.dest = dest;
        }
    }

    class Graph {
        int V, E;
        Edge[] edge;
        int sz = 0;

        public Graph(int v, int e) {
            V = v;
            E = e;
            edge = new Edge[e];
        }

        private void addEdge(int src, int dest) {
            edge[sz] = new Edge(src, dest);
            sz++;
        }
    }

    class Subset {
        int parent;
        int rank;

        private Subset(int parent, int rank) {
            this.parent = parent;
            this.rank = rank;
        }
    }
}
