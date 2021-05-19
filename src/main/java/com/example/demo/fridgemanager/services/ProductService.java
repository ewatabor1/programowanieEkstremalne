package com.example.demo.fridgemanager.services;

import com.example.demo.fridgemanager.dao.ProductDAO;
import com.example.demo.fridgemanager.entities.Product;
import com.example.demo.fridgemanager.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductDAO dao;

    public List<Product> findAll() {
        List<Product> products = dao.findAll();
        return products;
    }

    public List<Product> getProductsByName(String name) {
        List<Product> products = dao.findByName(name);
        return products;
    }

    @Transactional
    public void delete(Long id) {
        dao.delete(id);
    }

    public Product getById(Long id) {
        return dao.getById(id);
    }

    @Transactional
    public void deleteByName(String name) {
        dao.deleteByName(name);
    }

    @Transactional
    public Product save(ProductDTO dto) {
        Product product = dto.toEntity();
        dao.save(product);
        return product;
    }

    @Transactional
    public Product update(Long id, ProductDTO dto) {
        Product entityToUpdate = dao.getById(id);
        if (entityToUpdate != null) {
            if (dto.getExpiryDate() != null) entityToUpdate.setExpiryDate(dto.getExpiryDate());
            if (dto.getKcal() != null) entityToUpdate.setKcal(dto.getKcal());
            if (dto.getQuantity() != null) entityToUpdate.setQuantity(dto.getQuantity());
            if (dto.getMinQuantity() != null) entityToUpdate.setMinQuantity(dto.getMinQuantity());
            dao.save(entityToUpdate);
        }
        return entityToUpdate;
    }
}
