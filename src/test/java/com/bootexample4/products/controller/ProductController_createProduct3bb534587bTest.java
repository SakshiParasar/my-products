package com.bootexample4.products.controller;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.bootexample4.products.model.Product;
import com.bootexample4.products.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ProductController_createProduct3bb534587bTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductRepository productRepository;

    private Product product1;
    private Product product2;

    @BeforeEach
    public void setUp() {
        product1 = new Product(1L, "Product 1", "Description 1", 100.0);
        product2 = new Product(2L, "Product 2", "Description 2", 200.0);
    }

    @Test
    public void testCreateProduct_success() {
        Mockito.when(productRepository.save(product1)).thenReturn(product1);

        Product createdProduct = productController.createProduct(product1);

        assertEquals(product1, createdProduct);
    }

    @Test
    public void testCreateProduct_failure() {
        Mockito.when(productRepository.save(product1)).thenReturn(null);

        Product createdProduct = productController.createProduct(product1);

        assertEquals(null, createdProduct);
    }
}