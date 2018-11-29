package com.hwy.proj_425.service;

import com.hwy.proj_425.entities.Customer;
import com.hwy.proj_425.entities.Product;
import com.hwy.proj_425.exception.NotEnoughPointException;
import com.hwy.proj_425.exception.NotEnoughProductsInStockException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;


public interface SaleService {
    //set the user for sale
    void setCustomerById(Integer id);
    Customer getCustomer();
    Customer changePoint(Customer customer);
    boolean alert(Customer customer);

    //
    void addProduct(Product product);
    void removeProduct(Product product);
    Map<Product, Integer> getAllProductsInSale();
    void checkout() throws NotEnoughProductsInStockException;
    BigDecimal getTotal();
    public boolean makeSelect(boolean result);
    public void freeSale() throws NotEnoughPointException, NotEnoughProductsInStockException;











}
