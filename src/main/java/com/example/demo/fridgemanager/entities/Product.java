package com.example.demo.fridgemanager.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer kcal;
    private LocalDate expiryDate;

    public Integer getKcal() {
        return kcal;
    }

    public void setKcal(Integer calories) {
        this.kcal = calories;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Long getId() {
        return id;
    }

    public Product() {
    }

    public Product(String name, Integer kcal, LocalDate expiryDate) {
        this.name = name;
        this.kcal = kcal;
        this.expiryDate = expiryDate;
    }

    public String getName() {
        return name;
    }

    public void update(String name) {
        this.name = name;
    }
}
