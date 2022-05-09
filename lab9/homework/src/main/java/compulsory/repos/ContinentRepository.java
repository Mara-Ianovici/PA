package compulsory.repos;

import compulsory.entities.CityEntity;
import compulsory.entities.ContinentsEntity;
import compulsory.entities.HcountriesEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class ContinentRepository extends DataRepository<ContinentsEntity, Integer> {

    private final EntityManagerFactory entityManagerFactory = PersistenceManager.getInstance().getEntityManagerFactory(); //create it somehow

    public ContinentsEntity findById(Integer id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return (ContinentsEntity) entityManager.createQuery("select c from HcountriesEntity c where c.id =:id").setParameter("id", id).getResultList().get(0);
    }

    public ContinentsEntity findByName(String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        return (ContinentsEntity) entityManager.createNamedQuery("ContinentsEntity.findByName").setParameter("name", name).getResultList().get(0);
    }

}
