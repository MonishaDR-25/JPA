package com.xworkz.place.repository;

import com.xworkz.place.entity.PlaceEntity;

import javax.persistence.*;
import java.util.List;

public class PlaceRepositoryImpl implements PlaceRepository {

    private EntityManagerFactory emf;

    public PlaceRepositoryImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public boolean save(PlaceEntity entity) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            System.out.println("Place saved successfully.");
            return true;
        } catch (PersistenceException e) {
            System.out.println("Error during save: " + e.getMessage());
        } finally {
            if (em != null) em.close();
        }
        return false;
    }

    @Override
    public PlaceEntity readById(int id) {
        EntityManager em = emf.createEntityManager();
        PlaceEntity entity = null;
        try {
            entity = em.find(PlaceEntity.class, id);
        } finally {
            em.close();
        }
        return entity;
    }

    @Override
    public PlaceEntity findByName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("findByPlaceName", PlaceEntity.class)
                    .setParameter("nm", name)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    @Override
    public PlaceEntity findByCity(String city) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("findByCity", PlaceEntity.class)
                    .setParameter("ct", city)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    @Override
    public PlaceEntity findByFamousFor(String famousFor) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("findByFamousFor", PlaceEntity.class)
                    .setParameter("ff", famousFor)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    @Override
    public List<PlaceEntity> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("getAllPlaces", PlaceEntity.class).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void updateNameById(int id, String newName) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            PlaceEntity entity = em.find(PlaceEntity.class, id);
            if (entity != null) {
                entity.setName(newName);
                em.merge(entity);
                em.getTransaction().commit();
                System.out.println("Name updated.");
            } else {
                System.out.println("Place not found with ID: " + id);
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
            PlaceEntity entity = em.find(PlaceEntity.class, id);
            if (entity != null) {
                em.remove(entity);
                em.getTransaction().commit();
                System.out.println("Deleted successfully.");
            } else {
                System.out.println("Place not found with ID: " + id);
            }
        } finally {
            em.close();
        }
    }
}
