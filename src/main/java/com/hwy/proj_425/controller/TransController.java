package com.hwy.proj_425.controller;

import com.hwy.proj_425.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/trans")
public class TransController {
    @Autowired
    TransactionService transactionService;
    @RequestMapping("/")
    public String showAllTrans(Model model)
    {
        model.addAttribute("transactions", transactionService.showAllTrans());
        return "trans";
    }
}
