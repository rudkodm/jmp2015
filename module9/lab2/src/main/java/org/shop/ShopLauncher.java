package org.shop;


import org.shop.repository.UserRepository;
import org.shop.repository.factory.UserRepositoryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

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
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Application.class);
        ctx.getBean(Application.class).run(args);
    }

}

@Configuration
@ComponentScan
class Application {
    @Autowired
    public  DataInitializer dataInitializer;

    public void run(String...args){
        System.out.println("Hello world");
    }

    @Bean
    public Map<Long, String> sellerNames(){
        Map<Long, String> map = new HashMap<Long, String>();
        map.put(1L,"Name1");
        return map;
    }

    @Bean
    public UserRepository userRepository(UserRepositoryFactory factory){
        return factory.createUserRepository();
    }
}
