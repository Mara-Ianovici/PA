public class Router extends Node implements Identifiable {
    private final String IP;

    public Router(String name, String location, String IP, String hardwareAddress, String[] nodeNames, Integer[] nodeCosts) {
        this.name = name;
        this.location = location;
        this.IP = IP;
        this.hardwareAddress = hardwareAddress;
        fillTimeCostsMap(nodeNames, nodeCosts);
    }

    public String getIP() {
        return IP;
    }
}
