package com.xworkz.project.runner;

import com.xworkz.project.entity.FootwearEntity;

import javax.persistence.*;

public class FootwearCreate {
    public static void main(String[] args) {
        FootwearEntity footwearEntity = new FootwearEntity();
        footwearEntity.setName("Air Max");
        footwearEntity.setBrand("Nike");
        footwearEntity.setPrice(4500.00);
        footwearEntity.setType("Sneakers");

        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;

        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("x-workz");
            System.out.println("Connection established");
            entityManager = entityManagerFactory.createEntityManager();
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.persist(footwearEntity);
            entityTransaction.commit();
            System.out.println("Insert success");
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
            if (entityTransaction != null) {
                entityTransaction.rollback();
                System.out.println("Rollback done");
            }
        } finally {
            if (entityManagerFactory != null) {
                entityManagerFactory.close();
            }
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
}
