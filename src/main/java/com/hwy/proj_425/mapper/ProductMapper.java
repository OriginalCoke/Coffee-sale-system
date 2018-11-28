package com.hwy.proj_425.mapper;

import com.hwy.proj_425.entities.Product;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface ProductMapper {
    List<Product> findAllProducts();

    Product getProductById(Integer id);
    void updateProduct(Product product);
    void createProduct(Product product);
    void deleteProduct(Integer id);
}
