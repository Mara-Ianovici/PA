import java.sql.*;

public class CountryDAO {
        public void create(String name, int idContinent){
            Connection connection = Database.getConnection();

            try (PreparedStatement stmt = connection.prepareStatement(
                    "insert into countries (NAME, ID_CONTINENT) values (?, ?)")) {

//                stmt.setInt(1, id);
                stmt.setString(1, name);
                stmt.setInt(2, idContinent);

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
}
