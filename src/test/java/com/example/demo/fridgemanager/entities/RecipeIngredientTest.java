package com.example.demo.fridgemanager.entities;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(JUnit4.class)
public class RecipeIngredientTest {
    private Product product;
    private Recipe recipe;
    private BigDecimal amount;
    private RecipeIngredient recipeIngredient;

    @Before
    public void setUp() {
        product = new Product("pepsi", 250, LocalDate.now(), null, null, null, null, null);
        recipe = new Recipe.Builder()
                .setName("student's breakfast")
                .setDescription("typical breakfast")
                .addIngredient(product, BigDecimal.valueOf(2))
                .addInstruction("drink pepsi")
                .create();
        amount = BigDecimal.valueOf(123);
        recipeIngredient = new RecipeIngredient(recipe, product, amount);


    }

    @Test
    public   void getRecipe() {
        assertEquals(recipeIngredient.getRecipe(), recipe);
    }

    @Test
    public   void setRecipe() {
        Product cola = new Product("cola", 250, LocalDate.now(), null, null, null, null, null);
        Recipe newRecipe = new Recipe.Builder()
                .setName("students drink")
                .setDescription("typical drink")
                .addIngredient(cola, BigDecimal.valueOf(1))
                .addInstruction("drink cola")
                .create();
        recipeIngredient.setRecipe(newRecipe);
        assertEquals(recipeIngredient.getRecipe(), newRecipe);
    }

    @Test
    public   void getProduct() {
        assertEquals(recipeIngredient.getProduct(), product);
    }

    @Test
    public  void setProduct() {
        Product cola = new Product("cola", 250, LocalDate.now(), null, null, null, null, null);
        recipeIngredient.setProduct(cola);
        assertEquals(recipeIngredient.getProduct(), cola);
    }

    @Test
    public   void getAmount() {
        assertEquals(recipeIngredient.getAmount(), amount);
    }

    @Test
    public  void setAmount() {
        BigDecimal newAmount = new BigDecimal(456);
        recipeIngredient.setAmount(newAmount);
        assertEquals(recipeIngredient.getAmount(), newAmount);
    }

    @Test
    public   void getId() {
        assertNull(recipeIngredient.getId());
    }
}