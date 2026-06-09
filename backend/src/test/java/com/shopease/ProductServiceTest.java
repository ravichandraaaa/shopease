package com.shopease;

import com.shopease.model.Product;
import com.shopease.repository.ProductRepository;
import com.shopease.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void testGetAllProducts() {
        Product p1 = new Product(1L, "Laptop", "Gaming Laptop", 999.99, 10);
        Product p2 = new Product(2L, "Phone", "Smartphone", 499.99, 25);
        when(productRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

        List<Product> products = productService.getAllProducts();

        assertThat(products).hasSize(2);
        assertThat(products.get(0).getName()).isEqualTo("Laptop");
        assertThat(products.get(1).getName()).isEqualTo("Phone");
    }

    @Test
    public void testCreateProduct() {
        Product product = new Product(null, "Tablet", "iPad", 799.99, 5);
        Product saved = new Product(1L, "Tablet", "iPad", 799.99, 5);
        when(productRepository.save(product)).thenReturn(saved);

        Product result = productService.createProduct(product);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("Tablet");
    }

    @Test
    public void testGetProductById() {
        Product product = new Product(1L, "Laptop", "Gaming Laptop", 999.99, 10);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Optional<Product> result = productService.getProductById(1L);

        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo("Laptop");
    }

    @Test
    public void testDeleteProduct() {
        productService.deleteProduct(1L);
        verify(productRepository, times(1)).deleteById(1L);
    }
}
