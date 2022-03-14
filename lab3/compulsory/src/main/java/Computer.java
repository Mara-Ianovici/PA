public class Computer extends Node implements Storage, Identifiable{
    private final String IP;
    private final int storage;

    public Computer(String name, String location, String hardwareAddress, String IP, int storage) {
        this.name = name;
        this.location = location;
        this.hardwareAddress = hardwareAddress;
        this.IP = IP;
        this.storage = storage;
    }

    @Override
    public String getName() {
        return super.name;
    }

    @Override
    public String getHardwareAddress() {
        return super.hardwareAddress;
    }

    @Override
    public String getLocation() {
        return super.location;
    }

    public String getIP() {
        return this.IP;
    }

    public int getStorage() {
        return this.storage;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "name='" + name + '\'' +
                ", IP='" + IP + '\'' +
                ", storage=" + storage +
                ", location='" + location + '\'' +
                ", hardwareAddress='" + hardwareAddress + '\'' +
                ", storage=" + storage +
                "}\n";
    }
}
