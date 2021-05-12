package com.example.demo.fridgemanager.controller;

import com.example.demo.fridgemanager.dto.ProductDTO;
import com.example.demo.fridgemanager.entities.Product;
import com.example.demo.fridgemanager.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.*;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping(value = "/products",produces = APPLICATION_JSON_VALUE)
    List<ProductDTO> all() {
        // return all products
        return productService.findAll().stream().map(product ->
            new ProductDTO(product.getId(), product.getName(), product.getKcal(), product.getExpiryDate())
        ).collect(Collectors.toList());
    }

    @GetMapping(value = "/products/{name}",produces = APPLICATION_JSON_VALUE)
    List<ProductDTO> allByName(@PathVariable String name) {
        // return all products by name
        return productService.getProductsByName(name).stream().map(product ->
                new ProductDTO(product.getId(), product.getName(), product.getKcal(), product.getExpiryDate())
        ).collect(Collectors.toList());
    }

    @PostMapping(value = "/products", consumes = APPLICATION_JSON_VALUE,produces = APPLICATION_JSON_VALUE)
    ProductDTO newProduct(@RequestBody ProductDTO newProduct) {
        // save product to db
        Product saved = productService.save(newProduct);
        return new ProductDTO(saved.getId(), saved.getName(), saved.getKcal(), saved.getExpiryDate());
    }

    @DeleteMapping("/products/{id}")
    void deleteProduct(@PathVariable Long id) {
        //delete product by id
        productService.delete(id);
    }

    @DeleteMapping("/products/delete_by_name/{name}")
    void deleteProduct(@PathVariable String name) {
        //delete product by name
        productService.deleteByName(name);
    }
}
