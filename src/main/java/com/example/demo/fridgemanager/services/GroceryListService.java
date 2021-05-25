package com.example.demo.fridgemanager.services;

import com.example.demo.fridgemanager.dao.GroceryListDAO;
import com.example.demo.fridgemanager.dto.GroceryListDTO;
import com.example.demo.fridgemanager.entities.GroceryList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class GroceryListService {
    @Autowired
    private GroceryListDAO dao;

    public List<GroceryList> findAll() {
        return dao.findAll();
    }

    public List<GroceryList> getGroceryListsByName(String name) {
        return dao.findByName(name);
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
}
