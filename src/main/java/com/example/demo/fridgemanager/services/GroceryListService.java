package com.example.demo.fridgemanager.services;

import com.example.demo.fridgemanager.dto.GroceryListDTO;
import com.example.demo.fridgemanager.entities.GroceryList;
import com.example.demo.fridgemanager.entities.Product;

import java.util.List;

public interface GroceryListService {
    List<GroceryList> findAll();

    List<GroceryList> getGroceryListsByName(String name);

    GroceryList save(GroceryListDTO dto);

    void delete(Long id);

    void deleteByName(String name);

    GroceryList getById(Long id);

    GroceryList update(Long id, GroceryListDTO dto);
}
