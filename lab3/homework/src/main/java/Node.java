import java.util.HashMap;
import java.util.Map;

public abstract class Node implements Comparable<Node>{
    protected String name, location, hardwareAddress;
    HashMap<String, Integer> timeCosts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHardwareAddress() {
        return hardwareAddress;
    }

    public void setHardwareAddress(String hardwareAddress) {
        this.hardwareAddress = hardwareAddress;
    }

    protected void fillTimeCostsMap(String[] nodeNames, Integer[] nodeCosts)
    {
        if(nodeNames.length != nodeCosts.length)
        {
            System.out.println("The arrays nodeNames and nodeCosts do not have the same length. Can't initialize map.");
            System.exit(1);
        }

        timeCosts = new HashMap<>();
        if(nodeNames.length != 0)
        {
            for(int index = 0 ;index < nodeNames.length; index++)
                timeCosts.put(nodeNames[index], nodeCosts[index]);
        }
    }
    @Override
    public int compareTo(Node node2)
    {
        if(node2.name == null)
        {
            System.out.println("The name of the second node is null.");
            System.exit(1);
        }
        return this.name.compareTo(node2.name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(timeCosts.isEmpty())
            sb.append(name + " --> no connection\n");
        else
            for(Map.Entry<String, Integer> link : timeCosts.entrySet())
                sb.append(name + " --> " + link.getKey() + " " + link.getValue() + "\n");

        return sb.toString();
    }
}
