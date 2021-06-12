package com.example.demo.fridgemanager;

import com.example.demo.fridgemanager.controller.ProductController;
import com.example.demo.fridgemanager.dao.ProductDAO;
import com.example.demo.fridgemanager.dto.ProductDTOMapper;
import com.example.demo.fridgemanager.entities.Product;
import com.example.demo.fridgemanager.services.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(ProductController.class)
@ContextConfiguration(classes = {FridgeManagerApp.class,ProductService.class,ProductController.class, ProductDTOMapper.class})
public class ProductControllerSpec {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductDAO dao;

    @Test
    public void shouldAddProductToDatabase() throws Exception {
        Product pepsi = new Product("pepsi", null, LocalDate.now(), null, null, 1d,2d,3d);

        List<Product> allProducts = Collections.singletonList(pepsi);

        given(dao.findAll()).willReturn(allProducts);

        mvc.perform(get("/products")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(pepsi.getName())))
                .andExpect(jsonPath("$[0].fats", is(3.0d)))
                .andExpect(jsonPath("$[0].carbohydrates", is(2d)))
                .andExpect(jsonPath("$[0].proteins", is(1d)))
                .andExpect(jsonPath("$[0].kcal", is(39)));
    }

    @Test
    public   void shouldDeleteProductFromDatabase() throws Exception {
        Product pepsi = new Product("pepsi", 250, LocalDate.now(), null, null, null, null, null);

        List<Product> allProducts = Collections.singletonList(pepsi);

        given(dao.findAll()).willReturn(allProducts);

        mvc.perform(delete("/products/delete_by_name/pepsi"))
                .andExpect(status().isOk());

        mvc.perform(get("/products/pepsi")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public    void shouldSupplyProduct() throws Exception {
        Product pepsi = new Product("pepsi", 250, LocalDate.now(), null, null,null, null, null);

        given(dao.getById(1L)).willReturn(pepsi);

        mvc.perform(put("/products/supply/1/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.quantity", is(3)));
    }

    @Test
    public     void shouldConsumeProduct() throws Exception {
        Product pepsi = new Product("pepsi", 250, LocalDate.now(), 2, null, null, null, null);

        given(dao.getById(1L)).willReturn(pepsi);

        mvc.perform(put("/products/consume/1/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.product.quantity", is(1)));
    }

    @Test
    public   void shouldConsumeProductAndReturnMinimalQuantityMessage() throws Exception {
        Product pepsi = new Product("pepsi", 250, LocalDate.now(), 3, 2, null, null, null);

        given(dao.getById(1L)).willReturn(pepsi);

        mvc.perform(put("/products/consume/1/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.product.quantity", is(1)))
                .andExpect(jsonPath("$.message", is("Quantity of product: pepsi is lower than minimal quantity.")));
    }

    @Test
    public   void shouldNotConsumeProduct() throws Exception {
        Product pepsi = new Product("pepsi", 250, LocalDate.now(), 2, null, null, null, null);

        given(dao.getById(1L)).willReturn(pepsi);

        mvc.perform(put("/products/consume/1/3"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("There is no enough product: " + pepsi.getName() + ". Available: " + pepsi.getQuantity()));
    }

}
