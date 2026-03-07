package Annotation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;

@Component
public class MessageService {

    @Autowired
    private EmailService emailService;
    private int mailID;

    @Autowired
    public MessageService(EmailService emailService) {
        this.emailService = emailService;
    }


    public MessageService(){}

    public MessageService(EmailService emailService, int mailID) {
        this.emailService = emailService;
        this.mailID = mailID;
    }

    public void SendMessage(int mailID){
        System.out.println("Message sent ");
        emailService.sent();
        System.out.println(mailID);
    }
}
