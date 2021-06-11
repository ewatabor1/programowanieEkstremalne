package com.example.demo.fridgemanager.entities;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;


import static org.junit.jupiter.api.Assertions.*;

class GroceryEntryTest {
    private String product;
    private Integer quantity;
    private GroceryEntry groceryEntry;

    @BeforeEach
    void setUp() {
        product = "Cola";
        quantity = 123;
        groceryEntry = new GroceryEntry(product, quantity);
    }

    @Test
    void getId() {
        assertNull(groceryEntry.getId());
    }

    @Test
    void setId() {
        groceryEntry.setId(3L);
        assertEquals(groceryEntry.getId(), 3L);
    }

    @Test
    void getProduct() {
        assertEquals(groceryEntry.getProduct(), product);

    }

    @Test
    void setProduct() {
        groceryEntry.setProduct("pepsi");
        assertEquals(groceryEntry.getProduct(), "pepsi");
    }

    @Test
    void getQuantity() {
        assertEquals(groceryEntry.getQuantity(), quantity);
    }

    @Test
    void setQuantity() {
        groceryEntry.setQuantity(54352);
        assertEquals(groceryEntry.getQuantity(), 54352);
    }

    @Test
    void testEquals_same_object() {
        assertEquals(groceryEntry, groceryEntry);
    }

    @Test
    void testEquals_not_instanceof_GroceryEntry() {
        assertNotEquals(groceryEntry, new RecipeStep(null, null));
    }

    @Test
    void testEquals_different_instance_same_parameters() {
        assertEquals(new GroceryEntry(product, 2), new GroceryEntry(product, 2));
    }

    @Test
    void testEquals_different_instance_different_parameters() {
        assertNotEquals(new GroceryEntry(null, 3), new GroceryEntry(null, 2));
    }

}