package com.xworkz.bakery.runner;

import com.xworkz.project.entity.SoapEntity;

import javax.persistence.*;

public class SoapCreate {
    public static void main(String[] args) {
        SoapEntity soapEntity=new SoapEntity();
        soapEntity.setName("Medimix");
        soapEntity.setBrand("Medimix Soap Manufacture");
        soapEntity.setPrice(50.00);
        soapEntity.setColor("Green");

        EntityManagerFactory entityManagerFactory=null;
        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;

        try {
            entityManagerFactory= Persistence.createEntityManagerFactory("x-workz");
            System.out.println("connection established");
            entityManager=entityManagerFactory.createEntityManager();
            entityTransaction=entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.persist(soapEntity);
            entityTransaction.commit();
            System.out.println("insert success");
        }catch (PersistenceException e){
            System.out.println(e.getMessage());
            if(entityTransaction!=null){
                entityTransaction.rollback();
                System.out.println("Roll back data");
            }
        }finally {
            if(entityManagerFactory!=null){
                System.out.println("entityManagerFactory is not null");
                entityManagerFactory.close();
            }
            if(entityManager!=null){
                System.out.println("EntityManager is not null");
                entityManager.close();
            }
        }


    }
}
