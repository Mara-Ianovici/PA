package repos;

import com.opencsv.CSVReader;
import entities.SisterCity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SisterCityRepository extends repos.DataRepository<SisterCity, Integer> {
    private static final EntityManagerFactory entityManagerFactory = PersistenceManager.getInstance().getEntityManagerFactory(); //create it somehow

    public void create(SisterCity city){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(city);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static int getMinId() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return (int) entityManager.createQuery("select min(c.id) from SisterCity c").getResultList().get(0);
    }

    public static int getMaxId() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return (int) entityManager.createQuery("select max(c.id) from SisterCity c").getResultList().get(0);
    }

    public static String getNameById(int value) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return (String) entityManager.createQuery("select c.name from SisterCity c where c.id = :value").setParameter("value", value).getResultList().get(0);
    }

    public static String getCountryById(int value) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return (String) entityManager.createQuery("select c.countryName from CityEntity c where c.id = :value").setParameter("value", value).getResultList().get(0);
    }

    public SisterCity findById(Integer id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return (SisterCity) entityManager.createQuery("select c from SisterCity c where c.id =:id").setParameter("id", id).getResultList().get(0);
    }

    public SisterCity findByName(String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        return (SisterCity) entityManager.createNamedQuery("SisterCity.findByName").setParameter("name", name).getResultList().get(0);
    }

    public void insertCities() {
        List<List<String>> records = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader("src/main/resources/databook2018.csv"))) {
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                records.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        records.remove(0);

        for (List<String> city : records) {
            SisterCity sisterEntity = new SisterCity();
            sisterEntity.setName(city.get(1).replaceAll("'", " "));

            sisterEntity.setCountry("United Kingdom");

            sisterEntity.setSisterName(city.get(2).replaceAll("'", " "));
            sisterEntity.setSisterCountry(city.get(3).replaceAll("'", " "));

            create(sisterEntity);
        }
    }
}

