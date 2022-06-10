package course2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Graph {
    private int V;   // No. of vertices
    private LinkedList<Integer> adj[]; //Adjacency List

    private int componentSize;

    private Graph(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList<Integer>();
    }

    //Function to add an edge into the graph
    private void addEdge(int v, int w) {
        adj[v].add(w);
    }

    // A recursive function to print DFS starting from v
    private void dfs(int v, boolean visited[]) {
        // Mark the current node as visited and print it
        visited[v] = true;
        //System.out.print(v + " ");
        componentSize++;

        // Recur for all the vertices adjacent to this vertex
        for (Integer n : adj[v]) {
            if (!visited[n])
                dfs(n, visited);
        }
    }

    // Function that returns reverse (or transpose) of this graph
    private Graph getTranspose() {
        Graph g = new Graph(V);
        for (int v = 0; v < V; v++) {
            for (Integer w : adj[v]) g.adj[w].add(v);
        }
        return g;
    }

    private void fillOrder(int v, boolean visited[], Stack<Integer> stack) {
        visited[v] = true;

        // Recur for all the vertices adjacent to this vertex
        for (Integer n : adj[v]) {
            if (!visited[n])
                fillOrder(n, visited, stack);
        }

        // All vertices reachable from v are processed by now, push v to Stack
        stack.push(v);
    }

    private void printSCCs() {
        Stack<Integer> stack = new Stack<Integer>();

        // Mark all the vertices as not visited (For first DFS)
        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; i++)
            visited[i] = false;

        // Fill vertices in stack according to their finishing times
        for (int i = 0; i < V; i++)
            if (!visited[i])
                fillOrder(i, visited, stack);

        Graph gr = getTranspose();

        // Mark all the vertices as not visited (For second DFS)
        for (int i = 0; i < V; i++)
            visited[i] = false;

        // Now process all vertices in order defined by Stack
        List<Integer> compSizes = new ArrayList<>();
        while (!stack.empty()) {
            int v = stack.pop();
            gr.componentSize = 0;

            // Print Strongly connected component of the popped vertex
            if (!visited[v]) {
                gr.dfs(v, visited);
            }
            if (gr.componentSize > 3) {
                compSizes.add(gr.componentSize);
            }
        }
        compSizes.sort(Collections.reverseOrder());
        System.out.println(compSizes);
    }

    // Driver method
    public static void main(String args[]) {
        // Create a graph given in the above diagram
        Graph g = new Graph(875715);
        g.readGraph();

        System.out.println("Following are strongly connected components in given graph ");
        g.printSCCs();
    }

    private void readGraph() {
        try (Scanner scanner = new Scanner(new File("src/main/resources/sCC.txt"))) {
            while (scanner.hasNextInt()) {
                int a1 = scanner.nextInt();
                int a2 = scanner.nextInt();
                addEdge(a1, a2);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
