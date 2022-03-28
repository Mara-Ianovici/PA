package utils;

import com.github.javafaker.Faker;
import model.interfaces.Item;
import model.main.Catalog;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.alg.matching.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Contains operations that help with the item's concept's manipulation.
 */
public class Utils {

    public static List<String> conceptList = generateConceptList();

    /**
     * Generates a list of random concepts using Faker Class.
     * @return conceptList
     */
    private static List<String> generateConceptList(){
        List<String> listOfConcepts;
        Faker faker = new Faker();

        listOfConcepts = IntStream.rangeClosed(0, 4).mapToObj(i -> new String(faker.address().streetName())).collect(Collectors.toList());
        return listOfConcepts;
    }

    /**
     * Gets a reunion of all the lists of concepts form the itemList.
     * @param catalog The catalog to get the concepts from.
     * @return A lists of concepts.
     */
    public static Set<String> getUsedConcepts(Catalog catalog){
        Set<String> usedConcepts = new HashSet<>(Utils.conceptList);

        List<Item> tempItemList = catalog.getItemList();

        for(Item item : tempItemList)
            usedConcepts.addAll(item.getConceptSet());

        return usedConcepts;
    }

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
