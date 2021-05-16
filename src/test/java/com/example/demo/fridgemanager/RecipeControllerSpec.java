package com.example.demo.fridgemanager;

import com.example.demo.fridgemanager.config.DatabaseConfig;
import com.example.demo.fridgemanager.controller.ApiResponse;
import com.example.demo.fridgemanager.controller.ProductController;
import com.example.demo.fridgemanager.controller.RecipeController;
import com.example.demo.fridgemanager.dao.ProductDAO;
import com.example.demo.fridgemanager.dao.RecipeDAO;
import com.example.demo.fridgemanager.dto.*;
import com.example.demo.fridgemanager.entities.Product;
import com.example.demo.fridgemanager.entities.Recipe;
import com.example.demo.fridgemanager.services.ProductService;
import com.example.demo.fridgemanager.services.RecipeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(ProductController.class)
@ContextConfiguration(classes = {DatabaseConfig.class, ProductService.class, ProductController.class, RecipeController.class, RecipeService.class, ProductDTOMapper.class, RecipeDTOMapper.class, ProductDAO.class,RecipeDAO.class})
class RecipeControllerSpec {

    @Autowired
    private MockMvc mvc;

    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    private TransactionTemplate transactionTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldFindRecipe() throws Exception {

        Product pepsi = new Product("pepsi", 250, LocalDate.now(),null,null,null);
        saveToDb(pepsi);
        Recipe recipe = new Recipe(
                "student's breakfast",
                "typical breakfast",
                new HashMap<Product, BigDecimal>() {{
                    put(pepsi, BigDecimal.valueOf(2));
                }},
                Arrays.asList("drink pepsi"));

        saveToDb(recipe);


        mvc.perform(get("/api/recipes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(recipe.getName())));
    }

    @Test
    void createRecipe() throws Exception {
        Product pepsi = new Product("pepsi", 250, LocalDate.now(), null,null,null);
        Product sausage = new Product("sausage", 251, LocalDate.now(),  null,null,null);


        saveToDb(pepsi);
        saveToDb(sausage);

        RecipeDTO recipe = new RecipeDTO(
                null,
                Arrays.asList("drink pepsi"),
                Arrays.asList(
                        new RecipeIngredientDTO(null, pepsi.getId(), BigDecimal.ONE),
                        new RecipeIngredientDTO(null, sausage.getId(), BigDecimal.valueOf(4))
                ),
                "student's breakfast",
                "typical breakfast"
        );

        mvc.perform(post("/api/recipes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(recipe)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(responseContent(RecipeDTO.class, response -> {
                    Assertions.assertEquals(1, response.getSteps().size());
                    Assertions.assertEquals("drink pepsi", response.getSteps().get(0));
                    Assertions.assertEquals("student's breakfast", response.getName());
                    Assertions.assertEquals("typical breakfast", response.getDescription());
                    Assertions.assertEquals(2, response.getIngredients().size());
                    RecipeIngredientDTO pepsiIngredient = response.getIngredients().stream().filter(ing -> ing.getProductId().equals(pepsi.getId())).findFirst().get();
                    RecipeIngredientDTO sausageIngredient = response.getIngredients().stream().filter(ing -> ing.getProductId().equals(sausage.getId())).findFirst().get();
                    Assertions.assertEquals(0, pepsiIngredient.getAmount().compareTo(BigDecimal.ONE));
                    Assertions.assertEquals(0, sausageIngredient.getAmount().compareTo(BigDecimal.valueOf(4)));
                }));
    }

    @Test
    void updateRecipe() throws Exception{
        Product pepsi = new Product("pepsi", 250, LocalDate.now(),  null,null,null);
        Product sausage = new Product("sausage", 251, LocalDate.now(),  null,null,null);

        saveToDb(pepsi);
        saveToDb(sausage);

        RecipeDTO recipe = new RecipeDTO(
                null,
                Collections.emptyList(),
                Collections.emptyList(),
                "test",
                "Test"
        );

        AtomicLong id = new AtomicLong();

        mvc.perform(post("/api/recipes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(recipe)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(responseContent(RecipeDTO.class, response -> {
                    id.set(response.getId());
                }));

        RecipeDTO recipeUpdate = new RecipeDTO(
                null,
                Arrays.asList("drink pepsi"),
                Arrays.asList(
                        new RecipeIngredientDTO(null, pepsi.getId(), BigDecimal.ONE),
                        new RecipeIngredientDTO(null, sausage.getId(), BigDecimal.valueOf(4))
                ),
                "student's breakfast",
                "typical breakfast"
        );

        mvc.perform(put("/api/recipes/" +id.get())
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(recipeUpdate)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(responseContent(RecipeDTO.class, response -> {
                    Assertions.assertEquals(1, response.getSteps().size());
                    Assertions.assertEquals("drink pepsi", response.getSteps().get(0));
                    Assertions.assertEquals("student's breakfast", response.getName());
                    Assertions.assertEquals("typical breakfast", response.getDescription());
                    Assertions.assertEquals(2, response.getIngredients().size());
                    Assertions.assertEquals(0, response.getIngredients().get(1).getAmount().compareTo(BigDecimal.ONE));
                    Assertions.assertEquals(pepsi.getId(), response.getIngredients().get(1).getProductId());
                    Assertions.assertEquals(0, response.getIngredients().get(0).getAmount().compareTo(BigDecimal.valueOf(4)));
                    Assertions.assertEquals(sausage.getId(), response.getIngredients().get(0).getProductId());
                }));
    }

    @Test
    void deleteRecipe() throws Exception{
        RecipeDTO recipe = new RecipeDTO(
                null,
                Collections.emptyList(),
                Collections.emptyList(),
                "test",
                "Test"
        );

        AtomicLong id = new AtomicLong();

        mvc.perform(post("/api/recipes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(recipe)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(responseContent(RecipeDTO.class, response -> {
                    id.set(response.getId());
                }));

        mvc.perform(delete("/api/recipes/" + id.get()))
                .andExpect(status().is2xxSuccessful());
    }

    private String toJson(Object obj) throws JsonProcessingException {
        return objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(obj);
    }

    protected <T> ResultMatcher responseContent(Class<T> cls, Consumer<T> assertions) {
        return (result) -> {
            String contentAsString = result.getResponse().getContentAsString();
            ApiResponse<T> response = objectMapper.readValue(contentAsString, objectMapper.getTypeFactory().constructParametricType(ApiResponse.class, cls));
            assertions.accept(response.getData());
        };
    }

    private void saveToDb(Object o){
        transactionTemplate.executeWithoutResult(s->entityManager.persist(o));
    }
    protected <T> ResultMatcher responseContentList(Class<T> cls, Consumer<List<T>> assertions) {
        return (result) -> {
            String contentAsString = result.getResponse().getContentAsString();
            JavaType nestedListType = objectMapper.getTypeFactory().constructParametricType(List.class, cls);
            ApiResponse<List<T>>response =  objectMapper.readValue(contentAsString, objectMapper.getTypeFactory().constructParametricType(ApiResponse.class, nestedListType));

            assertions.accept(response.getData());
        };
    }
}
