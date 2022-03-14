public abstract class Node implements Comparable<Node>{
    protected String name, location, hardwareAddress;

    public abstract String getName();
    public abstract String getHardwareAddress();
    public abstract String getLocation();

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
}
