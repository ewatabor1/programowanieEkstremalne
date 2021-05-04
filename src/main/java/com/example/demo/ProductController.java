package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.*;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping(value = "/products",produces = APPLICATION_JSON_VALUE)
    List<ProductDTO> all() {
        // return all products
        return productService.findAll().stream().map(product -> new ProductDTO(product.getId(), product.getName())).collect(Collectors.toList());
    }

    @PostMapping(value = "/products", consumes = APPLICATION_JSON_VALUE,produces = APPLICATION_JSON_VALUE)
    ProductDTO newProduct(@RequestBody ProductDTO newProduct) {
        // save product to db
        Product saved = productService.save(newProduct);
        return new ProductDTO(saved.getId(), saved.getName());
    }

    @DeleteMapping("/products/{id}")
    void deleteProduct(@PathVariable Long id) {
        //delete product by id
        productService.delete(id);
    }
}
