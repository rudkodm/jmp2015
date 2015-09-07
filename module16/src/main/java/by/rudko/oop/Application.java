package by.rudko.oop;


import by.rudko.oop.menu.control.CommandFactorySelector;
import by.rudko.oop.menu.control.command.Command;
import by.rudko.oop.menu.state.ApplicationState;
import by.rudko.oop.menu.view.MenuViewSelector;
import by.rudko.oop.model.ApplicationContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Application {
    private static final Logger LOG = LogManager.getLogger(Application.class);

    public static void main(String[] args) throws Exception {

        ApplicationState state = ApplicationState.MAIN_MENU_STATE;
        MenuViewSelector viewSelector = new MenuViewSelector();
        CommandFactorySelector factorySelector = new CommandFactorySelector();
        ApplicationContext applicationContext = new ApplicationContext();

        while (true) {
            viewSelector.getView(state).print();

            String input = new Scanner(System.in).nextLine();
            Command command = factorySelector.getFactory(state).getCommand(input);
            command.apply(applicationContext);

            state = state.switchToNextState(command);

        }
    }


}
