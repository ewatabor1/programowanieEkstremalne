package com.example.demo.fridgemanager.dao;

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
import java.util.List;
import java.util.Locale;

@Service
public class RecipeDAO extends DAO<Recipe> {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Recipe> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Recipe> query = builder.createQuery(Recipe.class);
        Root<Recipe> root = query.from(Recipe.class);
        TypedQuery<Recipe> allQuery = entityManager.createQuery(query.select(root));
        return allQuery.getResultList();
    }

    public Recipe findById(Long id) {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Recipe> query = builder.createQuery(Recipe.class);
            Root<Recipe> root = query.from(Recipe.class);
            TypedQuery<Recipe> allQuery = entityManager.createQuery(
                    query.select(root)
                            .where(builder.equal(root.get("id"), id))
            );
            return allQuery.getSingleResult();
        } catch (NoResultException noResult) {
            return null;
        }
    }

    public List<Recipe> findByName(String name) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Recipe> query = builder.createQuery(Recipe.class);
        Root<Recipe> root = query.from(Recipe.class);
        TypedQuery<Recipe> allQuery = entityManager.createQuery(
                query.select(root)
                        .where(builder.equal(builder.upper(root.get("name")), name.toUpperCase(Locale.ROOT)))
        );
        return allQuery.getResultList();
    }

    @Transactional
    public void delete(Long id) {
        entityManager.createQuery("DELETE FROM RecipeStep  WHERE recipe.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        entityManager.createQuery("DELETE FROM RecipeIngredient  WHERE recipe.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        entityManager.createQuery("DELETE FROM Recipe WHERE id =:id")
                .setParameter("id", id)
                .executeUpdate();
    }


}

