package com.xworkz.product.runner;

import com.xworkz.dmart.entity.ProductEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

public class ProductRead {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory=null;
        EntityManager entityManager=null;

        try{
            entityManagerFactory= Persistence.createEntityManagerFactory("x-workz");
            entityManager=entityManagerFactory.createEntityManager();
            ProductEntity ref=entityManager.find(ProductEntity.class,1);
            System.out.println(ref);
        }catch (PersistenceException e){
            System.out.println(e.getMessage());
        }finally {
            if (entityManagerFactory != null) {
                entityManagerFactory.close();
            }

            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
}
