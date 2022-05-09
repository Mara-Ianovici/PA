package repos;

import entities.CountryEntity;
import repos.PersistenceManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class CountryRepository extends repos.DataRepository<CountryEntity, Integer> {

    private final EntityManagerFactory entityManagerFactory = PersistenceManager.getInstance().getEntityManagerFactory(); //create it somehow

    public CountryEntity findById(Integer id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return (CountryEntity) entityManager.createQuery("select c from CountryEntity c where c.id =:id").setParameter("id", id).getResultList().get(0);
    }

    public CountryEntity findByName(String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        return (CountryEntity) entityManager.createNamedQuery("CountryEntity.findByName").setParameter("name", name).getResultList().get(0);
    }
}
