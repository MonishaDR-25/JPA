package com.xworkz.project.runner;

import com.xworkz.project.entity.ClothesEntity;

import javax.persistence.*;

public class ClothesUpdate {
    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction et = null;

        try {
            emf = Persistence.createEntityManagerFactory("x-workz");
            System.out.println("Connection Established");

            em = emf.createEntityManager();
            et = em.getTransaction();

            et.begin();
            ClothesEntity clothesEntity = em.find(ClothesEntity.class, 1);
            System.out.println("Clothes entity for id 1: " + clothesEntity);

            if (clothesEntity != null) {
                clothesEntity.setName("T-Shirt");
                clothesEntity.setBrand("Roadster");
                clothesEntity.setPrice(499.99);
                clothesEntity.setSize("M");

                ClothesEntity updatedEntity = em.merge(clothesEntity);
                System.out.println("Updated data: " + updatedEntity);
            }

            et.commit();
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
            if (et != null) {
                et.rollback();
                System.out.println("Rollback done");
            }
        } finally {
            if (em != null) {
                em.close();
                System.out.println("EntityManager closed");
            }
            if (emf != null) {
                emf.close();
                System.out.println("EntityManagerFactory closed");
            }
        }
    }
}
