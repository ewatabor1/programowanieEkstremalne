package com.example.demo.fridgemanager.services;

import com.example.demo.fridgemanager.dao.ProductDAO;
import com.example.demo.fridgemanager.dao.RecipeDAO;
import com.example.demo.fridgemanager.dto.RecipeDTO;
import com.example.demo.fridgemanager.dto.RecipeIngredientDTO;
import com.example.demo.fridgemanager.entities.Product;
import com.example.demo.fridgemanager.entities.Recipe;
import com.example.demo.fridgemanager.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecipeService {
    @Autowired
    private RecipeDAO dao;
    @Autowired
    private ProductDAO productDao;

    public List<Recipe> findAll() {
        List<Recipe> recipes = dao.findAll();
        return recipes;
    }

    public Recipe getRecipeById(Long id) {
        Recipe recipe = dao.findById(id);
        return recipe;
    }


    public List<Recipe> findAvailable() {
        List<Product> allProducts = productDao.findAll();
        Map<Product,BigDecimal> availableProducts = new HashMap<>();
        for (Product product : allProducts) {
            // TODO: 16.05.2021 change to check state of the product instead of adding 1
            BigDecimal state = availableProducts.get(product);
            if(state!=null){
                availableProducts.put(product,state.add(BigDecimal.ONE));
            }else
                availableProducts.put(product,BigDecimal.ONE);
        }
        List<Recipe> result = new ArrayList<>();
        L0:
        for (Recipe recipe : dao.findAll()) {
            Map<Product,BigDecimal> productAmounts = recipe.getProductsWithAmounts();
            for (Map.Entry<Product, BigDecimal> entry : productAmounts.entrySet()) {
                Product product = entry.getKey();
                BigDecimal requiredAmount = entry.getValue();
                BigDecimal availableAmount = availableProducts.getOrDefault(product,BigDecimal.ZERO);
                if(requiredAmount.compareTo(availableAmount) > 0)
                    continue L0;

            }
            result.add(recipe);
        }
        return result;
    }

    @Transactional
    public void delete(Long id) {
        dao.delete(id);
    }

    public Recipe save(RecipeDTO dto) {
        Recipe recipe = new Recipe();
        return updateInternal(recipe, dto);
    }

    public Recipe update(Long id, RecipeDTO dto) {
        Recipe recipe = dao.findById(id);
        if (recipe == null)
            throw new EntityNotFoundException(Recipe.class, id);
        return updateInternal(recipe, dto);
    }

    private Recipe updateInternal(Recipe recipe, RecipeDTO dto) {
        Set<Long> productIds = dto.getIngredients().stream().map(RecipeIngredientDTO::getProductId).collect(Collectors.toSet());
        List<Product> products = productDao.findByIds(productIds);
        Map<Product, BigDecimal> ingredientMap = new HashMap<>();
        if (dto.getIngredients() != null) {
            for (RecipeIngredientDTO ing : dto.getIngredients()) {
                Product product = products.stream().filter(p -> p.getId().equals(ing.getProductId())).findFirst().orElseThrow(() -> new EntityNotFoundException(Product.class, ing.getProduct().getId()));
                ingredientMap.put(product, ing.getAmount());
            }
        }

        recipe.update(
                dto.getName(),
                dto.getDescription(),
                ingredientMap,
                dto.getSteps() != null ? dto.getSteps() : Collections.emptyList()
        );
        dao.save(recipe);
        return recipe;
    }

}
