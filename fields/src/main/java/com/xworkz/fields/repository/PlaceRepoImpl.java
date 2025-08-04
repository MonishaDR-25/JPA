package com.xworkz.fields.repository;

import com.xworkz.fields.entity.PlaceEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class PlaceRepoImpl implements PlaceRepository {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("x-workz");

    @Override
    public boolean save(PlaceEntity entity) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        em.close();
        return true;
    }

    @Override
    public PlaceEntity findByName(String name) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("findByPlaceName");
        query.setParameter("nm", name);
        PlaceEntity result = (PlaceEntity) query.getSingleResult();
        em.close();
        return result;
    }

    @Override
    public PlaceEntity findByCity(String city) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("findByCity");
        query.setParameter("ct", city);
        PlaceEntity result = (PlaceEntity) query.getSingleResult();
        em.close();
        return result;
    }

    @Override
    public PlaceEntity findByFamousFor(String famousFor) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("findByFamousFor");
        query.setParameter("ff", famousFor);
        PlaceEntity result = (PlaceEntity) query.getSingleResult();
        em.close();
        return result;
    }

    @Override
    public boolean updateCityById(String city, int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        PlaceEntity entity = em.find(PlaceEntity.class, id);
        if (entity != null) {
            entity.setCity(city);
            em.getTransaction().commit();
            em.close();
            return true;
        }
        em.getTransaction().rollback();
        em.close();
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        PlaceEntity entity = em.find(PlaceEntity.class, id);
        if (entity != null) {
            em.remove(entity);
            em.getTransaction().commit();
            em.close();
            return true;
        }
        em.getTransaction().rollback();
        em.close();
        return false;
    }
}
