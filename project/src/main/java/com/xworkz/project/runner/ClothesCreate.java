package com.xworkz.project.runner;

import com.xworkz.project.entity.ClothesEntity;

import javax.persistence.*;

public class ClothesCreate {
    public static void main(String[] args) {
        ClothesEntity clothesEntity = new ClothesEntity();
        clothesEntity.setName("Jeans");
        clothesEntity.setBrand("Levi's");
        clothesEntity.setPrice(1999.0);
        clothesEntity.setSize("L");

        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction et = null;

        try {
            emf = Persistence.createEntityManagerFactory("x-workz");
            em = emf.createEntityManager();
            et = em.getTransaction();

            et.begin();
            em.persist(clothesEntity);
            et.commit();
            System.out.println("Insert success");
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
            if (et != null) {
                et.rollback();
                System.out.println("Rollback done");
            }
        } finally {
            if (em != null) em.close();
            if (emf != null) emf.close();
        }
    }
}
