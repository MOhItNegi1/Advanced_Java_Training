package org.example;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    PaymentGateway paymentGateway;

    @InjectMocks
    OrderService orderService;


    @Test
    void testSuccess() {

        when(paymentGateway.processPayment(2000)).thenReturn(true);

        String result = orderService.placeOrder(2000);

        assertEquals("Order Confirmed", result);

        verify(paymentGateway).processPayment(2000);
    }


    @Test
    void testFailure() {

        when(paymentGateway.processPayment(1500)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> {
            orderService.placeOrder(1500);
        });

        verify(paymentGateway).processPayment(1500);
    }


    @Test
    void testInvalidAmount() {

        assertThrows(IllegalArgumentException.class, () -> {
            orderService.placeOrder(0);
        });

        verify(paymentGateway, never()).processPayment(anyDouble());
    }
}