package com.xworkz.bakery.runner;

import com.xworkz.project.entity.SweetEntity;

import javax.persistence.*;

public class SweetCreate {
    public static void main(String[] args) {
        SweetEntity sweetEntity = new SweetEntity();
        sweetEntity.setName("Gulab Jamun");
        sweetEntity.setType("Milk-Based");
        sweetEntity.setPrice(150.0);
        sweetEntity.setColor("Brown");

        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction et = null;

        try {
            emf = Persistence.createEntityManagerFactory("x-workz");
            System.out.println("Connection established");
            em = emf.createEntityManager();
            et = em.getTransaction();

            et.begin();
            em.persist(sweetEntity);
            et.commit();
            System.out.println("Insert success");
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
