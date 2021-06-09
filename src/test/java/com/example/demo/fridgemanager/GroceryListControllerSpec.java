package com.example.demo.fridgemanager;

import com.example.demo.fridgemanager.controller.GroceryListController;
import com.example.demo.fridgemanager.dao.GroceryListRepository;
import com.example.demo.fridgemanager.entities.GroceryEntry;
import com.example.demo.fridgemanager.entities.GroceryList;
import com.example.demo.fridgemanager.services.GroceryListService;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(GroceryListController.class)
@ContextConfiguration(classes = { GroceryListService.class, GroceryListController.class })
class GroceryListControllerSpec {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private GroceryListRepository dao;

    private GroceryList prepareGL() {
        List<GroceryEntry> groceries = new ArrayList<>();
        groceries.add(new GroceryEntry("pepsi", 3));
        groceries.add(new GroceryEntry("sprite", 2));

        return new GroceryList("test_list", LocalDate.now(), groceries);
    }

    @Test
    void shouldAddGroceryListToDatabase() throws Exception {

        GroceryList gl = prepareGL();
        List<GroceryList> allGroceryLists = Collections.singletonList(gl);

        given(dao.findAll()).willReturn(allGroceryLists);

        mvc.perform(get("/api/grocery-lists")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(gl.getName())))
                .andExpect(jsonPath("$[0].products[0].product", is("pepsi")));
    }

    @Test
    void shouldDeleteGroceryListFromDatabase() throws Exception {
        GroceryList gl = prepareGL();

        List<GroceryList> allGroceryLists = Collections.singletonList(gl);

        given(dao.findAll()).willReturn(allGroceryLists);

        mvc.perform(delete("/api/grocery-lists/delete_by_name/test_list"))
                .andExpect(status().isOk());

        mvc.perform(get("/api/grocery-lists/test_list")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

}
