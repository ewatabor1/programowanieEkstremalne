package com.example.demo.fridgemanager.dao;

import com.example.demo.fridgemanager.entities.GroceryList;
import com.example.demo.fridgemanager.entities.Recipe;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
class GroceryListDAOImpl extends AbstractDAO<GroceryList> implements GroceryListDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public List<GroceryList> findAll() {
        return entityManager.createQuery("SELECT p FROM GroceryList p").getResultList();
    }

    public List<GroceryList> findByName(String name) {
        return entityManager.createQuery("SELECT FROM GroceryList WHERE UPPER(name) = UPPER(:name)")
                .setParameter("name", name)
                .getResultList();
    }

    @Override
    public GroceryList findById(Long id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<GroceryList> query = builder.createQuery(GroceryList.class);
        Root<GroceryList> root = query.from(GroceryList.class);
        TypedQuery<GroceryList> allQuery = entityManager.createQuery(
                query.select(root)
                        .where(root.get("id").in(Arrays.asList(id)))
        );
        try {
            return allQuery.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<GroceryList> findByIds(Collection<Long> ids) {
        if (ids == null || ids.isEmpty())
            return Collections.emptyList();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<GroceryList> query = builder.createQuery(GroceryList.class);
        Root<GroceryList> root = query.from(GroceryList.class);
        TypedQuery<GroceryList> allQuery = entityManager.createQuery(
                query.select(root)
                        .where(root.get("id").in(ids))
        );
        return allQuery.getResultList();
    }

    @Transactional
    public void delete(Long id) {
        entityManager.createQuery("DELETE FROM GroceryList WHERE id =:id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Transactional
    public void deleteByName(String name) {
        entityManager.createQuery("DELETE FROM GroceryList WHERE UPPER(name) = UPPER(:name)")
                .setParameter("name", name)
                .executeUpdate();
    }

    public GroceryList getById(Long id) {
        return entityManager.find(GroceryList.class, id);
    }
}
