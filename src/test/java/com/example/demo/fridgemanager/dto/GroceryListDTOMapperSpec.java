package com.example.demo.fridgemanager.dto;


import com.example.demo.fridgemanager.entities.GroceryEntry;
import com.example.demo.fridgemanager.entities.GroceryList;
import com.example.demo.fridgemanager.entities.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class GroceryListDTOMapperSpec {

    private GroceryListDTOMapper mapper;
    private LocalDate date;

    private GroceryEntry cola;
    private GroceryEntry chicken;
    private GroceryEntry water;
    private GroceryEntry sausage;
    private GroceryList grocList;
    private GroceryList grocList2;
    private List<GroceryEntry> producList;
    private List<GroceryEntry> producList2;
    private List<GroceryList> groceryListsList;

    @Before
    public void setUp() {
        date = LocalDate.of(2021, 12, 18);
        cola = new GroceryEntry("cola", 1);
        chicken = new GroceryEntry("Chicken", 1);
        water = new GroceryEntry("Water",1);
        sausage = new GroceryEntry("Sausage",1);
        producList = new ArrayList<>();
        producList2= new ArrayList<>();
        producList.add(cola);
        producList.add(chicken);
        producList2.add(water);
        producList2.add(sausage);
        grocList = new GroceryList("Grocery List 1",date,producList);
        grocList2 = new GroceryList("Grocery List 2",date,producList2);

        groceryListsList = new ArrayList<>(Arrays.asList(grocList, grocList2));
        mapper = new GroceryListDTOMapper();
    }

    @Test
    public void toDtoSingle() {
        GroceryListDTO groceryListDTO = mapper.mapToDTO(grocList);
        assertEquals(groceryListDTO.getName(), grocList.getName());
        assertEquals(groceryListDTO.getProducts(), producList);
        assertEquals(groceryListDTO.getCreatedAt(), date);

    }

    @Test
    public void toDtoList() {
        List<GroceryListDTO> productDTOS = mapper.mapToDTO(groceryListsList);

        assertEquals(productDTOS.size(), groceryListsList.size());
        for(int i=0; i < groceryListsList.size(); ++i) {
            assertEquals(productDTOS.get(i).getName(), groceryListsList.get(i).getName());
            assertEquals(productDTOS.get(i).getProducts().size(), groceryListsList.get(i).getProducts().size());
            for(int j=0; j< productDTOS.get(i).getProducts().size(); ++j) {
                assertEquals(productDTOS.get(i).getProducts().get(i).getProduct(),groceryListsList.get(i).getProducts().get(i).getProduct());
                assertEquals(productDTOS.get(i).getProducts().get(i).getQuantity(),groceryListsList.get(i).getProducts().get(i).getQuantity());
                assertEquals(productDTOS.get(i).getProducts().get(i).getId(),groceryListsList.get(i).getProducts().get(i).getId());
            }

            assertEquals(productDTOS.get(i).getCreatedAt(), groceryListsList.get(i).getCreatedAt());

        }
    }

    @Test
    public void toDtoListFilterNull() {
        grocList.addEntry(null);
        assertEquals(2, mapper.mapToDTO(grocList).getProducts().size());
    }
}
