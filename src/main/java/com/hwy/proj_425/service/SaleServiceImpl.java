package com.hwy.proj_425.service;

import com.hwy.proj_425.entities.Customer;
import com.hwy.proj_425.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class SaleServiceImpl implements SaleService{
    @Autowired
    ProductService productService;
    @Autowired
    CustomerService customerService;

    Customer customer;
    Map<Product, Integer> productInSale = new HashMap<>();
    @Override
    public void setCustomerById(Integer id)
    {
        this.customer = customerService.getCusById(id.toString());
    }

    @Override
    public Customer getCustomer()
    {
        return this.customer;
    }
    @Override
    public Customer changePoint(Customer customer)
    {
        BigDecimal total = getTotal();
        customer.setTotPoint(customer.getTotPoint() + total.intValue());
        return customer;
    }
    @Override
    public boolean alert(Customer customer){
       if(customer.getTotPoint() >= 25)
           return true;
       else
           return false;
    }

    //
    @Override
    public void addProduct(Product product)
    {
        productInSale.put(product, productInSale.getOrDefault(product, 0) +1);
    }
    @Override
    public void removeProduct(Product product)
    {
        if(productInSale.containsKey(product))
        {
            if(productInSale.get(product) > 1)
                productInSale.replace(product, productInSale.get(product) -1);
            else if(productInSale.get(product) == 1)
                productInSale.remove(product);
        }
    }
    @Override
    public Map<Product, Integer> getAllProductsInSale()
    {
        return this.productInSale;
    }
    @Override
    public void checkout() throws Exception
    {
        changePoint(customer);
        for(Map.Entry<Product, Integer> entity : productInSale.entrySet())
        {
            Product product = entity.getKey();
            if(product.getCount() < entity.getValue())
                throw new Exception("error");

            entity.getKey().setCount(product.getCount() - entity.getValue());

        }

        // save product with product service
        productService.save(productInSale.keySet());

        productInSale.clear();
    }
    @Override
    public BigDecimal getTotal(){
        return productInSale.entrySet().stream()
                .map(entry -> entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    public boolean makeSelect(boolean result)
    {
        return result;
    }


}
