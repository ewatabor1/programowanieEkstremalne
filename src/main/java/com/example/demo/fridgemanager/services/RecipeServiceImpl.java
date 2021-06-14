package com.example.demo.fridgemanager.services;

import com.example.demo.fridgemanager.dao.ProductDAO;
import com.example.demo.fridgemanager.dao.RecipeDAO;
import com.example.demo.fridgemanager.dto.RecipeDTO;
import com.example.demo.fridgemanager.dto.RecipeIngredientDTO;
import com.example.demo.fridgemanager.entities.Product;
import com.example.demo.fridgemanager.entities.Recipe;
import com.example.demo.fridgemanager.exceptions.EntityNotFoundException;
import com.example.demo.fridgemanager.utils.JsonLoader;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public
class RecipeServiceImpl implements RecipeService {
    @Autowired
    private RecipeDAO dao;
    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    public void initialize(Environment environment) throws JsonProcessingException {
        String initialRecipesFile = environment.getProperty("initial_recipes");
        if (initialRecipesFile != null) {
            List<RecipeDTO> recipes = JsonLoader.readJsonListFromFile(initialRecipesFile, RecipeDTO.class, objectMapper);
            for (RecipeDTO recipe : recipes) {
                if (dao.findByName(recipe.getName()).isEmpty())
                    save(recipe);
            }
        }
    }

    public List<Recipe> findAll() {
        List<Recipe> recipes = dao.findAll();
        return recipes;
    }

    public Recipe getRecipeById(Long id) {
        Recipe recipe = dao.findById(id);
        return recipe;
    }


    public List<Recipe> findAvailable() {
        List<Product> allProducts = productDAO.findAll();
        Map<Product, BigDecimal> availableProducts = new HashMap<>();
        for (Product product : allProducts) {
            // TODO: 16.05.2021 change to check state of the product instead of adding 1
            BigDecimal state = availableProducts.get(product);
            if (state != null) {
                availableProducts.put(product, state.add(BigDecimal.ONE));
            } else
                availableProducts.put(product, BigDecimal.ONE);
        }
        List<Recipe> result = new ArrayList<>();
        L0:
        for (Recipe recipe : dao.findAll()) {
            Map<Product, BigDecimal> productAmounts = recipe.getProductsWithAmounts();
            for (Map.Entry<Product, BigDecimal> entry : productAmounts.entrySet()) {
                Product product = entry.getKey();
                BigDecimal requiredAmount = entry.getValue();
                BigDecimal availableAmount = availableProducts.getOrDefault(product, BigDecimal.ZERO);
                if (requiredAmount.compareTo(availableAmount) > 0)
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

    @Transactional
    public Recipe save(RecipeDTO dto) {
        Recipe recipe = new Recipe();
        return updateInternal(recipe, dto);
    }

    @Transactional
    public Recipe update(Long id, RecipeDTO dto) {
        Recipe recipe = dao.findById(id);
        if (recipe == null)
            throw new EntityNotFoundException(Recipe.class, id);
        return updateInternal(recipe, dto);
    }

    private Recipe updateInternal(Recipe recipe, RecipeDTO dto) {
        Map<Product, BigDecimal> ingredientMap = new HashMap<>();
        if (dto.getIngredients() != null) {
            Set<Long> productIds = dto.getIngredients().stream().map(RecipeIngredientDTO::getProductId).filter(Objects::nonNull).collect(Collectors.toSet());
            Set<String> productNames = dto.getIngredients().stream().map(RecipeIngredientDTO::getProductName).filter(Objects::nonNull).collect(Collectors.toSet());
            List<Product> products = productDAO.findByIds(productIds);
            products.addAll(productDAO.findByNames(productNames));
            for (RecipeIngredientDTO ing : dto.getIngredients()) {
                Product product = products.stream()
                        .filter(p -> p.getId().equals(ing.getProductId()))
                        .findFirst().orElseGet(() ->
                                products.stream().filter(p -> p.getName().equals(ing.getProductName()))
                                        .findFirst().orElseThrow(() -> new EntityNotFoundException(Product.class, ing.getProductId() != null ? ing.getProductId() : ing.getProductName())));
                ingredientMap.put(product, ing.getAmount());
            }
        }

        Recipe.Builder builder = new Recipe.Builder(dto.getName())
                .withDescription(dto.getDescription())
                .withIngredients(ingredientMap)
                .withInstructions(dto.getSteps());

        recipe.update(builder.create());
        dao.save(recipe);
        return recipe;
    }

}
