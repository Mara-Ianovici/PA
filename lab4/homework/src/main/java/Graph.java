import java.util.*;
import java.lang.*;

class Graph {
    private final int vertexNr;
    private Edge[] edge;
    private List<Integer>[] adj;
    private final List<Intersection> intersectionList;

    /**
     * Creates a graph thet represents a city.
     *
     * @param intersectionList The list of intersections in the city. (intersections will represent graph's vertexes)
     * @param streetList       The list of streets in the city. (streets will represent graph's edges)
     */
    Graph(List<Intersection> intersectionList, List<Street> streetList) {
        vertexNr = intersectionList.size();
        initializeAdjList(intersectionList.size());
        initializeEdges(streetList.size());
        this.intersectionList = intersectionList;
    }

    private void initializeEdges(int size) {
        edge = new Edge[size];
        for (int i = 0; i < size; ++i)
            edge[i] = new Edge();
    }

    private void initializeAdjList(int size) {
        adj = new LinkedList[size];
        for (int i = 0; i < size; ++i)
            adj[i] = new LinkedList();
    }

    private int find(Subset[] subsets, int i) {
        // find root and make root as parent of i
        // (path compression)
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);

        return subsets[i].parent;
    }

    private void Union(Subset[] subsets, int x, int y) {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        // Attach smaller rank tree under root
        // of high rank tree (Union by Rank)
        if (subsets[xroot].rank < subsets[yroot].rank)
            subsets[xroot].parent = yroot;

        else if (subsets[xroot].rank > subsets[yroot].rank)
            subsets[yroot].parent = xroot;

            // If ranks are same, then make one as
            // root and increment its rank by one
        else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }

    /**
     * Constructs MST using Kruskal's algorithm.
     */
    public Edge[] KruskalMST() {
        Edge[] result = new Edge[vertexNr];

        int indexResult = 0;

        for (int index = 0; index < vertexNr; index++)
            result[index] = new Edge();

        Arrays.sort(edge);

        Subset[] subsets = new Subset[vertexNr];
        for (int index = 0; index < vertexNr; ++index)
            subsets[index] = new Subset();

        for (int v = 0; v < vertexNr; ++v) {
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }

        int index = 0;

        while (indexResult < vertexNr - 1) {
            Edge next_edge = edge[index++];

            int vertex1 = find(subsets, next_edge.getSrc());
            int vertex2 = find(subsets, next_edge.getDest());
            if (vertex1 != vertex2) {
                result[indexResult++] = next_edge;
                Union(subsets, vertex1, vertex2);
            }
        }

        System.out.println("Following are the edges in " + "the constructed MST");
        int minimumCost = 0;
        for (index = 0; index < indexResult; ++index) {
            System.out.println(result[index].getSrc() + " (" + intersectionList.get(result[index].getSrc()).getName() + ')' + " -- "
                    + result[index].getDest() + " (" + intersectionList.get(result[index].getDest()).getName() + ')'
                    + " == " + result[index].getWeight());
            minimumCost += result[index].getWeight();
        }
        System.out.println("Minimum Cost Spanning Tree " + minimumCost + '\n');

        return result;
    }

    /**
     * Calls the DFS algorithm.
     * @param startVertex
     * @param visited A boolean Array of visited nodes.
     */
    private void dfsUtil(int startVertex, boolean[] visited) {
        visited[startVertex] = true;
        System.out.print(intersectionList.get(startVertex) + " ");

        for (int n : adj[startVertex]) {
            if (!visited[n])
                dfsUtil(n, visited);
        }
    }

    /**
     * Prepares for a DFS recursive algorithm.
     * @param startVertex the vertex from which to start the DFS algorithm.
     */
    public void dfs(int startVertex) {
        boolean[] visited = new boolean[vertexNr];

        dfsUtil(startVertex, visited);
    }

    /**
     * Finds a Hamiltonian circuit using DFS search applied on a MST.
     * @param startVertex The starting point for the DFS algorithm.
     */
    public void getHamiltonianCircle(int startVertex)
    {
        Edge[] edgesKruskal = KruskalMST();
        setAdjLinkedList(edgesKruskal);
        dfs(startVertex);
    }


    private void setAdjLinkedList(Edge[] result) {
        for (Edge edge : result) {
            if (edge.getDest() != edge.getSrc()) {
                adj[edge.getSrc()].add(edge.getDest());
                adj[edge.getDest()].add(edge.getSrc());
            }
        }

    }

    public void setEdge(Edge[] edge) {
        this.edge = edge;
    }

    public Edge[] getEdge() {
        return edge;
    }

    public class Subset {
        int parent, rank;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < edge.length; i++) {
            sb.append("Source: ").append(edge[i].getSrc()).append(" Dest: ").append(edge[i].getDest()).append(" Weight: ").append(edge[i].getWeight()).append("\n");
        }

        return sb.toString();
    }
}

