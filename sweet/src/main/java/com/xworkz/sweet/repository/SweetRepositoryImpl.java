package com.xworkz.sweet.repository;

import com.xworkz.sweet.entity.SweetEntity;

import javax.persistence.*;
import java.util.List;

public class SweetRepositoryImpl implements SweetRepository {

    private EntityManagerFactory emf;

    public SweetRepositoryImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public boolean save(SweetEntity entity) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            System.out.println("Sweet saved successfully.");
            return true;
        } catch (PersistenceException e) {
            System.out.println("Error during save: " + e.getMessage());
        } finally {
            if (em != null) em.close();
        }
        return false;
    }

    @Override
    public SweetEntity readById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(SweetEntity.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public SweetEntity findByName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("findBySweetName", SweetEntity.class)
                    .setParameter("nm", name)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    @Override
    public SweetEntity findByMadeBy(String madeBy) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("findByMadeBy", SweetEntity.class)
                    .setParameter("mb", madeBy)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    @Override
    public SweetEntity findByType(String type) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("findByType", SweetEntity.class)
                    .setParameter("tp", type)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    @Override
    public List<SweetEntity> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("getAllSweets", SweetEntity.class).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void updateNameById(int id, String newName) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            SweetEntity entity = em.find(SweetEntity.class, id);
            if (entity != null) {
                entity.setName(newName);
                em.merge(entity);
                em.getTransaction().commit();
                System.out.println("Sweet name updated.");
            } else {
                System.out.println("No Sweet found for ID: " + id);
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
            SweetEntity entity = em.find(SweetEntity.class, id);
            if (entity != null) {
                em.remove(entity);
                em.getTransaction().commit();
                System.out.println("Sweet deleted.");
            } else {
                System.out.println("No Sweet found for ID: " + id);
            }
        } finally {
            em.close();
        }
    }
}
