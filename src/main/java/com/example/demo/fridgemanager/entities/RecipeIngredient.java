package com.example.demo.fridgemanager.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class RecipeIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Recipe recipe;
    @OneToOne
    private Product product;

    private BigDecimal amount;

    public RecipeIngredient() {
    }

    public RecipeIngredient(Recipe recipe, Product product, BigDecimal amount) {
        this.setRecipe(recipe);
        this.setProduct(product);
        this.setAmount(amount);
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }
}
