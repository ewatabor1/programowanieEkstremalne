package com.example.demo.fridgemanager.dto;

import com.example.demo.fridgemanager.entities.GroceryEntry;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GroceryEntryDTOMapper {

    public GroceryEntryDTO mapToDTO(GroceryEntry groceryEntry) {
        if (groceryEntry == null)
            return null;
        return new GroceryEntryDTO(
                groceryEntry.getId(),
                groceryEntry.getProduct(),
                groceryEntry.getQuantity()
        );
    }

    public List<GroceryEntryDTO> mapToDTO(Collection<GroceryEntry> groceryEntries) {
        if (groceryEntries == null)
            return Collections.emptyList();
        return groceryEntries.stream().map(groceryEntry -> mapToDTO(groceryEntry)).filter(Objects::nonNull).collect(Collectors.toList());
    }
}
