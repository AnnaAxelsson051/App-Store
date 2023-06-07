package com.appstore.controller;

import com.appstore.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.appstore.service.ProductService;

@Controller
public class ProductController {

    //Class handles admin adding and updating products in the store

    @Autowired
    ProductService productService;

    @GetMapping("/newproduct")
    public String adminAddNewProduct(Model model){
        model.addAttribute("product", new Product());
        return "admin_add_product";
    }
    @PostMapping("/newproduct")
    public String adminAddNewProduct(@ModelAttribute Product product, Model model){
        productService.setProduct(product);
        model.addAttribute("addedProduct", productService.SaveProductIfNew(product));
        return "admin_add_product";
    }
}
