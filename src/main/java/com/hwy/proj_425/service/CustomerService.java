package com.hwy.proj_425.service;


import com.hwy.proj_425.entities.Customer;
import com.hwy.proj_425.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    public List<Customer> findAllCus(){
        return customerMapper.findAllCus();
    }

    public Customer getCusById(String id){
        return customerMapper.getCusById(id);
    }

    public void createCus(Customer customer){
        customerMapper.createCus(customer);
    }

    public void deleteCus(String id){
        customerMapper.deleteCus(id);
    }

    public void updateCustomer(Customer customer){customerMapper.updateCus(customer);}
}
