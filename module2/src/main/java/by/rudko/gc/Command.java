package by.rudko.gc;

public enum Command {
    TEST_YONG_GEN("young"),
    TEST_OLD_GET("old");

    private final String value;

    Command(String value) {
        this.value = value;
    }

    static Command toEnum(String value) {
        for (Command command : values()) {
            if (command.value.equals(value)) {
                return command;
            }
        }
        return null;
    }
}
