package repos;

import com.opencsv.CSVReader;
import entities.CityEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CityRepository {
    private static final EntityManagerFactory entityManagerFactory = PersistenceManager.getInstance().getEntityManagerFactory(); //create it somehow

    public void create(CityEntity city){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(city);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static int getMinId() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return (int) entityManager.createQuery("select min(c.id) from CityEntity c").getResultList().get(0);
    }

    public static int getMaxId() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return (int) entityManager.createQuery("select max(c.id) from CityEntity c").getResultList().get(0);
    }

    public static int getPopulationById(int value) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return (int) entityManager.createQuery("select c.population from CityEntity c where c.id = :value").setParameter("value", value).getResultList().get(0);
    }

    public static String getNameById(int value) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return (String) entityManager.createQuery("select c.name from CityEntity c where c.id = :value").setParameter("value", value).getResultList().get(0);
    }

    public static String getCountryById(int value) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return (String) entityManager.createQuery("select c.countryName from CityEntity c where c.id = :value").setParameter("value", value).getResultList().get(0);
    }

    public CityEntity findById(Integer id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return (CityEntity) entityManager.createQuery("select c from CityEntity c where c.id =:id").setParameter("id", id).getResultList().get(0);
    }

    public CityEntity findByName(String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        return (CityEntity) entityManager.createNamedQuery("CityEntity.findByName").setParameter("name", name).getResultList().get(0);
    }

    public void insertCities() {
        List<List<String>> records = new ArrayList<>();

        try {
            CSVReader reader = new CSVReader(new FileReader("src/main/resources/worldcities.csv"));
            reader.readNext();

            List<String> nextLine;
            while ((nextLine = Arrays.asList(reader.readNext())).size() != 0) {
                records.add(nextLine);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        for (List<String> city : records) {
            CityEntity cityEntity = new CityEntity();


            String name = city.get(1).replaceAll("\"", "").replaceAll("'", " ");
            if (name.equals("")) name = " ";
            cityEntity.setName(name);

            String country = city.get(4).replaceAll("\"", "").replaceAll("'", " ");
            if (country.equals("")) country = " ";
            cityEntity.setCountryName(country);

            cityEntity.setIsCapital(city.get(8).equals("\"primary\""));

            cityEntity.setLatitude(Double.parseDouble(city.get(2).replaceAll("\"", "")));
            cityEntity.setLongitude(Double.parseDouble(city.get(3).replaceAll("\"", "")));
            cityEntity.setPopulation(Integer.parseInt(city.get(9)));
            create(cityEntity);
        }
    }
}

