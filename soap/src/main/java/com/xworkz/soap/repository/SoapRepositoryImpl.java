package com.xworkz.soap.repository;

import com.xworkz.soap.entity.SoapEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Repository
public class SoapRepositoryImpl implements SoapRepository{

    private SoapRepositoryImpl(){
        System.out.println("Running SoapRepoImpl");
    }

    private EntityManagerFactory emf= Persistence.createEntityManagerFactory("x-workz");
    @Override
    public boolean save(SoapEntity soapEntity) {
        System.out.println("Running save in repo:"+soapEntity);
        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;

        try {
            entityManager= emf.createEntityManager();
            entityTransaction=entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.persist(soapEntity);
            entityTransaction.commit();
        }
        catch (PersistenceException e){
            System.out.println("Error Occurred:"+e.getMessage());
            if(entityTransaction!=null){
                entityTransaction.rollback();
                System.out.println("Entity roll back");
                return false;
            }

        }finally {
            if(entityManager!=null && entityManager.isOpen()){
                entityManager.close();
            }

        }
        return true;
    }

    @Override
    public String fetchSoapName(String name) {
        EntityManager entityManager=null;
        String soapNameFetch=null;
        try{
            entityManager=emf.createEntityManager();
            soapNameFetch= (String) entityManager.createNamedQuery("fetchSoapName")
                    .setParameter("name",name)
                    .getSingleResult();

        }catch (PersistenceException e){
            System.out.println(e.getMessage());

        }
        return soapNameFetch;
    }

    @Override
    public List<SoapEntity> findAllEntity() {
        System.out.println("Find All in repo");
        EntityManager entityManager=null;
        List<SoapEntity> list=null;

        try{
            entityManager=emf.createEntityManager();
            list=entityManager.createNamedQuery("findAllEntity").getResultList();

        }catch (PersistenceException e){
            System.out.println(e.getMessage());

        }finally {
            if(entityManager!=null && entityManager.isOpen()){
                entityManager.close();
            }

        }
        return list;
    }
}
