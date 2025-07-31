package com.xworkz.project.runner;

import com.xworkz.project.entity.FootwearEntity;

import javax.persistence.*;

public class FootwearUpdate {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;

        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("x-workz");
            System.out.println("Connection Established");

            entityManager = entityManagerFactory.createEntityManager();
            entityTransaction = entityManager.getTransaction();

            entityTransaction.begin();
            FootwearEntity footwearEntity = entityManager.find(FootwearEntity.class, 1);
            System.out.println("Footwear entity for id 1: " + footwearEntity);

            if (footwearEntity != null) {
                footwearEntity.setName("Campus");
                footwearEntity.setBrand("Campus Activewear");
                footwearEntity.setPrice(1999.99);
                footwearEntity.setType("Sneakers");

                FootwearEntity updatedEntity = entityManager.merge(footwearEntity);
                System.out.println("Updated data: " + updatedEntity);
            }

            entityTransaction.commit();
        } catch (PersistenceException e) {
            System.out.println("Error during update: " + e.getMessage());
            if (entityTransaction != null) {
                entityTransaction.rollback();
                System.out.println("Transaction rolled back");
            }
        } finally {
            if (entityManager != null) {
                entityManager.close();
                System.out.println("EntityManager closed");
            }
            if (entityManagerFactory != null) {
                entityManagerFactory.close();
                System.out.println("EntityManagerFactory closed");
            }
        }
    }
}
