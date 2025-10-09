package com.xworkz.DairyManagement.repository;

import com.xworkz.DairyManagement.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;

@Repository
public class ProductRepoImpl implements ProductRepository{
    @Autowired
    private EntityManagerFactory emf;

    @Override
    public List<ProductEntity> getAllProducts(int page, int size) {
        EntityManager em=null;
        try {
            em = emf.createEntityManager();
            TypedQuery<ProductEntity> query = em.createNamedQuery("findAllProducts", ProductEntity.class);
            query.setFirstResult((page - 1) * size);
            query.setMaxResults(size);
           // List<ProductEntity> findAllProducts = em.createNamedQuery("findAllProducts", ProductEntity.class).getResultList();
            List<ProductEntity> findAllProducts = query.getResultList();
            return findAllProducts;
        }catch (PersistenceException e){
            return Collections.emptyList();

        }
        finally {
            if (em!=null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public void save(ProductEntity productEntity) {
        EntityManager em=null;
        EntityTransaction transaction=null;
        try {
            em = emf.createEntityManager();
            transaction = em.getTransaction();
            transaction.begin();
            em.persist(productEntity);
            transaction.commit();

        }catch (PersistenceException e){
            if (transaction!=null && transaction.isActive()) {
                transaction.rollback();

            }

        }
        finally {
            if (em!=null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public ProductEntity findById(Integer id) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            ProductEntity productEntity = em.find(ProductEntity.class, id);
            System.out.println("id in ProductRepo: " + id);
            System.out.println("productEntity in Repo: " + productEntity);
            return productEntity;
        } catch (PersistenceException e) {
            System.err.println("Failed to find product by id: " + id);
            e.printStackTrace();
            return null;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public void update(ProductEntity productEntity) {
        EntityManager em = null;
        EntityTransaction transaction = null;
        try {
            em = emf.createEntityManager();
            transaction = em.getTransaction();
            transaction.begin();
            em.merge(productEntity); // update product entity
            transaction.commit();
            System.out.println("Product updated successfully: " + productEntity.getProductId());
        } catch (PersistenceException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Failed to update product");
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = null;
        EntityTransaction transaction = null;
        try {
            em = emf.createEntityManager();
            transaction = em.getTransaction();
            transaction.begin();

            ProductEntity product = em.find(ProductEntity.class, id);
            if (product != null) {
                em.remove(product); // hard delete
                System.out.println("Product deleted successfully with id " + id);
            } else {
                System.out.println("No Product found with id: " + id);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Failed to delete Product with id: " + id);
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public long getProductCount() {
        EntityManager em=null;
        try {
            em = emf.createEntityManager();
            TypedQuery<Long> query = em.createNamedQuery("countProducts", Long.class);
            return query.getSingleResult();
        }
        catch (PersistenceException e){
            return 0;
        }
        finally {
            if (em!=null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public List<ProductEntity> searchProducts(String trim, int start, int size) {
        EntityManager em=null;
        try {
            em = emf.createEntityManager();
            String query = "SELECT p FROM ProductEntity p " +
                    "WHERE lower(p.productName) LIKE :trim " +
                    "OR lower(p.productType) LIKE :trim " +
                    "AND p.active = true";
            return em.createQuery(query, ProductEntity.class)
                    .setParameter("trim", "%" + trim.toLowerCase() + "%")
                    .setFirstResult(start)
                    .setMaxResults(size)
                    .getResultList();

        }catch (PersistenceException e){
            return Collections.emptyList();
        }
        finally {
            if (em!=null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public long getProductSearchCount(String trim) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();

            String query = "SELECT COUNT(p) FROM ProductEntity p " +
                    "WHERE lower(p.productName) LIKE :trim " +
                    "OR lower(p.productType) LIKE :trim " +
                    "AND p.active = true";
            return em.createQuery(query, Long.class)
                    .setParameter("trim", "%" + trim.toLowerCase() + "%")
                    .getSingleResult();
        }catch (Exception e) {
           // log.error("Failed to count Products by search", e);
            return 0;
        }finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
