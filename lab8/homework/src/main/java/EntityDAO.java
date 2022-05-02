import java.sql.*;

public class EntityDAO {

    public void create(Entity entity) throws SQLException {
        Connection connection = Database.getConnection();

        if(entity instanceof Continent)
        {
            System.out.println("continent");
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
            System.out.println("city");
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
            System.out.println("Country");
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

    };
    public Integer findByName(Entity entity) throws SQLException{
        Connection connection = Database.getConnection();
        String query;

        if(entity instanceof Country)
            query = "select id from country where name=";
        else if(entity instanceof Continent)
            query = "select id from continents where name=";
        else query = "select id from cities where name=";

        try (Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(query + "'" + entity.getName() + "'")) {
            return resultSet.next() ? resultSet.getInt(1) : null;
        }
    };
}
