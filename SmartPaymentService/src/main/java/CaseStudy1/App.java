package CaseStudy1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[]args){
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext( AppConfig.class);

        PaymentProcessor p1= context.getBean(PaymentProcessor.class);
        p1.makePayment(1000);

        context.close();

    }

}
