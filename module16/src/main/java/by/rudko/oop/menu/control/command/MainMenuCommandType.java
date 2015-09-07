package by.rudko.oop.menu.control.command;

import by.rudko.oop.model.ApplicationContext;
import by.rudko.oop.model.duck.DuckyDuck;
import by.rudko.oop.model.duck.ToyDuck;
import by.rudko.oop.utils.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
* Created by rudkodm on 9/7/15.
*/
public enum MainMenuCommandType implements Command<ApplicationContext> {

    CREATE_TOY_DUCK("1") {
        @Override
        public void apply(ApplicationContext commandContext) {
            commandContext.setDuck(new ToyDuck(Constants.DEFAULT_DUCK_ENERGY_CAPACITY));
            LOG.info("New ToyDuck created");
        }
    },

    CREATE_REAL_DUCK("2") {
        @Override
        public void apply(ApplicationContext commandContext) {
            commandContext.setDuck(new DuckyDuck(Constants.DEFAULT_DUCK_ENERGY_CAPACITY));
            LOG.info("New DuckyDuck created");
        }
    },

    EXIT("q") {
        @Override
        public void apply(ApplicationContext commandContext) {
            System.exit(Constants.EXIT_CODE);
        }
    },

    NULL(null) {
        @Override
        public void apply(ApplicationContext commandContext) {
           // NON
        }
    };

    private static final Logger LOG = LogManager.getLogger(MainMenuCommandType.class);

    private String value;
    MainMenuCommandType(String value) {
        this.value = value;
    }

    public static MainMenuCommandType toEnum(String str) {
        for (MainMenuCommandType element : MainMenuCommandType.values()) {
            if (NULL != element && element.value.equals(str)) {
                return element;
            }
        }
        LOG.warn("No such MainMenuCommandType type with value: {}", str);
        return NULL;
    }
}
