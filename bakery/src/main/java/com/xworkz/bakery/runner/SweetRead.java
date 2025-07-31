package com.xworkz.bakery.runner;

import com.xworkz.project.entity.SweetEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

public class SweetRead {
    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            emf = Persistence.createEntityManagerFactory("x-workz");
            em = emf.createEntityManager();

            SweetEntity sweet = em.find(SweetEntity.class, 1); // Change ID if needed
            System.out.println("Read Sweet Entity: " + sweet);
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        } finally {
            if (em != null) em.close();
            if (emf != null) emf.close();
        }
    }
}
