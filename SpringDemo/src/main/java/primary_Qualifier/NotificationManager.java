package primary_Qualifier;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class NotificationManager {
    public static void  main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("Qualifierconfig");




    }
}
