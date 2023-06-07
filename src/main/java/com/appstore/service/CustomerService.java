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

    //The class handles authentication of customers, displaying categories and cart to customer
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


    //If a customer exists return it otherwise create a new user
    public Customer getAuthenticatedCustomer(String username, String password) {
        if (customerRepository.existsByUsernameAndPassword(username, password)) {
            selectedCustomer = customerRepository.getCustomersByUsername(username);
        } else {
            selectedCustomer = customerRepository.save(new Customer(username, password));
        }
        return selectedCustomer;
    }

    //Getting the category that was selected
    public String getSelectedCategory() {
        return this.selectedCategory;
    }


    //List of products in cart
    private List<CartProduct> cartProducts = new ArrayList<>();


    //Returning a list of cartproducts for updating the cart
    public List<CartProduct> getCart() {
        return this.cartProducts;
    }

    //Showing products depending on selected category
    public String setAndGetCategory(String category) {
        this.selectedCategory = category;
        return this.selectedCategory;
    }

    //Adding product to cart
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

    //Decreasing, increasing and deleting cartproducts
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

    //Calculating quantity * price, rounding off number,
    //displaying cart content and price
    public String calculateCartValue() {
        double sum = 0;
        for (CartProduct p : this.cartProducts) {
            p.setTotPrice(Double.valueOf(p.getQuantity()) * Double.valueOf(p.getPrice()));
            sum += Double.valueOf(p.getTotPrice());
        }
        String returnSum = String.valueOf(Math.floor(sum * 100) / 100);

        return returnSum;
    }

    // Creating an order and sending an email to customer with order details

    public List<CartProduct> createOrder() {
        Orders orders = new Orders();
        orders.setCustomer_id(this.selectedCustomer.getId());
        orders.addOrderLine(this.cartProducts);
        orderRepository.save(orders);
        emailServiceImpl.sendSimpleMessage("reza.alimolaei51@gmail.com", "Order Confirmation", "Thanks for your order!" + orders);
        return this.cartProducts;
    }

}



