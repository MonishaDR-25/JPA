package com.xworkz.DairyManagement.repository;

import com.xworkz.DairyManagement.entity.ProductCollectionAuditEntity;
import com.xworkz.DairyManagement.entity.ProductCollectionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductCollectionRepoImpl implements ProductCollectionRepository{

    @Autowired
    private EntityManagerFactory emf;

    @Override
    public void save(ProductCollectionEntity entity) {

        EntityManager em = null;
        EntityTransaction et = null;
        try {
            em = emf.createEntityManager();
            et = em.getTransaction();
            et.begin();
            em.persist(entity);
            et.commit();
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void saveAudit(ProductCollectionAuditEntity audit) {
        EntityManager em = null;
        EntityTransaction et = null;
        try {
            em = emf.createEntityManager();
            et = em.getTransaction();
            et.begin();
            em.persist(audit);
            et.commit();
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<ProductCollectionEntity> getAllProductsByCollectionDate() {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            return em.createQuery(
                    "SELECT pc FROM ProductCollectionEntity pc " +
                            "JOIN FETCH pc.agent a " +
                            "JOIN FETCH pc.admin ad " +
                            "ORDER BY pc.productCollectionId DESC",
                    ProductCollectionEntity.class
            ).getResultList();
        } finally {
            if (em != null) em.close();
        }
    }

    @Override
    public List<ProductCollectionEntity> findByCollectedDate(LocalDate d) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            return em.createQuery(
                    "SELECT pc FROM ProductCollectionEntity pc " +
                            "JOIN FETCH pc.agent a " +
                            "JOIN FETCH pc.admin ad " +
                            "WHERE pc.collectedAt = :d " +
                            "ORDER BY pc.productCollectionId DESC",
                    ProductCollectionEntity.class
            ).setParameter("d",d).getResultList();
        } finally {
            if (em != null) em.close();
        }
    }

    public ProductCollectionEntity findByIdWithRelations(Integer id) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();

            EntityGraph<ProductCollectionEntity> graph =
                    em.createEntityGraph(ProductCollectionEntity.class);
            graph.addAttributeNodes("agent", "admin"); // load both

            Map<String, Object> hints = new HashMap<>();
            hints.put("javax.persistence.fetchgraph", graph);

            return em.find(ProductCollectionEntity.class, id, hints);
        } finally {
            if (em != null) em.close();
        }
    }

    @Override
    public List<ProductCollectionEntity> findBetweenDates(LocalDate start, LocalDate end) {

        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            return em.createQuery(
                            "SELECT pc FROM ProductCollectionEntity pc " +
                                    "JOIN FETCH pc.agent a " +
                                    "JOIN FETCH pc.admin ad " +
                                    "WHERE pc.collectedAt BETWEEN :start AND :end",
                            ProductCollectionEntity.class
                    )
                    .setParameter("start", start)
                    .setParameter("end", end)
                    .getResultList();

        } finally {
            if (em != null) em.close();
        }
    }

}
