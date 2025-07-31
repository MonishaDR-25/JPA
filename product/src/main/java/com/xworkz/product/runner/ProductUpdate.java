package com.xworkz.product.runner;

import com.xworkz.product.entity.ProductEntity;

import javax.persistence.*;

public class ProductUpdate {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory=null;
        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;

        try{
            entityManagerFactory= Persistence.createEntityManagerFactory("x-workz");
            System.out.println("Connection Established");
            entityManager=entityManagerFactory.createEntityManager();
            entityTransaction=entityManager.getTransaction();

            entityTransaction.begin();
            ProductEntity productEntity=entityManager.find(ProductEntity.class,2);
            System.out.println("Product entity for id 1:"+productEntity);
            if(productEntity!=null){
                productEntity.setName("Medicine");
                productEntity.setCompany("Apollo");
                productEntity.setMfd("12-9-2025");
                productEntity.setCost(30.00);
                ProductEntity updateEntity=entityManager.merge(productEntity);
                System.out.println("Updated data:"+productEntity);
            }
            entityTransaction.commit();
        }catch (PersistenceException e){
            System.out.println(e.getMessage());
            if(entityTransaction!=null){
                entityTransaction.rollback();
                System.out.println("Rollback data");
            }

        }finally {
            if(entityManagerFactory!=null){
                System.out.println("EntityManagerFactory closed");
                entityManager.close();
            }
            if(entityManager!=null){
                System.out.println("entity manager closed");
                entityManager.close();
            }

        }
    }
}
