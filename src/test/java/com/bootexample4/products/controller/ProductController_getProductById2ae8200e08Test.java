import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.bootexample4.products.model.Product;
import com.bootexample4.products.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
public class ProductController_getProductById2ae8200e08Test {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void getProductById_whenProductExists_thenReturnProduct() throws Exception {
        // given
        Product product = new Product();
        product.setId(1L);
        product.setName("Product 1");
        product.setPrice(100.0);

        when(productRepository.findById(1L)).thenReturn(java.util.Optional.of(product));

        // when
        ResponseEntity<Product> response = mockMvc.perform(MockMvcRequestBuilders.get("/products/1"))
                .andReturn().getResponse().getEntity();

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
    }

    @Test
    public void getProductById_whenProductDoesNotExist_thenReturnNotFound() throws Exception {
        // given
        when(productRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        // when
        ResponseEntity<Product> response = mockMvc.perform(MockMvcRequestBuilders.get("/products/1"))
                .andReturn().getResponse().getEntity();

        // then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}