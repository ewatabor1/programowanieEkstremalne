package com.example.demo;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductService {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Product> findAll() {
        List<Product> products = entityManager.createQuery("SELECT p FROM Product p").getResultList();
        return products;
    }

    @Transactional
    public void delete(Long id) {
        entityManager.createQuery("DELETE FROM Product where id =:id").setParameter("id", id).executeUpdate();
    }

    @Transactional
    public Product save(ProductDTO dto) {
        Product product = new Product(dto.getName());
        entityManager.persist(product);
        return product;
    }
}
