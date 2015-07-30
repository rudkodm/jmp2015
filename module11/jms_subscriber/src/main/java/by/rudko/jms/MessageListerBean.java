package by.rudko.jms;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by rudkodm on 7/30/15.
 */
@MessageDriven(name = "MessageLogger", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/queue/Messages"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.xms.Queue")
})
public class MessageListerBean implements MessageListener{

    @Inject
    private Logger logger;

    @Override
    public void onMessage(Message message) {
        TextMessage msg = (TextMessage) message;
        try {
            logger.info(msg.getText());
        } catch (JMSException e) {
            logger.log(Level.WARNING, "Error during message processing", e);
        }
    }
}
