package com.example.demo.fridgemanager.dao;

import com.example.demo.fridgemanager.entities.GroceryList;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class GroceryListDAO {
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

    @Transactional
    public GroceryList save(GroceryList gl) {
        entityManager.persist(gl);
        return gl;
    }

    public GroceryList getById(Long id) {
        return entityManager.find(GroceryList.class, id);
    }
}
