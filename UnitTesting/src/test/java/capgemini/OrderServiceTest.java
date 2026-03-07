package capgemini;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.anyDouble;

public class OrderServiceTest {

    @Mock
    PaymentService paymentServiceMock;

    @InjectMocks
    OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        System.out.println("\n=== Setting up fresh mocks for next test ===");
    }

    @Test
    @DisplayName("Test successful order placement")
    void testPlaceOrder_Success() {

        when(paymentServiceMock.processPayment(500.0))
                .thenReturn(true);

        String result = orderService.placeOrder(500.0);

        assertEquals("ORDER PLACED", result);

        verify(paymentServiceMock).processPayment(500.0);
    }

    @Test
    @DisplayName("Test failed order placement")
    void testPlaceOrder_Failure() {

        when(paymentServiceMock.processPayment(300.0))
                .thenReturn(false);

        String result = orderService.placeOrder(300.0);

        assertEquals("PAYMENT FAILED", result);

        verify(paymentServiceMock).processPayment(300.0);
    }

    @Test
    @DisplayName("Test with any amount")
    void testPlaceOrder_AnyAmount() {

        when(paymentServiceMock.processPayment(anyDouble()))
                .thenReturn(true);

        String result = orderService.placeOrder(999.99);

        assertEquals("ORDER PLACED", result);

        verify(paymentServiceMock).processPayment(anyDouble());
    }

    @Test
    @DisplayName("Test when payment service throws exception")
    void testPlaceOrder_Exception() {

        when(paymentServiceMock.processPayment(anyDouble()))
                .thenThrow(new RuntimeException("Bank API down!"));

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> orderService.placeOrder(100.0)
        );

        assertEquals("Bank API down!", exception.getMessage());

        verify(paymentServiceMock).processPayment(anyDouble());
    }
}
