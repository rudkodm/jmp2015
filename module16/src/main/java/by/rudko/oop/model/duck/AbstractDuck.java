package by.rudko.oop.model.duck;

import by.rudko.oop.model.context.WalkDirection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by rudkodm on 9/5/15.
 */
public abstract class AbstractDuck {
    private static final Logger LOG = LogManager.getLogger(AbstractDuck.class);
    protected int energyCapacity;
    protected int energyRemaining;
    protected DuckState state = DuckState.WALKING;
    private String clazzName = this.getClass().getSimpleName();

    public void walk(WalkDirection direction) {
        state = DuckState.WALKING;
        doEnergyRequiredAction();
        LOG.info("{} is walking {}", clazzName, direction);
    }

    public void swim() {
        state = DuckState.SWIMING;
        doEnergyRequiredAction();
        LOG.info("{} is swimming", clazzName);
    }

    public void quack() {
        LOG.info("{} is quacks", clazzName);
    }

    protected final void doEnergyRequiredAction() {
        energyRemaining--;
        if (energyRemaining == 0) {
            recreateEnergy();
            energyRemaining = energyCapacity;
        }
    }

    ;

    public abstract void fly();

    protected abstract void recreateEnergy();

}
