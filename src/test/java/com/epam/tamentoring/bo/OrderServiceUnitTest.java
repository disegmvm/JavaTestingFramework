package com.epam.tamentoring.bo;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class OrderServiceUnitTest {
    @Mock
    private DiscountUtility discountUtility;

    @InjectMocks
    private OrderService orderService;

    private UserAccount user;
    private ShoppingCart shoppingCart;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        shoppingCart = new ShoppingCart(new ArrayList<>());
        user = new UserAccount();
        user.setName("John Smith");

        LocalDate dob = LocalDate.of(1990, 10, 10);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        user.setDateOfBirth(dob.format(formatter));
        user.setShoppingCart(shoppingCart);

        when(discountUtility.calculateDiscount(user)).thenReturn(3.0);
    }

    @Test
    public void testGetOrderPrice() {
        double discount = orderService.getOrderPrice(user);
        assertEquals(-3.0, discount, 0.0);

        verify(discountUtility, times(1)).calculateDiscount(user);
        verifyNoMoreInteractions(discountUtility);
    }
}
