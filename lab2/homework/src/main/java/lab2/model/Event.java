package lab2.model;

/**
 * Represents an event.
 *
 *  @author Mara Ianovici
 */
public class Event {
    protected String name;
    protected int size;
    protected int startTime;
    protected int endTime;

    /**
     * Creates an event with the specified properties.
     * @param name The event's name (it is unique).
     * @param size The event's size (how many people can attend).
     * @param startTime The event's starting time.
     * @param endTime The event's ending time.
     */
    public Event(String name, int size, int startTime, int endTime) {
        this.name = name;
        this.size = size;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    /**
     * This operator is comparing 2 instances of the class <code>lab2.model.Event</code> by the name.
     * The name is a unique identifier for an <code>event</code>, so no two events with
     * the same <code>name</code> should be added in the array.
     *
     * @param object the object that the comparison is made with.
     * @return
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Event event = (Event) object;
        return name.equals(event.name);
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", size=" + size +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
