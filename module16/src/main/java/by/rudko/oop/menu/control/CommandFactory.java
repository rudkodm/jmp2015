package by.rudko.oop.menu.control;

import by.rudko.oop.menu.control.command.Command;

/**
 * Created by rudkodm on 9/7/15.
 */
public interface CommandFactory {
    Command getCommand(String str);
}
