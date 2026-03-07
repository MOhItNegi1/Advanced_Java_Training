package org.example;

public class EmailService {

    public void sent(){
        System.out.println("Through Email");
    }

    public EmailService(){
        System.out.println(" Constructor called ");
    }

    public void  init(){
        System.out.println(" init method called ");
    }
    public void  destroy(){
        System.out.println("Destroy method called");
    }

}

