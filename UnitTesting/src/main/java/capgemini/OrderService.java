package capgemini;

public class OrderService {

    PaymentService paymentService;

    public String placeOrder(double amount) {

        boolean status = paymentService.processPayment(amount);

        if (status) {
            return "ORDER PLACED";
        } else {
            return "PAYMENT FAILED";
        }
    }
}
