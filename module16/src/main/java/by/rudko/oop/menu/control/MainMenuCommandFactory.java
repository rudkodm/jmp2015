package by.rudko.oop.menu.control;

import by.rudko.oop.menu.control.command.Command;
import by.rudko.oop.menu.control.command.MainMenuCommandType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by rudkodm on 9/7/15.
 */
public class MainMenuCommandFactory implements CommandFactory {

    private static final Logger LOG = LogManager.getLogger(MainMenuCommandFactory.class);


    @Override
    public Command getCommand(String str) {
        return MainMenuCommandType.toEnum(str);
    }
}
