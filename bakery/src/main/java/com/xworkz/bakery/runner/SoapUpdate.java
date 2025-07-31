package com.xworkz.bakery.runner;

import com.xworkz.project.entity.SoapEntity;

import javax.persistence.*;

public class SoapUpdate {
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
            SoapEntity soapEntity = entityManager.find(SoapEntity.class, 1);
            System.out.println("Soap entity for id 1: " + soapEntity);

            if (soapEntity != null) {
                soapEntity.setName("Dettol");
                soapEntity.setBrand("Dettol India Ltd");
                soapEntity.setPrice(45.00);
                soapEntity.setColor("White");

                SoapEntity updatedEntity = entityManager.merge(soapEntity);
                System.out.println("Updated data: " + updatedEntity);
            }

            entityTransaction.commit();
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
            if (entityTransaction != null) {
                entityTransaction.rollback();
                System.out.println("Rollback done");
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
