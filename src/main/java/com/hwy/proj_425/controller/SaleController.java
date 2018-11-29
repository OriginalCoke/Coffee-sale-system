package com.hwy.proj_425.controller;

import com.hwy.proj_425.entities.Customer;
import com.hwy.proj_425.entities.Product;
import com.hwy.proj_425.service.ProductService;
import com.hwy.proj_425.service.SaleService;
import com.hwy.proj_425.service.SaleServiceImpl;
import com.hwy.proj_425.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/sale")
public class SaleController {
    @Autowired
    private SaleServiceImpl saleService;
    @Autowired
    private ProductService productService;

    @RequestMapping("/customer/{customerId}")
    public String Sale(Model model, @PathVariable("customerId") Integer id)
    {
        saleService.setCustomerById(id);

        model.addAttribute("customer", saleService.getCustomer());
        model.addAttribute("products", productService.findAllProducts());
        model.addAttribute("productsInSale", saleService.getAllProductsInSale());
        model.addAttribute("total", saleService.getTotal().toString());
        return "salePage";

    }

    @RequestMapping("/addProduct/{productId}")
    public String addProduct(Model model, @PathVariable("productId") Integer id)
    {
        Product product = productService.getProductById(id);
        saleService.addProduct(product);
     //   model.addAttribute("productsInSale", saleService.getAllProductsInSale());
        return "redirect:/sale/customer/" + saleService.getCustomer().getId();
    }

    @RequestMapping("/removeProduct/{productId}")
    public String removeProduct(Model model,@PathVariable("productId") Integer id)
    {
        saleService.removeProduct(productService.getProductById(id));
       // model.addAttribute("productsInSale", saleService.getAllProductsInSale());
        return "redirect:/sale/customer/" + saleService.getCustomer().getId();
    }
    @RequestMapping("/checkout")
    public String checkout(Model model) throws Exception
    {
        saleService.checkout();
        return "redirect:/sale/customer/" + saleService.getCustomer().getId();
    }
}
