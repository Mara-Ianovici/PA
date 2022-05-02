package utils;

import database.EntityDAO;
import models.City;
import models.Entity;

import java.sql.SQLException;
import java.util.List;

public class Utils {
    public static double calculateDistance(double latitude1, double latitude2, double longitude1, double longitude2) {
        longitude1 = Math.toRadians(longitude1);
        longitude2 = Math.toRadians(longitude2);
        latitude1 = Math.toRadians(latitude1);
        latitude2 = Math.toRadians(latitude2);

        double doubleLongitude = longitude2 - longitude1;
        double doubleLatitude = latitude2 - latitude1;
        double answer = Math.pow(Math.sin(doubleLatitude / 2), 2) + Math.cos(latitude1) * Math.cos(latitude2)
                * Math.pow(Math.sin(doubleLongitude / 2), 2);

        answer = 2 * Math.asin(Math.sqrt(answer));
        double value = 6371;

        return (answer * value);
    }

    public static void printCitiesDistance(int index1, int index2){
        EntityDAO entityDAO = new EntityDAO();
        List<Entity> cityList= entityDAO.findAll("city");
        City city1 = (City)cityList.get(0);
        City city2 = (City)cityList.get(1);

        double distance = calculateDistance(city1.getLatitude(), city2.getLatitude(), city1.getLongitude(), city2.getLongitude());

        System.out.println("The distance between " + city1.getName() + " and " + city2.getName() + " is " + distance);
    }
}
