public abstract class Node implements Storage, Identifiable {
    protected String name, location, IP, hardwareAddress;
    protected int storage;

    public abstract String getName();
    public abstract String getHardwareAddress();
    public abstract String getLocation();
}
