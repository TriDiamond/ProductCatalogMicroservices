package dev.benish.productcatalogservice.controller;

//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
import dev.benish.productcatalogservice.dto.ProductResponseDto;
import dev.benish.productcatalogservice.model.Product;
import dev.benish.productcatalogservice.service.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(ProductController.class)
class ProductControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IProductService productService;

    @Test
    public void testGetAllProducts_RunSuccessfully() throws Exception {

        List<Product> products = new ArrayList<>();
        List<ProductResponseDto> productResponseDto = new ArrayList<>();
        Product product = new Product();
        product.setId(1L);
        product.setName("Electronics");
        product.setDescription("Electronics Products");
        product.setPrice(10000);
        product.setQuantity(5);
        product.setCategory(null);
        products.add(product);


        when(productService.getProductById(1L)).thenReturn(product);
        ProductResponseDto productResponse = product.convert();
        productResponseDto.add(productResponse);

        //ObjectMapper objectMapper = new ObjectMapper();
       // String response = objectMapper.writeValueAsString(productResponseDto);

        //mockMvc.perform(get("/products")).andExpect(status().isOk())
                //.andExpect(content().json(response));
    }


}