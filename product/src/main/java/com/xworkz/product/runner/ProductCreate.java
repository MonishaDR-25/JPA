package com.xworkz.product.runner;

import com.xworkz.dmart.entity.ProductEntity;

import javax.persistence.*;

public class ProductCreate {
    public static void main(String[] args) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName("Mysore Sandal");
        productEntity.setCompany("Karnataka Soaps");
       productEntity.setMfd("22-05-2025");
        productEntity.setCost(30.00);

        EntityManagerFactory entityManagerFactory=null;
        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;

        try {

            entityManagerFactory = Persistence.createEntityManagerFactory("x-workz");//load,register,create driver
            System.out.println("connection established");
            entityManager = entityManagerFactory.createEntityManager();//DML(insert,delete,update),DQL(select)
            entityTransaction = entityManager.getTransaction();//TCL(commit,rollback,savepoint)

            entityTransaction.begin();//setautocommit=0
            entityManager.persist(productEntity);//insert
            entityTransaction.commit();//permenent store into table
            System.out.println("insert success");
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
            if(entityTransaction!=null) {
                entityTransaction.rollback();
                System.out.println("Rollback data");
            }
        } finally {
            if (entityManagerFactory != null) {
                System.out.println("entityManagerFactory is closed");
                entityManagerFactory.close();
            }
            if (entityManager != null) {
                System.out.println("entityManager closed");
                entityManager.close();
            }

        }

    }
}
