package com.example.demo.fridgemanager.dto;

import com.example.demo.fridgemanager.entities.Product;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;

public class ProductDTO {
    private Long id;
    private String name;
    private Integer kcal;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate expiryDate;
    private Integer quantity;
    private Integer minQuantity;
    private Double proteins;
    private Double carbohydrates;
    private Double fats;

    public ProductDTO() {
    }

    public ProductDTO(Long id, String name, Integer kcal, LocalDate expiryDate, Integer quantity, Integer minQuantity, Double proteins, Double carbohydrates, Double fats) {
        this.id = id;
        this.name = name;
        this.kcal = kcal;
        this.expiryDate = expiryDate;
        this.quantity = quantity;
        if(this.quantity == null) this.quantity = 1;
        this.minQuantity = minQuantity;
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

    public Product toEntity() {
        Product p = new Product(this.name, this.kcal, this.expiryDate, this.quantity, this.minQuantity, this.proteins, this.carbohydrates, this.fats);
        p.setId(id);
        return p;
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
}
