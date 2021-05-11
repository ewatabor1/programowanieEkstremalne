package com.example.demo.fridgemanager.services;

import com.example.demo.fridgemanager.entities.Product;
import com.example.demo.fridgemanager.dto.ProductDTO;
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
    public List<Product> getProducts(String name) {
        List<Product> products = entityManager.createQuery("SELECT FROM Product where name =:name").setParameter("name", name).getResultList();
        return products;
    }

    @Transactional
    public void delete(Long id) {
        entityManager.createQuery("DELETE FROM Product where id =:id").setParameter("id", id).executeUpdate();
    }

    @Transactional
    public void deleteByName(String name) {
        entityManager.createQuery("DELETE FROM Product where name =:name").setParameter("name", name).executeUpdate();
    }

    @Transactional
    public Product save(ProductDTO dto) {
        Product product = new Product(dto.getName(), dto.getKcal(), dto.getExpiryDate());
        entityManager.persist(product);
        return product;
    }
}
