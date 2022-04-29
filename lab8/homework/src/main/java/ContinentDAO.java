import java.sql.*;

public class ContinentDAO {

    public void create(String name) throws SQLException {
        Connection connection = Database.getConnection();

        try (PreparedStatement stmt = connection.prepareStatement("insert into continents (NAME) values (?)")) {

            stmt.setString(1, name);
            stmt.executeUpdate();

            System.out.println(stmt);

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public Integer findByName(String name) throws SQLException {
        Connection connection = Database.getConnection();
        try (Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery("select id from continents where name='" + name + "'")) {
                return resultSet.next() ? resultSet.getInt(1) : null;
        }
    }

}
