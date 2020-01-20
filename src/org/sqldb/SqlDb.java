package org.sqldb;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SqlDb {
    private static EntityManagerFactory emf;

    public SqlDb(){
        emf = getEMF();
    }

    public static EntityManager getSqlDb(){
        return getEMF().createEntityManager();
    }

    private static EntityManagerFactory getEMF(){
        if(emf == null)
            emf = Persistence.createEntityManagerFactory("org.sqldb");
        return emf;
    }
}
