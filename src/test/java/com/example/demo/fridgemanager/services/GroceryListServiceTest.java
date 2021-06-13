package com.example.demo.fridgemanager.services;

import com.example.demo.fridgemanager.dao.GroceryListDAO;
import com.example.demo.fridgemanager.dao.ProductDAO;
import com.example.demo.fridgemanager.dao.RecipeDAO;
import com.example.demo.fridgemanager.dto.GroceryListDTO;
import com.example.demo.fridgemanager.entities.GroceryEntry;
import com.example.demo.fridgemanager.entities.GroceryList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GroceryListServiceTest {

    @Mock
    GroceryListDAO groceryListDAO = Mockito.mock(GroceryListDAO.class);
    @InjectMocks
    GroceryListService groceryListService = new GroceryListService();

    GroceryEntry groceryEntry1;
    GroceryEntry groceryEntry2;
    List<GroceryEntry> groceries;
    LocalDate localDate;

    @Before
    public void prepareData(){
        groceryEntry1 = new GroceryEntry("pepsi", 3);
        groceryEntry2 = new GroceryEntry("sprite", 2);
        localDate = LocalDate.now();
        groceries = new ArrayList<>();
    }

    @Test
    public void groceryListSavedGivenAllCorrectData() {
        groceries.add(new GroceryEntry("pepsi", 3));
        groceries.add(new GroceryEntry("sprite", 2));
        String groceryListName = "Grocery list test";

        GroceryList groceryList = new GroceryList(groceryListName, localDate, groceries);

        GroceryList result = groceryListService.save(groceryList.toDTO());
        verify(groceryListDAO, Mockito.times(1)).save(any());

        assertEquals(groceryListName, result.getName());
        assertEquals(localDate, result.getCreatedAt());
        assertEquals(groceries, result.getProducts());
    }

    @Test
    public void groceryListSavedGivenNullOrEmptyName() {
        groceries.add(new GroceryEntry("pepsi", 3));
        groceries.add(new GroceryEntry("sprite", 2));

        GroceryList groceryListNull = new GroceryList(null, localDate, groceries);
        GroceryList groceryListEmpty = new GroceryList("", localDate, groceries);

        GroceryList resultNull = groceryListService.save(groceryListNull.toDTO());
        GroceryList resultEmpty = groceryListService.save(groceryListEmpty.toDTO());
        verify(groceryListDAO, Mockito.times(2)).save(any());

        assertNull(resultNull.getName());
        assertEquals(localDate, resultNull.getCreatedAt());
        assertEquals(groceries, resultNull.getProducts());

        assertEquals("", resultEmpty.getName());
        assertEquals(localDate, resultEmpty.getCreatedAt());
        assertEquals(groceries, resultEmpty.getProducts());
    }

    @Test
    public void groceryListSavedGivenNullCreatedAt() {
        groceries.add(new GroceryEntry("pepsi", 3));
        groceries.add(new GroceryEntry("sprite", 2));
        String groceryListName = "Grocery list test";

        GroceryList groceryList = new GroceryList("Grocery list test", null, groceries);

        GroceryList result = groceryListService.save(groceryList.toDTO());
        verify(groceryListDAO, Mockito.times(1)).save(any());

        assertEquals(groceryListName, result.getName());
        assertNull(result.getCreatedAt());
        assertEquals(groceries, result.getProducts());
    }

    @Test
    public void groceryListSavedGivenNullOrEmptyGroceryEntryListData() {
        String groceryListName = "Grocery list test";

        GroceryList groceryListNull = new GroceryList("Grocery list test", localDate, null);
        GroceryList groceryListEmpty = new GroceryList("Grocery list test", localDate, Collections.emptyList());

        GroceryList resultNull = groceryListService.save(groceryListNull.toDTO());
        GroceryList resultEmpty = groceryListService.save(groceryListEmpty.toDTO());
        verify(groceryListDAO, Mockito.times(2)).save(any());

        assertEquals(groceryListName, resultNull.getName());
        assertEquals(localDate, resultNull.getCreatedAt());
        assertNull(resultNull.getProducts());

        assertEquals(groceryListName, resultEmpty.getName());
        assertEquals(localDate, resultEmpty.getCreatedAt());
        assertEquals(Collections.emptyList(), resultEmpty.getProducts());
    }

    @Test
    public void shouldFindAllAddedGroceryLists(){
        groceries.add(new GroceryEntry("pepsi", 3));
        groceries.add(new GroceryEntry("sprite", 2));
        String groceryListName1 = "Grocery list test 1";
        String groceryListName2 = "Grocery list test 2";

        GroceryList groceryList1 = new GroceryList(groceryListName1, localDate, groceries);
        GroceryList groceryList2 = new GroceryList(groceryListName2, localDate, groceries);

        GroceryList result1 = groceryListService.save(groceryList1.toDTO());
        GroceryList result2 = groceryListService.save(groceryList2.toDTO());
        verify(groceryListDAO, Mockito.times(2)).save(any());

        when(groceryListDAO.findAll()).thenReturn(Arrays.asList(result1,result2));

        List<GroceryList> foundGroceryLists = groceryListService.findAll();
        assertTrue(foundGroceryLists.contains(result1));
        assertTrue(foundGroceryLists.contains(result2));
        assertEquals(2, foundGroceryLists.size());
    }

    @Test
    public void findAddedGroceryListsByName(){
        groceries.add(new GroceryEntry("pepsi", 3));
        groceries.add(groceryEntry2);
        String groceryListName = "Grocery list test";
        String groceryListNameNotTheSame = groceryListName+" not the same";

        GroceryList groceryList = new GroceryList(groceryListName, localDate, groceries);
        GroceryList groceryList2 = new GroceryList(groceryListName, localDate, Arrays.asList(groceryEntry1,groceryEntry2, new GroceryEntry("milk",5)));
        GroceryList groceryList3 = new GroceryList(groceryListNameNotTheSame, localDate, groceries);

        GroceryList result1 = groceryListService.save(groceryList.toDTO());
        GroceryList result2 = groceryListService.save(groceryList2.toDTO());
        GroceryList result3 = groceryListService.save(groceryList3.toDTO());

        verify(groceryListDAO, Mockito.times(3)).save(any());

        when(groceryListDAO.findByName(groceryListName)).thenReturn(Arrays.asList(result1, result2));

        List<GroceryList> resultLists = groceryListService.getGroceryListsByName(groceryListName);

        assertTrue(resultLists.contains(result1));
        assertTrue(resultLists.contains(result2));
        assertFalse(resultLists.contains(result3));
        assertEquals(2, resultLists.size());
    }

    @Test
    public void findAddedGroceryListsById(){
        groceries.add(new GroceryEntry("pepsi", 3));
        groceries.add(groceryEntry2);
        String groceryListName = "Grocery list test";
        String groceryListNameNotTheSame = groceryListName+" not the same";

        GroceryList groceryList = new GroceryList(groceryListName, localDate, groceries);
        GroceryList groceryList2 = new GroceryList(groceryListName, localDate, Arrays.asList(groceryEntry1,groceryEntry2, new GroceryEntry("milk",5)));

        GroceryList result1 = groceryListService.save(groceryList.toDTO());
        groceryListService.save(groceryList2.toDTO());

        verify(groceryListDAO, Mockito.times(2)).save(any());

        when(groceryListDAO.getById(1L)).thenReturn(result1);

        GroceryList foundGroceryList = groceryListService.getById(1L);

        assertEquals(result1, foundGroceryList);
    }

    @Test
    public void shouldDelete(){
        groceryListService.delete(1L);
        verify(groceryListDAO,times(1)).delete(eq(1L));
    }

    @Test
    public void shouldNotUpdateForNullGroceryList(){
        groceryListService.update(1L, null);
        verify(groceryListDAO,times(0)).save(any());
    }

    @Test
    public void shouldUpdateForNotNullGroceryList(){
        groceries.add(new GroceryEntry("pepsi", 3));
        groceries.add(groceryEntry2);
        String groceryListName = "Grocery list test";
        String groceryListDifferentName = "Different name";

        GroceryList groceryList = new GroceryList(groceryListName, localDate, groceries);

        GroceryList resultFirst = groceryListService.save(groceryList.toDTO());
        verify(groceryListDAO,times(1)).save(any());

        groceryList.setCreatedAt(LocalDate.now());
        groceryList.setName(groceryListDifferentName);

        when(groceryListDAO.getById(1L)).thenReturn(resultFirst);
        when(groceryListDAO.save(any())).thenReturn(groceryList);

        GroceryList result = groceryListService.update(1L, groceryList.toDTO());
        verify(groceryListDAO,times(2)).save(any());

        assertEquals(groceryListDifferentName, result.getName());
        assertEquals(groceryList.getCreatedAt(), result.getCreatedAt());
        assertEquals(groceries, result.getProducts());
    }
}
