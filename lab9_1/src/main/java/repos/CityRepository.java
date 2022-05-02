package repos;

import entities.CityEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class CityRepository {
    private final EntityManagerFactory entityManagerFactory = PersistenceManager.getInstance().getEntityManagerFactory(); //create it somehow

    public void create(CityEntity city){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(city);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public CityEntity findById(Integer id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return (CityEntity) entityManager.createQuery("select c from CityEntity c where c.id =:id").setParameter("id", id).getResultList();
    }

    public CityEntity findByName(String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        return (CityEntity) entityManager.createNamedQuery("CityEntity.findByName").setParameter("name", name).getResultList();
    }

}

