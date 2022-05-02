package models;

import java.sql.*;

public class City extends Entity {
    private final String countryName;
    private final double latitude, longitude;

    public City(String name, String countryName, double latitude, double longitude) {
        super(name);
        this.countryName = countryName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getCountryName() {
        return countryName;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
