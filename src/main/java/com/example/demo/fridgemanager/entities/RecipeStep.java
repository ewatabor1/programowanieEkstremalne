package com.example.demo.fridgemanager.entities;

import javax.persistence.*;

@Entity
public class RecipeStep {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Recipe recipe;
    private String instruction;

    public RecipeStep() {
    }

    public RecipeStep(Recipe recipe, String instruction) {
        this.recipe = recipe;
        this.instruction = instruction;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe=recipe;
    }
}
