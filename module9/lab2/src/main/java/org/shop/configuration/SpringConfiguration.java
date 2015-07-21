package org.shop.configuration;

import org.shop.repository.UserRepository;
import org.shop.repository.factory.UserRepositoryFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@Configurable
@ImportResource("Beans.xml")
@ComponentScan("org.shop")
public class SpringConfiguration {
    
    @Bean
    public UserRepository userRepository(UserRepositoryFactory factory){
        return factory.createUserRepository();
    }
    
}
