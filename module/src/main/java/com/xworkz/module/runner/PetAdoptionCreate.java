package com.xworkz.module.runner;

import com.xworkz.project.entity.PetAdoptionEntity;

import javax.persistence.*;

public class PetAdoptionCreate {
    public static void main(String[] args) {
        PetAdoptionEntity pet = new PetAdoptionEntity();
        pet.setName("Bruno");
        pet.setType("Dog");
        pet.setAge(2);
        pet.setAdopted("No");

        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction et = null;

        try {
            emf = Persistence.createEntityManagerFactory("x-workz");
            em = emf.createEntityManager();
            et = em.getTransaction();

            et.begin();
            em.persist(pet);
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
