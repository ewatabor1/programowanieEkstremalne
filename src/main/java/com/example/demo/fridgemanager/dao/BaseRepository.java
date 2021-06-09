package com.example.demo.fridgemanager.dao;

import com.example.demo.fridgemanager.entities.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public abstract class BaseRepository<Entity> {
    @PersistenceContext
    protected EntityManager entityManager;

    public abstract List<Entity> findAll();

    public abstract void delete(Long id);

    @Transactional
    public Entity save(Entity entity) {
        entityManager.persist(entity);
        return entity;
    }
}
