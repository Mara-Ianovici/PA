package dao;

import compulsory.db.Database;
import model.City;

import java.sql.*;

public class CityDAO {
    public void create(City city) {
        Connection connection = Database.getConnection();

        try (PreparedStatement stmt = connection.prepareStatement(
                "insert into bonus_cities (NAME, COUNTRY, SISTER_NAME, SISTER_COUNTRY) values (?, ?, ?, ?)")) {

            stmt.setString(1, city.getName());
            stmt.setString(2, city.getCountry());
            stmt.setString(3, city.getSisterName());
            stmt.setString(4, city.getSisterCountry());

            stmt.executeUpdate();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public City findByName(String name) throws SQLException {
        Connection connection = Database.getConnection();

        try (Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery("select country, sister_name, sister_country from sister_cities where name='" + name + "'")) {

            return resultSet.next() ? new City(name, resultSet.getString(1), resultSet.getString(2), resultSet.getString(3)) : null;
        }
    }

    public City findById(Integer id) throws SQLException {
        Connection connection = Database.getConnection();

        try (Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery("select name, country, sister_name, sister_country from sister_cities where id='" + id + "'")) {
            return resultSet.next() ? new City(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)) : null;
        }
    }
}
