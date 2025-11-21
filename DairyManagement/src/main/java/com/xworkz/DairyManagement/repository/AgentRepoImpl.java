package com.xworkz.DairyManagement.repository;

import com.xworkz.DairyManagement.entity.AdminEntity;
import com.xworkz.DairyManagement.entity.AgentBankEntity;
import com.xworkz.DairyManagement.entity.AgentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class AgentRepoImpl implements AgentRepository {

    @Autowired
    EntityManagerFactory emf;

    @Override
    public List<AgentEntity> getAllAgents(int start, int size) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            String query = "SELECT a FROM AgentEntity a ORDER BY a.firstName ASC";
            return em.createQuery(query, AgentEntity.class)
                    .setFirstResult(start)
                    .setMaxResults(size)
                    .getResultList();
        } catch (PersistenceException e) {
            return Collections.emptyList();
        } finally {
            if (em != null && em.isOpen()) {
            }
        }
    }

    @Override
    public void register(AgentEntity agentEntity) {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            em.persist(agentEntity);

            tx.commit();

        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
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

            return agentEntity;

        } catch (PersistenceException e) {
            return null;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public void saveBankDetails(AgentBankEntity bankEntity) {
        EntityManager em = null;
        EntityTransaction transaction = null;
        try {
            em = emf.createEntityManager();
            transaction = em.getTransaction();
            transaction.begin();
            em.persist(bankEntity);
            transaction.commit();

        } catch (PersistenceException e) {

            e.printStackTrace();


        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public AgentBankEntity getBankDetailsByAgentId(int agentId) {
        EntityManager em=null;
        EntityTransaction transaction=null;
        try {
            em=emf.createEntityManager();


            String query="SELECT a FROM AgentBankEntity a WHERE a.agentId=:agentId";
            return em.createQuery(query, AgentBankEntity.class)
                    .setParameter("agentId", agentId)
                    .getSingleResult();






        } catch (PersistenceException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }



        return null;
    }

    @Override
    public boolean updateBankDetails(AgentBankEntity bankEntity) {
        EntityManager em = null;
        EntityTransaction transaction = null;
        try {
            em = emf.createEntityManager();
            transaction = em.getTransaction();
            transaction.begin();
            em.merge(bankEntity);
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
        return false;
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

    @Override
    public long getAgentCount() {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            TypedQuery<Long> query = em.createQuery("SELECT COUNT(a) FROM AgentEntity a", Long.class);
            return query.getSingleResult();
        } catch (PersistenceException e) {
            return 0;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public List<AgentEntity> searchAgents(String trim, int start, int size) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            String query = "SELECT a FROM AgentEntity a " +
                    "WHERE (LOWER(a.firstName) LIKE :trim " +
                    "OR LOWER(a.email) LIKE :trim " +
                    "OR LOWER(a.phoneNumber) LIKE :trim) ";
            return em.createQuery(query, AgentEntity.class)
                    .setParameter("trim", "%" + trim.toLowerCase() + "%")
                    .setFirstResult(start)
                    .setMaxResults(size)
                    .getResultList();
        } catch (PersistenceException e) {
            return Collections.emptyList();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public long getAgentSearchCount(String trim) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            String query = "SELECT COUNT(a) FROM AgentEntity a " +
                    "WHERE (LOWER(a.firstName) LIKE :trim " +
                    "OR LOWER(a.email) LIKE :trim " +
                    "OR LOWER(a.phoneNumber) LIKE :trim)";
            return em.createQuery(query, Long.class)
                    .setParameter("trim", "%" + trim.toLowerCase() + "%")
                    .getSingleResult();
        } catch (PersistenceException e) {
            return 0;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public AgentEntity findByPhone(String phoneNumber) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            String query = "select a from AgentEntity a where a.phoneNumber=:phoneNumber";
            return em.createQuery(query, AgentEntity.class)
                    .setParameter("phoneNumber",phoneNumber )
                    .getSingleResult();
        } catch (PersistenceException e) {
            return null;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }


        // ✅ Check if email exists in DB
        public boolean isEmailRegistered(String email) {
            EntityManager em = null;
            try {
                em = emf.createEntityManager();
                Long count = em.createQuery("SELECT COUNT(a) FROM AgentEntity a WHERE a.email = :email", Long.class)
                        .setParameter("email", email)
                        .getSingleResult();
                return count > 0;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            } finally {
                if (em != null) em.close();
            }
        }

        // ✅ Fetch Agent by Email
        public AgentEntity findByEmail(String email) {
            EntityManager em = null;
            try {
                em = emf.createEntityManager();
                AgentEntity agent = em.createQuery("SELECT a FROM AgentEntity a WHERE a.email = :email", AgentEntity.class)
                        .setParameter("email", email)
                        .getSingleResult();
                return agent;
            } catch (NoResultException e) {
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            } finally {
                if (em != null) em.close();
            }
        }

    public void updateAgent(AgentEntity agentEntity) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.merge(agentEntity);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (em != null && em.getTransaction().isActive())
                em.getTransaction().rollback();
        } finally {
            if (em != null) em.close();
        }
    }

  /*  @Override
    public void saveBankDetails(AgentBankEntity bankEntity) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.merge(bankEntity);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (em != null && em.getTransaction().isActive())
                em.getTransaction().rollback();
        } finally {
            if (em != null) em.close();
        }
    }*/

}

