package by.rudko.oop.menu.control;

import by.rudko.oop.menu.control.command.Command;
import by.rudko.oop.menu.control.command.DuckControlCommandType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by rudkodm on 9/7/15.
 */
public class DuckControlCommandFactory implements CommandFactory {

    private static final Logger LOG = LogManager.getLogger(DuckControlCommandFactory.class);


    @Override
    public Command getCommand(String str) {
        return DuckControlCommandType.toEnum(str);
    }
}
