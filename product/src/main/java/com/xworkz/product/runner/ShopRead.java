package com.xworkz.product.runner;

import com.xworkz.shop.entity.ShopEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

public class ShopRead {
    public static void main(String[] args) {

        EntityManagerFactory emf=null;
        EntityManager manager=null;

        try{
            emf= Persistence.createEntityManagerFactory("x-workz");
            manager= emf.createEntityManager();
            ShopEntity ref=manager.find(ShopEntity.class,1);
            System.out.println(ref);
        }catch (PersistenceException e){
            e.printStackTrace();
        }finally {
            if(emf!=null){
                emf.close();
            }if(manager!=null){
                manager.close();
            }
        }

    }
}
