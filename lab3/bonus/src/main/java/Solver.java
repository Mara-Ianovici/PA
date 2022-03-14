import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Solver {
    private double[][] costMatrix;
    List<Node> nodeList;

    public Solver(List<Node> nodeList) {
        this.nodeList = nodeList;
        initializeCostMatrix(nodeList);
    }

    private void initializeCostMatrix(List<Node> nodeList)
    {
        int indexInNodeList;

        costMatrix = new double[nodeList.size()][nodeList.size()]; // crearea matricei O(n^2)

        for (double[] matrix : costMatrix) Arrays.fill(matrix, 0);

        for(int index = 0; index < costMatrix.length ; index++)
        {
            for(Map.Entry<String, Double> link : nodeList.get(index).timeCosts.entrySet()) //pentru indexul curent parcurg Map-ul
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

    private int minDistance(double[] dist, Boolean[] sptSet)
    {
        double min = Double.MAX_VALUE;
        int min_index = 0;

        for (int v = 0; v < nodeList.size(); v++)
            if (!sptSet[v] && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }

        return min_index;
    }

    private void printSolution(double[] dist, int src)
    {
        System.out.println("Distance from node " + src + "\tto vertex:");

        for (int i = 0; i < nodeList.size(); i++)
            if(nodeList.get(i) instanceof Identifiable)
                if(dist[i] == Double.MAX_VALUE)
                    System.out.println("inf" + " \t " + i);
                else
                    System.out.println(dist[i] + " \t " + i);

        System.out.println("");
    }

    private void dijkstra(double[][] costMatrix, int src)
    {
        double[] dist = new double[nodeList.size()];

        Boolean[] sptSet = new Boolean[nodeList.size()];

        for (int i = 0; i < nodeList.size(); i++) {
            dist[i] = Double.MAX_VALUE;
            sptSet[i] = false;
        }

        dist[src] = 0;

        for (int count = 0; count < nodeList.size() - 1; count++) {
            int u = minDistance(dist, sptSet);

            sptSet[u] = true;

            for (int v = 0; v < nodeList.size(); v++)
                if (!sptSet[v] && costMatrix[u][v] != 0 && dist[u] != Double.MAX_VALUE && dist[u] + costMatrix[u][v] < dist[v])
                    dist[v] = Math.log(dist[u]) + costMatrix[u][v];
        }

        printSolution(dist, src);
    }


    public void getSolution()
    {
        for(int index = 0 ; index < nodeList.size() ; index ++)
        {
            if(nodeList.get(index) instanceof Identifiable) //the start node must be identifiable
                dijkstra(costMatrix, index);
        }
    }

}
