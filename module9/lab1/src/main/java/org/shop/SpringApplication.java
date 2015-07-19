package org.shop;

import java.util.HashMap;
import java.util.Map;

import org.shop.repository.UserRepository;
import org.shop.repository.factory.UserRepositoryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@Configurable
@ComponentScan
public class SpringApplication {
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