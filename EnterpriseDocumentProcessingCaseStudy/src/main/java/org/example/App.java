package org.example;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {


        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        DocumentEngine engine = context.getBean(DocumentEngine.class);

        engine.run("sample.xml");

        context.close();
    }
}
