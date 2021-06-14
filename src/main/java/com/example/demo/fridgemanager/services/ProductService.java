package com.example.demo.fridgemanager.services;

import com.example.demo.fridgemanager.dto.ProductDTO;
import com.example.demo.fridgemanager.entities.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    List<Product> getProductsByName(String name);

    Product save(ProductDTO dto);

    void delete(Long id);

    void deleteByName(String name);

    Product getById(Long id);

    Product update(Long id, ProductDTO dto);
}
