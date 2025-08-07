package com.xworkz.event.repository;

import com.xworkz.event.entity.EventEntity;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

public class EventRepoImpl implements EventRepository {

    private EntityManagerFactory emf;

    public EventRepoImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public boolean save(EventEntity eventEntity) {
        System.out.println("Running save in EventRepositoryImpl: " + eventEntity);
        EntityManager entityManager = null;
        try {
            entityManager = emf.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(eventEntity);
            entityManager.getTransaction().commit();
            System.out.println("Entity saved successfully");
        } catch (PersistenceException e) {
            System.out.println("Error occurred: " + e.getMessage());
            return false;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return true;
    }

    @Override
    public EventEntity readById(int id) {
        EntityManager entityManager = null;
        EventEntity eventEntity = null;
        try {
            entityManager = emf.createEntityManager();
            eventEntity = entityManager.find(EventEntity.class, id);
        } catch (PersistenceException e) {
            System.out.println("Exception while reading: " + e.getMessage());
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return eventEntity;
    }

    @Override
    public EventEntity getEventByName(String name) {
        EntityManager em = null;
        EventEntity entity = null;
        try {
            em = emf.createEntityManager();
            entity = em.createNamedQuery("findByName", EventEntity.class)
                    .setParameter("nm", name)
                    .getSingleResult();
        } catch (PersistenceException e) {
            System.out.println("Error occurred: " + e.getMessage());
        } finally {
            if (em != null) em.close();
        }
        return entity;
    }

    @Override
    public EventEntity getEventByLocation(String location) {
        EntityManager em = null;
        EventEntity entity = null;
        try {
            em = emf.createEntityManager();
            entity = em.createNamedQuery("findByLocation", EventEntity.class)
                    .setParameter("loc", location)
                    .getSingleResult();
        } catch (PersistenceException e) {
            System.out.println("Error occurred: " + e.getMessage());
        } finally {
            if (em != null) em.close();
        }
        return entity;
    }

    @Override
    public EventEntity getEventByBudget(double budget) {
        EntityManager em = null;
        EventEntity entity = null;
        try {
            em = emf.createEntityManager();
            entity = em.createNamedQuery("findByBudget", EventEntity.class)
                    .setParameter("bud", budget)
                    .getSingleResult();
        } catch (PersistenceException e) {
            System.out.println("Error occurred: " + e.getMessage());
        } finally {
            if (em != null) em.close();
        }
        return entity;
    }

    @Override
    public List<EventEntity> getEvent() {
        EntityManager em = emf.createEntityManager();
        List<EventEntity> events = null;
        try {
            TypedQuery<EventEntity> query = em.createNamedQuery("getEventEntity", EventEntity.class);
            events = query.getResultList();
        } catch (PersistenceException e) {
            e.printStackTrace();
        } finally {
            if (em != null) em.close();
        }
        return events;
    }

    @Override
    public List<String> getEventName() {
        EntityManager entityManager=null;
        List<String> ref=null;
        try {
            ref=emf.createEntityManager().createNamedQuery("findEventName").getResultList();

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
    public List<Double> getEventBudget() {
        EntityManager entityManager=null;
        List<Double> ref=null;
        try {
            ref=emf.createEntityManager().createNamedQuery("findEventBudget").getResultList();

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
    public List<Object> getEventDate() {
        EntityManager entityManager=null;
        List<Object> ref=null;
        try {
            ref=emf.createEntityManager().createNamedQuery("findEventDate").getResultList();

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
    public List<Object[]> getEventOrganiserAndSponsor() {
        EntityManager entityManager=null;
        List<Object[]> ref=null;
        try {
            ref=emf.createEntityManager().createNamedQuery("findEventOrganiserAndSponsor").getResultList();

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
    public void updateEventById(int id, String newName) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            EventEntity eventEntity = em.find(EventEntity.class, id);
            if (eventEntity != null) {
                eventEntity.setName(newName);
                em.merge(eventEntity);
                em.getTransaction().commit();
                System.out.println("Event name updated successfully.");
            } else {
                System.out.println("No Event found for ID: " + id);
            }
        } catch (PersistenceException e) {
            System.out.println("Exception during update: " + e.getMessage());
        } finally {
            if (em != null) em.close();
        }
    }

    @Override
    public void deleteById(int id) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            EventEntity eventEntity = em.find(EventEntity.class, id);
            if (eventEntity != null) {
                em.remove(eventEntity);
                em.getTransaction().commit();
                System.out.println("Event deleted successfully.");
            } else {
                System.out.println("No Event found for ID: " + id);
            }
        } catch (PersistenceException e) {
            System.out.println("Exception during delete: " + e.getMessage());
        } finally {
            if (em != null) em.close();
        }
    }
}
