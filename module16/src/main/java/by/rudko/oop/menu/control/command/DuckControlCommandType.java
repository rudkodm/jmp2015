package by.rudko.oop.menu.control.command;

import by.rudko.oop.model.ApplicationContext;
import by.rudko.oop.model.movements.WalkDirection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
* Created by rudkodm on 9/7/15.
*/
public enum DuckControlCommandType implements Command<ApplicationContext> {

    UP("w") {
        @Override
        public void apply(ApplicationContext commandContext) {
            commandContext.getDuck().walk(WalkDirection.UP);
        }
    },

    DOWN("s") {
        @Override
        public void apply(ApplicationContext commandContext) {
            commandContext.getDuck().walk(WalkDirection.DOWN);
        }
    },

    LEFT("a") {
        @Override
        public void apply(ApplicationContext commandContext) {
            commandContext.getDuck().walk(WalkDirection.LEFT);
        }
    },

    RIGHT("d") {
        @Override
        public void apply(ApplicationContext commandContext) {
            commandContext.getDuck().walk(WalkDirection.RIGHT);
        }
    },

    BACK("q") {
        @Override
        public void apply(ApplicationContext commandContext) {
            //NON
        }
    },

    NULL(null) {
        @Override
        public void apply(ApplicationContext commandContext) {
            //NON
        }
    };


    private static final Logger LOG = LogManager.getLogger(DuckControlCommandType.class);

    private String value;
    DuckControlCommandType(String value) {
        this.value = value;
    }

    public static DuckControlCommandType toEnum(String str) {
        for (DuckControlCommandType element : DuckControlCommandType.values()) {
            if (NULL != element && element.value.equals(str)) {
                return element;
            }
        }
        LOG.warn("No such DuckControlCommandType type with value: {}", str);
        return NULL;
    }
}
