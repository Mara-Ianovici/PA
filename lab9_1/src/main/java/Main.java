import repos.CityRepository;

public class Main {
    public static void main(String[] args){
        CityRepository cityRepository = new CityRepository();

        System.out.println(cityRepository.findById(1));
    }
}
