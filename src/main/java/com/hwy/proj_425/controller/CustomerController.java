package com.hwy.proj_425.controller;

import com.hwy.proj_425.entities.Customer;
import com.hwy.proj_425.exception.DuplicateIdException;
import com.hwy.proj_425.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    private String errormsg;
    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public String findAllCus(Model model) {
        model.addAttribute("customers", customerService.findAllCus());
        return "customers";
    }

    @RequestMapping("customer/{id}")
    public String findCusById(@PathVariable Integer id, Model model) {
        model.addAttribute("customer", customerService.getCusById(id));
        return "cusDetail";
    }

    @RequestMapping("customer/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("customer", customerService.getCusById(id));
        customerService.deleteCus(id);
        return "cusForm";
    }

    @RequestMapping("customer/new")
    public String newCus(Model model) {
        model.addAttribute("customer", new Customer());
        return "cusForm";
    }

    @RequestMapping(value = "customer", method = RequestMethod.POST)
    public String createCus(Customer customer, Model model) {
        try{
            customerService.createCus(customer);
        }catch (Exception e)
        {
            model.addAttribute("errorMsg", e.getMessage());
            return "cusForm";
        }
        return "redirect:/customer/" + customer.getId();
    }

    @RequestMapping("customer/delete/{id}")
    public String deleteCus(@PathVariable Integer id) {
        customerService.deleteCus(id);
        return "redirect:/customers";
    }
}
