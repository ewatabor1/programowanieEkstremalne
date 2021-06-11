package com.example.demo.fridgemanager.entities;

import java.util.Comparator;

public class RecipeIngedientsComparator implements Comparator<RecipeIngredient> {
    @Override
    public int compare(RecipeIngredient recipeIngredient, RecipeIngredient t1) {
        return recipeIngredient.getProduct().getName().compareTo(t1.getProduct().getName());
    }
}
