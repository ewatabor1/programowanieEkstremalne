package com.example.demo.fridgemanager.services;

import com.example.demo.fridgemanager.dao.GroceryListDAO;
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
public class GroceryListServiceImplTest {

    @Mock
    GroceryListDAO groceryListDAOImpl = Mockito.mock(GroceryListDAO.class);
    @InjectMocks
    GroceryListServiceImpl groceryListServiceImpl = new GroceryListServiceImpl();

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

        GroceryList result = groceryListServiceImpl.save(groceryList.toDTO());
        verify(groceryListDAOImpl, Mockito.times(1)).save(any());

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

        GroceryList resultNull = groceryListServiceImpl.save(groceryListNull.toDTO());
        GroceryList resultEmpty = groceryListServiceImpl.save(groceryListEmpty.toDTO());
        verify(groceryListDAOImpl, Mockito.times(2)).save(any());

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

        GroceryList result = groceryListServiceImpl.save(groceryList.toDTO());
        verify(groceryListDAOImpl, Mockito.times(1)).save(any());

        assertEquals(groceryListName, result.getName());
        assertNull(result.getCreatedAt());
        assertEquals(groceries, result.getProducts());
    }

    @Test
    public void groceryListSavedGivenNullOrEmptyGroceryEntryListData() {
        String groceryListName = "Grocery list test";

        GroceryList groceryListNull = new GroceryList("Grocery list test", localDate, null);
        GroceryList groceryListEmpty = new GroceryList("Grocery list test", localDate, Collections.emptyList());

        GroceryList resultNull = groceryListServiceImpl.save(groceryListNull.toDTO());
        GroceryList resultEmpty = groceryListServiceImpl.save(groceryListEmpty.toDTO());
        verify(groceryListDAOImpl, Mockito.times(2)).save(any());

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

        GroceryList result1 = groceryListServiceImpl.save(groceryList1.toDTO());
        GroceryList result2 = groceryListServiceImpl.save(groceryList2.toDTO());
        verify(groceryListDAOImpl, Mockito.times(2)).save(any());

        when(groceryListDAOImpl.findAll()).thenReturn(Arrays.asList(result1,result2));

        List<GroceryList> foundGroceryLists = groceryListServiceImpl.findAll();
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

        GroceryList result1 = groceryListServiceImpl.save(groceryList.toDTO());
        GroceryList result2 = groceryListServiceImpl.save(groceryList2.toDTO());
        GroceryList result3 = groceryListServiceImpl.save(groceryList3.toDTO());

        verify(groceryListDAOImpl, Mockito.times(3)).save(any());

        when(groceryListDAOImpl.findByName(groceryListName)).thenReturn(Arrays.asList(result1, result2));

        List<GroceryList> resultLists = groceryListServiceImpl.getGroceryListsByName(groceryListName);

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

        GroceryList result1 = groceryListServiceImpl.save(groceryList.toDTO());
        groceryListServiceImpl.save(groceryList2.toDTO());

        verify(groceryListDAOImpl, Mockito.times(2)).save(any());

        when(groceryListDAOImpl.findById(1L)).thenReturn(result1);

        GroceryList foundGroceryList = groceryListServiceImpl.getById(1L);

        assertEquals(result1, foundGroceryList);
    }

    @Test
    public void shouldDelete(){
        groceryListServiceImpl.delete(1L);
        verify(groceryListDAOImpl,times(1)).delete(eq(1L));
    }

    @Test
    public void shouldNotUpdateForNullGroceryList(){
        groceryListServiceImpl.update(1L, null);
        verify(groceryListDAOImpl,times(0)).save(any());
    }

    @Test
    public void shouldUpdateForNotNullGroceryList(){
        groceries.add(new GroceryEntry("pepsi", 3));
        groceries.add(groceryEntry2);
        String groceryListName = "Grocery list test";
        String groceryListDifferentName = "Different name";

        GroceryList groceryList = new GroceryList(groceryListName, localDate, groceries);

        GroceryList resultFirst = groceryListServiceImpl.save(groceryList.toDTO());
        verify(groceryListDAOImpl,times(1)).save(any());

        groceryList.setCreatedAt(LocalDate.now());
        groceryList.setName(groceryListDifferentName);

        when(groceryListDAOImpl.findById(1L)).thenReturn(resultFirst);
        when(groceryListDAOImpl.save(any())).thenReturn(groceryList);

        GroceryList result = groceryListServiceImpl.update(1L, groceryList.toDTO());
        verify(groceryListDAOImpl,times(2)).save(any());

        assertEquals(groceryListDifferentName, result.getName());
        assertEquals(groceryList.getCreatedAt(), result.getCreatedAt());
        assertEquals(groceries, result.getProducts());
    }
}
