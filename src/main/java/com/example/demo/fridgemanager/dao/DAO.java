package com.example.demo.fridgemanager.dao;

import com.example.demo.fridgemanager.entities.Product;
import com.example.demo.fridgemanager.entities.Recipe;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface DAO<T> {
    List<T> findAll();

    T findById(Long id);

    void delete(Long id);

    List<T> findByIds(Collection<Long> ids);

    T save(T data);
}
