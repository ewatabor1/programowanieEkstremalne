package com.example.demo.fridgemanager.dao;

import com.example.demo.fridgemanager.entities.Recipe;

import java.util.Collection;
import java.util.List;

public interface RecipeDAO extends DAO<Recipe>{
    List<Recipe> findByName(String name);
}
