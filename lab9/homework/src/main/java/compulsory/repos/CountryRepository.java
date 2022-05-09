package compulsory.repos;

import compulsory.entities.HcountriesEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class CountryRepository extends DataRepository<HcountriesEntity, Integer> {

    private final EntityManagerFactory entityManagerFactory = PersistenceManager.getInstance().getEntityManagerFactory(); //create it somehow

    public HcountriesEntity findById(Integer id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return (HcountriesEntity) entityManager.createQuery("select c from HcountriesEntity c where c.id =:id").setParameter("id", id).getResultList().get(0);
    }

    public HcountriesEntity findByName(String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        return (HcountriesEntity) entityManager.createNamedQuery("HcountriesEntity.findByName").setParameter("name", name).getResultList().get(0);
    }
}
