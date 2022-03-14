import java.util.*;

public class Network {
    private List<Node> nodeList;

    public Network(List<Node> nodeList) {
        this.nodeList = nodeList;
    }

    public List<Node> getNodeList() {
        return nodeList;
    }

    public void displayIdentifiable(){
        List<Node> identifiableNodes = new ArrayList<>();

        for(Node node : nodeList)
            if(node instanceof Identifiable) {
                identifiableNodes.add(node);
                ((Identifiable)node).getIP();
            }

        Collections.sort(identifiableNodes, new Comparator<Node>()
        {
            @Override
            public int compare(Node node1, Node node2){
                return ((Identifiable)node1).getIP().compareTo(((Identifiable)node2).getIP());
            }
        });

        StringBuilder sb = new StringBuilder();
        for(Node node : identifiableNodes)
            sb.append(node.getName() + " " + ((Identifiable)node).getIP() + "\n");

        System.out.println(sb);
    }

    @Override
    public String toString() {
        Collections.sort(nodeList);
        StringBuilder sb = new StringBuilder();
        for (Node node : nodeList)
            sb.append(node.toString());

        return sb.toString();
    }
}
