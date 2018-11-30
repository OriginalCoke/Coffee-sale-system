package com.hwy.proj_425.controller;

import com.hwy.proj_425.entities.Transaction;
import com.hwy.proj_425.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/trans")
public class TransController {
    @Autowired
    TransactionService transactionService;
    @RequestMapping("/")
    public String showAllTrans(Model model)
    {
        model.addAttribute("transactions", transactionService.findAllTrans());

        model.addAttribute("transInMonth", transactionService.calTotal(1));
        model.addAttribute("transInThreeMonth", transactionService.calTotal(3));
        model.addAttribute("transInYear", transactionService.calTotal(12));

        model.addAttribute("countInMonth", transactionService.calCount(1));
        model.addAttribute("countInThreeMonth", transactionService.calCount(3));
        model.addAttribute("countInYear", transactionService.calCount(12));
        return "trans";
    }
}
