package utils;

import dao.CityDAO;
import repos.SisterCityRepository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

public class Utils {
    public static void decideImplementation(String configPath) throws IOException, SQLException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(configPath));
        String text = bufferedReader.readLine();
        if(text.equals("JPA")){
            SisterCityRepository cityRepository = new SisterCityRepository();

            cityRepository.insertCities();

            System.out.println("JPA is in use:");
            System.out.println(cityRepository.findByName("Cambridge"));
        }
        else if(text.equals("JDBC")){
            CityDAO cityDAO = new CityDAO();

            System.out.println("JDBC is in use:");
            System.out.println(cityDAO.findByName("Cambridge"));
        }
        else
        {
            System.out.println("The implementation should be either JPA or JDBC.");
        }
    }
}
