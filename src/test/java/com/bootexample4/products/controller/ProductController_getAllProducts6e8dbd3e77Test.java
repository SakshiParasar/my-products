package com.bootexample4.products.controller;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.bootexample4.products.model.Product;
import com.bootexample4.products.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ProductController_getAllProducts6e8dbd3e77Test {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllProducts_success() {
        // Prepare sample products
        Product product1 = new Product(1L, "Product1", "Description1", 100.0);
        Product product2 = new Product(2L, "Product2", "Description2", 200.0);

        // Mock the productRepository.findAll() method
        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        // Call the getAllProducts method
        List<Product> result = productController.getAllProducts();

        // Assert the result
        assertEquals(2, result.size());
        assertEquals(product1, result.get(0));
        assertEquals(product2, result.get(1));
    }

    @Test
    public void testGetAllProducts_empty() {
        // Mock the productRepository.findAll() method
        when(productRepository.findAll()).thenReturn(Arrays.asList());

        // Call the getAllProducts method
        List<Product> result = productController.getAllProducts();

        // Assert the result
        assertEquals(0, result.size());
    }
}