package com.example.demo.fridgemanager.entities;

import com.example.demo.fridgemanager.dto.ProductDTO;

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
    private Integer quantity;
    private Integer minQuantity;
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

    public void setId(Long id) { this.id = id; }

    public Product() {
    }

    public Product(String name, Integer kcal, LocalDate expiryDate, Integer quantity, Integer minQuantity, Double proteins, Double carbohydrates, Double fats) {
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
        this.quantity = quantity;
        if(this.quantity == null) this.quantity = 1;
        this.minQuantity = minQuantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(Integer minQuantity) {
        this.minQuantity = minQuantity;
    }

    public String getName() {
        return name;
    }

    public ProductDTO toDTO() {
        ProductDTO dto =  new ProductDTO(
            this.id,
            this.name,
            this.kcal,
            this.expiryDate,
            this.quantity,
            this.minQuantity,
            this.proteins,
            this.carbohydrates,
            this.fats
        );
        if(this.id != null) dto.setId(this.id);
        return dto;
    }

    public Double getCarbohydrates() {
        return carbohydrates;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getFats() {
        return fats;
    }

    public Double getProteins() {
        return proteins;
    }

    public void setProteins(Double proteins) {
        this.proteins = proteins;
    }

    public void setCarbohydrates(Double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public void setFats(Double fats) {
        this.fats = fats;
    }
}
