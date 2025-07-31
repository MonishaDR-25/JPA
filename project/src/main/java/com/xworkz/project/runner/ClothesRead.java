package com.xworkz.project.runner;

import com.xworkz.project.entity.ClothesEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

public class ClothesRead {
    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            emf = Persistence.createEntityManagerFactory("x-workz");
            em = emf.createEntityManager();

            ClothesEntity clothes = em.find(ClothesEntity.class, 1);
            System.out.println("Read Clothes Entity: " + clothes);
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        } finally {
            if (em != null) em.close();
            if (emf != null) emf.close();
        }
    }
}
