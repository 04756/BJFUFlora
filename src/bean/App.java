package bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(GraphSearch.class);
        GraphSearch obj = (GraphSearch) context.getBean("graphSearchBean");

        obj.check();

    }
}
