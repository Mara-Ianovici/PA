import java.sql.*;

public class CityDAO {
    public void create(int id_country, String name, double latitude, double longitude){
        Connection connection = Database.getConnection();

        try (PreparedStatement stmt = connection.prepareStatement(
                "insert into city (ID_COUNTRY, NAME, IS_CAPITAL, LATITUDE, LONGITUDE) values (?, ?, ?, ?, ?)")) {

            stmt.setInt(1, id_country);
            stmt.setString(2, name);
            stmt.setBoolean(3, true);
            stmt.setDouble(4, latitude);
            stmt.setDouble(5, longitude);

            stmt.executeUpdate();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public Integer findByName(String name) {
        Connection connection = Database.getConnection();
        try (Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery("select id from city where name='" + name + "'")) {
            return resultSet.next() ? resultSet.getInt(1) : null;
        }
        catch(SQLException exception)
        {
            System.out.println(exception.getMessage());
        }

        return null;
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
}
