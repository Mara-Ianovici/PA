import dao.CityDAO;
import repos.SisterCityRepository;
import utils.Utils;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        try {
            Utils.decideImplementation("src/main/resources/config.txt");
        }
        catch(IOException exception)
        {
            exception.printStackTrace();
        }
    }

}
