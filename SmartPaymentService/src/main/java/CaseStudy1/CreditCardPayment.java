package CaseStudy1;


import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class CreditCardPayment implements PaymentService{

    @Override
    public void processPayment(double amount) {
        System.out.println("Processing Credit Card Payment  :"+ amount);
    }
}
