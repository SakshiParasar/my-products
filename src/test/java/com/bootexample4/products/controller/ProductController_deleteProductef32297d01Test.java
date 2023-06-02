```java
package com.bootexample4.products.controller;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.bootexample4.products.model.Product;
import com.bootexample4.products.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
public class ProductController_deleteProductef32297d01Test {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void deleteProduct_whenProductExists_shouldReturnOk() throws Exception {
        // Given
        Product product = new Product();
        product.setId(1L);

        Mockito.when(productRepository.findById(1L)).thenReturn(java.util.Optional.of(product));

        // When
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/products/1")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        // Then
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    public void deleteProduct_whenProductDoesNotExist_shouldReturnNotFound() throws Exception {
        // Given
        Mockito.when(productRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        // When
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/products/1")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        // Then
        assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
    }
}