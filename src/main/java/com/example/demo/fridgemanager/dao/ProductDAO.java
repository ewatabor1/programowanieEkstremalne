package com.example.demo.fridgemanager.dao;

import com.example.demo.fridgemanager.dto.ProductDTO;
import com.example.demo.fridgemanager.entities.Product;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Product> findAll() {
        return entityManager.createQuery("SELECT p FROM Product p").getResultList();
    }

    public List<Product> findByName(String name) {
        return entityManager.createQuery("SELECT FROM Product WHERE UPPER(name) = UPPER(:name)")
                .setParameter("name", name)
                .getResultList();
    }

    @Transactional
    public void delete(Long id) {
        entityManager.createQuery("DELETE FROM Product WHERE id =:id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Transactional
    public void deleteByName(String name) {
        entityManager.createQuery("DELETE FROM Product WHERE UPPER(name) = UPPER(:name)")
                .setParameter("name", name)
                .executeUpdate();
    }

    @Transactional
    public Product save(Product product) {
        entityManager.persist(product);
        return product;
    }
}
