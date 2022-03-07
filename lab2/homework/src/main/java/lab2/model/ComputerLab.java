package lab2.model;

/**
 * Extends the abstract class <code>lab2.model.Room</code> and adds a new characteristic: computer's OS.
 *
 * @author Mara Ianovici
 */
public class ComputerLab extends Room {
    private String computerOS;

    /**
     * Uses the constructor from the <code>lab2.model.Room</code> class and additionally sets the attribute computerOS.
     *
     * @param name A string that represents the name of the room.
     * @param capacity An int that represents the capacity of the room.
     * @param computerOS A string representing computer's OS, specific to a computer lab.
     */
    public ComputerLab(String name, int capacity, String computerOS) {
        super(name, capacity);
        this.computerOS = computerOS;
    }

    /**
     * Returns computer's OS.
     * @return A string that represents the computer's OS.
     */
    public String getComputerOS() {
        return computerOS;
    }

    /**
     * Sets the computer's OS.
     * @param computerOS A string that represents computer's OS.
     */
    public void setComputerOS(String computerOS) {
        this.computerOS = computerOS;
    }


    @Override
    public String toString() {
        return "ComputerLab{" +
                " name='" + super.name + '\'' +
                ", capacity=" + super.capacity +
                ", computerOS='" + computerOS + '\'' +
                '}';
    }
}
