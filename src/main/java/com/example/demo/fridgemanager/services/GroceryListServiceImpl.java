package com.example.demo.fridgemanager.services;

import com.example.demo.fridgemanager.dao.GroceryListDAO;
import com.example.demo.fridgemanager.dto.GroceryListDTO;
import com.example.demo.fridgemanager.entities.GroceryList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public
class GroceryListServiceImpl implements GroceryListService {
    @Autowired
    private GroceryListDAO dao;

    public List<GroceryList> findAll() {
        return dao.findAll();
    }

    public List<GroceryList> getGroceryListsByName(String name) {
        return dao.findByName(name);
    }

    public GroceryList getById(Long id) {
        return dao.findById(id);
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
    public GroceryList save(GroceryListDTO dto) {
        GroceryList product = new GroceryList(dto.getName(), dto.getCreatedAt(), dto.getProducts());
        dao.save(product);
        return product;
    }

    @Transactional
    public GroceryList update(Long id, GroceryListDTO dto) {
        GroceryList entityToUpdate = dao.findById(id);
        if (entityToUpdate != null) {
            if (dto.getName() != null) entityToUpdate.setName(dto.getName());
            if (dto.getProducts() != null) entityToUpdate.setProducts(dto.getProducts());
            return dao.save(entityToUpdate);
        }
        return entityToUpdate;
    }

}
