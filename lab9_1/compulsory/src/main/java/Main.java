import entities.CityEntity;
import repos.CityRepository;

public class Main {
    public static void main(String[] args){
        CityRepository cityRepository = new CityRepository();

        System.out.println(cityRepository.findById(1));

        CityEntity cityEntity = new CityEntity();
        cityEntity.setCapital(false);
        cityEntity.setName("Tara2");

        cityRepository.create(cityEntity);

        System.out.println(cityRepository.findById(2));
    }
}
