package by.rudko.oop.model.duck;

import by.rudko.oop.model.movements.WalkDirection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by rudkodm on 9/5/15.
 */
public abstract class AbstractDuck {
    private static final Logger LOG = LogManager.getLogger(AbstractDuck.class);
    private String clazzName = this.getClass().getSimpleName();

    public void  walk(WalkDirection direction) {
        LOG.info("{} is walking {}", clazzName, direction);
    };
    public void  swim() {
        LOG.info("{} is swimming", clazzName);
    };
    public void  quack() {
        LOG.info("{} is quacks", clazzName);
    };
    public abstract void  fly();
}
