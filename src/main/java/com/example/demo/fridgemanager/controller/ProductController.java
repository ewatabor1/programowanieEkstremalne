package com.example.demo.fridgemanager.controller;

import com.example.demo.fridgemanager.dto.ProductDTO;
import com.example.demo.fridgemanager.dto.ProductDTOMapper;
import com.example.demo.fridgemanager.entities.Product;
import com.example.demo.fridgemanager.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(originPatterns = "*")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductDTOMapper productDTOMapper;

    @GetMapping(value = "/products",produces = APPLICATION_JSON_VALUE)
    List<ProductDTO> all() {
        // return all products
        List<Product> entities = productService.findAll();
        return productDTOMapper.mapToDTO(entities);
    }

    @GetMapping(value = "/products/{name}",produces = APPLICATION_JSON_VALUE)
    List<ProductDTO> allByName(@PathVariable String name) {
        // return all products by name
        List<Product> entities = productService.getProductsByName(name);
        return productDTOMapper.mapToDTO(entities);
    }

    @PostMapping(value = "/products", consumes = APPLICATION_JSON_VALUE,produces = APPLICATION_JSON_VALUE)
    ProductDTO newProduct(@RequestBody ProductDTO newProduct) {
        // save product to db
        Product saved = productService.save(newProduct);
        return productDTOMapper.mapToDTO(saved);
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

    @PutMapping(value = "/products/supply/{id}/{quantity}", produces = APPLICATION_JSON_VALUE)
    ProductDTO addProduct(@PathVariable Long id, @PathVariable Integer quantity) {
        Product current = productService.getById(id);
        ProductDTO updated = current.toDTO();
        updated.setQuantity(current.getQuantity() + quantity);
        return productService.update(id, updated).toDTO();
    }

    @PutMapping(value = "/products/consume/{id}/{quantity}", produces = APPLICATION_JSON_VALUE)
    Object consumeProduct(@PathVariable Long id, @PathVariable Integer quantity) {
        Product current = productService.getById(id);
        ProductDTO updated = current.toDTO();
        if(current.getQuantity() < quantity) {
            String message = "There is no enough product: " + current.getName() + ". Available: " + current.getQuantity();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        updated.setQuantity(current.getQuantity() - quantity);
        return productService.update(id, updated).toDTO();
    }
}
