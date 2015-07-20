package org.shop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringManagedBeansPrinter implements ApplicationContextAware{
    
    private final Logger LOG = LogManager.getLogger(SpringManagedBeansPrinter.class);
    
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        for (String beanName : applicationContext.getBeanDefinitionNames()) {
            Class<?> beanClass =  applicationContext.getBean(beanName).getClass();
            LOG.info("Bean name: {};  Bean class: {};", beanName, beanClass.getSimpleName());
        }
    }



}
