package com.appstore.service;

import com.appstore.EmailServiceImpl;
import com.appstore.model.Customer;
import com.appstore.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import com.appstore.model.CartProduct;
import com.appstore.repository.CustomerRepository;
import com.appstore.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
@SessionScope
public class CustomerService {

    // Class handles authentication of customer, displaying categories and cart to customer
    // plus cart functionalities like increase, decrease, delete items in cart
    // as customer interacts with the store
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    EmailServiceImpl emailServiceImpl;

    Customer selectedCustomer;
    String selectedCategory = "all";

    public Customer getAuthenticatedCustomer(String username, String password) {
        if (customerRepository.existsByUsernameAndPassword(username, password)) {
            selectedCustomer = customerRepository.getCustomersByUsername(username);
        } else {
            selectedCustomer = customerRepository.save(new Customer(username, password));
        }
        return selectedCustomer;
    }

    public String getSelectedCategory() {
        return this.selectedCategory;
    }

    private List<CartProduct> cartProducts = new ArrayList<>();

    public List<CartProduct> getCart() {
        return this.cartProducts;
    }

    public String setAndGetCategory(String category) {
        this.selectedCategory = category;
        return this.selectedCategory;
    }

    public void addProductToCart(Long productId,
                                 String productName,
                                 String price,
                                 String quantity) {
        cartProducts.add(new CartProduct(
                productId,
                productName,
                price,
                quantity
        ));
    }

    public String selectAll() {
        this.selectedCategory = "all";
        return this.selectedCategory;
    }

    public List<CartProduct> decreaseQuantityInCart(int index) {
        this.cartProducts.get(index).decrQuantity();
        return this.cartProducts;
    }

    public List<CartProduct> increaseQuantityInCart(int index) {
        this.cartProducts.get(index).incrQuantity();
        return this.cartProducts;
    }

    public List<CartProduct> removeItemInCart(int index) {
        this.cartProducts.remove(index);
        return this.cartProducts;
    }

    public String calculateCartValue() {
        double sum = 0;
        for (CartProduct p : this.cartProducts) {
            p.setTotPrice(Double.valueOf(p.getQuantity()) * Double.valueOf(p.getPrice()));
            sum += Double.valueOf(p.getTotPrice());
        }
        String returnSum = String.valueOf(Math.floor(sum * 100) / 100);

        return returnSum;
    }
    
    public List<CartProduct> createOrder() {
        Orders orders = new Orders();
        orders.setCustomer_id(this.selectedCustomer.getId());
        orders.addOrderLine(this.cartProducts);
        orderRepository.save(orders);
        emailServiceImpl.sendSimpleMessage("reza.alimolaei51@gmail.com", "Order Confirmation", "Thanks for your order!" + orders);
        return this.cartProducts;
    }

}



