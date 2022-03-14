import java.util.Arrays;
import java.util.List;
import java.util.Map;

/***
 * Used to find the solution to the minimum time problem.
 * It uses Dijkstra Algorithm to find the shortest path between nodes.
 */

public class Solver {
    private int[][] costMatrix;
    List<Node> nodeList;

    public Solver(List<Node> nodeList) {
        this.nodeList = nodeList;
        initializeCostMatrix(nodeList);
    }

    /***
     * Processes the list of nodes and turns it into an adjacency matrix with costs. 4
     * If matrix[index1][index2] = c > 0, then c is the cost to reach from index1 to index2.
     * @param nodeList
     */
    private void initializeCostMatrix(List<Node> nodeList)
    {
        int indexInNodeList;

        costMatrix = new int[nodeList.size()][nodeList.size()]; // creating matrix

        for (int[] matrix : costMatrix) Arrays.fill(matrix, 0);

        for(int index = 0; index < costMatrix.length ; index++)
        {
            for(Map.Entry<String, Integer> link : nodeList.get(index).timeCosts.entrySet()) //for the current index, loops through the map
            {
                try {
                    indexInNodeList = getIndexByName(link.getKey());
                    costMatrix[index][indexInNodeList] = link.getValue();
                }
                catch(Exception e)
                {
                    System.out.println("The index is out of bounds! Error message: " + e.getMessage());
                }
            }
        }
    }

    /***
     * Returns the index from the nodeList
     * @param name The name of the node.
     * @return integer representing the index in the nodeList.
     */
    private int getIndexByName(String name)
    {
        for(int index = 0 ; index < nodeList.size() ; index ++)
        {
            if(nodeList.get(index).name.equals(name))
                return index;
        }

        System.out.println("The algorithm for shortest time crashed.");
        return -1;
    }

    /**
     * Finds the vertex with minimum distance, from the set of vertices not yet included in the shortest path tree.
     * @param dist Integer Array where the minimum distances are retained.
     * @param isAdded a boolean Array where sptSet[index] = true <=> index was not added in the path.
     * @return Integer representing the minimum distance
     */
    private int getMinDistance(int []dist, Boolean []isAdded)
    {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int index = 0; index < nodeList.size(); index++)
            if (!isAdded[index] && dist[index] <= min) {
                min = dist[index];
                min_index = index;
            }

        return min_index;
    }

    private void printSolution(int []dist, int src)
    {
        System.out.println("Distance from node " + src + "\tto vertex:");

        for (int index = 0; index < nodeList.size(); index++)
            if(nodeList.get(index) instanceof Identifiable) //the node is printed only if it is Identifiable
                if(dist[index] == Integer.MAX_VALUE)
                    System.out.println("inf" + " \t\t\t\t\t " + index);
                else
                    System.out.println(dist[index] + " \t\t\t\t\t\t " + index);
        System.out.println("");
    }

    private void dijkstra(int[][] costMatrix, int src)
    {
        int[] dist = new int[nodeList.size()];

        Boolean[] isAdded = new Boolean[nodeList.size()];

        for (int index = 0; index < nodeList.size(); index++) {
            dist[index] = Integer.MAX_VALUE; //the distance to all nodes equals infinite in the beginning
            isAdded[index] = false; //no node was added in the solution yet
        }

        dist[src] = 0;

        for (int count = 0; count < nodeList.size() - 1; count++) {
            int minDistance = getMinDistance(dist, isAdded);

            isAdded[minDistance] = true;

            for (int v = 0; v < nodeList.size(); v++)
                if (!isAdded[v] && costMatrix[minDistance][v] != 0 && dist[minDistance] != Integer.MAX_VALUE && dist[minDistance] + costMatrix[minDistance][v] < dist[v])
                    dist[v] = dist[minDistance] + costMatrix[minDistance][v];
        }

        printSolution(dist, src);
    }

    /**
     * Calls Dijkstra's Algorithm to calculate the distance between Identifiable nodes.
     */
    public void getSolution()
    {
        for(int index = 0 ; index < nodeList.size() ; index ++)
        {
            if(nodeList.get(index) instanceof Identifiable)//the start node must be identifiable
                dijkstra(costMatrix, index);
        }
    }

}
