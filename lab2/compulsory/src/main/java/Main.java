public class Main {
    public static void main(String[] args) {

        Event javaCourse = new Event(8, 10, "C1", 100);
        System.out.println(javaCourse);

        Room laboratory = new Room(RoomType.LAB, 12, "401");
        System.out.println(laboratory);

        Room lectureHall = new Room(RoomType.LECTURE_HALL, 30, "403");
        System.out.println(lectureHall);
    }
}
