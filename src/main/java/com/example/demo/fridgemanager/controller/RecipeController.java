package com.example.demo.fridgemanager.controller;

import com.example.demo.fridgemanager.dto.RecipeDTO;
import com.example.demo.fridgemanager.dto.RecipeDTOMapper;
import com.example.demo.fridgemanager.entities.Recipe;
import com.example.demo.fridgemanager.exceptions.EntityNotFoundException;
import com.example.demo.fridgemanager.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin(originPatterns = "*")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private RecipeDTOMapper recipeDTOMapper;
    @Autowired
    private SmartValidator smartValidator;

    @GetMapping(value = "/recipes", produces = APPLICATION_JSON_VALUE)
    List<RecipeDTO> all() {
        // return all recipes
        List<Recipe> all = recipeService.findAll();
        return recipeDTOMapper.mapToDTO(all);
    }
    @GetMapping(value = "/recipes/available", produces = APPLICATION_JSON_VALUE)
    List<RecipeDTO> availableRecipes() {
        // return all recipes
        List<Recipe> all = recipeService.findAvailable();
        return recipeDTOMapper.mapToDTO(all);
    }

    @GetMapping(value = "/recipes/{id}", produces = APPLICATION_JSON_VALUE)
    RecipeDTO getById(@PathVariable Long id) {
        // return all recipes by name
        Recipe recipe = recipeService.getRecipeById(id);
        return recipeDTOMapper.mapToDTO(recipe);
    }

    @PostMapping(value = "/recipes", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ApiResponse<RecipeDTO> newRecipe(@RequestBody RecipeDTO newRecipe, BindingResult bindingResult) {
        smartValidator.validate(newRecipe, bindingResult);
        if (bindingResult.hasErrors()) {
            return parseError(bindingResult);
        }
        // save recipe to db
        Recipe saved = recipeService.save(newRecipe);
        return new ApiResponse<>(recipeDTOMapper.mapToDTO(saved));
    }

    @PutMapping(value = "/recipes/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ApiResponse<RecipeDTO> newRecipe(@RequestBody RecipeDTO newRecipe, @PathVariable Long id, BindingResult bindingResult) {
        smartValidator.validate(newRecipe, bindingResult);
        if (bindingResult.hasErrors()) {
            return parseError(bindingResult);
        }
        // save recipe to db
        Recipe saved = recipeService.update(id, newRecipe);
        return new ApiResponse<>(recipeDTOMapper.mapToDTO(saved));
    }

    @DeleteMapping("/recipes/{id}")
    void deleteRecipe(@PathVariable Long id) {
        //delete recipe by id
        recipeService.delete(id);
    }

    protected <T> ApiResponse<T> parseError(BindingResult bindingResult) {
        List<Error> errors = new ArrayList<>();
        if (bindingResult != null) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                String field = tryGetField(error);
                errors.add(new Error(field, error.getDefaultMessage()));
            }
        }
        return new ApiResponse<>(errors);
    }

    private String tryGetField(ObjectError error) {
        if (error instanceof FieldError)
            return ((FieldError) error).getField();
        else
            return null;
    }


    @ExceptionHandler(value = {EntityNotFoundException.class})
    protected ResponseEntity<Object> handleAllErrors(EntityNotFoundException ex, WebRequest request) {
        return ResponseEntity.notFound().build();
    }


}

