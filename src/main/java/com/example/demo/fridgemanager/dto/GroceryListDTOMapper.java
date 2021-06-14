package com.example.demo.fridgemanager.dto;

import com.example.demo.fridgemanager.entities.GroceryList;
import com.example.demo.fridgemanager.entities.Recipe;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GroceryListDTOMapper {

    public GroceryListDTO mapToDTO(GroceryList groceryList) {
        if (groceryList == null)
            return null;
        return new GroceryListDTO(
                groceryList.getId(),
                groceryList.getName(),
                groceryList.getCreatedAt(),
                groceryList.getProducts()
        );
    }

    public List<GroceryListDTO> mapToDTO(Collection<GroceryList> grocLists) {
        if (grocLists == null)
            return Collections.emptyList();
        return grocLists.stream().map(grocList -> mapToDTO(grocList)).filter(Objects::nonNull).collect(Collectors.toList());
    }
}
