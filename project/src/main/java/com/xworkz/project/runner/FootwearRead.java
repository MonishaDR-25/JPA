package com.xworkz.project.runner;

import com.xworkz.project.entity.FootwearEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

public class FootwearRead {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory=null;
        EntityManager entityManager=null;

        try {
            entityManagerFactory= Persistence.createEntityManagerFactory("x-workz");
            entityManager=entityManagerFactory.createEntityManager();
            FootwearEntity ref=entityManager.find(FootwearEntity.class,1);
            System.out.println(ref);
        }catch (PersistenceException e){
            System.out.println(e.getMessage());
        }finally {
            if (entityManagerFactory != null) {
                entityManagerFactory.close();
            }
            if (entityManager != null) {
                entityManager.close();
            }
        }

    }
}
