package Annotation;

import org.springframework.stereotype.Component;

import java.security.PublicKey;

@Component
public class EmailService {

    public void sent(){
        System.out.println(" Email is  sent !!");
    }
}
