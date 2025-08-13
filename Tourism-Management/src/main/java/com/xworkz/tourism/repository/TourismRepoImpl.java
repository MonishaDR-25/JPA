package com.xworkz.tourism.repository;

import com.xworkz.tourism.entity.TourismEntity;

import javax.persistence.*;
import java.util.List;

public class TourismRepoImpl implements TourismRepository{

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("x-archive");

    @Override
    public boolean save(TourismEntity entity) {
        System.out.println("Entity Save:" + entity);
        EntityManager manager = emf.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        transaction.begin();
        manager.persist(entity);
        transaction.commit();
        return true;
    }

    @Override
    public List<TourismEntity> getAllPackage() {
        System.out.println("List RETRIEVED...");
        EntityManager manager = emf.createEntityManager();
        Query query = manager.createNamedQuery("getAllPackage");
        return query.getResultList();
    }

    @Override
    public TourismEntity fetchDataByID(Integer id) {
        EntityManager manager = null;
        TourismEntity entity = null;
        try {
            manager = emf.createEntityManager();
            entity = (TourismEntity) manager.createNamedQuery("fetchDataByID")
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return entity;
    }
    }

