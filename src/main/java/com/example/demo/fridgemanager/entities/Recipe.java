package com.example.demo.fridgemanager.entities;

import org.hibernate.annotations.SortComparator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @SortComparator(RecipeIngedientsComparator.class)
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<RecipeIngredient> ingredients = new TreeSet<>(new RecipeIngedientsComparator());
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<RecipeStep> steps = new LinkedHashSet<>();

    private String name;
    private String description;

    public Recipe() {
    }

    private Recipe(String name, String description, Map<Product, BigDecimal> ingredients, Collection<String> instructions) {
        if (name == null || name.isEmpty())
            throw new RuntimeException("name must not be empty");
        this.name = name;
        this.description = description;
        this.ingredients.addAll(ingredients.entrySet().stream().map(entry -> new RecipeIngredient(this, entry.getKey(), entry.getValue())).collect(Collectors.toSet()));
        this.steps.addAll(instructions.stream().map(instruction -> new RecipeStep(this, instruction)).collect(Collectors.toSet()));
    }


    public void update(Recipe recipe) {
        this.name = recipe.getName();
        this.description = recipe.getDescription();
        this.ingredients.clear();
        this.ingredients.addAll(recipe.getIngredients());
        for (RecipeIngredient ingredient : this.ingredients) {
            ingredient.setRecipe(this);
        }
        this.steps.clear();
        this.steps.addAll(recipe.getSteps());
        for (RecipeStep step : this.steps) {
            step.setRecipe(this);
        }
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

    public static class Builder {
        private List<String> instructions = new ArrayList<>();
        private Map<Product, BigDecimal> ingredients = new HashMap<>();
        private final String name;
        private String description;

        public Builder(String name) {
            this.name = name;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withInstructions(Collection<String> steps) {
            if (steps != null) {
                this.instructions.clear();
                this.instructions.addAll(steps);
            }
            return this;
        }

        public Builder withIngredients(Map<Product, BigDecimal> ingredients) {
            if (ingredients != null) {
                this.ingredients.clear();
                this.ingredients.putAll(ingredients);
            }
            return this;
        }

        public Builder addInstruction(String instruction) {
            this.instructions.add(instruction);
            return this;
        }

        public Builder addIngredient(Product product, BigDecimal amount) {
            this.ingredients.put(product, amount);
            return this;
        }

        public Recipe create() {
            return new Recipe(name, description, ingredients, instructions);
        }
    }
}
