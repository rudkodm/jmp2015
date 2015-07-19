package org.shop;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * The ShopLauncher class.
 */
public class ShopLauncher {
    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
//        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringApplication.class);
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("Beans.xml");
        ctx.getBean(SpringApplication.class).run(args);
        ctx.close();
    }

}
