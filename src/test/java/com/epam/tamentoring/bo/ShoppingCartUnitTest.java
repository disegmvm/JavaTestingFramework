package com.epam.tamentoring.bo;

import com.epam.tamentoring.exceptions.ProductNotFoundException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class ShoppingCartUnitTest {

    private ShoppingCart cart;
    private Product product1;
    private Product product2;

    @Before
    public void setUp() {
        cart = new ShoppingCart(new ArrayList<>());
        product1 = new Product(1, "Product1", 10.0, 2.0);
        product2 = new Product(2, "Product2", 15.0, 3.0);
    }

    @Test
    public void testAddNewProducts() {
        cart.addProductToCart(product1);
        assertTrue("The cart should contain the product", cart.getProductIds().contains(product1.getId()));
        assertEquals("The quantity of the product should match", 2.0, cart.getProductById(product1.getId()).getQuantity(), 0.0);
    }

    @Test
    public void testRemoveExistingProduct() {
        cart.addProductToCart(product1);
        cart.removeProductFromCart(product1);
        assertFalse("The cart should not contain the product after removal", cart.getProductIds().contains(product1.getId()));
    }

    @Test(expected = ProductNotFoundException.class)
    public void testRemoveNonExistingProductThrowsException() {
        Product nonExistingProduct = new Product(3, "Product3", 20.0, 1.0);
        cart.removeProductFromCart(nonExistingProduct);
    }

    @Test
    public void testGetCartTotalPrice() {
        cart.addProductToCart(product1);
        cart.addProductToCart(product2);
        double expectedTotalPrice = 20.0 + (15.0 * 3.0);
        assertEquals("The total price of the cart should be correctly calculated", expectedTotalPrice, cart.getCartTotalPrice(), 0.0);
    }

    @Test
    public void testGetProductById() {
        cart.addProductToCart(product1);
        Product retrievedProduct = cart.getProductById(1);
        assertEquals("Retrieved product should match the added product", product1, retrievedProduct);
    }

    @Test(expected = ProductNotFoundException.class)
    public void testGetProductByIdThrowsExceptionForInvalidId() {
        cart.getProductById(99);
    }
}
