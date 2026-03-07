package primary_Qualifier;


import org.springframework.stereotype.Component;

@Component
public class EmailNotification implements  NotificationService{


    @Override
    public void sendmsg(String msg) {
        System.out.println("Email "+ msg);
    }
}



