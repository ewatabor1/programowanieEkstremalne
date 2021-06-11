package com.example.demo.fridgemanager.entities;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


import static org.junit.jupiter.api.Assertions.*;
@RunWith(JUnit4.class)
public class GroceryEntryTest {
    private String product;
    private Integer quantity;
    private GroceryEntry groceryEntry;

    @Before
    public void setUp() {
        product = "Cola";
        quantity = 123;
        groceryEntry = new GroceryEntry(product, quantity);
    }

    @Test
    public void getId() {
        assertNull(groceryEntry.getId());
    }

    @Test
    public void setId() {
        groceryEntry.setId(3L);
        assertEquals(groceryEntry.getId(), 3L);
    }

    @Test
    public void getProduct() {
        assertEquals(groceryEntry.getProduct(), product);

    }

    @Test
    public void setProduct() {
        groceryEntry.setProduct("pepsi");
        assertEquals(groceryEntry.getProduct(), "pepsi");
    }

    @Test
    public void getQuantity() {
        assertEquals(groceryEntry.getQuantity(), quantity);
    }

    @Test
    public  void setQuantity() {
        groceryEntry.setQuantity(54352);
        assertEquals(groceryEntry.getQuantity(), 54352);
    }

    @Test
    public   void testEquals_same_object() {
        assertEquals(groceryEntry, groceryEntry);
    }

    @Test
    public   void testEquals_not_instanceof_GroceryEntry() {
        assertNotEquals(groceryEntry, new RecipeStep(null, null));
    }

    @Test
    public   void testEquals_different_instance_same_parameters() {
        assertEquals(new GroceryEntry(product, 2), new GroceryEntry(product, 2));
    }

    @Test
    public   void testEquals_different_instance_different_parameters() {
        assertNotEquals(new GroceryEntry(null, 3), new GroceryEntry(null, 2));
    }

}