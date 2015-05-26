package by.rudko.gc;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class Application {
    static {
        BasicConfigurator.configure();
    }

    private static final Logger LOG = Logger.getLogger(Application.class);

    public static void main(String[] args) throws Exception {
        LOG.debug("Hello from app");
        Application.class.getMethod("method").

    }

    public void method(){

    }
}
