package com.bootexample4.products.controller;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bootexample4.products.model.Product;
import com.bootexample4.products.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProductController_deleteProductef32297d01Test {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDeleteProduct_success() {
        Long productId = 1L; // TODO: Change this value to a valid product ID in your system
        Product product = new Product();
        product.setId(productId);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        ResponseEntity<Object> response = productController.deleteProduct(productId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(productRepository).delete(product);
    }

    @Test
    public void testDeleteProduct_notFound() {
        Long productId = 999L; // TODO: Change this value to a non-existent product ID in your system

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        ResponseEntity<Object> response = productController.deleteProduct(productId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(productRepository, never()).delete(any(Product.class));
    }
}