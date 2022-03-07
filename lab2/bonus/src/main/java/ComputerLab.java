public class ComputerLab extends Room {
    private String computerOS;

    public ComputerLab(String name, int capacity, String computerOS) {
        super(name, capacity);
        this.computerOS = computerOS;
    }

    public String getComputerOS() {
        return computerOS;
    }

    public void setComputerOS(String computerOS) {
        this.computerOS = computerOS;
    }

}
