package com.example.demo.fridgemanager;

import com.example.demo.fridgemanager.controller.ProductController;
import com.example.demo.fridgemanager.dao.ProductDAO;
import com.example.demo.fridgemanager.dto.ProductDTOMapper;
import com.example.demo.fridgemanager.entities.Product;
import com.example.demo.fridgemanager.services.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(ProductController.class)
@ContextConfiguration(classes = {ProductService.class,ProductController.class, ProductDTOMapper.class})
class ProductControllerSpec {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductDAO dao;

    @Test
    void shouldAddProductToDatabase() throws Exception {
        Product pepsi = new Product("pepsi", null, LocalDate.now(),1d,2d,3d);

        List<Product> allProducts = Collections.singletonList(pepsi);

        given(dao.findAll()).willReturn(allProducts);

        mvc.perform(get("/api/products")
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
    void shouldDeleteProductFromDatabase() throws Exception {
        Product pepsi = new Product("pepsi", 250, LocalDate.now(), null,null,null);

        List<Product> allProducts = Collections.singletonList(pepsi);

        given(dao.findAll()).willReturn(allProducts);

        mvc.perform(delete("/api/products/delete_by_name/pepsi"))
                .andExpect(status().isOk());

        mvc.perform(get("/api/products/pepsi")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

}