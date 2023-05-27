package com.example.guessgamenew;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.anna.model.CartProduct;
import se.anna.service.CustomerService;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerServiceTests {

    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        customerService = new CustomerService();
    }

    //Add test for Deleting product

    @Test
    void testDecreaseQuantityInCartDecreasesQuantityByOneWhenDecreaseQUantityIsCalled() {
        CartProduct cartProduct = new CartProduct(1L, "Test Product 2", "210", "4");
        customerService.getCart().add(cartProduct);
        customerService.decreaseQuantityInCart(0);
        assertEquals("3", customerService.getCart().get(0).getQuantity());
    }

    @Test
    void testIncreaseQuantityInCartIncreasesQuantityByOneWhenIncreaseQUantityIsCalled() {
        CartProduct cartProduct = new CartProduct(1L, "Test Product 1", "200", "1");
        customerService.getCart().add(cartProduct);
        customerService.increaseQuantityInCart(0);
        assertEquals("2", customerService.getCart().get(0).getQuantity());
    }
}
