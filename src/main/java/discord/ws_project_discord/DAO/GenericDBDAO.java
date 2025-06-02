package discord.ws_project_discord.DAO;
import discord.ws_project_discord.ApplicationListener;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.io.Serializable;
import java.util.List;

public class GenericDBDAO<T extends Serializable, ID extends Serializable> {
    private final Class<T> entityClass;

    protected GenericDBDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected static EntityManager getEM() {
        return ApplicationListener.getEmf().createEntityManager();
    }

    protected void create(T entity) {
        EntityManager em = getEM();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(entity);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    protected List<T> findAll() {
        try (EntityManager em = getEM()) {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<T> cq = cb.createQuery(entityClass);
            Root<T> root = cq.from(entityClass);
            cq.select(root);
            TypedQuery<T> query = em.createQuery(cq);
            return query.getResultList();
        }
    }


    protected T find (ID id, EntityManager em) {
        return em.find(entityClass, id);
    }
    protected T find(ID id) {
        try (EntityManager em = getEM()) {
            return this.find(id, em);
        }
    }

    protected void delete(ID id) {
        boolean entityDeleted = false;
        EntityManager em = getEM();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            T entity = em.find(entityClass, id);
            if (entity != null) {
                em.remove(entity);
                entityDeleted = true;
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw new RuntimeException("Error while deleting entity", e);
        } finally {
            em.close();
        }
    }

    protected void update(T entity) {
        EntityManager em = getEM();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(entity);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw new RuntimeException("Error while updating entity", e);
        } finally {
            em.close();
        }
    }
}
