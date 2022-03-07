package lab2.model;

/**
 * Extends the abstract class <code>lab2.model.Room</code> and adds a new characteristic: if it has a projector.
 *
 * @author Mara Ianovici
 */
public class LectureHall extends Room {
    private boolean hasProjector;

    /**
     * Uses the constructor from the <code>lab2.model.Room</code> class and additionally sets the attribute hasProjector.
     *
     * @param name A string that represents the name of the room.
     * @param capacity An int that represents the capacity of the room.
     * @param hasProjector A boolean value, specific to a lecture hall.
     */
    public LectureHall(String name, int capacity, boolean hasProjector) {
        super(name, capacity);
        this.hasProjector = hasProjector;
    }

    public boolean isHasProjector() {
        return hasProjector;
    }

    public void setHasProjector(boolean hasProjector) {
        this.hasProjector = hasProjector;
    }

    @Override
    public String toString() {
        return "LectureHall{" +
                " name='" + super.name + '\'' +
                ", capacity=" + super.capacity +
                ", hasProjector=" + hasProjector +
                '}';
    }
}
