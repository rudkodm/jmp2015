package by.rudko.oop.model.duck;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by rudkodm on 9/5/15.
 */
public class ToyDuck extends AbstractDuck{
    private static final Logger LOG = LogManager.getLogger(ToyDuck.class);

    @Override
    public void fly() {
        LOG.info("ToyDuck is flapping its wings");
    }
}
