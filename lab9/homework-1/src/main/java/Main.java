import entities.CityEntity;
import repos.CityRepository;
import utils.DBUtils;

public class Main {
    public static void main(String[] args){
        CityRepository cityRepository = new CityRepository();

        DBUtils.printInsertionTime();

        System.out.println(cityRepository.findById(1));
        System.out.println(cityRepository.findByName("Ankara"));

//        CityEntity cityEntity = new CityEntity();
//        cityEntity.setIsCapital(false);
//        cityEntity.setCountryName("Romania");
//        cityEntity.setName("Vatra Dornei");
//
//        cityRepository.create(cityEntity);

//        DBUtils.chocoSolver('a', 6_000_000, 8_000_000);

    }
}
