package com.xworkz.application.repository;

import com.xworkz.application.entity.ApplicationEntity;

import javax.persistence.*;

public class ApplicationRepositoryImpl implements ApplicationRepository {

    @Override
    public void saveApplication(ApplicationEntity applicationEntity) {
        System.out.println("Running save in ApplicationRepositoryImpl");

        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("x-workz");
            entityManager = entityManagerFactory.createEntityManager();

            entityManager.getTransaction().begin();
            entityManager.persist(applicationEntity);
            entityManager.getTransaction().commit();

            System.out.println("Application saved successfully.");
        } catch (PersistenceException e) {
            System.out.println("Exception occurred while saving: " + e.getMessage());
        } finally {
            if (entityManager != null) {
                entityManager.close();
                System.out.println("EntityManager closed");
            }
            if (entityManagerFactory != null) {
                entityManagerFactory.close();
                System.out.println("EntityManagerFactory closed");
            }
        }
    }

    @Override
    public ApplicationEntity readById(int id) {
        System.out.println("Running readById in ApplicationRepositoryImpl");

        EntityManagerFactory factory = null;
        EntityManager manager = null;
        ApplicationEntity entity = null;

        try {
            factory = Persistence.createEntityManagerFactory("x-workz");
            manager = factory.createEntityManager();

            entity = manager.find(ApplicationEntity.class, id);

            if (entity != null) {
                System.out.println("Application found: " + entity.getApplicationName());
            } else {
                System.out.println("No Application found for ID: " + id);
            }

        } catch (PersistenceException e) {
            System.out.println("Exception while reading: " + e.getMessage());
        } finally {
            if (manager != null) {
                manager.close();
                System.out.println("EntityManager closed");
            }
            if (factory != null) {
                factory.close();
                System.out.println("EntityManagerFactory closed");
            }
        }

        return entity;
    }

    @Override
    public void updateCompanyById(int id, String newCompanyName) {
        System.out.println("Running updateCompanyById in ApplicationRepositoryImpl");

        EntityManagerFactory factory = null;
        EntityManager manager = null;

        try {
            factory = Persistence.createEntityManagerFactory("x-workz");
            manager = factory.createEntityManager();

            manager.getTransaction().begin();
            ApplicationEntity entity = manager.find(ApplicationEntity.class, id);

            if (entity != null) {
                entity.setCompany(newCompanyName);
                manager.merge(entity);
                manager.getTransaction().commit();
                System.out.println("Company name updated successfully.");
            } else {
                System.out.println("No Application found for ID: " + id);
            }

        } catch (PersistenceException e) {
            System.out.println("Exception during update: " + e.getMessage());
        } finally {
            if (manager != null) {
                manager.close();
                System.out.println("EntityManager closed");
            }
            if (factory != null) {
                factory.close();
                System.out.println("EntityManagerFactory closed");
            }
        }
    }

    @Override
    public void deleteById(int id) {
        System.out.println("Running deleteById in ApplicationRepositoryImpl");

        EntityManagerFactory factory = null;
        EntityManager manager = null;

        try {
            factory = Persistence.createEntityManagerFactory("x-workz");
            manager = factory.createEntityManager();

            manager.getTransaction().begin();
            ApplicationEntity entity = manager.find(ApplicationEntity.class, id);

            if (entity != null) {
                manager.remove(entity);
                manager.getTransaction().commit();
                System.out.println("Application deleted successfully.");
            } else {
                System.out.println("No Application found for ID: " + id);
            }

        } catch (PersistenceException e) {
            System.out.println("Exception during delete: " + e.getMessage());
        } finally {
            if (manager != null) {
                manager.close();
                System.out.println("EntityManager closed");
            }
            if (factory != null) {
                factory.close();
                System.out.println("EntityManagerFactory closed");
            }
        }
    }
}
