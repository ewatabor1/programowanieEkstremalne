package com.example.demo.fridgemanager.controller;

import com.example.demo.fridgemanager.dto.GroceryListDTO;
import com.example.demo.fridgemanager.entities.GroceryList;
import com.example.demo.fridgemanager.services.GroceryListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api")
public class GroceryListController {
    @Autowired
    private GroceryListService groceryListService;

    @GetMapping(value = "/grocery-lists",produces = APPLICATION_JSON_VALUE)
    List<GroceryListDTO> all() {
        // return all grocery-lists
        return groceryListService.findAll().stream().map(groceryList ->
                new GroceryListDTO(groceryList.getId(), groceryList.getName(), groceryList.getCreatedAt(), groceryList.getProducts())
        ).collect(Collectors.toList());
    }

    @GetMapping(value = "/grocery-lists/{name}",produces = APPLICATION_JSON_VALUE)
    List<GroceryListDTO> allByName(@PathVariable String name) {
        // return all grocery-lists by name
        return groceryListService.getGroceryListsByName(name).stream().map(groceryList ->
                new GroceryListDTO(groceryList.getId(), groceryList.getName(), groceryList.getCreatedAt(), groceryList.getProducts())
        ).collect(Collectors.toList());
    }

    @PostMapping(value = "/grocery-lists", consumes = APPLICATION_JSON_VALUE,produces = APPLICATION_JSON_VALUE)
    GroceryListDTO newGroceryList(@RequestBody GroceryListDTO newGroceryList) {
        // save groceryList to db
        GroceryList saved = groceryListService.save(newGroceryList);
        return new GroceryListDTO(saved.getId(), saved.getName(), saved.getCreatedAt(), saved.getProducts());
    }

    @DeleteMapping("/grocery-lists/{id}")
    void deleteGroceryList(@PathVariable Long id) {
        //delete groceryList by id
        groceryListService.delete(id);
    }

    @DeleteMapping("/grocery-lists/delete_by_name/{name}")
    void deleteGroceryList(@PathVariable String name) {
        //delete groceryList by name
        groceryListService.deleteByName(name);
    }
}