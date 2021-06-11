package com.example.demo.fridgemanager.dto;

import com.example.demo.fridgemanager.entities.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RecipeDTOMapper {
    @Autowired
    private ProductDTOMapper productDTOMapper;
    public RecipeDTO mapToDTO(Recipe recipe) {
        if (recipe == null)
            return null;

        if(productDTOMapper == null) {
            productDTOMapper = new ProductDTOMapper();
        }
        return new RecipeDTO(
                recipe.getId(),
                recipe.getSteps().stream().map(step ->step.getInstruction()).collect(Collectors.toList()),
                recipe.getIngredients().stream().map(ing ->new RecipeIngredientDTO(productDTOMapper.mapToDTO(ing.getProduct()), ing.getProduct().getName(),ing.getProduct().getId(), ing.getAmount())).collect(Collectors.toList()),
                recipe.getName(),
                recipe.getDescription()
        );
    }

    public List<RecipeDTO> mapToDTO(Collection<Recipe> recipes) {
        if (recipes == null)
            return Collections.emptyList();
        return recipes.stream().map(recipe -> mapToDTO(recipe)).filter(Objects::nonNull).collect(Collectors.toList());
    }
}
