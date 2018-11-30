package com.hwy.proj_425.service;

import com.hwy.proj_425.entities.Customer;
import com.hwy.proj_425.entities.Product;
import com.hwy.proj_425.entities.Transaction;
import com.hwy.proj_425.entities.User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface TransactionService {
    void createAndSave(Map<Product, Integer> products, Customer customer, boolean type);
    List<Transaction> getTransaction(Map<Product, Integer> products, Customer customer, boolean type);
    void save(Collection<Transaction> trans);
    List<Transaction> showAllTrans();
}
