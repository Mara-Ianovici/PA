import java.sql.*;

public class Country extends Entity{
    private final String code, continentName;

    public Country(String name, String code, String continentName) {
        super(name);
        this.code = code;
        this.continentName = continentName;
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

    public String getContinentName() {
        return continentName;
    }

    public String getCode() {
        return code;
    }
}
