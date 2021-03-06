package com.example.demo.fridgemanager.services;

import com.example.demo.fridgemanager.dao.ProductDAO;
import com.example.demo.fridgemanager.dto.ProductDTO;
import com.example.demo.fridgemanager.entities.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {
    @Mock
    ProductDAO dao;
    @InjectMocks
    ProductServiceImpl productServiceImpl = new ProductServiceImpl();

    @Test
    public void productSaved() {
        ProductDTO dto = new ProductDTO(null, "test", 35, LocalDate.of(2000, 1, 1), 3, 2, 1d, 2d, 3d);
        Product saved = productServiceImpl.save(dto);
        verify(dao, Mockito.times(1)).save(any());

        assertEquals("test", saved.getName());
        assertEquals(35, saved.getKcal().intValue());
        assertEquals(0, saved.getExpiryDate().compareTo(LocalDate.of(2000, 1, 1)));
        assertEquals(3, saved.getQuantity().intValue());
        assertEquals(2, saved.getMinQuantity().intValue());
        assertEquals(1, saved.getProteins().intValue());
        assertEquals(2, saved.getCarbohydrates().intValue());
        assertEquals(3, saved.getFats().intValue());

    }

    @Test
    public void productUpdated() {
        Product productToUpdate = new Product("initial product", null, null, null, null, null, null, null);
        when(dao.findById(eq(1L))).thenReturn(productToUpdate);

        ProductDTO dto = new ProductDTO(null, "test", 35, LocalDate.of(2000, 1, 1), 3, 2, 1d, 2d, 3d);
        Product saved = productServiceImpl.update(1L, dto);

        verify(dao, Mockito.times(1)).save(any());

        assertEquals("test", saved.getName());
        assertEquals(35, saved.getKcal().intValue());
        assertEquals(0, saved.getExpiryDate().compareTo(LocalDate.of(2000, 1, 1)));
        assertEquals(3, saved.getQuantity().intValue());
        assertEquals(2, saved.getMinQuantity().intValue());
        assertEquals(1, saved.getProteins().intValue());
        assertEquals(2, saved.getCarbohydrates().intValue());
        assertEquals(3, saved.getFats().intValue());
    }

    @Test
    public void getProducts() {
        Product product = new Product("test", 35, LocalDate.of(2000,1,1),3, 2, 1d,2d,3d);
        when(dao.findAll()).thenReturn(Arrays.asList(product));

        List<Product> products = productServiceImpl.findAll();
        assertEquals(1,products.size());

        Product result = products.get(0);
        assertEquals("test", result.getName());
        assertEquals(35, result.getKcal().intValue());
        assertEquals(0, result.getExpiryDate().compareTo(LocalDate.of(2000, 1, 1)));
        assertEquals(3, result.getQuantity().intValue());
        assertEquals(2, result.getMinQuantity().intValue());
        assertEquals(1, result.getProteins().intValue());
        assertEquals(2, result.getCarbohydrates().intValue());
        assertEquals(3, result.getFats().intValue());
    }
    @Test
    public void getProductsByName() {
        Product product = new Product("test", 35, LocalDate.of(2000,1,1),3, 2, 1d,2d,3d);
        when(dao.findByName(eq("test"))).thenReturn(Arrays.asList(product));

        List<Product> products = productServiceImpl.getProductsByName("test");
        assertEquals(1,products.size());

        Product result = products.get(0);
        assertEquals("test", result.getName());
        assertEquals(35, result.getKcal().intValue());
        assertEquals(0, result.getExpiryDate().compareTo(LocalDate.of(2000, 1, 1)));
        assertEquals(3, result.getQuantity().intValue());
        assertEquals(2, result.getMinQuantity().intValue());
        assertEquals(1, result.getProteins().intValue());
        assertEquals(2, result.getCarbohydrates().intValue());
        assertEquals(3, result.getFats().intValue());
    }
    @Test
    public void getProductsById() {
        Product product = new Product("test", 35, LocalDate.of(2000,1,1),3, 2, 1d,2d,3d);
        when(dao.findById(eq(1L))).thenReturn(product);

        Product productReturned = productServiceImpl.getById(1L);
        assertEquals("test", productReturned.getName());
        assertEquals(35, productReturned.getKcal().intValue());
        assertEquals(0, productReturned.getExpiryDate().compareTo(LocalDate.of(2000, 1, 1)));
        assertEquals(3, productReturned.getQuantity().intValue());
        assertEquals(2, productReturned.getMinQuantity().intValue());
        assertEquals(1, productReturned.getProteins().intValue());
        assertEquals(2, productReturned.getCarbohydrates().intValue());
        assertEquals(3, productReturned.getFats().intValue());
    }

    @Test
    public void deleteProductById() {
         productServiceImpl.delete(1L);

         verify(dao,times(1)).delete(eq(1L));
    }

    @Test
    public void deleteProductByName() {
        productServiceImpl.deleteByName("test");

        verify(dao,times(1)).deleteByName(eq("test"));
    }
}