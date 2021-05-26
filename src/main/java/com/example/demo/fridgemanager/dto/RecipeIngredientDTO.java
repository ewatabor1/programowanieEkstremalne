package com.example.demo.fridgemanager.dto;

import com.example.demo.fridgemanager.validators.Positive;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class RecipeIngredientDTO {
    private Long productId;
    private String productName;
    private ProductDTO product;
    @NotNull
    @Positive
    private BigDecimal amount;

    public RecipeIngredientDTO() {
    }

    public RecipeIngredientDTO(ProductDTO product, String productName,Long productId, BigDecimal amount) {
        this.productId = productId;
        this.amount = amount;
        this.setProduct(product);
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
