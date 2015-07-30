package by.rudko.jms;

import java.util.logging.Logger;

import javax.ejb.Singleton;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

@Singleton
public class LogProducer {
 
   @Produces
   public Logger producer(InjectionPoint ip) {
      return Logger.getLogger(ip.getMember().getDeclaringClass().getName());
   }
}
