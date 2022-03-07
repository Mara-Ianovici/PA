import java.time.LocalTime;

public class Event {
    protected String name;
    protected int size;
    protected LocalTime startTime;
    protected LocalTime endTime;

    public Event(String name, int size, LocalTime startTime, LocalTime endTime) {
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

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Event event = (Event) object;
        return name.equals(event.name);
    }
}
