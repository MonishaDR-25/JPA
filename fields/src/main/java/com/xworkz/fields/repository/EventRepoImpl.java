package com.xworkz.fields.repository;

import com.xworkz.fields.entity.EventEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EventRepoImpl implements EventRepository{
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("x-workz");

    @Override
    public boolean save(EventEntity entity) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(entity);
        manager.getTransaction().commit();
        manager.close();
        return true;
    }

    @Override
    public EventEntity findByName(String name) {
        EntityManager manager = factory.createEntityManager();
        EventEntity entity = manager.createNamedQuery("findByName", EventEntity.class)
                .setParameter("nm", name)
                .getSingleResult();
        manager.close();
        return entity;
    }

    @Override
    public EventEntity findByLocation(String location) {
        EntityManager manager = factory.createEntityManager();
        EventEntity entity = manager.createNamedQuery("findByLocation", EventEntity.class)
                .setParameter("loc", location)
                .getSingleResult();
        manager.close();
        return entity;
    }

    @Override
    public EventEntity findByBudget(double budget) {
        EntityManager manager = factory.createEntityManager();
        EventEntity entity = manager.createNamedQuery("findByBudget", EventEntity.class)
                .setParameter("bud", budget)
                .getSingleResult();
        manager.close();
        return entity;
    }

    @Override
    public boolean updateOrganiserById(int id, String organiser) {
        EntityManager manager = factory.createEntityManager();
        EventEntity entity = manager.find(EventEntity.class, id);
        if (entity != null) {
            manager.getTransaction().begin();
            entity.setOrganiser(organiser);
            manager.getTransaction().commit();
            manager.close();
            return true;
        }
        manager.close();
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        EntityManager manager = factory.createEntityManager();
        EventEntity entity = manager.find(EventEntity.class, id);
        if (entity != null) {
            manager.getTransaction().begin();
            manager.remove(entity);
            manager.getTransaction().commit();
            manager.close();
            return true;
        }
        manager.close();
        return false;
    }
}
