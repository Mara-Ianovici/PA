import java.sql.*;

public class City extends Entity{
    private String countryName;
    private double latitude, longitude;

    public City(String name, String countryName, double latitude, double longitude) {
        super(name);
        this.countryName = countryName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude(String name) {
        Connection connection = Database.getConnection();
        try (Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery("select latitude from city where name='" + name + "'")) {
            return resultSet.next() ? resultSet.getDouble(1) : null;
        }
        catch(SQLException exception)
        {
            System.out.println(exception.getMessage());
        }

        return null;
    }

    public Double getLongitude(String name) {
        Connection connection = Database.getConnection();
        try (Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery("select longitude from city where name='" + name + "'")) {
            return resultSet.next() ? resultSet.getDouble(1) : null;
        }
        catch(SQLException exception)
        {
            System.out.println(exception.getMessage());
        }

        return null;
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
