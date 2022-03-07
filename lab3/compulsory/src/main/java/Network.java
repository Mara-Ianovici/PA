import java.util.ArrayList;
import java.util.List;

public class Network {
    private List<Node> nodeList;

    public Network(List<Node> nodeList) {
        this.nodeList = nodeList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node node : nodeList) {
            sb.append(node.getName() + " " + node.getLocation() + " " + node.getHardwareAddress() + " " +
                    node.getIP() + " " + node.getStorage() + "\n");
        }
        return sb.toString();
    }
}
