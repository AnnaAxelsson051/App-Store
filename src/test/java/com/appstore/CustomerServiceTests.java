package com.appstore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.appstore.model.CartProduct;
import com.appstore.service.CustomerService;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerServiceTests {

    private CustomerService customerService;


    @BeforeEach
    void setUp() {
        customerService = new CustomerService();
    }

    @Test
    void testDeleteItemInCart() {
        CartProduct cartProduct = new CartProduct(1L, "Test Product 2", "210", "4");
        customerService.getCart().clear();
        customerService.getCart().add(cartProduct);
        customerService.removeItemInCart(0);
        assertEquals(0, customerService.getCart().size());
    }
    @Test
    void testDecreaseQuantityInCart() {
        CartProduct cartProduct = new CartProduct(1L, "Test Product 2", "210", "4");
        customerService.getCart().add(cartProduct);
        customerService.decreaseQuantityInCart(0);
        assertEquals("3", customerService.getCart().get(0).getQuantity());
    }

    @Test
    void testIncreaseQuantityInCart() {
        CartProduct cartProduct = new CartProduct(1L, "Test Product 1", "200", "1");
        customerService.getCart().add(cartProduct);
        customerService.increaseQuantityInCart(0);
        assertEquals("2", customerService.getCart().get(0).getQuantity());
    }
}
