import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    private List<Event> eventList;
    private List<Room> roomList;

    private List<Event> eventListSolution;
    private List<Room> roomsListSolution;

    public Solution(Problem p) {
        eventList = p.getEventList();
        roomList = p.getRoomList();

        eventListSolution = new ArrayList<>();
        roomsListSolution = new ArrayList<>();
    }

    public void greedyAlgorithm() {
        long t0 = System.currentTimeMillis();

        Collections.sort(roomList, (o1, o2) -> {
            if (o1.capacity == o2.capacity)
                return 0;
            if (o1.capacity > o2.capacity)
                return 1;
            return -1;
        });

        Collections.sort(eventList, (o1, o2) -> {
            if (o1.size == o2.size) {
                return o1.getEndTime().compareTo(o2.getEndTime());
            }
            if (o1.size > o2.size)
                return 1;
            return -1;
        });

        for (Event event : eventList) {
            for (Room room : roomList) {
                if (event.size <= room.capacity) {
                    if (checkAndAdd(event, room))
                        break;
                }
            }
        }

        long t1 = System.currentTimeMillis();
        System.out.println("The time to solve the problem with Greedy algorithm: " + (t1 - t0));
    }

    private boolean checkAndAdd(Event event, Room room) {
        int index;

        for (index = 0; index < roomsListSolution.size(); index++) {
            Event eventSol = eventListSolution.get(index);
            if (roomsListSolution.get(index) == room)
                if (eventSol.startTime.isBefore(event.endTime) && event.startTime.isBefore(eventSol.endTime))
                    return false;
        }

        eventListSolution.add(event);
        roomsListSolution.add(room);
        return true;
    }

    public void DSatur() {
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int index = 0; index < eventList.size(); index++) {
            adjacencyList.add(new ArrayList<>());
        }

        for (int index1 = 0; index1 < eventList.size() - 1; index1++) {
            Event event1 = eventList.get(index1);
            for (int index2 = index1 + 1; index2 < eventList.size(); index2++) {
                Event event2 = eventList.get(index2);

                if (event1.startTime.isBefore(event2.endTime) && event2.startTime.isBefore(event1.endTime)){
                    adjacencyList.get(index1).add(index2);
                    adjacencyList.get(index2).add(index1);
                    /*
                    When adapting DSatur for the current problem, the nodes are represented by the events and are
                    adjacent if the time intervals overlap. Colors are represented by the rooms and there will be an
                    additional verification if a node can be colored with a certain color (event.size <= room.capacity).
                    */
                }
            }
        }

    }

}
