package dev.benish.productcatalogservice.controller;

import dev.benish.productcatalogservice.dto.ProductResponseDto;
import dev.benish.productcatalogservice.model.Product;
import dev.benish.productcatalogservice.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @Autowired
    @MockitoBean
    private ProductService productService;

    @Test
    public void testGetProductById_WithValidId(){
        /*
          let us assume we are passing valid Id exist in database and return expected product
         */
        Product product = new Product();
        product.setId(1L);
        product.setName("Electronics");
        product.setDescription("Electronics Products");
        product.setPrice(10000);
        product.setQuantity(5);
        product.setCategory(null);

        when(productService.getProductById(1L)).thenReturn(product);
        product.convert();
        ResponseEntity<ProductResponseDto> response =  productController.getProductById(1L);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(product.getId(), response.getBody().getId());
        assertEquals(product.getName(), response.getBody().getName());
        assertEquals(product.getDescription(), response.getBody().getDescription());
        assertEquals(product.getPrice(), response.getBody().getPrice());
        assertEquals(product.getQuantity(), response.getBody().getQuantity());
        assertEquals(product.getCategory(), response.getBody().getCategory());

        verify(productService, times(1)).getProductById(1L);
        /*
         verify method is Mockito to check whether a method was called how many times
         */

    }
    @Test
    public void testGetProductById_WithInvalidId(){
        assertThrows(IllegalArgumentException.class, () -> {
           productController.getProductById(-1L);
        });

        verify(productService, times(0)).getProductById(-1L);

        Exception exception1 = assertThrows(IllegalArgumentException.class, () -> {
            productController.getProductById(-1L);
        });

        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> {
            productController.getProductById(0L);
        });


        assertEquals("Product Id not found", exception1.getMessage());
        assertEquals("Product Id starts with positive", exception2.getMessage());
    }


}