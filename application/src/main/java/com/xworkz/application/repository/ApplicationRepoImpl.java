package com.xworkz.application.repository;

import com.xworkz.application.entity.ApplicationEntity;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

public class ApplicationRepoImpl implements ApplicationRepository {

    private EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("x-workz");

    @Override
    public ApplicationEntity getApplicationByName(String name) {
        EntityManager em=null;
        ApplicationEntity entity=null;
        try{
            em=this.entityManagerFactory.createEntityManager();
            entity=(ApplicationEntity) em.createNamedQuery("findByApplicationName").setParameter("name",name).getSingleResult();
            System.out.println("getApplicationByName:"+entity);
        }catch (PersistenceException e){
            System.out.println("Error occurred:"+e.getMessage());
        }finally {
//            if(entityManagerFactory!=null){
//                entityManagerFactory.close();
//            }
            if(em!=null){
                em.close();
            }
        }
        return entity;
    }

    @Override
    public ApplicationEntity getApplicationBySize(String size) {
        EntityManager em=null;
        ApplicationEntity entity=null;
        try{
            em=this.entityManagerFactory.createEntityManager();
            entity=(ApplicationEntity) em.createNamedQuery("findApplicationSize").setParameter("size",size).getSingleResult();
            System.out.println("getApplicationBySize:"+entity);
        }catch (PersistenceException e){
            System.out.println("Error occurred:"+e.getMessage());
        }finally {
//            if(entityManagerFactory!=null){
//                entityManagerFactory.close();
//            }
            if(em!=null){
                em.close();
            }
        }
        return entity;
    }

    @Override
    public ApplicationEntity getApplicationByCompany(String company) {
        EntityManager em=null;
        ApplicationEntity entity=null;
        return null;
    }

    @Override
    public List<ApplicationEntity> getApplication() {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        List<ApplicationEntity> ref=null;
        try{
            Query query=entityManager.createNamedQuery("getApplicationEntity");
            ref=query.getResultList();
        }catch (PersistenceException e){
            e.printStackTrace();
        }finally {
            if(entityManager!=null){
                entityManager.close();
            }
        }
        return ref;
    }

    @Override
    public List<String> getApplicationName() {
        EntityManager entityManager=null;
        List<String> ref=null;
        try{
            ref=entityManagerFactory.createEntityManager().createNamedQuery("getApplicationName").getResultList();

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
    public List<Integer> getApplicationNoOfUsers() {
        EntityManager entityManager=null;
        List<Integer> ref=null;
        try{
            ref=entityManagerFactory.createEntityManager().createNamedQuery("getApplicationNoOfUsers").getResultList();

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
    public List<Object> getApplicationDate() {
        EntityManager em=null;
        List<Object> ref=null;
        try{
            ref=entityManagerFactory.createEntityManager().createNamedQuery("getApplicationDate").getResultList();

        }catch (PersistenceException e){
            ref=Collections.emptyList();

        }finally {
            if(em!=null){
                em.close();
            }

        }
        return ref;
    }

    @Override
    public List<Object[]> getApplicationNameAndNoOfUsers() {
        EntityManager entityManager=null;
        List<Object[]> ref=null;
        try{
            ref=entityManagerFactory.createEntityManager().createNamedQuery("getApplicationNameAndNoOfUsers").getResultList();

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
    public List<String[]> getApplicationNameAndSize() {
        EntityManager entityManager=null;
        List<String[]> ref=null;
        try{
            ref=entityManagerFactory.createEntityManager().createNamedQuery("getApplicationNameAndSize").getResultList();

        }catch (PersistenceException e){
            ref=Collections.emptyList();

        }finally {
            if(entityManager!=null){
                entityManager.close();
            }
            return ref;
        }
    }

    @Override
    public void saveApplication(ApplicationEntity applicationEntity) {
        System.out.println("Running save in ApplicationRepositoryImpl"+applicationEntity);
        EntityManager entityManager = null;
        try {
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

      //EntityManagerFactory factory = null;
        EntityManager manager = null;
        ApplicationEntity entity = null;

        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("x-workz");
            manager = entityManagerFactory.createEntityManager();

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
            if (entityManagerFactory != null) {
                entityManagerFactory.close();
                System.out.println("EntityManagerFactory closed");
            }
        }

        return entity;
    }

    @Override
    public void updateCompanyById(int id, String newCompanyName) {
        System.out.println("Running updateCompanyById in ApplicationRepositoryImpl");

       //ntityManagerFactory factory = null;
        EntityManager manager = null;

        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("x-workz");
            manager = entityManagerFactory.createEntityManager();

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
            if (entityManagerFactory != null) {
                entityManagerFactory.close();
                System.out.println("EntityManagerFactory closed");
            }
        }
    }

    @Override
    public void deleteById(int id) {
        System.out.println("Running deleteById in ApplicationRepositoryImpl");

       //ntityManagerFactory factory = null;
        EntityManager manager = null;

        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("x-workz");
            manager = entityManagerFactory.createEntityManager();

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
            if (entityManagerFactory != null) {
                entityManagerFactory.close();
                System.out.println("EntityManagerFactory closed");
            }
        }
    }
}
