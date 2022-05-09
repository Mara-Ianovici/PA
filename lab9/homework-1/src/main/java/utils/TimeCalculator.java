package utils;

import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import java.util.concurrent.TimeUnit;

public class TimeCalculator {
    private static long startTime;

    @PrePersist
    private static void timeBefore(Object object){
        startTime = System.nanoTime();
    }

    @PostPersist
    private static void getFinalTime(Object object){
        long endTime = System.nanoTime();

        System.out.println("The time of the insertion is:" + (endTime - startTime));
    }
}
