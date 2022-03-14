public class Switch extends Node {

    public Switch(String name, String location, String hardwareAddress)
    {
        this.name = name;
        this.location = location;
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

    @Override
    public String toString() {
        return "Switch\t{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", hardwareAddress='" + hardwareAddress + '\'' +
                "}\n";
    }
}
