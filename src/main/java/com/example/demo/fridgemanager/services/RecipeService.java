package com.example.demo.fridgemanager.services;

import com.example.demo.fridgemanager.dto.RecipeDTO;
import com.example.demo.fridgemanager.entities.Recipe;

import java.util.List;

public interface RecipeService {
    List<Recipe> findAll();

    List<Recipe> findAvailable();

    Recipe getRecipeById(Long id);

    Recipe save(RecipeDTO dto);

    Recipe update(Long id, RecipeDTO dto);

    void delete(Long id);
}
