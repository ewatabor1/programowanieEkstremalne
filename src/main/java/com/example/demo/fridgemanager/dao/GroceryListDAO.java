package com.example.demo.fridgemanager.dao;

import com.example.demo.fridgemanager.entities.GroceryList;

import java.util.List;

public interface GroceryListDAO extends DAO<GroceryList> {
    List<GroceryList> findByName(String name);

    void deleteByName(String name);
}
