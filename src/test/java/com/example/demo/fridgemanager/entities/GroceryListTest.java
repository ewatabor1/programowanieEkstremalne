package com.example.demo.fridgemanager.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GroceryListTest {

    private GroceryList groceryList;

    @BeforeEach
    void setUp() {
        GroceryEntry cola = new GroceryEntry("Cola", 123);
        cola.setId(1L);
        GroceryEntry milk = new GroceryEntry("Milk", 2);
        milk.setId(2L);
        GroceryEntry mentos = new GroceryEntry("Mentos", 1);
        mentos.setId(3L);
        List<GroceryEntry> entries = new ArrayList<>();
        entries.add(cola);
        entries.add(milk);
        entries.add(mentos);

        groceryList = new GroceryList("party", LocalDate.of(2021, 7, 20), entries);
    }

    @Test
    void getId() {
        assertNull(groceryList.getId());
    }

    @Test
    void setId() {
        groceryList.setId(3L);
        assertEquals(groceryList.getId(), 3L);
    }

    @Test
    void addProduct() {
        GroceryEntry breadSticks = new GroceryEntry("Bread Stricks", 3);
        groceryList.addEntry(breadSticks);
        assertEquals(groceryList.getProducts().size(), 4);
    }

    @Test
    void removeProduct() {
        groceryList.removeEntry(2L);
        assertEquals(groceryList.getProducts().size(), 2);
    }

}