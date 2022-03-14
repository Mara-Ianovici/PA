import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Node v1 = new Computer("v1", "ComputerA", "00:00:5e:00:53:af", "97.0.0.1", 0);
        Node v2 = new Router("v4", "RouterA", "127.0.0.1", "00:00:5e:00:53:af");
        Node v3 = new Switch("v3", "SwitchB", "00:00:5e:00:53:af");

        List<Node> nodesList = new ArrayList<>();
        nodesList.add(v1);
        nodesList.add(v2);
        nodesList.add(v3);
        Network network = new Network(nodesList);

        System.out.println(network);
    }
}
