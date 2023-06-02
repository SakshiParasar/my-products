package com.bootexample4.products.controller;

import java.util.Optional;

import com.bootexample4.products.model.Product;
import com.bootexample4.products.repository.ProductRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ProductController_deleteProductef32297d01Test {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductRepository productRepository;

    private Product product;

    @BeforeEach
    public void setUp() {
        product = new Product();
        product.setId(1L);
        product.setName("Product Test");
        product.setDescription("Product Test Description");
        product.setPrice(100.0);
    }

    @Test
    public void testDeleteProduct_success() {
        Mockito.when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        ResponseEntity<Object> response = productController.deleteProduct(product.getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Mockito.verify(productRepository).delete(product);
    }

    @Test
    public void testDeleteProduct_notFound() {
        Mockito.when(productRepository.findById(product.getId())).thenReturn(Optional.empty());
        ResponseEntity<Object> response = productController.deleteProduct(product.getId());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Mockito.verify(productRepository, Mockito.never()).delete(product);
    }
}