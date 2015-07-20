package org.shop;

import org.shop.repository.UserRepository;
import org.shop.repository.factory.UserRepositoryFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

import java.util.HashMap;
import java.util.Map;

@Configurable
@ComponentScan
@ImportResource("Beans.xml")
public class SpringConfiguration {
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
