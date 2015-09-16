package by.rudko.oop.menu.state;

import by.rudko.oop.menu.control.command.Command;

import static by.rudko.oop.menu.control.command.DuckControlCommandType.BACK;
import static by.rudko.oop.menu.control.command.MainMenuCommandType.CREATE_REAL_DUCK;
import static by.rudko.oop.menu.control.command.MainMenuCommandType.CREATE_TOY_DUCK;


/**
 * Created by rudkodm on 9/7/15.
 */
public enum ApplicationState {

    MAIN_MENU_STATE {
        @Override
        public ApplicationState switchToNextState(Command command) {
            if (CREATE_REAL_DUCK.equals(command) || CREATE_TOY_DUCK.equals(command)) {
                return DUCK_CHOSEN_STATE;
            } else {
                return MAIN_MENU_STATE;
            }
        }
    },

    DUCK_CHOSEN_STATE {
        @Override
        public ApplicationState switchToNextState(Command command) {
            if (BACK.equals(command)) {
                return MAIN_MENU_STATE;
            } else {
                return DUCK_CHOSEN_STATE;
            }
        }
    };

    public abstract ApplicationState switchToNextState(Command command);

}
