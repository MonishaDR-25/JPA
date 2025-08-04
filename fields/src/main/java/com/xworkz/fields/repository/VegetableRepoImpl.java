package com.xworkz.fields.repository;

import com.xworkz.fields.entity.VegetableEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class VegetableRepoImpl implements VegetableRepository{

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("x-workz");

    public boolean save(VegetableEntity entity) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        em.close();
        return true;
    }

    public VegetableEntity findByName(String name) {
        EntityManager em = emf.createEntityManager();
        VegetableEntity result = em.createNamedQuery("getByName", VegetableEntity.class).setParameter("nm", name).getSingleResult();
        em.close();
        return result;
    }

    public VegetableEntity findByColor(String color) {
        EntityManager em = emf.createEntityManager();
        VegetableEntity result = em.createNamedQuery("getByColor", VegetableEntity.class).setParameter("cl", color).getSingleResult();
        em.close();
        return result;
    }

    public VegetableEntity findByType(String type) {
        EntityManager em = emf.createEntityManager();
        VegetableEntity result = em.createNamedQuery("getByType", VegetableEntity.class).setParameter("tp", type).getSingleResult();
        em.close();
        return result;
    }

    public boolean updateCostById(int id, double cost) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        VegetableEntity entity = em.find(VegetableEntity.class, id);
        if (entity != null) {
            entity.setCost(cost);
            em.merge(entity);
            em.getTransaction().commit();
            em.close();
            return true;
        }
        em.close();
        return false;
    }

    public boolean deleteById(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        VegetableEntity entity = em.find(VegetableEntity.class, id);
        if (entity != null) {
            em.remove(entity);
            em.getTransaction().commit();
            em.close();
            return true;
        }
        em.close();
        return false;
    }
}
