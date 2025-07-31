package com.xworkz.module.runner;

import com.xworkz.project.entity.PetAdoptionEntity;

import javax.persistence.*;

public class PetAdoptionUpdate {
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
            PetAdoptionEntity petEntity = em.find(PetAdoptionEntity.class, 1);
            System.out.println("Pet entity for id 1: " + petEntity);

            if (petEntity != null) {
                petEntity.setName("Tommy");
                petEntity.setType("Dog");
                petEntity.setAge(3);
                petEntity.setAdopted("Yes");

                PetAdoptionEntity updatedEntity = em.merge(petEntity);
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
