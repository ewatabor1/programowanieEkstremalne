package com.example.demo.fridgemanager.services;

import com.example.demo.fridgemanager.dao.ProductDAO;
import com.example.demo.fridgemanager.entities.Product;
import com.example.demo.fridgemanager.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Transactional
    public void deleteByName(String name) {
        dao.deleteByName(name);
    }

    @Transactional
    public Product save(ProductDTO dto) {
        Product product = new Product(dto.getName(), dto.getKcal(), dto.getExpiryDate(),dto.getProteins(),dto.getCarbohydrates(),dto.getFats());
        dao.save(product);
        return product;
    }
}
