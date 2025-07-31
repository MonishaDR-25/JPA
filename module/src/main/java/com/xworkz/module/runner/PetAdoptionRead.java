package com.xworkz.module.runner;

import com.xworkz.project.entity.PetAdoptionEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

public class PetAdoptionRead {
    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            emf = Persistence.createEntityManagerFactory("x-workz");
            em = emf.createEntityManager();

            PetAdoptionEntity pet = em.find(PetAdoptionEntity.class, 1);
            System.out.println("Read Pet Entity: " + pet);
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        } finally {
            if (em != null) em.close();
            if (emf != null) emf.close();
        }
    }
}
