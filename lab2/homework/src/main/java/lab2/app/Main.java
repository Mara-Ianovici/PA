package lab2.app;
import lab2.model.*;
import lab2.alg.*;

/**
 * Is the main project's class.
 * Class <code>lab2.app.Main</code> instantiates all classes and calls the algorithm to solve the specified problem.
 *
 * @author Mara Ianovici
 */
public class Main {
    public static void main(String[] args) {

        Room lectureHall309 = new LectureHall("309", 100, true);
        Room lab401 = new ComputerLab("401", 30, "Windows");
        Room lab403 = new ComputerLab("403", 30, "Windows");
        Room lab405 = new ComputerLab("405", 30, "Windows");

        Event c1 = new Event("C1", 100, 8, 10);
        Event c2 = new Event("C2", 100, 10, 12);
        Event l1 = new Event("L1", 30, 8, 10);
        Event l2 = new Event("L2", 30, 8, 10);
        Event l3 = new Event("L3", 30, 10, 12);

        Problem p1 = new Problem();
        p1.addRoom(lectureHall309);
        p1.addRoom(lab401);
        p1.addRoom(lab403);
        p1.addRoom(lab405);
        p1.addEvent(c1);
        p1.addEvent(c2);
        p1.addEvent(l1);
        p1.addEvent(l2);
        p1.addEvent(l3);

        Solution s1 = new Solution(p1);
        s1.greedyAlgorithm();
    }
}
