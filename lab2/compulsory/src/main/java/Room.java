public class Room {
    private RoomType roomType;
    private int capacity;
    private String name;

    public Room(RoomType roomType, int capacity, String name) {
        this.roomType = roomType;
        this.capacity = capacity;
        this.name = name;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name + '(' +
                "capacity=" + capacity +
                ", roomType=" + roomType +
                ')';
    }
}
