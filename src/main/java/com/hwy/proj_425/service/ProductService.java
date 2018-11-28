package com.hwy.proj_425.service;

import com.hwy.proj_425.entities.Product;
import com.hwy.proj_425.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
@Service
public class ProductService {
    @Autowired
    private ProductMapper productMapper;

    public List<Product> findAllProducts(){ return productMapper.findAllProducts();}
    public Product getProductById(int id) {return productMapper.getProductById(id);}

    public void createProduct(Product product){ productMapper.createProduct(product);}

    public void deleteProduct(int id) {productMapper.deleteProduct(id);}

    public void updateProduct(Product product){ productMapper.updateProduct(product);}

    public void save(Collection<Product> products) {
        for(Product product: products)
            productMapper.updateProduct(product);
    }
}
