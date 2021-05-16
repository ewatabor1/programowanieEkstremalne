package com.example.demo.fridgemanager.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import java.util.List;

public class RecipeDTO {
    private Long id;
    @Valid
    private List<String> steps;
    @Valid
    private List<RecipeIngredientDTO> ingredients;
    @NotBlank
    private String name;
    private String description;

    public RecipeDTO(Long id, List<String> steps, List<RecipeIngredientDTO> ingredients, String name, String description) {
        this.name = name;
        this.description = description;
        this.setId(id);
        this.setSteps(steps);
        this.setIngredients(ingredients);
    }

    public RecipeDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }

    public List<RecipeIngredientDTO> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<RecipeIngredientDTO> ingredients) {
        this.ingredients = ingredients;
    }
}
