import com.opencsv.CSVReader;

import java.io.FileReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExtractTool {
    String csvPath;

    public ExtractTool(String csvPath) {
        this.csvPath = csvPath;
    }

    public void read() {
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

    private void addContinent(String name) throws SQLException {
        Continent continent = new Continent(name);
        EntityDAO entityDAO = new EntityDAO();

        Integer isFound = entityDAO.findByName(continent);

        if(isFound == null)//if it was not added before
            entityDAO.create(continent);
    }

    private void addCountry(String name, String code, String continentName) throws SQLException {
        Country country = new Country(name, code, continentName);
        EntityDAO entityDAO = new EntityDAO();

        if(entityDAO.findByName(country) == null) //if it was not added before
            entityDAO.create(country);
    }

    private void addCity(String countryName, String name, String latitude, String longitude) throws SQLException {
        City city = new City(name, countryName, Float.parseFloat(latitude), Float.parseFloat(longitude));
        EntityDAO entityDAO = new EntityDAO();

        if(entityDAO.findByName(city) == null)
            entityDAO.create(city);
    }
}
