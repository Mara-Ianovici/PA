public class Switch extends Node {

    public Switch(String name, String location, String hardwareAddress, String[] nodeNames, Double[] nodeCosts)
    {
        this.name = name;
        this.location = location;
        this.hardwareAddress = hardwareAddress;
        fillTimeCostsMap(nodeNames, nodeCosts);
    }
}
