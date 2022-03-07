package lab2.alg;

import lab2.model.Event;
import lab2.model.Room;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A solution to the Events-Rooms problem.
 *
 * @author Mara Ianovici
 */
public class Solution {
    private List<Event> eventList;
    private List<Room> roomList;

    private List<Event> eventListSolution;
    private List<Room> roomsListSolution;

    /**
     * @param problem an instance of the class <code>problem</code>
     */
    public Solution(Problem problem) {
        eventList = problem.getEventList();
        roomList = problem.getRoomList();

        eventListSolution = new ArrayList<>();
        roomsListSolution = new ArrayList<>();
    }

    /**
     * Finds and prints a solution for the problem, using Greedy choice.
     * This method orders the events by <code>size</code> and <code>endingTime</code>
     * and the rooms by <code>size</code>, for the Greedy choice. It will always print a solution that contains the
     * maximum number of events.
     */
    public void greedyAlgorithm() {
        Collections.sort(roomList, (o1, o2) -> { //sorts roomList asc by capacity
            if (o1.getCapacity() == o2.getCapacity())
                return 0;
            if (o1.getCapacity() > o2.getCapacity())
                return 1;
            return -1;
        });

        Collections.sort(eventList, (o1, o2) -> { //sorts eventList asc by size, endTime
            if (o1.getSize() == o2.getSize()) {
                if (o1.getEndTime() > o2.getEndTime())
                    return 1;
                if (o1.getEndTime() == o2.getEndTime())
                    return 0;
                return -1;
            }
            if (o1.getSize() > o2.getSize())
                return 1;
            return -1;
        });

        for (Event event : eventList) {
            for (Room room : roomList) {
                if (event.getSize() <= room.getCapacity()) {
                    if (checkAndAdd(event, room)) //picks the first room and verifies if the current event can be added
                        break;
                }
            }
        }

        for (int i = 0; i < eventListSolution.size(); i++) {
            System.out.print(eventListSolution.get(i).getName() + " (cap:" + eventListSolution.get(i).getSize()
                    + ", " + eventListSolution.get(i).getStartTime() + "-" + eventListSolution.get(i).getEndTime()
                    + ") -> " + roomsListSolution.get(i).getName() + " (" + roomsListSolution.get(i).getCapacity()
                    + ")\n");
        }

    }

    /**
     * Checks if the current <code>event</code> can be added in the current <code>room</code>.
     *
     * @param event the event that should be added to the final solution.
     * @param room the room in which the event should be added in.
     * @return true (the event does not overlap with the events already added), false otherwise.
     */
    private boolean checkAndAdd(Event event, Room room) {
        int index;

        for (index = 0; index < roomsListSolution.size(); index++)
            if (roomsListSolution.get(index) == room)
                if (eventListSolution.get(index).getEndTime() > event.getStartTime() && eventListSolution.get(index).getStartTime() < event.getEndTime())
                    return false;

        if (index == roomsListSolution.size()) { //if the for loop did not break
            eventListSolution.add(event);
            roomsListSolution.add(room);
            return true;
        }
        return false;
    }
}
