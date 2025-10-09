package com.xworkz.DairyManagement.repository;

import com.xworkz.DairyManagement.entity.AdminEntity;
import com.xworkz.DairyManagement.entity.AgentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import java.util.Collections;
import java.util.List;

@Repository
public class AgentRepoImpl implements AgentRepository {

    @Autowired
    EntityManagerFactory emf;

    @Override
    public List<AgentEntity> findAllAgents() {
        EntityManager em = null;

        try {
            em = emf.createEntityManager();

            List<AgentEntity> findAllAgents = em.createNamedQuery("findAllAgents", AgentEntity.class).getResultList();

            return findAllAgents;


        } catch (PersistenceException e) {
            return Collections.emptyList();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }

        }
    }

    @Override
    public void register(AgentEntity agentEntity) {
        EntityManager em = null;
        EntityTransaction transaction = null;
        try {
            em = emf.createEntityManager();
            transaction = em.getTransaction();
            transaction.begin();
            em.persist(agentEntity);
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
    public AgentEntity findById(Integer id) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            AgentEntity agentEntity = em.find(AgentEntity.class, id);
            System.out.println("id in Repo: " + id);
            System.out.println("agentEntity in Repo: " + agentEntity);
            return agentEntity;
        } catch (PersistenceException e) {
            System.err.println("Failed to find agent by id:" + id);
            e.printStackTrace();

            return null;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public void update(AgentEntity agentEntity) {
        EntityManager em = null;
        EntityTransaction transaction = null;
        try {
            em = emf.createEntityManager();
            transaction = em.getTransaction();
            transaction.begin();
            em.merge(agentEntity);
            transaction.commit();

        } catch (PersistenceException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
                System.out.println("Failed to update agent");
                e.printStackTrace();
            }
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }

    }

    @Override
    public void delete(Integer id) {
        EntityManager em = null;
        EntityTransaction et = null;
        try {
            em = emf.createEntityManager();
            et = em.getTransaction();
            et.begin();
            AgentEntity agent = em.find(AgentEntity.class, id);
//            agent.setActive(false);
            em.merge(agent);  // update instead of delete
             em.remove(em.find(AgentEntity.class, id));
            et.commit();
            System.out.println("Agent Entity deleted Successfully with id"+id);

        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            System.out.println("Failed to delete AgentEntity with id:"+id);

        } finally {
            if (em != null) {
                em.close();
            }
        }

        }
    }

