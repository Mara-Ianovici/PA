import com.opencsv.CSVReader;

import java.io.FileReader;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class CreatorDB {

    public static void read(String csvPath) {
        try {

            CSVReader reader = new CSVReader(new FileReader(csvPath));
            reader.readNext();

            List<String> nextLine;

            while ((nextLine = Arrays.stream(reader.readNext()).toList()) != null) {

                addContinent(nextLine.get(5));
                addCountry(nextLine.get(0), nextLine.get(4), nextLine.get(5));
                addCity(nextLine.get(0), nextLine.get(1), nextLine.get(2), nextLine.get(3));

                Database.getConnection().commit();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static void addContinent(String name) throws SQLException {
        ContinentDAO continent = new ContinentDAO();
        Integer isFound = continent.findByName(name);

        if(isFound == null)
            continent.create(name);
    }

    private static void addCountry(String name, String code, String continentName) throws SQLException {
        CountryDAO country = new CountryDAO();

        if(country.findByName(name) == null)
        {
            ContinentDAO continent = new ContinentDAO();
            country.create(name, code, continent.findByName(continentName));
        }
    }

    private static void addCity(String countryName, String name, String latitude, String longitude) throws SQLException {
        CityDAO city = new CityDAO();

        if(city.findByName(name) == null)
        {
            CountryDAO country = new CountryDAO();
            city.create(country.findByName(countryName), name, Float.parseFloat(latitude), Float.parseFloat(longitude));
        }
    }
}
