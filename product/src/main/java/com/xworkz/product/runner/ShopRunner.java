package com.xworkz.product.runner;

import com.xworkz.shop.entity.ShopEntity;

import javax.persistence.*;

public class ShopRunner {
    public static void main(String[] args) {
        ShopEntity shopEntity=new ShopEntity();
        shopEntity.setName("Pharmacy Shop");
        shopEntity.setOwner("Monisha");
        shopEntity.setCost(5000);


        EntityManagerFactory entityManagerFactory=null;
        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;

        try {
            entityManagerFactory= Persistence.createEntityManagerFactory("x-workz");
            System.out.println("connection established");
            entityManager=entityManagerFactory.createEntityManager();
            entityTransaction=entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.persist(shopEntity);
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
