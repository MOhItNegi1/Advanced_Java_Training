package CaseStudy1;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class PaymentProcessor {

    private PaymentService credit;
    private PaymentService upi;



    @Autowired
    public PaymentProcessor( PaymentService credit, @Qualifier("upiPayment") PaymentService upi) {
        this.credit = credit;
        this.upi=upi;
    }
    @Autowired
    private  TransactionLogger logger;

    public void makePayment(double amount) {
        logger.process(amount);

    }




}
