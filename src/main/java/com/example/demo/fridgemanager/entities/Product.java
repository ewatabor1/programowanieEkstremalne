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

    public Product(String name, Integer kcal, LocalDate expiryDate, Integer quantity, Integer minQuantity) {
        this.name = name;
        this.kcal = kcal;
        this.expiryDate = expiryDate;
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
        ProductDTO dto =  new ProductDTO(this.name, this.kcal, this.expiryDate, this.quantity, this.minQuantity);
        if(this.id != null) dto.setId(this.id);
        return dto;
    }

}
