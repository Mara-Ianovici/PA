package database;

import models.City;
import models.Continent;
import models.Country;
import models.Entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EntityDAO {

    /**
     * Inserts a city, continent or country into the DB.
     */
    public void create(Entity entity){
        Connection connection = Database.getConnection();

        if(entity instanceof Continent)
        {
            try (PreparedStatement stmt = connection.prepareStatement("insert into continents (NAME) values (?)")) {

                stmt.setString(1, entity.getName());
                stmt.executeUpdate();

                System.out.println(stmt);

            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
        else if(entity instanceof City)
        {
            try (PreparedStatement stmt = connection.prepareStatement(
                    "insert into cities (COUNTRY_NAME, NAME, IS_CAPITAL, LATITUDE, LONGITUDE) values (?, ?, ?, ?, ?)")) {

                stmt.setString(1, ((City)entity).getCountryName());
                stmt.setString(2, entity.getName());
                stmt.setBoolean(3, true);
                stmt.setDouble(4, ((City)entity).getLatitude());
                stmt.setDouble(5, ((City)entity).getLongitude());

                stmt.executeUpdate();

            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
        else if(entity instanceof Country)
        {
            try (PreparedStatement stmt = connection.prepareStatement(
                    "insert into country (NAME, CODE, CONTINENT_NAME) values (?, ?, ?)")) {

                stmt.setString(1, entity.getName());
                stmt.setString(2, ((Country)entity).getCode());
                stmt.setString(3, ((Country)entity).getContinentName());

                stmt.executeUpdate();

            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }

    }

    public Entity findByName(String name, String tableName) throws SQLException{
        Connection connection = Database.getConnection();

        switch (tableName) {
            case "country":
                try (Statement stmt = connection.createStatement();
                     ResultSet resultSet = stmt.executeQuery("select code, continent_name from country where name='" + name + "'")) {
                    return resultSet.next() ? new Country(name, resultSet.getString(1), resultSet.getString(2)) : null;

                }
            case "continent":
                try (Statement stmt = connection.createStatement();
                     ResultSet resultSet = stmt.executeQuery("select id from continents where name='" + name + "'")) {
                    return resultSet.next() ? new Continent(name) : null;

                }
            case "city":
                try (Statement stmt = connection.createStatement();
                     ResultSet resultSet = stmt.executeQuery("select country_name, latitude, longitude from cities where name='" + name + "'")) {
                    return resultSet.next() ? new City(name, resultSet.getString(1), resultSet.getDouble(2), resultSet.getDouble(3)) : null;
                }
        }

        return null;
    }

    public Entity findById(int id, String tableName) throws SQLException{
        Connection connection = Database.getConnection();

        switch (tableName) {
            case "country":
                try (Statement stmt = connection.createStatement();
                     ResultSet resultSet = stmt.executeQuery("select name, code, continent_name from country where id='" + id + "'")) {
                    return resultSet.next() ? new Country(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3)) : null;

                }
            case "continent":
                try (Statement stmt = connection.createStatement();
                     ResultSet resultSet = stmt.executeQuery("select name from continents where id='" + id + "'")) {
                    return resultSet.next() ? new Continent(resultSet.getString(1)) : null;

                }
            case "city":
                try (Statement stmt = connection.createStatement();
                     ResultSet resultSet = stmt.executeQuery("select name, country_name, latitude, longitude from cities where id='" + id + "'")) {
                    return resultSet.next() ? new City(resultSet.getString(1), resultSet.getString(2), resultSet.getDouble(3), resultSet.getDouble(3)) : null;
                }
        }

        return null;
    }

    public List<Entity> findAll(String tableName) {
        Connection connection = Database.getConnection();
        List<Entity> entityList = new ArrayList<>();

        switch (tableName) {
            case "country":
                try (Statement stmt = connection.createStatement();
                     ResultSet resultSet = stmt.executeQuery("select name, code, continent_name from country")) {

                    while (resultSet.next())
                        entityList.add(new Country(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3)));
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            case "continent":
                try (Statement stmt = connection.createStatement();
                     ResultSet resultSet = stmt.executeQuery("select name from continents")) {
                    while (resultSet.next()) entityList.add(new Continent(resultSet.getString(1)));
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            case "city":
                try (Statement stmt = connection.createStatement();
                     ResultSet resultSet = stmt.executeQuery("select name, country_name, latitude, longitude from cities")) {
                    while (resultSet.next())
                        entityList.add(new City(resultSet.getString(1), resultSet.getString(2), resultSet.getDouble(3), resultSet.getDouble(4)));
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }

        }
        return entityList;
    }
 }
