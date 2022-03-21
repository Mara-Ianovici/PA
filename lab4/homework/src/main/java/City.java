import java.util.*;
import java.util.stream.Collectors;

public class City {
    Map<Intersection, List<Street>> cityMap;
    List<Street> streetList;
    List<Intersection> intersectionList;

    public City(Set<Intersection> intersectionSet, List<Street> streetList) {
        initializeMap(intersectionSet, streetList);

        this.streetList = streetList;
        this.intersectionList = intersectionSet.stream().toList();
    }

    /**
     * Initializes the cityMap.
     * For each intersection, it will search the streets that are joined by it and add them to the streetList.
     * @param intersectionSet The intersections to be added in the map.
     * @param streetList The streets to be added in the map.
     */
    private void initializeMap(Set<Intersection> intersectionSet, List<Street> streetList)
    {
        cityMap = new HashMap<>();

        for(Intersection intersection : intersectionSet){
            List<Street> intersectionStreets = new ArrayList<Street>();

            for(Street street : streetList)
                if(street.containsIntersection(intersection)){
                    intersectionStreets.add(street);
            }
            cityMap.put(intersection, intersectionStreets);
        }
    }

    /**
     * Gets the number of streets joined by an intersection.
     * @param intersection
     * @return The number of streets joined.
     */
    private int getStreetsJoined(Intersection intersection)
    {
        return cityMap.get(intersection).size();
    }

    /**
     * Returns a street list that has a bigger length than the minimumLength and joins at least 3 streets.
     * @param minimumLength An integer specifying the minimum number of streets
     * @return A street list with the specified property
     */
    public List<Street> getCustomStreetList(int minimumLength)
    {
        return streetList.stream().filter(s -> s.getLength() >= minimumLength).
                filter(s-> this.getStreetsJoined(s.getIntersection()[0]) + this.getStreetsJoined(s.getIntersection()[1]) >= 3).collect(Collectors.toList());
    }

    /**
     * Solves the problem and bonus problems.
     * @param problemNumber 0 = solves homework, 1 = solves bonus
     */
    public void solveProblem(int problemNumber) {
        Graph graph = new Graph(intersectionList, streetList);
        Edge[] edge = graph.getEdge();

        for (int index = 0; index < streetList.size(); index++) {
            edge[index].setSrc(intersectionList.indexOf(streetList.get(index).getIntersection()[0]));
            edge[index].setDest(intersectionList.indexOf(streetList.get(index).getIntersection()[1]));
            edge[index].setWeight((int)(streetList.get(index).getLength()));
        }
        graph.setEdge(edge);

        if(problemNumber == 0)
            graph.KruskalMST();
        else
            graph.getHamiltonianCircle(8);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<Intersection,List<Street>> entry : cityMap.entrySet()){

            sb.append(entry.getKey().getName() + "{ ");
            for(Street street : entry.getValue()){
                sb.append(street.getName() + ' ');
            }
            sb.append("}\n");
        }
        return sb.toString();
    }
}
