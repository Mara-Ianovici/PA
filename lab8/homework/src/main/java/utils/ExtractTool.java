package utils;

import com.opencsv.CSVReader;
import database.Database;
import database.EntityDAO;
import models.City;
import models.Continent;
import models.Country;

import java.io.FileReader;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * Creates the DB.
 */
public class ExtractTool {
    String csvPath;

    public ExtractTool(String csvPath) {
        this.csvPath = csvPath;
    }

    /**
     * Reads from the csv and adds the information to the DB.
     */
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

    /**
     * Verifies if the continent is already in the database.
     * If the continent doesn't exist it is added.
     * @param name The name of the continent to te added.
     */
    private void addContinent(String name) throws SQLException {
        Continent continent = new Continent(name);
        EntityDAO entityDAO = new EntityDAO();

        if(entityDAO.findByName(name, "continent") == null)//if it was not added before
            entityDAO.create(continent);
    }

    /**
     * Verifies if the country is already in the database.
     * If the country doesn't exist it is added.
     */
    private void addCountry(String name, String code, String continentName) throws SQLException {
        Country country = new Country(name, code, continentName);
        EntityDAO entityDAO = new EntityDAO();

        if(entityDAO.findByName(name, "country") == null) //if it was not added before
            entityDAO.create(country);
    }

    /**
     * It adds the city in the DB.
     */
    private void addCity(String countryName, String name, String latitude, String longitude){
        City city = new City(name, countryName, Float.parseFloat(latitude), Float.parseFloat(longitude));
        EntityDAO entityDAO = new EntityDAO();

        entityDAO.create(city);
    }
}
