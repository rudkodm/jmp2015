package by.rudko.jms;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;


@SpringBootApplication
public class JmsPublisherApplication {
    private static final String QUEUE_NAME = "Messages";
    
    private static final Logger LOG = LoggerFactory.getLogger(JmsPublisherApplication.class);
    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
   
    public static void main(String[] args) throws Exception {
        ApplicationContext context = SpringApplication.run(JmsPublisherApplication.class, args);

        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
        LOG.info("Sending write a message:");
        
        for(;;){
            jmsTemplate.send(QUEUE_NAME, message(in.readLine()));
        }
    }

    public static MessageCreator message(String str) {
        return new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(str);
            }
        };
    }
}
