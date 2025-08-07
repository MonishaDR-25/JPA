package com.xworkz.vegetable.repository;

import com.xworkz.vegetable.entity.VegetableEntity;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

public class VegetableRepositoryImpl implements VegetableRepository {

    private EntityManagerFactory emf;

    public VegetableRepositoryImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public boolean save(VegetableEntity entity) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            System.out.println("Vegetable saved successfully.");
            return true;
        } catch (PersistenceException e) {
            System.out.println("Error during save: " + e.getMessage());
        } finally {
            if (em != null) em.close();
        }
        return false;
    }

    @Override
    public VegetableEntity readById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(VegetableEntity.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public VegetableEntity findByName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("findByVegetableName", VegetableEntity.class)
                    .setParameter("nm", name)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    @Override
    public VegetableEntity findByColor(String color) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("findByColor", VegetableEntity.class)
                    .setParameter("clr", color)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    @Override
    public VegetableEntity findByType(String type) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("findByType", VegetableEntity.class)
                    .setParameter("tp", type)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    @Override
    public List<String> getVegetableName() {
        EntityManager entityManager=null;
        List<String> ref=null;
        try {
            ref=emf.createEntityManager().createNamedQuery("getVegetableName").getResultList();

        }catch (PersistenceException e){
            ref=Collections.emptyList();

        }finally {
            if (entityManager!=null){
                entityManager.close();
            }

        }
        return ref;
    }

    @Override
    public List<Double> getVegetableCost() {
        EntityManager entityManager=null;
        List<Double> ref=null;
        try{
            ref=emf.createEntityManager().createNamedQuery("getVegetableCost").getResultList();

        }catch (PersistenceException e){
            ref=Collections.emptyList();

        }finally {
            if(entityManager!=null){
                entityManager.close();
            }

        }
        return ref;
    }

    @Override
    public List<Object> getVegetableArrivalDate() {
        EntityManager entityManager=null;
        List<Object> ref=null;
        try {
            ref=emf.createEntityManager().createNamedQuery("getVegetableArrivalDate").getResultList();

        }catch (PersistenceException e){
            ref=Collections.emptyList();

        }finally {
            if(entityManager!=null){
                entityManager.close();
            }
        }
        return ref;
    }

    @Override
    public List<Object[]> getVegetableColorAndOrigin() {
        EntityManager entityManager=null;
        List<Object[]> ref=null;
        try {
            ref=emf.createEntityManager().createNamedQuery("getVegetableColorAndOrigin").getResultList();

        }catch (PersistenceException e){
            ref=Collections.emptyList();

        }finally {
            if(entityManager!=null){
                entityManager.close();
            }
        }
        return ref;
    }


    @Override
    public List<VegetableEntity> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("getAllVegetables", VegetableEntity.class).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void updateNameById(int id, String newName) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            VegetableEntity entity = em.find(VegetableEntity.class, id);
            if (entity != null) {
                entity.setName(newName);
                em.merge(entity);
                em.getTransaction().commit();
                System.out.println("Name updated.");
            } else {
                System.out.println("Vegetable not found for ID: " + id);
            }
        } finally {
            em.close();
        }
    }

    @Override
    public void deleteById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            VegetableEntity entity = em.find(VegetableEntity.class, id);
            if (entity != null) {
                em.remove(entity);
                em.getTransaction().commit();
                System.out.println("Vegetable deleted.");
            } else {
                System.out.println("Vegetable not found for ID: " + id);
            }
        } finally {
            em.close();
        }
    }
}
