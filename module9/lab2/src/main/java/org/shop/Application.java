package org.shop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class Application {
    
    private static final Logger LOG = LogManager.getLogger(Application.class);

    public void run(String...args){
        LOG.info("--> Running spring app");
    }

}
