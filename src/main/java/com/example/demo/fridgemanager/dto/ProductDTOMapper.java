package com.example.demo.fridgemanager.dto;

import com.example.demo.fridgemanager.entities.Product;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductDTOMapper {

    public ProductDTO mapToDTO(Product product) {
        if (product == null)
            return null;
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getKcal(),
                product.getExpiryDate(),
                product.getProteins(),
                product.getCarbohydrates(),
                product.getFats()
        );
    }

    public List<ProductDTO> mapToDTO(Collection<Product> products) {
        if (products == null)
            return Collections.emptyList();
        return products.stream().map(product -> mapToDTO(product)).filter(Objects::nonNull).collect(Collectors.toList());
    }
}

