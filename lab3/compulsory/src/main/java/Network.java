import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Network {
    private List<Node> nodeList;

    public Network(List<Node> nodeList) {
        this.nodeList = nodeList;
    }

    public List<Node> getNodeList() {
        return nodeList;
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
