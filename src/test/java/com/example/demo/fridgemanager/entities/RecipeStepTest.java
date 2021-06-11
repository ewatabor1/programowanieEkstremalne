package com.example.demo.fridgemanager.entities;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class RecipeStepTest {
    private RecipeStep recipeStep;
    private Recipe recipe;
    private final String recipeInstruction = "This is instruction for the recipe";

    @BeforeEach
    void setUp() {
        Product pepsi = new Product("pepsi", 250, LocalDate.now(), null, null, null, null, null);
        recipe = new Recipe.Builder()
                .setName("student's breakfast")
                .setDescription("typical breakfast")
                .addIngredient(pepsi, BigDecimal.valueOf(2))
                .addInstruction("drink pepsi")
                .create();
        recipeStep = new RecipeStep(recipe, recipeInstruction);
    }

    @Test
    void getRecipe() {
        assertEquals(recipeStep.getRecipe(), recipe);
    }

    @Test
    void getInstruction() {
        assertEquals(recipeStep.getInstruction(), recipeInstruction);
    }

    @Test
    void setRecipe() {
        Product cola = new Product("cola", 250, LocalDate.now(), null, null, null, null, null);
        Recipe newRecipe = new Recipe.Builder()
                .setName("students drink")
                .setDescription("typical drink")
                .addIngredient(cola, BigDecimal.valueOf(1))
                .addInstruction("drink cola")
                .create();
        recipeStep.setRecipe(newRecipe);
        assertEquals(recipeStep.getRecipe(), newRecipe);
    }
}