package com.example.demo.fridgemanager.dto;

import java.time.LocalDate;

public class ProductDTO {
    private Long id;
    private String name;
    private Integer kcal;
    private LocalDate expiryDate;

    public ProductDTO() {
    }

    public ProductDTO(Long id, String name, Integer kcal, LocalDate expiryDate) {
        this.id = id;
        this.name = name;
        this.kcal = kcal;
        this.expiryDate = expiryDate;
    }

    public Integer getKcal() {
        return kcal;
    }

    public void setKcal(Integer kcal) {
        this.kcal = kcal;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
