package com.hwy.proj_425.service;


import com.hwy.proj_425.entities.Customer;
import com.hwy.proj_425.exception.DuplicateIdException;
import com.hwy.proj_425.exception.pointException;
import com.hwy.proj_425.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    public List<Customer> findAllCus() {
        return customerMapper.findAllCus();
    }

    public Customer getCusById(Integer id) {
        return customerMapper.getCusById(id);
    }

    public void createCus(Customer customer) throws DuplicateIdException, pointException, TypeNotPresentException {
        String temp = customer.getFakeTot();
        boolean flag = false;
        for (int i = 0; i < temp.length(); i++) {
            if (temp.charAt(i) > '9' || temp.charAt(i) < '0') {
                flag = true;
                break;
//                throw new TypeNotPresentException();
            }
        }
        if(flag) throw new TypeNotPresentException();
        if (customer.getAvaPoint() < 0 || customer.getTotPoint() < 0)
            throw new pointException();
        if (getCusById(customer.getId()) != null)
            throw new DuplicateIdException();

        customerMapper.createCus(customer);
    }

    public void deleteCus(Integer id) {
        customerMapper.deleteCus(id);
    }

    public void updateCustomer(Customer customer) {

        customerMapper.updateCus(customer);
    }
  /*  public void editCus(Customer customer){
        customerMapper.editCus(customer);
    }*/
}
