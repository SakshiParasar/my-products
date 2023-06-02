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
import static org.mockito.Mockito.when;

public class ProductController_updateProduct1aa1a9f53dTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUpdateProduct_success() {
        Long id = 1L;
        Product existingProduct = new Product(id, "Old Product", "Old Description", 100.0);
        Product updatedProduct = new Product(id, "New Product", "New Description", 200.0);

        when(productRepository.findById(id)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(existingProduct)).thenReturn(updatedProduct);

        ResponseEntity<Product> response = productController.updateProduct(id, updatedProduct);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedProduct, response.getBody());
    }

    @Test
    public void testUpdateProduct_notFound() {
        Long id = 1L;
        Product updatedProduct = new Product(id, "New Product", "New Description", 200.0);

        when(productRepository.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<Product> response = productController.updateProduct(id, updatedProduct);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}