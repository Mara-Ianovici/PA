package compulsory.repos;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceManager {
    public static final boolean DEBUG = true;
    private static final PersistenceManager singleton = new PersistenceManager();

    protected EntityManagerFactory entityManagerFactory;

    public static PersistenceManager getInstance(){ return singleton;}

    private PersistenceManager(){}

    protected void createEntityManagerFactory(){
        this.entityManagerFactory = Persistence.createEntityManagerFactory("default");

        if(DEBUG)
            System.out.println("Persistence started at " + new java.util.Date());
    }

    public EntityManagerFactory getEntityManagerFactory(){
        if(entityManagerFactory == null)
            createEntityManagerFactory();
        return entityManagerFactory;
    }

    public void closeEntityManagerFactory(){
        if(entityManagerFactory != null){
            entityManagerFactory.close();
            entityManagerFactory = null;

            if(DEBUG)
            {
                System.out.println("Persistence finished at " + new java.util.Date());
            }
        }
    }
}
