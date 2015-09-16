package by.rudko.oop.menu.control.command;

/**
 * Created by rudkodm on 9/7/15.
 */
public interface Command<T extends Object> {
    void apply(T commandContext);
}
