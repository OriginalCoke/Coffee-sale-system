package com.hwy.proj_425.service;

import com.hwy.proj_425.entities.Customer;
import com.hwy.proj_425.entities.Product;
import com.hwy.proj_425.entities.Transaction;
import com.hwy.proj_425.entities.User;
import com.hwy.proj_425.mapper.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class TransactionServiceImpl implements TransactionService{
    @Autowired
    private TransactionMapper transMapper;
    @Override
    public void createAndSave(Map<Product, Integer> products, Customer customer, boolean type)
    {
        save(getTransaction(products, customer, type));
    }
    @Override
    public List<Transaction> getTransaction(Map<Product, Integer> products, Customer customer, boolean type)
    {
        Date createTime = new Date();
        List<Transaction> res = new ArrayList<>();
        for(Product product : products.keySet())
        {
            Transaction transaction = new Transaction();
            transaction.setCustomerId(customer.getId());
            transaction.setCustomer(customer);

            transaction.setProductId(product.getId());
            transaction.setProduct(product);
            transaction.setCount(products.get(product));
            if(type == true)
            transaction.setTotal(product.getPrice().multiply(BigDecimal.valueOf(products.get(product))));
            else
                transaction.setTotal(BigDecimal.valueOf(0));

            transaction.setTime(createTime);
            res.add(transaction);
        }
        return res;
    }
    @Override
    public void save(Collection<Transaction> trans)
    {
        for(Transaction tran : trans)
        {
            transMapper.createTrans(tran);
        }
    }

    @Override
    public List<Transaction> showAllTrans()
    {
        return transMapper.selectAllTrans();
    }
}
