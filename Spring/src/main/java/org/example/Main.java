package org.example;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        MessageService messageService = context.getBean(MessageService.class);
        MessageService messageService1=context.getBean(MessageService.class);

        System.out.println(messageService1);
        System.out.println(messageService);




        System.out.println(messageService1==messageService);








        messageService.sendMessage();
        ((ClassPathXmlApplicationContext)context).close();


    }
}


