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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Room room = (Room) object;
        return name.equals(room.name);
    }
}
