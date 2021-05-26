package com.example.demo.fridgemanager.dao;

import com.example.demo.fridgemanager.entities.Product;
import com.example.demo.fridgemanager.entities.Recipe;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Service
public class RecipeDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Recipe> findAll() {
        return entityManager.createQuery("SELECT r FROM Recipe r LEFT JOIN RecipeIngredient  ri ON ri.recipe.id = r.id LEFT JOIN RecipeStep rs ON rs.recipe.id = r.id").getResultList();
    }

    public Recipe findById(Long id) {
        try {
            Object result = entityManager.createQuery("SELECT r FROM Recipe r LEFT JOIN RecipeIngredient  ri ON ri.recipe.id = r.id LEFT JOIN RecipeStep rs ON rs.recipe.id = r.id WHERE r.id = :id")
                    .setParameter("id", id)
                    .getSingleResult();
            return ((Recipe) result);
        } catch (NoResultException noResult) {
            return null;
        }
    }
    public List<Recipe> findByName(String name) {
            return entityManager.createQuery("SELECT r FROM Recipe r LEFT JOIN RecipeIngredient  ri ON ri.recipe.id = r.id LEFT JOIN RecipeStep rs ON rs.recipe.id = r.id WHERE UPPER(r.name) = UPPER(:name)")
                    .setParameter("name", name)
                    .getResultList();
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


    @Transactional
    public Recipe save(Recipe recipe) {
        entityManager.persist(recipe);
        return recipe;
    }


}

