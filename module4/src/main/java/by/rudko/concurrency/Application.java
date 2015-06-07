package by.rudko.concurrency;


import by.rudko.concurrency.config.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static java.lang.System.out;

public class Application {
    private static Logger LOG  = LogManager.getLogger(Application.class);
    public static void main(String[] args) throws Exception {

        int conut = 0;
        while(calculate()){
            LOG.debug(conut++);
        }

        out.println(Configuration.TEST_DATA_PATH);
    }

    private static boolean calculate() throws InterruptedException {
        Thread.sleep(1000);
        return true;
    }
}
