package com.example.demo.fridgemanager.dao;

import com.example.demo.fridgemanager.entities.Product;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface ProductDAO extends DAO<Product>{
    List<Product> findByName(String name);

    List<Product> findByNames(Collection<String> names);

    void deleteByName(String name);
}
