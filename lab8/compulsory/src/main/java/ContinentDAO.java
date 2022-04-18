import java.sql.*;

public class ContinentDAO {
    private static int counterId = 4;

    public void create(String name) throws SQLException {
        Connection connection = Database.getConnection();

        try (PreparedStatement stmt = connection.prepareStatement("insert into continents (ID, NAME) values (?, ?)")) {

            stmt.setInt(1, counterId);
            stmt.setString(2, name);
            stmt.executeUpdate();
            counterId ++;

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

//    public String findById(int id) throws SQLException {
//        Connection con = Database.getConnection();
//        try (Statement stmt = con.createStatement();
//             ResultSet rs = stmt.executeQuery("select name from continents where id='" + name + "'")) {
//            return rs.next() ? rs.getInt(1) : null;
//        }
//    }

}
