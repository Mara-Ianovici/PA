package repos;
import entities.ContinentEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class ContinentRepository extends repos.DataRepository<ContinentEntity, Integer> {

    private final EntityManagerFactory entityManagerFactory = PersistenceManager.getInstance().getEntityManagerFactory(); //create it somehow

    public ContinentEntity findById(Integer id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return (ContinentEntity) entityManager.createQuery("select c from ContinentEntity c where c.id =:id").setParameter("id", id).getResultList().get(0);
    }

    public ContinentEntity findByName(String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        return (ContinentEntity) entityManager.createNamedQuery("ContinentEntity.findByName").setParameter("name", name).getResultList().get(0);
    }

}
