package by.rudko.oop.menu.view;

/**
 * Created by rudkodm on 9/7/15.
 */
public abstract class ConsoleView {
    protected abstract String getStringRepresentation();

    public void print() {
        String s = this.getStringRepresentation();
        System.out.print(s);
    }
}
