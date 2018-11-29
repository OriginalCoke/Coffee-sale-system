package com.hwy.proj_425.service;

import com.hwy.proj_425.entities.Customer;
import com.hwy.proj_425.entities.Product;
import com.hwy.proj_425.entities.User;
import com.hwy.proj_425.exception.NotEnoughPointException;
import com.hwy.proj_425.exception.NotEnoughProductsInStockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class SaleServiceImpl implements SaleService{
    @Autowired
    ProductService productService;
    @Autowired
    CustomerService customerService;
    @Autowired
    private TransactionService transService;
    //Mock user;
    private final User user = new User();
    Customer customer;
    Map<Product, Integer> productInSale = new HashMap<>();
    @Override
    public void setCustomerById(Integer id)
    {
        this.customer = customerService.getCusById(id);
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
        customer.setAvaPoint(customer.getAvaPoint() + total.intValue());
        customerService.updateCustomer(customer);
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
    public void checkout() throws NotEnoughProductsInStockException
    {

        for(Map.Entry<Product, Integer> entity : productInSale.entrySet())
        {
            Product product = entity.getKey();
            if(product.getCount() < entity.getValue())
                throw new NotEnoughProductsInStockException(product);

            entity.getKey().setCount(product.getCount() - entity.getValue());

        }
        changePoint(customer);
        // create transaction

        transService.createAndSave(productInSale, customer, true);

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

    public void freeSale() throws NotEnoughPointException, NotEnoughProductsInStockException
    {
        if(customer.getAvaPoint() < 25)
            throw new NotEnoughPointException();
        if(productInSale.size() == 1)
        {
            Product free = productInSale.keySet().iterator().next();
            if(free.getCount() < 1)
                throw new NotEnoughProductsInStockException(free);
            if(productInSale.get(free) == 1)
            {

                customer.setAvaPoint(customer.getAvaPoint() - 25);
                customerService.updateCustomer(customer);
                transService.createAndSave(productInSale, customer, false);
                productService.updateProduct(free);
                productInSale.clear();
            }
        }
    }


}
