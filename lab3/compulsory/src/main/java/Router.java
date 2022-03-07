public class Router extends Node {
    public Router(String name, String location, String IP, String hardwareAddress) {
        this.name = name;
        this.location = location;
        this.IP = IP;
        this.hardwareAddress = hardwareAddress;
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
        return super.IP;
    }
}
