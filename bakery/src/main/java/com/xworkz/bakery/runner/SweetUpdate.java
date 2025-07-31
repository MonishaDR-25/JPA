package com.xworkz.bakery.runner;

import com.xworkz.project.entity.SweetEntity;

import javax.persistence.*;

public class SweetUpdate {
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
            SweetEntity sweetEntity = em.find(SweetEntity.class, 1);
            System.out.println("Sweet entity for id 1: " + sweetEntity);

            if (sweetEntity != null) {
                sweetEntity.setName("Rasgulla");
                sweetEntity.setType("Bengali");
                sweetEntity.setPrice(120.00);
                sweetEntity.setColor("White");

                SweetEntity updatedEntity = em.merge(sweetEntity);
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
