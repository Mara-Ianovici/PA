import java.sql.*;

public class CountryDAO {
        public void create(String name, String code, int idContinent){
            Connection connection = Database.getConnection();

            try (PreparedStatement stmt = connection.prepareStatement(
                    "insert into countries (NAME, CODE, ID_CONTINENT) values (?, ?, ?)")) {

                stmt.setString(1, name);
                stmt.setString(2, code);
                stmt.setInt(3, idContinent);

                stmt.executeUpdate();

            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }

        public String getCountriesFromContinent(int continentId) throws SQLException {
            Connection connection = Database.getConnection();
            StringBuilder stringBuilder = new StringBuilder();

            try (Statement stmt = connection.createStatement();
                 ResultSet resultSet = stmt.executeQuery("select name from countries where id_continent='" + continentId + "'")) {
                    while(resultSet.next()){
                        stringBuilder.append(resultSet.getString(1)).append(" ");
                    }
            }

            return stringBuilder.toString();
        }

    public Integer findByName(String name) throws SQLException {
        Connection connection = Database.getConnection();
        try (Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery("select id from countries where name='" + name + "'")) {
            return resultSet.next() ? resultSet.getInt(1) : null;
        }
    }
}
