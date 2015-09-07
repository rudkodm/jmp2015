package by.rudko.oop.menu.view;

import by.rudko.oop.menu.state.ApplicationState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rudkodm on 9/7/15.
 */
public class MenuViewSelector {
    private static final Logger LOG = LogManager.getLogger(MenuViewSelector.class);

    private static final ConsoleView DEFAULT_VIEW = new DefaultView();
    private static final Map<ApplicationState, ConsoleView> CONFIGURATION = new HashMap<>();

    public MenuViewSelector() {
        CONFIGURATION.put(null, DEFAULT_VIEW);
        CONFIGURATION.put(ApplicationState.MAIN_MENU_STATE, new MainMenuView());
        CONFIGURATION.put(ApplicationState.DUCK_CHOSEN_STATE, new DuckControlMenuView());
    }

    public ConsoleView getView(ApplicationState state){
        ConsoleView view = CONFIGURATION.get(state);
        if(view != null) {
            return view;
        } else {
            LOG.warn("No view for state: {}", state);
            return DEFAULT_VIEW;
        }
    }
}
