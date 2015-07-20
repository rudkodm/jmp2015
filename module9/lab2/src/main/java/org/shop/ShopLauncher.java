package org.shop;


import org.shop.configuration.SpringConfiguration;
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
        try (
                AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfiguration.class);
                ClassPathXmlApplicationContext xmlConfCtx = new ClassPathXmlApplicationContext("Beans.xml")
        ) {
            ctx.getBean(Application.class).run(args);
        }

    }

}

