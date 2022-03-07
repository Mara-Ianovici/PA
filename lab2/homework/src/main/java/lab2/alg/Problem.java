package lab2.alg;

import lab2.model.Event;
import lab2.model.Room;

import java.util.ArrayList;
import java.util.List;

/**
 * An instance of the Rooms-Events problem.
 *
 * @author Mara Ianovici
 */
public class Problem {
    private List<Event> eventList;
    private List<Room> roomList;

    /**
     * The default constructor. Objects can be added using <code>addEvent</code> and
     * <code>addRoom</code> methods.
     */
    public Problem() {
        this.eventList = new ArrayList<Event>();
        this.roomList = new ArrayList<Room>();
    }

    /**
     * Gets the list of events from the problem's instance.
     * @return A list of unique events.
     */
    public List<Event> getEventList() {
        return eventList;
    }

    /**
     * Gets the list of rooms from the problem's instance.
     * @return A list of unique rooms.
     */
    public List<Room> getRoomList() {
        return roomList;
    }

    /**
     * Adds a unique <code>lab2.model.Room</code> to the instance.
     * This method uses the overridden operator "equals" from the <code>lab2.model.Room</code> class
     * to ensure that no two rooms are the same in the problem instance.
     *
     * @param room an instance of <code>lab2.model.Room</code> object.
     */
    public void addRoom(Room room)
    {
        int index;
        for (index = 0 ; index < roomList.size(); index ++) {
            if(roomList.get(index) == room) {
                System.out.println("lab2.model.Room " + room.getName() + " already exists. It cannot be added.");
                System.exit(1);
            }
        }

        roomList.add(room);
    }

    /**
     * Adds a new unique lab2.model.Event to the instance.
     * Method addEvent uses the overridden operator "equals" from the <code>lab2.model.Event</code> class
     * to ensure that no two events are the same in the problem instance.
     *
     * @param event an instance of <code>event</code> class.
     */
    public void addEvent(Event event)
    {
        int index;
        for (index = 0 ; index < eventList.size(); index ++) {
            if(eventList.get(index)== event) {
                System.out.println("lab2.model.Event " + event.getName() + " already exists. It cannot be added.");
                System.exit(1);
            }
        }

        eventList.add(event);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Events: \n");

        for(Event event : eventList)
            sb.append(event.toString() + '\n');
        sb.append('\n');

        sb.append("Rooms: \n");
        for(Room room : roomList)
            sb.append(room.toString() + '\n');

        return sb.toString();
    }
}
