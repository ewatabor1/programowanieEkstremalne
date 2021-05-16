package com.example.demo.fridgemanager.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<RecipeIngredient> ingredients = new LinkedHashSet<>();

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<RecipeStep> steps = new LinkedHashSet<>();

    private String name;
    private String description;

    public Recipe() {
    }

    public Recipe(String name, String description, Map<Product, BigDecimal> ingredients, Collection<String> instructions) {
        this.name = name;
        this.description = description;
        this.ingredients = ingredients.entrySet().stream().map(entry -> new RecipeIngredient(this, entry.getKey(), entry.getValue())).collect(Collectors.toSet());
        this.steps = instructions.stream().map(instruction -> new RecipeStep(this, instruction)).collect(Collectors.toSet());
    }


    public void update(String name, String description, Map<Product, BigDecimal> ingredients, Collection<String> instructions) {
        if (name == null || name.isEmpty())
            throw new RuntimeException("name must not be empty");
        this.name = name;
        this.description = description;
        this.ingredients.clear();
        this.steps.clear();
        this.ingredients .addAll(ingredients.entrySet().stream().map(entry -> new RecipeIngredient(this, entry.getKey(), entry.getValue())).collect(Collectors.toSet()));
        this.steps .addAll( instructions.stream().map(instruction -> new RecipeStep(this, instruction)).collect(Collectors.toSet()));
    }

    public Long getId() {
        return id;
    }

    public Set<RecipeIngredient> getIngredients() {
        return ingredients;
    }

    public Set<RecipeStep> getSteps() {
        return steps;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Map<Product, BigDecimal> getProductsWithAmounts() {
        Map<Product, BigDecimal> requiredAmounts = new HashMap<>();
        for (RecipeIngredient ingredient : ingredients) {
            BigDecimal actual = requiredAmounts.get(ingredient.getProduct());
            if (actual != null)
                requiredAmounts.put(ingredient.getProduct(), ingredient.getAmount().add(actual));
            else
                requiredAmounts.put(ingredient.getProduct(), ingredient.getAmount());
        }
        return requiredAmounts;
    }
}
