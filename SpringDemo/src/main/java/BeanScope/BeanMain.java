package BeanScope;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanMain {
    public static void main(String[]args){
        AnnotationConfigApplicationContext context= new AnnotationConfigApplicationContext(BeanConfig.class);

        System.out.println("Singelton Scope ");
        SingletonBean s1=  context.getBean(SingletonBean.class);
        SingletonBean s2=  context.getBean(SingletonBean.class);
        System.out.println(s1);
        System.out.println(s2);

        System.out.println( " Same instance " + (s1==s2));

        System.out.println("Prototype Scope ");
        PrototypeBean p1=  context.getBean(PrototypeBean.class);
        PrototypeBean p2 =  context.getBean(PrototypeBean.class);

        System.out.println( "p1 == p2 " + (p1== p2));




    }
}
