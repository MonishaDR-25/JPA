package com.xworkz.DairyManagement.repository;

import com.xworkz.DairyManagement.entity.AdminAuditEntity;
import com.xworkz.DairyManagement.entity.AdminEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

@Repository
public class AdminRepoImpl implements AdminRepository {

    @Autowired
    EntityManagerFactory emf;

    public AdminRepoImpl() {
        System.out.println("Created AdminRepositoryImpl");
    }

    @Override
    public void save(AdminEntity entity) {
        EntityManager em = null;
        EntityTransaction transaction = null;
        try {
            em = emf.createEntityManager();
            transaction = em.getTransaction();
            transaction.begin();
            AdminAuditEntity adminAuditEntity = new AdminAuditEntity();
            adminAuditEntity.setAdmin(entity);
            entity.setAudit(adminAuditEntity);
            adminAuditEntity.setAdminName(entity.getAdminName());

            em.persist(entity);

            transaction.commit();


        } catch (PersistenceException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }


        }

    }

    @Override
    public AdminEntity findByEmail(String email) {
        EntityManager em = null;

        try {
            em = emf.createEntityManager();
            AdminEntity singleResult = em.createNamedQuery("findByEmail", AdminEntity.class).setParameter("email", email).getSingleResult();
            return singleResult;


        } catch (PersistenceException e) {
            return null;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }

        }


    }

    @Override
    public void updateAdmin(AdminEntity adminEntity) {
        EntityManager em = null;
        EntityTransaction transaction = null;
        try {
            em = emf.createEntityManager();
            transaction = em.getTransaction();
            transaction.begin();
            em.merge(adminEntity);
            transaction.commit();


        } catch (PersistenceException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }


        }

    }

    @Override
    public AdminEntity findByUnlockToken(String token) {
        EntityManager em = null;

        try {
            em = emf.createEntityManager();
            AdminEntity singleResult = em.createNamedQuery("findByUnlockToken", AdminEntity.class).setParameter("token", token).getSingleResult();
            return singleResult;


        } catch (PersistenceException e) {
            return null;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }

        }


    }

    @Override
    public AdminEntity findById(Integer adminId) {
        EntityManager em = null;

        try {
            em = emf.createEntityManager();

            AdminEntity adminEntity = em.find(AdminEntity.class, adminId);

            return adminEntity;


        } catch (PersistenceException e) {
            return null;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }

        }
    }
}
