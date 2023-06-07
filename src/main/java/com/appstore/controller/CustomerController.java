package com.appstore.controller;

import com.appstore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.SessionScope;
import com.appstore.service.ProductService;

@Controller
@SessionScope
public class CustomerController {

    // Class handels customer log in and actions withtaken by the customer in the store
    // like filtering products by category, searching a product, adding a product
    // to cart and modifying content quantity in cart as well as checking out

    @Autowired
    CustomerService customerService;
    @Autowired
    ProductService productService;


    @GetMapping("/customerauth")
    public String authenticateCustomer() {
        return "customer_auth";
    }

    @PostMapping("/shop")
    public String displayShopToCustomer(@RequestParam String username,
                                        @RequestParam String password,
                                        Model model) {

        model.addAttribute("loggedCustomer", customerService.getAuthenticatedCustomer(username, password));
        model.addAttribute("products", productService.getProducts(customerService.getSelectedCategory()));
        model.addAttribute("categories", productService.getCategories());
        model.addAttribute("selectedCategory", customerService.getSelectedCategory());
        return "shop";
    }


    @GetMapping(value = "/shop")
    public String filterProductsByCategory(@RequestParam(name = "selectedCategory") String category,
                                           Model model) {
        model.addAttribute("selectedCategory", customerService.setAndGetCategory(category));
        model.addAttribute("products", productService.getProducts(customerService.getSelectedCategory()));
        model.addAttribute("categories", productService.getCategories());
        model.addAttribute("cartProductList", customerService.getCart());
        model.addAttribute("cartsum", customerService.calculateCartValue());
        return "shop";
    }

    @GetMapping("/search")
    public String filterProductByProductName(@RequestParam(name = "searchProductName") String queryName,
                                             Model model) {
        model.addAttribute("selectedCategory", customerService.getSelectedCategory());
        model.addAttribute("products", productService.getProductByName(queryName));
        model.addAttribute("categories", productService.getCategories());
        model.addAttribute("cartProductList", customerService.getCart());
        model.addAttribute("cartsum", customerService.calculateCartValue());

        return "shop";
    }

    @PostMapping("/addproduct")
    public String addProductToCart(@RequestParam Long productId,
                                   @RequestParam String productName,
                                   @RequestParam String price,
                                   @RequestParam String quantity,
                                   Model model) {
        customerService.addProductToCart(
                productId,
                productName,
                price,
                quantity
        );
        model.addAttribute("products", productService.getProducts(customerService.getSelectedCategory()));
        model.addAttribute("categories", productService.getCategories());
        model.addAttribute("cartProductList", customerService.getCart());
        model.addAttribute("selectedCategory", customerService.selectAll());
        model.addAttribute("cartsum", customerService.calculateCartValue());
        return "shop";
    }


    @PostMapping("/deleteproduct")
    public String deleteProductInCart(@RequestParam int indexToRemove,
                                      Model model) {
        model.addAttribute("products", productService.getProducts(customerService.getSelectedCategory()));
        model.addAttribute("categories", productService.getCategories());
        model.addAttribute("cartProductList", customerService.removeItemInCart(indexToRemove));
        model.addAttribute("selectedCategory", customerService.getSelectedCategory());
        model.addAttribute("cartsum", customerService.calculateCartValue());
        return "shop";
    }


    @PostMapping("/decreasequantity")
    public String decreaseProductQuantityInCart(@RequestParam int indexToModify,
                                                Model model) {
        model.addAttribute("products", productService.getProducts(customerService.getSelectedCategory()));
        model.addAttribute("categories", productService.getCategories());
        model.addAttribute("cartProductList", customerService.decreaseQuantityInCart(indexToModify));
        model.addAttribute("selectedCategory", customerService.getSelectedCategory());
        model.addAttribute("cartsum", customerService.calculateCartValue());
        return "shop";
    }

    @PostMapping("/increasequantity")
    public String increaseProductQuantityInCart(@RequestParam int indexToModify,
                                                Model model) {
        model.addAttribute("products", productService.getProducts(customerService.getSelectedCategory()));
        model.addAttribute("categories", productService.getCategories());
        model.addAttribute("cartProductList", customerService.increaseQuantityInCart(indexToModify));
        model.addAttribute("selectedCategory", customerService.getSelectedCategory());
        model.addAttribute("cartsum", customerService.calculateCartValue());
        return "shop";
    }
    
    @GetMapping("/checkout")
    public String checkoutCart(Model model) {
        model.addAttribute("cart", customerService.createOrder());
        model.addAttribute("products", productService.getProducts(customerService.getSelectedCategory()));
        model.addAttribute("categories", productService.getCategories());
        model.addAttribute("selectedCategory", customerService.getSelectedCategory());
        model.addAttribute("cartsum", customerService.calculateCartValue());
        return "checkout";
    }

}