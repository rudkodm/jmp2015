package by.rudko.gc;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static java.lang.System.*;

public class Application {
    private static final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        final String key = "test";
        final String value = System.getProperty(key);

        switch (Command.toEnum(value)) {
            case TEST_YONG_GEN: {
                testYong();
                break;
            }
            case TEST_OLD_GET: {
//                testOld();
                break;
            }
            default: {
                err.printf("There is no such property %s : %s", key, value);
            }
        }


    }

    private static void testYong() {
        while (true) {
            out.println(new Bean().getId());
        }
    }

    private static void testOld() {
        while (true) {
            out.println(new Bean().getId());
        }
    }
}


class Bean {
    private static int counter = 0;

    @SuppressWarnings("unused")
    private int[] size = new int[1000];

    private int id = 0;

    public int getId() {
        return id;
    }

    public Bean() {
        this.id = counter++;
    }

}

enum Command {
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