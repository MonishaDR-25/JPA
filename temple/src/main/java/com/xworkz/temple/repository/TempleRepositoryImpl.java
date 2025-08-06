package com.xworkz.temple.repository;

import com.xworkz.temple.entity.TempleEntity;

import javax.persistence.*;
import java.util.Objects;

public class TempleRepositoryImpl implements TempleRepository {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("com.xworkz");

    @Override
    public boolean save(TempleEntity entity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();
            return true;
        } catch (PersistenceException e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return false;
    }

    @Override
    public TempleEntity findByName(String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<TempleEntity> query = entityManager.createNamedQuery("findByTempleName", TempleEntity.class);
            query.setParameter("tp", name);
            return query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("No result found for name: " + name);
        } catch (PersistenceException e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return null;
    }

    @Override
    public TempleEntity findByLocation(String location) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<TempleEntity> query = entityManager.createNamedQuery("findByTempleLocation", TempleEntity.class);
            query.setParameter("tp", location);
            return query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("No result found for location: " + location);
        } catch (PersistenceException e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return null;
    }

    @Override
    public TempleEntity findByMainGod(String mainGod) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<TempleEntity> query = entityManager.createNamedQuery("findByTempleMainGod", TempleEntity.class);
            query.setParameter("tp", mainGod);
            return query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("No result found for main god: " + mainGod);
        } catch (PersistenceException e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return null;
    }

    @Override
    public TempleEntity findById(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.find(TempleEntity.class, id);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return null;
    }

    @Override
    public boolean updateEntryFeeByName(double fee, String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            Query query = entityManager.createNamedQuery("updateTempleEntryFeeByName");
            query.setParameter("tfee", fee);
            query.setParameter("tn", name);
            int updated = query.executeUpdate();
            transaction.commit();
            return updated > 0;
        } catch (PersistenceException e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return false;
    }

    @Override
    public boolean deleteByName(String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            Query query = entityManager.createNamedQuery("deleteTempleByName");
            query.setParameter("tn", name);
            int deleted = query.executeUpdate();
            transaction.commit();
            return deleted > 0;
        } catch (PersistenceException e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return false;
    }
}
