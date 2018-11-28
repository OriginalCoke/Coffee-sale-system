package com.hwy.proj_425.controller;

import com.hwy.proj_425.entities.Product;
import com.hwy.proj_425.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping( value ="/products", method = RequestMethod.GET)
    public String listAllProducts(Model model)
    {
        model.addAttribute("products", productService.findAllProducts());
        return "products";
    }

    @RequestMapping(value = "product/{id}")
    public String getProductById(@PathVariable Integer id, Model model)
    {
        model.addAttribute("product", productService.getProductById(id));
        return "productDetail";
    }

    @RequestMapping(value = "product/new")
    public String newProduct(Model model)
    {
        model.addAttribute("product", new Product());
        return "productForm";
    }
    @RequestMapping(value = "product", method = RequestMethod.POST)
    public String createProduct(Product product)
    {
        productService.createProduct(product);
        return "redirect:/product/" + product.getId();
    }

    @RequestMapping(value = "product/delete/{id}")
    public String deleteProduct(@PathVariable Integer id)
    {
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}
