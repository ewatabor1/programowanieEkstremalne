package com.example.demo.fridgemanager.services;

import com.example.demo.fridgemanager.dao.ProductDAO;
import com.example.demo.fridgemanager.dao.RecipeDAO;
import com.example.demo.fridgemanager.dto.*;
import com.example.demo.fridgemanager.entities.Product;
import com.example.demo.fridgemanager.entities.Recipe;
import com.example.demo.fridgemanager.entities.RecipeIngredient;
import com.example.demo.fridgemanager.entities.RecipeStep;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class RecipeServiceTest {

    @Mock
    RecipeDAO dao = Mockito.mock(RecipeDAO.class);
    ProductDAO productDAO = Mockito.mock(ProductDAO.class);
    @InjectMocks
    RecipeService recipeService = new RecipeService();

    ProductDTO productDTO1;
    ProductDTO productDTO2;
    Product product1;
    Product product2;
    RecipeIngredientDTO recipeIngredientDTO1;
    RecipeIngredientDTO recipeIngredientDTO2;
    ProductDTOMapper productDTOMapper;

    @Before
    public void prepareData(){
        productDTOMapper = new ProductDTOMapper();
        product1 = new Product("product 1", null, null, null, null, null, null, null);
        product2 = new Product("product 2", null, null, null, null, null, null, null);
        product1.setId(1L);
        product2.setId(2L);
        productDTO1 = productDTOMapper.mapToDTO(product1);
        productDTO2 = productDTOMapper.mapToDTO(product2);
        recipeIngredientDTO1 = new RecipeIngredientDTO(productDTO1, productDTO1.getName(), productDTO1.getId(), BigDecimal.valueOf(3));
        recipeIngredientDTO2 = new RecipeIngredientDTO(productDTO2, productDTO2.getName(), productDTO2.getId(), BigDecimal.valueOf(2));
    }

    @Test
    public void recipeSavedCorrectlyGivenAllCorrectData() {
        when(productDAO.findByNames(eq(new HashSet<>(Arrays.asList(productDTO1.getName(), productDTO2.getName()))))).thenReturn(Arrays.asList(product1,product2));

        String recipeName1 = "Recipe 1";
        String description1 = "Recipe For Test";
        List<String> steps1 = Arrays.asList("Step 1", "Step 2", "Step 3");
        List<RecipeIngredientDTO> recipeIngredientDTOS = Arrays.asList(recipeIngredientDTO1, recipeIngredientDTO2);

        RecipeDTO recipeDTO = new RecipeDTO(null, steps1,
                recipeIngredientDTOS, recipeName1, description1);

        Recipe recipeResult = recipeService.save(recipeDTO);

        verify(dao, Mockito.times(1)).save(any());


        List<RecipeIngredient> recipeIngredientSet = recipeIngredientDTOS.stream().map(r -> new RecipeIngredient(recipeResult, productDtoToProduct(r.getProduct()), r.getAmount())).collect(Collectors.toList());

        assertEquals(recipeName1, recipeResult.getName());
        assertEquals(description1, recipeResult.getDescription());
        assertTrue(recipeResult.getIngredients().containsAll(recipeIngredientSet));
        assertTrue(recipeResult.getSteps().stream().map(RecipeStep::getInstruction).collect(Collectors.toList()).containsAll(steps1));
        assertEquals(Collections.singletonList(recipeResult), recipeResult.getSteps().stream().map(RecipeStep::getRecipe).distinct().collect(Collectors.toList()));
    }

    @Test
    public void recipeSavedGivenNullOrEmptyStepsData(){
        when(productDAO.findByNames(eq(new HashSet<>(Arrays.asList(productDTO1.getName(), productDTO2.getName()))))).thenReturn(Arrays.asList(product1,product2));

        String recipeName1 = "Recipe 1";
        String description1 = "Recipe For Test";
        List<RecipeIngredientDTO> recipeIngredientDTOS = Arrays.asList(recipeIngredientDTO1, recipeIngredientDTO2);

        RecipeDTO recipeDTONull = new RecipeDTO(null, null, recipeIngredientDTOS, recipeName1, description1);
        RecipeDTO recipeDTOEmpty = new RecipeDTO(null, Collections.emptyList(), recipeIngredientDTOS, recipeName1, description1);

        Recipe recipeResultNull = recipeService.save(recipeDTONull);
        Recipe recipeResultEmpty = recipeService.save(recipeDTOEmpty);

        verify(dao, Mockito.times(2)).save(any());


        List<RecipeIngredient> recipeIngredientSetNull = recipeIngredientDTOS.stream().map(r -> new RecipeIngredient(recipeResultNull, productDtoToProduct(r.getProduct()), r.getAmount())).collect(Collectors.toList());
        List<RecipeIngredient> recipeIngredientSetEmpty = recipeIngredientDTOS.stream().map(r -> new RecipeIngredient(recipeResultEmpty, productDtoToProduct(r.getProduct()), r.getAmount())).collect(Collectors.toList());

        assertEquals(recipeName1, recipeResultNull.getName());
        assertEquals(description1, recipeResultNull.getDescription());
        assertTrue(recipeResultNull.getIngredients().containsAll(recipeIngredientSetNull));
        assertEquals(Collections.emptySet(), recipeResultNull.getSteps());

        assertEquals(recipeName1, recipeResultEmpty.getName());
        assertEquals(description1, recipeResultEmpty.getDescription());
        assertTrue(recipeResultEmpty.getIngredients().containsAll(recipeIngredientSetEmpty));
        assertEquals(Collections.emptySet(), recipeResultEmpty.getSteps());
    }

    @Test
    public void recipeSavedGivenNullOrEmptyRecipeIngredientsData(){
        String recipeName1 = "Recipe 1";
        String description1 = "Recipe For Test";
        List<String> steps1 = Arrays.asList("Step 1", "Step 2", "Step 3");

        RecipeDTO recipeDTONull = new RecipeDTO(null, steps1, null, recipeName1, description1);
        RecipeDTO recipeDTOEmpty = new RecipeDTO(null, steps1, Collections.emptyList(), recipeName1, description1);

        Recipe recipeResultNull = recipeService.save(recipeDTONull);
        Recipe recipeResultEmpty = recipeService.save(recipeDTOEmpty);

        verify(dao, Mockito.times(2)).save(any());

        assertEquals(recipeName1, recipeResultNull.getName());
        assertEquals(description1, recipeResultNull.getDescription());
        assertEquals(Collections.emptySet(), recipeResultNull.getIngredients());
        assertTrue(recipeResultNull.getSteps().stream().map(RecipeStep::getInstruction).collect(Collectors.toList()).containsAll(steps1));

        assertEquals(recipeName1, recipeResultEmpty.getName());
        assertEquals(description1, recipeResultEmpty.getDescription());
        assertEquals(Collections.emptySet(), recipeResultEmpty.getIngredients());
        assertTrue(recipeResultEmpty.getSteps().stream().map(RecipeStep::getInstruction).collect(Collectors.toList()).containsAll(steps1));
    }

    @Test
    public void recipeSavedGivenNullOrEmptyDescription() {
        when(productDAO.findByNames(eq(new HashSet<>(Arrays.asList(productDTO1.getName(), productDTO2.getName()))))).thenReturn(Arrays.asList(product1,product2));

        String recipeName1 = "Recipe 1";
        List<String> steps1 = Arrays.asList("Step 1", "Step 2", "Step 3");
        List<RecipeIngredientDTO> recipeIngredientDTOS = Arrays.asList(recipeIngredientDTO1, recipeIngredientDTO2);

        RecipeDTO recipeDTONull = new RecipeDTO(null, steps1, recipeIngredientDTOS, recipeName1, null);
        RecipeDTO recipeDTOEmpty = new RecipeDTO(null, steps1, recipeIngredientDTOS, recipeName1, "");

        Recipe recipeResultNull = recipeService.save(recipeDTONull);
        Recipe recipeResultEmpty = recipeService.save(recipeDTOEmpty);

        verify(dao, Mockito.times(2)).save(any());

        List<RecipeIngredient> recipeIngredientSetNull = recipeIngredientDTOS.stream().map(r -> new RecipeIngredient(recipeResultNull, productDtoToProduct(r.getProduct()), r.getAmount())).collect(Collectors.toList());
        List<RecipeIngredient> recipeIngredientSetEmpty = recipeIngredientDTOS.stream().map(r -> new RecipeIngredient(recipeResultEmpty, productDtoToProduct(r.getProduct()), r.getAmount())).collect(Collectors.toList());

        assertEquals(recipeName1, recipeResultNull.getName());
        assertNull(recipeResultNull.getDescription());
        assertTrue(recipeResultNull.getIngredients().containsAll(recipeIngredientSetNull));
        assertTrue(recipeResultNull.getSteps().stream().map(RecipeStep::getInstruction).collect(Collectors.toList()).containsAll(steps1));
        assertEquals(Collections.singletonList(recipeResultNull), recipeResultNull.getSteps().stream().map(RecipeStep::getRecipe).distinct().collect(Collectors.toList()));

        assertEquals(recipeName1, recipeResultEmpty.getName());
        assertEquals("", recipeResultEmpty.getDescription());
        assertTrue(recipeResultEmpty.getIngredients().containsAll(recipeIngredientSetEmpty));
        assertTrue(recipeResultEmpty.getSteps().stream().map(RecipeStep::getInstruction).collect(Collectors.toList()).containsAll(steps1));
        assertEquals(Collections.singletonList(recipeResultEmpty), recipeResultEmpty.getSteps().stream().map(RecipeStep::getRecipe).distinct().collect(Collectors.toList()));
    }

    @Test
    public void recipeNotSavedGivenNullOrEmptyName(){
        when(productDAO.findByNames(eq(new HashSet<>(Arrays.asList(productDTO1.getName(), productDTO2.getName()))))).thenReturn(Arrays.asList(product1,product2));

        String description1 = "Recipe For Test";
        List<String> steps1 = Arrays.asList("Step 1", "Step 2", "Step 3");
        List<RecipeIngredientDTO> recipeIngredientDTOS = Arrays.asList(recipeIngredientDTO1, recipeIngredientDTO2);

        RecipeDTO recipeDTONullName = new RecipeDTO(null, steps1, recipeIngredientDTOS, null, description1);
        RecipeDTO recipeDTOEmptyName = new RecipeDTO(null, steps1, recipeIngredientDTOS, "", description1);

        Throwable exceptionNull = Assertions.assertThrows(RuntimeException.class, () -> {
            recipeService.save(recipeDTONullName);
        });
        Throwable exceptionEmpty = Assertions.assertThrows(RuntimeException.class, () -> {
            recipeService.save(recipeDTOEmptyName);
        });

        assertEquals(exceptionNull.getMessage(), "name must not be empty");
        assertEquals(exceptionEmpty.getMessage(), "name must not be empty");
    }

    @Test
    public void recipeNotSavedGivenNullOrEmptyDescription(){
        when(productDAO.findByNames(eq(new HashSet<>(Arrays.asList(productDTO1.getName(), productDTO2.getName()))))).thenReturn(Arrays.asList(product1,product2));

        String description1 = "Recipe For Test";
        List<String> steps1 = Arrays.asList("Step 1", "Step 2", "Step 3");
        List<RecipeIngredientDTO> recipeIngredientDTOS = Arrays.asList(recipeIngredientDTO1, recipeIngredientDTO2);

        RecipeDTO recipeDTONullName = new RecipeDTO(null, steps1, recipeIngredientDTOS, null, description1);
        RecipeDTO recipeDTOEmptyName = new RecipeDTO(null, steps1, recipeIngredientDTOS, "", description1);

        Throwable exceptionNull = Assertions.assertThrows(RuntimeException.class, () -> {
            recipeService.save(recipeDTONullName);
        });
        Throwable exceptionEmpty = Assertions.assertThrows(RuntimeException.class, () -> {
            recipeService.save(recipeDTOEmptyName);
        });

        assertEquals(exceptionNull.getMessage(), "name must not be empty");
        assertEquals(exceptionEmpty.getMessage(), "name must not be empty");
    }

    @Test
    public void shouldDelete(){
        recipeService.delete(1L);
        verify(dao,times(1)).delete(eq(1L));
    }

    @Test
    public void shouldUpdate(){
        when(productDAO.findByNames(eq(new HashSet<>(Arrays.asList(productDTO1.getName(), productDTO2.getName()))))).thenReturn(Arrays.asList(product1,product2));

        String recipeName = "Recipe 1";
        String recipeNameUpdated = "Recipe Updated";
        String description = "Recipe For Test";
        String descriptionUpdated = "Recipe For Test Updated";
        List<String> steps = Arrays.asList("Step 1", "Step 2", "Step 3");
        List<RecipeIngredientDTO> recipeIngredientDTOS = Arrays.asList(recipeIngredientDTO1, recipeIngredientDTO2);

        RecipeDTO recipeDTO = new RecipeDTO(1L, steps, recipeIngredientDTOS, recipeName, description);
        List<String> stepsUpdated = Arrays.asList("Step 1", "Step 2", "Step 3", "Step 4");
        RecipeDTO recipeDTOUpdated = new RecipeDTO(1L, stepsUpdated, recipeIngredientDTOS, recipeNameUpdated, descriptionUpdated);

        Recipe recipeResult = recipeService.save(recipeDTO);
        when(dao.findById(eq(1L))).thenReturn(recipeResult);
        verify(dao, Mockito.times(1)).save(any());

        Recipe recipeUpdated = recipeService.update(1L, recipeDTOUpdated);

        List<RecipeIngredient> recipeIngredientSet = recipeIngredientDTOS.stream().map(r -> new RecipeIngredient(recipeUpdated, productDtoToProduct(r.getProduct()), r.getAmount())).collect(Collectors.toList());

        assertEquals(recipeNameUpdated, recipeResult.getName());
        assertEquals(descriptionUpdated, recipeResult.getDescription());
        assertTrue(recipeResult.getIngredients().containsAll(recipeIngredientSet));
        assertTrue(recipeResult.getSteps().stream().map(RecipeStep::getInstruction).collect(Collectors.toList()).containsAll(stepsUpdated));
        assertEquals(Collections.singletonList(recipeResult), recipeResult.getSteps().stream().map(RecipeStep::getRecipe).distinct().collect(Collectors.toList()));
    }

    @Test
    public void returnSavedRecipes(){
        when(productDAO.findByNames(eq(new HashSet<>(Arrays.asList(productDTO1.getName(), productDTO2.getName()))))).thenReturn(Arrays.asList(product1,product2));

        String recipeName1 = "Recipe 1";
        String recipeName2 = "Recipe 2";
        String description1 = "Recipe 1 For Test";
        String description2 = "Recipe 2 For Test";
        List<String> steps1 = Arrays.asList("Step 1", "Step 2", "Step 3");
        List<String> steps2 = Arrays.asList("Step 1 2", "Step 2 2", "Step 3 2");
        List<RecipeIngredientDTO> recipeIngredientDTOS = Arrays.asList(recipeIngredientDTO1, recipeIngredientDTO2);

        RecipeDTO recipeDTO1 = new RecipeDTO(null, steps1, recipeIngredientDTOS, recipeName1, description1);
        RecipeDTO recipeDTO2 = new RecipeDTO(null, steps2, recipeIngredientDTOS, recipeName2, description2);

        Recipe recipeResult1 = recipeService.save(recipeDTO1);
        Recipe recipeResult2 = recipeService.save(recipeDTO2);

        verify(dao, Mockito.times(2)).save(any());
        when(dao.findAll()).thenReturn(Arrays.asList(recipeResult1,recipeResult2));

        List<Recipe> recipes = recipeService.findAll();
        verify(dao, Mockito.times(1)).findAll();

        assertTrue(recipes.contains(recipeResult1));
        assertTrue(recipes.contains(recipeResult2));
        assertEquals(recipes.size(), 2);
    }

    @Test
    public void returnEmptyAvailableRecipesForEmptyFridge(){
        when(productDAO.findByNames(eq(new HashSet<>(Arrays.asList(productDTO1.getName(), productDTO2.getName()))))).thenReturn(Arrays.asList(product1,product2));

        String recipeName1 = "Recipe 1";
        String recipeName2 = "Recipe 2";
        String description1 = "Recipe 1 For Test";
        String description2 = "Recipe 2 For Test";
        List<String> steps1 = Arrays.asList("Step 1", "Step 2", "Step 3");
        List<String> steps2 = Arrays.asList("Step 1 2", "Step 2 2", "Step 3 2");
        List<RecipeIngredientDTO> recipeIngredientDTOS = Arrays.asList(recipeIngredientDTO1, recipeIngredientDTO2);

        RecipeDTO recipeDTO1 = new RecipeDTO(null, steps1, recipeIngredientDTOS, recipeName1, description1);
        RecipeDTO recipeDTO2 = new RecipeDTO(null, steps2, recipeIngredientDTOS, recipeName2, description2);

        Recipe recipeResult1 = recipeService.save(recipeDTO1);
        Recipe recipeResult2 = recipeService.save(recipeDTO2);

        verify(dao, Mockito.times(2)).save(any());
        when(dao.findAll()).thenReturn(Arrays.asList(recipeResult1,recipeResult2));

        List<Recipe> recipes = recipeService.findAvailable();
        verify(dao, Mockito.times(1)).findAll();

        assertEquals(recipes.size(), 0);
        assertTrue(recipes.isEmpty());
    }


    private Product productDtoToProduct(ProductDTO productDTO){
        return new Product(productDTO.getName(), productDTO.getKcal(), productDTO.getExpiryDate(), productDTO.getQuantity(),
                productDTO.getMinQuantity(), productDTO.getProteins(), productDTO.getCarbohydrates(), productDTO.getFats());
    }


}
