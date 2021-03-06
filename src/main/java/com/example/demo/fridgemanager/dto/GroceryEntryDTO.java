package com.example.demo.fridgemanager.dto;

import com.example.demo.fridgemanager.entities.GroceryEntry;

public class GroceryEntryDTO {
    private Long id;
    private String product;
    private Integer quantity;

    public GroceryEntryDTO(Long id, String product, Integer quantity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public GroceryEntry toEntity() {
        return new GroceryEntry(product, quantity);
    }
}
