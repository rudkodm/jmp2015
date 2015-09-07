package by.rudko.oop.menu.control;

import by.rudko.oop.menu.state.ApplicationState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rudkodm on 9/7/15.
 */
public class CommandFactorySelector {
    private static final Logger LOG = LogManager.getLogger(CommandFactorySelector.class);

    private static final CommandFactory DEFAULT_FACTORY = new MainMenuCommandFactory();
    private static final Map<ApplicationState, CommandFactory> CONFIGURATION = new HashMap<>();

    public CommandFactorySelector() {
        CONFIGURATION.put(null, DEFAULT_FACTORY);
        CONFIGURATION.put(ApplicationState.MAIN_MENU_STATE, DEFAULT_FACTORY);
        CONFIGURATION.put(ApplicationState.DUCK_CHOSEN_STATE, new DuckControlCommandFactory());
    }

    public CommandFactory getFactory(ApplicationState state) {
        CommandFactory factory = CONFIGURATION.get(state);
        if (factory != null) {
            return factory;
        } else {
            LOG.warn("No factory for state: {}", state);
            return DEFAULT_FACTORY;
        }
    }
}
