public class Computer extends Node implements Identifiable, Storage {
    private final String IP;
    private final int storage;

    public Computer(String name, String location, String hardwareAddress, String IP, int storage, String[] nodeNames, Double[] nodeCosts) {
        this.name = name;
        this.location = location;
        this.hardwareAddress = hardwareAddress;
        this.IP = IP;
        this.storage = storage;
        fillTimeCostsMap(nodeNames, nodeCosts);
    }

    public String getIP() {
        return this.IP;
    }

    public int getStorage() {
        return this.storage;
    }
}
