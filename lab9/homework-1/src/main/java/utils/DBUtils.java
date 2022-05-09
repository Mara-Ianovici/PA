package utils;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;
import repos.CityRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class DBUtils {
    public static void printInsertionTime()
    {
        CityRepository cityRepository = new CityRepository();

        long startTime = System.nanoTime();

        cityRepository.insertCities();

        long endTime = System.nanoTime();

        System.out.println("Cities insertion time: " +
                TimeUnit.SECONDS.convert(endTime - startTime, TimeUnit.NANOSECONDS)
                + "seconds");
    }

    public static void chocoSolver(Character letter, Integer lowerBound, Integer upperBound) {
        Model model = new Model();
        IntVar firstId = model.intVar("firstId", CityRepository.getMinId(), CityRepository.getMaxId());
        IntVar secondId = model.intVar("secondId", CityRepository.getMinId(), CityRepository.getMaxId());

        model.arithm(firstId, "<", secondId).post();
        System.out.println(CityRepository.getMinId());
        System.out.println(CityRepository.getMaxId());

        Set<String> citySet = new HashSet<>();
        while (model.getSolver().solve()) {
            int populationResult = CityRepository.getPopulationById(firstId.getValue()) + CityRepository.getPopulationById(secondId.getValue());
            if (populationResult >= lowerBound && populationResult <= upperBound)
                if (CityRepository.getNameById(firstId.getValue()).startsWith(String.valueOf(letter)) && CityRepository.getNameById(secondId.getValue()).startsWith(String.valueOf(letter)))
                    if (!CityRepository.getCountryById(firstId.getValue()).equals(CityRepository.getCountryById(secondId.getValue()))) {
                        citySet.add(CityRepository.getNameById(firstId.getValue()));
                        citySet.add(CityRepository.getNameById(secondId.getValue()));
                    }
        }

        System.out.println(citySet);
    }
}
