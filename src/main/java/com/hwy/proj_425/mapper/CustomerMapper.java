package com.hwy.proj_425.mapper;

import com.hwy.proj_425.entities.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CustomerMapper {

    List<Customer> findAllCus();

    Customer getCusById(String id);

    void createCus(Customer customer);

    void deleteCus(String id);

}
