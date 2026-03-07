package CaseStudy1;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class TransactionLogger {


    private PaymentService creditcard;
    private PaymentService upiPayment;

    @Autowired
    public TransactionLogger(PaymentService creditcard, @Qualifier("upiPayment") PaymentService upiPayment){
        this.creditcard=creditcard;
        this.upiPayment=upiPayment;
    }

    @PostConstruct
    public void init(){
        System.out.println("Logger Initialized");
    }


    public void process(double amount) {
        creditcard.processPayment(amount);
        upiPayment.processPayment(amount);
    }

    @PreDestroy
    public void destroy(){
        System.out.println("Logger destroyed");
    }
}
