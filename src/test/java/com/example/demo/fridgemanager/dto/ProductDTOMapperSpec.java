package com.example.demo.fridgemanager.dto;

import com.example.demo.fridgemanager.entities.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductDTOMapperSpec {

    private ProductDTOMapper mapper;
    private LocalDate date;

    private Product cola;
    private Product chicken;
    private List<Product> productList;

    @BeforeEach
    void setUp() {
        date = LocalDate.of(2021, 12, 18);
        cola = new Product("Kolka", 130, date, 3, 2, 2.83, 80.99, 100.1);
        chicken = new Product("Chicken", 55, date, 1, 1, 20.83, 30.99, 60.1);
        productList = new ArrayList<>();
        productList.add(cola);
        productList.add(chicken);
        mapper = new ProductDTOMapper();
    }

    @Test
    void toDtoSingle() {
        ProductDTO colaDTO = mapper.mapToDTO(cola);
        assertEquals(colaDTO.getName(), cola.getName());
        assertEquals(colaDTO.getKcal(), cola.getKcal());
        assertEquals(colaDTO.getExpiryDate(), cola.getExpiryDate());
        assertEquals(colaDTO.getQuantity(), cola.getQuantity());
        assertEquals(colaDTO.getMinQuantity(), cola.getMinQuantity());
        assertEquals(colaDTO.getProteins(), cola.getProteins());
        assertEquals(colaDTO.getCarbohydrates(), cola.getCarbohydrates());
        assertEquals(colaDTO.getFats(), cola.getFats());
    }

    @Test
    void toDtoList() {
        List<ProductDTO> productDTOS = mapper.mapToDTO(productList);

        assertEquals(productDTOS.size(), productList.size());
        for(int i=0; i < productList.size(); ++i) {
            assertEquals(productDTOS.get(i).getName(), productList.get(i).getName());
            assertEquals(productDTOS.get(i).getKcal(), productList.get(i).getKcal());
            assertEquals(productDTOS.get(i).getExpiryDate(), productList.get(i).getExpiryDate());
            assertEquals(productDTOS.get(i).getQuantity(), productList.get(i).getQuantity());
            assertEquals(productDTOS.get(i).getMinQuantity(), productList.get(i).getMinQuantity());
            assertEquals(productDTOS.get(i).getProteins(), productList.get(i).getProteins());
            assertEquals(productDTOS.get(i).getCarbohydrates(), productList.get(i).getCarbohydrates());
            assertEquals(productDTOS.get(i).getFats(), productList.get(i).getFats());
        }
    }

    @Test
    void toDtoListFilterNull() {
        productList.add(null);
        assertEquals(mapper.mapToDTO(productList).size(), 2);
    }
}
