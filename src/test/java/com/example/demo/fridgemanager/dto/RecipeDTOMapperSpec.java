package com.example.demo.fridgemanager.dto;

import com.example.demo.fridgemanager.entities.Product;
import com.example.demo.fridgemanager.entities.Recipe;
import com.example.demo.fridgemanager.entities.RecipeIngredient;
import com.example.demo.fridgemanager.entities.RecipeStep;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class RecipeDTOMapperSpec {

//    @Mock
//    ProductDTOMapper productDTOMapper;

    @InjectMocks
    RecipeDTOMapper mapper = new RecipeDTOMapper();

    private LocalDate date;

    private Recipe applePie;
    private Product apple;
    private Product pie;

    private Recipe tomatoSoup;
    private Product soup;
    private Product rice;

    private List<String> instructions;

    private List<Recipe> recipesList;


    @BeforeEach
    void setUp() {
        date = LocalDate.of(2021, 12, 18);
        apple = new Product("Apple", 130, date, 3, 2, 2.83, 80.99, 100.1);
        apple.setId(1L);
        pie = new Product("Pie", 55, date, 1, 1, 20.83, 30.99, 60.1);
        pie.setId(2L);

        soup = new Product("Soup", 130, date, 3, 2, 2.83, 80.99, 100.1);
        soup.setId(3L);
        rice = new Product("Rice", 55, date, 1, 1, 20.83, 30.99, 60.1);
        rice.setId(4L);

        instructions = new ArrayList<>(Arrays.asList("step one", "step two"));

        Recipe.Builder applePieBuilder = new Recipe.Builder();
        applePieBuilder.setInstructions(instructions);
        applePieBuilder.addIngredient(apple, new BigDecimal(2));
        applePieBuilder.addIngredient(pie, BigDecimal.ONE);
        applePieBuilder.setName("Apple pie");
        applePieBuilder.setDescription("A recipe for apple pie");
        applePie = applePieBuilder.create();

        Recipe.Builder tomatoSoupBuilder = new Recipe.Builder();
        tomatoSoupBuilder.setInstructions(instructions);
        tomatoSoupBuilder.addIngredient(soup, BigDecimal.ONE);
        tomatoSoupBuilder.addIngredient(rice, BigDecimal.TEN);
        tomatoSoupBuilder.setName("Tomato soup");
        tomatoSoupBuilder.setDescription("A recipe for tomato soup");
        tomatoSoup = tomatoSoupBuilder.create();

        recipesList = new ArrayList<>(Arrays.asList(applePie, tomatoSoup));
    }

    @Test
    void toDtoSingle() {
        RecipeDTO applePieDTO = mapper.mapToDTO(applePie);
        assertEquals(applePieDTO.getName(), applePie.getName());
        assertEquals(applePieDTO.getDescription(), applePie.getDescription());

        List<RecipeIngredient> ingredients = new ArrayList<>(applePie.getIngredients());
        for(int i=0; i < applePieDTO.getIngredients().size(); ++i) {
            assertEquals(applePieDTO.getIngredients().get(i).getAmount(), ingredients.get(i).getAmount());
            assertEquals(applePieDTO.getIngredients().get(i).getProductName(), ingredients.get(i).getProduct().getName());
        }

        List<RecipeStep> steps = new ArrayList<>(applePie.getSteps());
        for(int i=0; i < applePieDTO.getSteps().size(); ++i) {
            assertEquals(applePieDTO.getSteps().get(i), steps.get(i).getInstruction());
        }
    }

    @Test
    void toDtoList() {
        List<RecipeDTO> recipesDTOs = mapper.mapToDTO(recipesList);
        assertEquals(recipesDTOs.size(), recipesList.size());

        for(int i=0; i < recipesDTOs.size(); ++i) {
            RecipeDTO recipeDTO = recipesDTOs.get(i);
            Recipe recipe = recipesList.get(i);
            List<RecipeIngredient> ingredients = new ArrayList<>(recipe.getIngredients());
            for(int j=0; j< recipeDTO.getIngredients().size(); ++j) {
                assertEquals(recipeDTO.getIngredients().get(j).getAmount(), ingredients.get(j).getAmount());
                assertEquals(recipeDTO.getIngredients().get(j).getProductName(), ingredients.get(j).getProduct().getName());
            }

            List<RecipeStep> steps = new ArrayList<>(recipe.getSteps());
            for(int j=0; j < recipeDTO.getSteps().size(); ++j) {
                assertEquals(recipeDTO.getSteps().get(j), steps.get(j).getInstruction());
            }
        }
    }


    @Test
    void toDtoListFilterNull() {
        recipesList.add(null);
        assertEquals(mapper.mapToDTO(recipesList).size(), 2);
    }
}
