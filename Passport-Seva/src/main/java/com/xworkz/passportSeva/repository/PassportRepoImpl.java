package com.xworkz.passportSeva.repository;

import com.xworkz.passportSeva.entity.PassportEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
public class PassportRepoImpl implements PassportRepo{

    private PassportRepoImpl()
    {
        System.out.println("PassportRepoImpl constructor");
    }
    private EntityManagerFactory emf= Persistence.createEntityManagerFactory("x-workz");
    @Override
    public boolean save(PassportEntity entity) {
        System.out.println("Save Entity in repo:"+entity);
        EntityManager entityManager=null;
        EntityTransaction entityTransaction=null;

        try{
            entityManager=emf.createEntityManager();
            entityTransaction=entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.persist(entity);
            entityTransaction.commit();

        }catch (PersistenceException e){
            System.out.println("Error occurred:"+e.getMessage());
            if(entityTransaction!=null) {
                entityTransaction.rollback();
                System.out.println("Entity roll backed");
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
    public String fetchEmail(String email) {
        EntityManager manager = null;
        String emailFetch = null;
        try {
            manager = emf.createEntityManager();
            emailFetch = (String) manager.createNamedQuery("fetchEmail")
                    .setParameter("emailId", email)
                    .getSingleResult();
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        }
        return emailFetch;
    }

}


