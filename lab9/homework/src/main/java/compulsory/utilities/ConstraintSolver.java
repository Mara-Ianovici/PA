package compulsory.utilities;

import compulsory.repos.CityRepository;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;

import java.util.HashSet;
import java.util.Set;

public class ConstraintSolver {
    public static void solver(Character letter, Integer lowerBound, Integer upperBound) {
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
