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
    private Double proteins;
    private Double carbohydrates;
    private Double fats;

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

    public Product(String name, Integer kcal, LocalDate expiryDate, Double proteins, Double carbohydrates, Double fats) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.proteins = proteins;
        this.carbohydrates = carbohydrates;
        this.fats = fats;
        if (kcal == null && proteins != null && carbohydrates != null && fats != null) {
            this.kcal = (int) (proteins * 4 + carbohydrates * 4 + fats * 9);
        } else {
            this.kcal = kcal;
        }
    }

    public String getName() {
        return name;
    }

    public void update(String name) {
        this.name = name;
    }

    public Double getCarbohydrates() {
        return carbohydrates;
    }

    public Double getFats() {
        return fats;
    }

    public Double getProteins() {
        return proteins;
    }
}
