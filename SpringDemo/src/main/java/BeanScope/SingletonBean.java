package BeanScope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class SingletonBean {

public SingletonBean(){
    System.out.println(" Singleton scope ");
}

}
