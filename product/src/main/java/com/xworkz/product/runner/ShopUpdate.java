package com.xworkz.product.runner;

import com.xworkz.product.entity.ProductEntity;
import com.xworkz.product.entity.ShopEntity;

import javax.persistence.*;

public class ShopUpdate {
    public static void main(String[] args) {
        EntityManagerFactory emf=null;
        EntityManager manager=null;

        EntityManagerFactory entityManagerFactory=null;
        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;

        try{
            entityManagerFactory= Persistence.createEntityManagerFactory("x-workz");
            System.out.println("Connection Established");
            entityManager=entityManagerFactory.createEntityManager();
            entityTransaction=entityManager.getTransaction();

            entityTransaction.begin();
            ShopEntity shopEntity=entityManager.find(ShopEntity.class,2);
            System.out.println("Product entity for id 1:"+shopEntity);
            if(shopEntity!=null){
                shopEntity.setName("Medicine");
                shopEntity.setOwner("Apollo");
                shopEntity.setCost(30);
                ShopEntity updateEntity=entityManager.merge(shopEntity);
                System.out.println("Updated data:"+shopEntity);
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
