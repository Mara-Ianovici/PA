package problem;

import model.interfaces.Item;
import model.main.Catalog;
import org.jgrapht.Graph;
import org.jgrapht.alg.matching.HopcroftKarpMaximumCardinalityBipartiteMatching;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import utils.Utils;

import java.util.HashSet;
import java.util.Set;

import static utils.Utils.getUsedConcepts;


public class Solver {
    /**
     * Uses JGraph to solve the problem (the largest set of pairs (item, concept) such that all items and all concepts
     * in this set are distinct)
     * @param catalog The items from this Catalog are used in the Graph creation.
     */

    public static void solver(Catalog catalog)
    {
        Graph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
        Set<String> partition1 = new HashSet<>();

        for(Item item : catalog.getItemList())
        {
            partition1.add(item.getIdentifier()); //adds item's identifiers in partition1
            graph.addVertex(item.getIdentifier());
        }

        Set<String> partition2 = new HashSet<>(getUsedConcepts(catalog)); //adds item's identifiers in partition2

        for(String concept : Utils.conceptList)
            graph.addVertex(concept);

        for(Item item: catalog.getItemList())
            for(String concept : item.getConceptSet())
                graph.addEdge(item.getIdentifier(), concept);

        var karpAlgorithm = new HopcroftKarpMaximumCardinalityBipartiteMatching<String, DefaultEdge>(graph, partition1, partition2);

        System.out.println(graph);
        System.out.println(karpAlgorithm.getMatching());
    }
}
