package org.example;

public class MessageService {

    private EmailService emailservice;

    public void setEmailservice(EmailService emailservice) {
        this.emailservice = emailservice;
    }

    public void sendMessage(){
        System.out.println(" Message sent!!");
        emailservice.sent();
    }
}
