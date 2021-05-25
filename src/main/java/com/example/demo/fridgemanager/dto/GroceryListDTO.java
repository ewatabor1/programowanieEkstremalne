package com.example.demo.fridgemanager.dto;

import com.example.demo.fridgemanager.entities.GroceryEntry;

import java.time.LocalDate;
import java.util.List;

public class GroceryListDTO {
    private Long id;
    private String name;
    private LocalDate createdAt;
    private List<GroceryEntry> products;

    public GroceryListDTO(Long id, String name, LocalDate createdAt, List<GroceryEntry> products) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public List<GroceryEntry> getProducts() {
        return products;
    }

    public void setProducts(List<GroceryEntry> products) {
        this.products = products;
    }
}
