package com.example.demo.fridgemanager.dto;

import java.time.LocalDate;

public class ProductDTO {
    private Long id;
    private String name;
    private Integer kcal;
    private LocalDate expiryDate;
    private Double proteins;
    private Double carbohydrates;
    private Double fats;

    public ProductDTO() {
    }

    public ProductDTO(Long id, String name, Integer kcal, LocalDate expiryDate, Double proteins, Double carbohydrates, Double fats) {
        this.id = id;
        this.name = name;
        this.kcal = kcal;
        this.expiryDate = expiryDate;
        this.setProteins(proteins);
        this.setCarbohydrates(carbohydrates);
        this.setFats(fats);
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

    public Double getProteins() {
        return proteins;
    }

    public void setProteins(Double proteins) {
        this.proteins = proteins;
    }

    public Double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(Double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public Double getFats() {
        return fats;
    }

    public void setFats(Double fats) {
        this.fats = fats;
    }
}
