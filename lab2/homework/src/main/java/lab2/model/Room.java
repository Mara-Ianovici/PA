package lab2.model;

/**
 * Is the abstract base class for the personalised room types lab2.model.ComputerLab and lab2.model.LectureHall.
 *
 * @author Mara Ianovici
 */
public abstract class Room {
    protected String name;
    protected int capacity;

    public Room(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public String getName() {
        return this.name;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * This operator is comparing 2 instances of the class <code>lab2.model.Room</code> by the name.
     * The name is an unique identifier for a <code>room</code>, so no two rooms with
     * the same <code>name</code> should be added in the array.
     *
     * @param object the object that the comparison is made with
     * @return
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Room room = (Room) object;
        return name.equals(room.name);
    }
}
