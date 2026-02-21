package org.example;

public class OrderService {

    private PaymentGateway paymentGateway;

    public OrderService(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public String placeOrder(double amount) {

        // Validate amount
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid Order Amount");
        }

        // Call external service
        boolean status = paymentGateway.processPayment(amount);

        // Check result
        if (!status) {
            throw new RuntimeException("Payment Failed");
        }

        return "Order Confirmed";
    }
}
