import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Computer v1 = new Computer("v1", "ComputerA", "00:00:5e:00:53:af", "97.0.0.1", 0, new String[]{"v2", "v3"}, new Integer[]{10, 50});
        Router v2 = new Router("v2", "RouterA", "127.0.0.1", "00:00:5e:00:53:af", new String[]{"v3", "v4", "v5"}, new Integer[]{20, 20, 20});
        Node v3 = new Switch("v3", "SwitchA", "00:00:5e:00:53:af", new String[]{"v4"}, new Integer[]{20});
        Node v4 = new Switch("v4", "SwitchB", "00:00:5e:00:53:af", new String[]{"v5", "v6"}, new Integer[]{30, 10});
        Router v5 = new Router("v5", "RouterB", "129.0.0.1", "00:00:5e:00:53:af", new String[]{"v6"}, new Integer[]{20});
        Computer v6 = new Computer("v6", "ComputerB", "00:00:5e:00:53:af", "97.0.0.1", 100, new String[]{}, new Integer[]{});

        List<Node> nodesList = new ArrayList<>();
        nodesList.add(v1);
        nodesList.add(v2);
        nodesList.add(v3);
        nodesList.add(v4);
        nodesList.add(v5);
        nodesList.add(v6);

        Network network = new Network(nodesList);
        System.out.println(network);

        network.displayIdentifiable();

        Solver solver = new Solver(network.getNodeList());
        solver.getSolution();

        System.out.println("size in MB " + v6.getStorage(StorageUnits.MEGABYTES, v6.getStorage()));
    }
}
