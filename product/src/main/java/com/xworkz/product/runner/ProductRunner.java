package com.xworkz.product.runner;

import com.xworkz.dmart.entity.ProductEntity;

import javax.persistence.*;

public class ProductRunner {
    public static void main(String[] args) {
        ProductEntity productEntity=new ProductEntity();
        productEntity.setName("Mysore Sandal");
        productEntity.setCompany("Karnataka Soaps");
        productEntity.setMfd("22-05-2025");
        productEntity.setCost(30.00);

        try {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("x-workz");//load,register,create driver
            System.out.println("connection established");
            EntityManager entityManager = entityManagerFactory.createEntityManager();//DML(insert,delete,update),DQL(select)
            EntityTransaction entitytransaction = entityManager.getTransaction();//TCL(commit,rollback,savepoint)

            entitytransaction.begin();
            entityManager.persist(productEntity);
            entitytransaction.commit();
            System.out.println("insert success");
        }catch (PersistenceException e){
            e.printStackTrace();
        }finally{

        }

    }
}
