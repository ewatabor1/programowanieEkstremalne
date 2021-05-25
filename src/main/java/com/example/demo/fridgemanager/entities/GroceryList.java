package com.example.demo.fridgemanager.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class GroceryList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private LocalDate createdAt;

    @OneToMany(orphanRemoval=true, cascade={CascadeType.ALL})
    private List<GroceryEntry> products;

    public GroceryList() {
    }

    public GroceryList(String name, LocalDate createdAt, List<GroceryEntry> products) {
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
