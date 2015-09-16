package by.rudko.oop.model;

import by.rudko.oop.model.duck.AbstractDuck;

/**
 * Created by rudkodm on 9/7/15.
 */
public class ApplicationContext {

    private AbstractDuck duck;

    public AbstractDuck getDuck() {
        return duck;
    }

    public void setDuck(AbstractDuck duck) {
        this.duck = duck;
    }
}
