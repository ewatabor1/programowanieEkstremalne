package com.example.demo.fridgemanager.entities;

import com.example.demo.fridgemanager.dto.ProductDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    private Product product;
    private HashMap<String, String> stringPropertyMap;
    private HashMap<String, Integer> integerPropertyMap;
    private HashMap<String, Double> doublePropertyMap;

    @BeforeEach
    void setUp() {

        stringPropertyMap = new HashMap<>();
        stringPropertyMap.put("name", "product_name");
        stringPropertyMap.put("expiryDate", "2019-01-21");
        integerPropertyMap = new HashMap<>();
        integerPropertyMap.put("kcal", 50);
        integerPropertyMap.put("id", 1);
        integerPropertyMap.put("quantity", 2);
        integerPropertyMap.put("minQuantity", 2);

        doublePropertyMap = new HashMap<>();
        doublePropertyMap.put("fats", 3.1);
        doublePropertyMap.put("carbohydrates", 4.3);
        doublePropertyMap.put("proteins", 5.2);
        product = new Product(stringPropertyMap.get("name"), integerPropertyMap.get("kcal"),
                LocalDate.parse(stringPropertyMap.get("expiryDate")), integerPropertyMap.get("quantity"),
                integerPropertyMap.get("minQuantity"), doublePropertyMap.get("proteins"),
                doublePropertyMap.get("carbohydrates"), doublePropertyMap.get("fats"));
    }

    @Test
    void getKcal() {
        assertEquals(product.getKcal(), integerPropertyMap.get("kcal"));
    }

    @Test
    void setKcal() {
        product.setKcal(3);
        assertEquals(product.getKcal(), 3);
    }

    @Test
    void getExpiryDate() {
        assertEquals(product.getExpiryDate(), LocalDate.parse(stringPropertyMap.get("expiryDate")));
    }

    @Test
    void setExpiryDate() {
        LocalDate date = LocalDate.now();
        product.setExpiryDate(date);
        assertEquals(product.getExpiryDate(), date);
    }

    @Test
    void getId() {
        assertNull(product.getId());
    }

    @Test
    void setId() {
        product.setId(2L);
        assertEquals(product.getId(), 2);
    }

    @Test
    void getQuantity() {
        assertEquals(product.getQuantity(), integerPropertyMap.get("quantity"));
    }


    @Test
    void setQuantity() {
        product.setQuantity(2);
        assertEquals(product.getQuantity(), 2);
    }

    @Test
    void getMinQuantity() {
        assertEquals(product.getMinQuantity(), integerPropertyMap.get("minQuantity"));
    }

    @Test
    void setMinQuantity() {
        product.setMinQuantity(5);
        assertEquals(product.getMinQuantity(), 5);
    }

    @Test
    void getName() {
        assertEquals(product.getName(), stringPropertyMap.get("name"));
    }

    @Test
    void toDTO_id_null() {
        ProductDTO dtoObj = product.toDTO();
        assertNull(dtoObj.getId());
        assertEquals(dtoObj.getName(), stringPropertyMap.get("name"));
        assertEquals(dtoObj.getQuantity(), integerPropertyMap.get("quantity"));
        assertEquals(dtoObj.getExpiryDate(), LocalDate.parse(stringPropertyMap.get("expiryDate")));
        assertEquals(dtoObj.getKcal(), integerPropertyMap.get("kcal"));
        assertEquals(dtoObj.getMinQuantity(), integerPropertyMap.get("minQuantity"));
        assertEquals(dtoObj.getFats(), doublePropertyMap.get("fats"));
        assertEquals(dtoObj.getProteins(), doublePropertyMap.get("proteins"));
        assertEquals(dtoObj.getProteins(), doublePropertyMap.get("proteins"));
    }

    @Test
    void toDTO_id_not_null() {
        product.setId(2L);
        ProductDTO dtoObj = product.toDTO();
        assertEquals(dtoObj.getId(), 2);
        assertEquals(dtoObj.getName(), stringPropertyMap.get("name"));
        assertEquals(dtoObj.getQuantity(), integerPropertyMap.get("quantity"));
        assertEquals(dtoObj.getExpiryDate(), LocalDate.parse(stringPropertyMap.get("expiryDate")));
        assertEquals(dtoObj.getKcal(), integerPropertyMap.get("kcal"));
        assertEquals(dtoObj.getMinQuantity(), integerPropertyMap.get("minQuantity"));
        assertEquals(dtoObj.getFats(), doublePropertyMap.get("fats"));
        assertEquals(dtoObj.getProteins(), doublePropertyMap.get("proteins"));
        assertEquals(dtoObj.getProteins(), doublePropertyMap.get("proteins"));
    }

    @Test
    void getCarbohydrates() {
        assertEquals(product.getCarbohydrates(), doublePropertyMap.get("carbohydrates"));
    }

    @Test
    void getFats() {
        assertEquals(product.getFats(), doublePropertyMap.get("fats"));
    }

    @Test
    void getProteins() {
        assertEquals(product.getProteins(), doublePropertyMap.get("proteins"));
    }

    @Test
    void setProteins() {
        product.setProteins(132.3);
        assertEquals(product.getProteins(), 132.3);
    }

    @Test
    void setCarbohydrates() {
        product.setCarbohydrates(1231.3);
        assertEquals(product.getCarbohydrates(), 1231.3);
    }

    @Test
    void setFats() {
        product.setCarbohydrates(423.0);
        assertEquals(product.getCarbohydrates(), 423.0);
    }

    @Test
    void create_product_without_calories() {
        Product product = new Product(stringPropertyMap.get("name"), null,
                LocalDate.parse(stringPropertyMap.get("expiryDate")), integerPropertyMap.get("quantity"),
                integerPropertyMap.get("minQuantity"), doublePropertyMap.get("proteins"),
                doublePropertyMap.get("carbohydrates"), doublePropertyMap.get("fats"));
        assertEquals(product.getKcal(), (int) (doublePropertyMap.get("proteins") * 4
                + doublePropertyMap.get("carbohydrates") * 4 + doublePropertyMap.get("fats") * 9));
    }
}