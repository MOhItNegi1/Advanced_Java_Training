package CaseStudy1;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class UpiPayment  implements PaymentService{


    @Override
    public void processPayment(double amount) {

        System.out.println("Processing Upi Payment :"+ amount);
    }
}
