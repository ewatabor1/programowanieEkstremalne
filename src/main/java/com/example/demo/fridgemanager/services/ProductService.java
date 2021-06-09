package com.example.demo.fridgemanager.services;

import com.example.demo.fridgemanager.dao.ProductRepository;
import com.example.demo.fridgemanager.entities.Product;
import com.example.demo.fridgemanager.dto.ProductDTO;
import com.example.demo.fridgemanager.utils.JsonLoader;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository dao;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    public void initialize(Environment environment) throws JsonProcessingException {
        String initialProductsFile = environment.getProperty("initial_products");
        if (initialProductsFile != null) {
            List<ProductDTO> products = JsonLoader.readJsonListFromFile(initialProductsFile, ProductDTO.class, objectMapper);
            for (ProductDTO product : products) {
                if (dao.findByName(product.getName()).isEmpty())
                    save(product);
            }
        }
    }


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
            if (dto.getProteins() != null) entityToUpdate.setProteins(dto.getProteins());
            if (dto.getCarbohydrates() != null) entityToUpdate.setCarbohydrates(dto.getCarbohydrates());
            if (dto.getFats() != null) entityToUpdate.setFats(dto.getFats());
            dao.save(entityToUpdate);
        }
        return entityToUpdate;
    }
}
