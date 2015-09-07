package by.rudko.oop.model.duck;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by rudkodm on 9/5/15.
 */
public class DuckyDuck extends AbstractDuck {

    private static final Logger LOG = LogManager.getLogger(DuckyDuck.class);

    public DuckyDuck(int capacity) {
        this.energyCapacity = capacity;
        this.energyRemaining = capacity;
    }

    public void drink() {
        LOG.info("DuckyDuck is drinking");
    }

    public void eat() {
        LOG.info("DuckyDuck is eating");
    }

    @Override
    public void fly() {
        state = DuckState.FLYING;
        doEnergyRequiredAction();
        LOG.info("DuckyDuck is flying");
    }

    @Override
    protected void recreateEnergy() {
        if (state == DuckState.SWIMING) {
            drink();
        } else {
            eat();
        }
    }
}
