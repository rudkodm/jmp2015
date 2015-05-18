package by.rudko.classloading;

import by.rudko.classloading.processing.CommandProcessor;
import by.rudko.classloading.processing.CustomCommandProcessor;
import by.rudko.classloading.processing.DefaultCommandProcessor;

import java.util.NoSuchElementException;

enum Command {
    DEFAULT("default", new DefaultCommandProcessor()),
    CUSTOM("custom", new CustomCommandProcessor());

    private final String value;

    private final CommandProcessor commandHandler;

    private Command(String value, CommandProcessor commandHandler) {
        this.value = value;
        this.commandHandler = commandHandler;
    }

    static Command toEnum(String value) {
        for (Command command : values()) {
            if (command.value.equals(value)) {
                return command;
            }
        }
        throw new NoSuchElementException("No such element: " + value);
    }

    public CommandProcessor getCommandHandler() {
        return commandHandler;
    }
}
