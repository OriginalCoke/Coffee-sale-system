package com.hwy.proj_425.controller;

import com.hwy.proj_425.config.StartAndEnd;
import com.hwy.proj_425.entities.Transaction;
import com.hwy.proj_425.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/period")
    public String showForm(Model model)
    {
        model.addAttribute("startAndEnd", new StartAndEnd());
        return "periodTotal";
    }
    @PostMapping("/period")
    public String getByTime(Model model, StartAndEnd startAndEnd)
    {
        String start = startAndEnd.getStart();
        String end = startAndEnd.getEnd();
        System.out.println(start + " " + end);
        BigDecimal res = transactionService.getByTime(start, end);

        model.addAttribute("start", start);
        model.addAttribute("end", end);
        model.addAttribute("total", res);
        System.out.println(res.toString());


        return "periodDetail";
    }
}
