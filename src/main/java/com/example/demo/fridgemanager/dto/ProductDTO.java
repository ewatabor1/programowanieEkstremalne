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

    public ProductDTO() {
    }

    public ProductDTO(String name, Integer kcal, LocalDate expiryDate, Integer quantity, Integer minQuantity) {
        this.name = name;
        this.kcal = kcal;
        this.expiryDate = expiryDate;
        this.quantity = quantity;
        if(this.quantity == null) this.quantity = 1;
        this.minQuantity = minQuantity;
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

    public Product toEntity() {
        Product p = new Product(this.name, this.kcal, this.expiryDate, this.quantity, this.minQuantity);
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
