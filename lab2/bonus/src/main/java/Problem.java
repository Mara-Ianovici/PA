import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Problem {
    private List<Event> eventList;
    private List<Room> roomList;

    private static int[] sizeList = new int[]{30, 100};
    private static int[] timeList = new int[]{8, 10, 12, 14, 16};

    public Problem(int eventNr, int computerLabNr, int lectureHallNr) {
        generateRandomElements(eventNr, computerLabNr, lectureHallNr);
    }

    private void generateRandomElements(int eventNr, int computerLabNr, int lectureHallNr) {
        this.eventList = new ArrayList<>(eventNr);
        this.roomList = new ArrayList<>(computerLabNr + lectureHallNr);

        int size;
        LocalTime startTime, endTime;
        String name;

        for (int i = 0; i < eventNr; i++) { //generates a random list of events
            name = "Event" + i;
            size = sizeList[ThreadLocalRandom.current().nextInt(0, 2)]; //randomly picks an index and gets sizeList[index]
            int startHour = timeList[ThreadLocalRandom.current().nextInt(0, 3)];

            startTime = LocalTime.of(startHour, 0);
            endTime = LocalTime.of(startHour + ThreadLocalRandom.current().nextInt(1, 7), 0);

            eventList.add(new Event(name, size, startTime, endTime));
        }

        for (int i = 0; i < computerLabNr; i++) {
            name = "ComputerLab" + i;
            size = 30;
            roomList.add(new ComputerLab(name, size, "Windows"));
        }

        for (int i = 0; i < lectureHallNr; i++) {
            name = "LectureHall" + i;
            size = 100;
            roomList.add(new LectureHall(name, size, true));
        }
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public List<Room> getRoomList() {
        return roomList;
    }
}
