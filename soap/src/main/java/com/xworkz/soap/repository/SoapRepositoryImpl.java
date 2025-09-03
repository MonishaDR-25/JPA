package com.xworkz.soap.repository;

import com.xworkz.soap.dto.SoapDto;
import com.xworkz.soap.entity.SoapEntity;
import org.hibernate.QueryException;
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

    @Override
    public SoapEntity findById(Integer id) {
        System.out.println("Find by Id in Repo");
        EntityManager em=null;
        SoapEntity soapEntity=null;
        try{
            em=emf.createEntityManager();
            soapEntity= (SoapEntity) em.createNamedQuery("findById").setParameter("id",id).getSingleResult();
            System.err.println(soapEntity);
        }catch (PersistenceException e){
            System.out.println(e.getMessage());

        }finally {
            if(em!=null && em.isOpen()){
                em.close();
            }

        }
        return soapEntity;
    }

    @Override
    public boolean updateById(SoapEntity soapEntity) {
        System.out.println("Update by Id in repo");
        EntityManager em=null;
        boolean isUpdated=false;
        try{
            em=emf.createEntityManager();
            int rows=em.createNamedQuery("updateByID").setParameter("name",soapEntity.getName())
                    .setParameter("brand",soapEntity.getBrand()).setParameter("color",soapEntity.getColor())
                    .setParameter("cost",soapEntity.getCost()).setParameter("id",soapEntity.getSoapId())
                    .executeUpdate();

            if(rows>0){
                System.out.println("Updated successfully in repo..");
                em.getTransaction().commit();
                isUpdated=true;
            }

        }catch (PersistenceException e){
            System.out.println(e.getMessage());
            em.getTransaction().rollback();
            isUpdated=false;
        }finally {
            if(em!=null && em.isOpen()){
                em.close();
            }

        }

        return isUpdated;
    }

    @Override
    public boolean deleteById(Integer id) {
        System.out.println("Repo Delete By Id...");
        EntityManager em=null;
        boolean isdeleted= false;
        try{
            em=emf.createEntityManager();
            em.getTransaction().begin();
            int delete=em.createNamedQuery("deleteById").setParameter("id",id).executeUpdate();
            if (delete>0 ){
                System.out.println("Rows Deleted");
                em.getTransaction().commit();
                isdeleted=true;
            }
        }catch (NoResultException | QueryException e){
            System.out.println(e.getMessage());
            isdeleted=false;
        }
        return isdeleted;
    }
}
